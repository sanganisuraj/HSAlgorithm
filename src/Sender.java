/*
@ Authors
1. Suraj Sangani  (sns140230@utdallas.edu)
2. Raghul Gandhi  (rxg150230@utdallas.edu)
3. Abinav Sridhar (axs143632@utdallas.edu)
*/

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

public class Sender implements Runnable {

    private final BlockingQueue<Message> q;
    private final BlockingQueue<Long> pq;
    private BlockingQueue<Long> lq;

    public Sender(BlockingQueue<Message> queue, BlockingQueue<Long> pq,
            BlockingQueue<Long> lq) {
        q = queue;
        this.pq = pq;
        this.lq = lq;
    }

    @Override
    public void run() {
        synchronized (q) {
            try {
                do {
                    while (!pq.isEmpty()) {
                        Thread c1 = new Thread(new ClockwiseReceiver(q, pq));
                        Thread cc1 = new Thread(new CounterClockwiseReceiver(q, pq));
                        c1.start();
                        cc1.start();

                        Message cm = new Message(pq.peek(), (int) Math.pow(2, Master.round));
                        int receiver = (Master.ids.indexOf(pq.peek()) + 1) % Master.slaveCount;
                        cm.setMessageReceiver(Master.ids.get(receiver));
                        cm.setDirection(Direction.Clockwise);
                        cm.setFlag(Flag.out);

                        Message ccm = new Message(pq.peek(), (int) Math.pow(2, Master.round));
                        int receiverCc = Master.ids.indexOf(pq.peek()) - 1 < 0 ? Master.slaveCount - 1 : Master.ids.indexOf(pq.peek()) - 1;
                        ccm.setMessageReceiver(Master.ids.get(receiverCc));
                        ccm.setDirection(Direction.Counterclockwise);
                        ccm.setFlag(Flag.out);

                        q.put(cm);
                        q.put(ccm);

                        pq.take();

                        q.wait();
                    }
                    Master.round++;
                    pq.addAll(Master.ids);
                    lq.clear();
                    Iterator<Message> i = q.iterator();
                    while (i.hasNext()) {
                        Message k = i.next();
                        if (k.getFlag().equals(Flag.in)) {
                            long sender = k.getMessageSender();
                            if (!lq.contains(sender)) {
                                lq.add(sender);
                            }
                        }
                    }
                    System.out.println("\nRounds ==>"+Master.round+"\tProcess(es) in contention==> : "+lq.toString());
                    q.clear();
                    if (lq.size() == 1) {
                        System.out.println("---------------------------------");
                        System.out.println("Leader is " + lq.toString());
                        break;
                    }
                } while (lq.size() != 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

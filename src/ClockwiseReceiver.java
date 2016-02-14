/*
@ Authors
1. Suraj Sangani  (sns140230@utdallas.edu)
2. Raghul Gandhi  (rxg150230@utdallas.edu)
3. Abinav Sridhar (axs143632@utdallas.edu)
*/
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

public class ClockwiseReceiver implements Runnable {

    private final BlockingQueue<Message> q;
    private final BlockingQueue<Long> pq;

    public ClockwiseReceiver(BlockingQueue<Message> q, BlockingQueue<Long> pq) {
        this.q = q;
        this.pq = pq;
    }

    @Override
    public void run() {
        
        synchronized (q) {
            Iterator<Message> i = q.iterator();
            Message m = new Message();
            while (i.hasNext()) {
                m = i.next();
                if (m.getDirection() == Direction.Clockwise && m.getFlag().equals(Flag.out)) {
                    q.remove(m);
                    break;
                } else {
                    m = new Message();
                }
            }
            int hops = m.getHops();
            while (hops > 0) {
                if (m.getMessageReceiver() < m.getMessageSender() && hops > 1 && m.getFlag() == Flag.out) {
                    q.remove(m);
                    int messageReceiverIndex = (Master.ids.indexOf(m.getMessageReceiver()) + 1) % Master.slaveCount;
                    m.setMessageReceiver(Master.ids.get(messageReceiverIndex));
                    hops--;
                    q.add(m);
                } else if (m.getMessageReceiver() < m.getMessageSender() && hops == 1) {
                    m.setFlag(Flag.in);
                    m.setMessageReceiver(m.getMessageSender());
                    q.add(m);
                    break;
                } else {
                    q.notifyAll();
                    break;
                }
            }
            q.notifyAll();
        }
    }
}

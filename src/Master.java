/*
@ Authors
1. Suraj Sangani  (sns140230@utdallas.edu)
2. Raghul Gandhi  (rxg150230@utdallas.edu)
3. Abinav Sridhar (axs143632@utdallas.edu)
*/
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Master implements Runnable {

    public static int slaveCount;
    public static ArrayList<Long> ids;
    public static int round = 0;
    public static ArrayList<Long> maxIdsSeenSoFar = new ArrayList<Long>();
    private final BlockingQueue<Message> queue;
    private final BlockingQueue<Long> processQueue;
    private final BlockingQueue<Long> leaderQueue;

    public Master(int count, ArrayList<Long> givenIds) {
        slaveCount = count;
        ids = givenIds;
        maxIdsSeenSoFar.addAll(ids);
        queue = new LinkedBlockingQueue<Message>();
        processQueue = new LinkedBlockingQueue<Long>();
        leaderQueue = new LinkedBlockingQueue<Long>();
    }

    public void run() {
        try {
            Iterator<Long> i = ids.iterator();
            while (i.hasNext()) {
                processQueue.add(i.next());
            }
            Thread sender = new Thread(new Sender(queue, processQueue, leaderQueue));
            sender.start();
            sender.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

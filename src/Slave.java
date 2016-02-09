import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;


class Slave extends Thread {

	private Resource sharedResource;
	private long id;
	private int slaveIndex;
	private final BlockingQueue<Message> clockwiseQueue;
	private final BlockingQueue<Message> counterclockwiseQueue;

	public Slave(Resource rcs, long givenId, int slaveIndex) {
		sharedResource = rcs;
		id = givenId;
		this.slaveIndex = slaveIndex;
		clockwiseQueue = new LinkedBlockingQueue<Message>();
		counterclockwiseQueue = new LinkedBlockingQueue<Message>();
	}

    public synchronized void run() {
    	System.out.println("I am here with" + Slave.currentThread().getName());
    	Message clockWiseOutMessage = new Message(this.slaveIndex, (int) Math.pow(2, Master.round));
    	clockWiseOutMessage.setFlag(Flag.out);
    	clockWiseOutMessage.setDirection(Direction.Clockwise);
    	clockWiseOutMessage.setMessageReceiver((this.slaveIndex + 1)%Master.slaveCount);
    	try {
			clockwiseQueue.put(clockWiseOutMessage);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	Message counterClockWiseOutMessage = new Message(this.slaveIndex, (int) Math.pow(2, Master.round));
    	counterClockWiseOutMessage.setFlag(Flag.out);
    	counterClockWiseOutMessage.setDirection(Direction.Counterclockwise);
    	counterClockWiseOutMessage.setMessageReceiver((this.slaveIndex - 1)<0 ? Master.slaveCount-1 : this.slaveIndex - 1);
    	

    	try {
			counterclockwiseQueue.put(counterClockWiseOutMessage);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	//			this.wait();
					int hops = clockWiseOutMessage.getHops();
					while(hops>0) {
						Master.slaves[((int) clockWiseOutMessage.getMessageReceiver() + hops) % Master.slaveCount].start();
						Master.slaves[(int) counterClockWiseOutMessage.getMessageReceiver() - hops < 0 ? Master.slaveCount - (int) counterClockWiseOutMessage.getMessageReceiver() - hops - 1 : (int) counterClockWiseOutMessage.getMessageReceiver() - hops].start();
						hops--;
					}
    }
}
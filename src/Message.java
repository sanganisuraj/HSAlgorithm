
public class Message {

	private long messageSender;
	private long messageReceiver;
	private Flag flag;
	private int hops;
	private Direction direction;
	private int maxIdSeenSoFar;
	
	public int getMaxIdSeenSoFar() {
		return maxIdSeenSoFar;
	}

	public void setMaxIdSeenSoFar(int maxIdSeenSoFar) {
		this.maxIdSeenSoFar = maxIdSeenSoFar;
	}

	Message(long messageSender, int hops) {
		this.messageSender = messageSender;
		this.hops = hops;
	}
	
	public long getMessageSender() {
		return messageSender;
	}
	
	public long getMessageReceiver() {
		return messageReceiver;
	}
	
	public void setMessageReceiver(long messageReceiver) {
		this.messageReceiver = messageReceiver;
	}
	@Override
	public String toString() {
		return "Message [messageSender=" + messageSender + ", messageReceiver="
				+ messageReceiver + ", flag=" + flag + ", hops=" + hops
				+ ", direction=" + direction + "]";
	}

	public Flag getFlag() {
		return flag;
	}
	public void setFlag(Flag flag) {
		this.flag = flag;
	}
	public int getHops() {
		return hops;
	}
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}

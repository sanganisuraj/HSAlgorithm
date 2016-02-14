/*
@ Authors
1. Suraj Sangani  (sns140230@utdallas.edu)
2. Raghul Gandhi  (rxg150230@utdallas.edu)
3. Abinav Sridhar (axs143632@utdallas.edu)
*/
public class Message {

	private long messageSender;
	private long messageReceiver;
	private Flag flag;
	private int hops;
	public void setHops(int hops) {
		this.hops = hops;
	}
	private Direction direction;

	Message() {
		this.hops = (int) Math.pow(2,Master.round); 
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

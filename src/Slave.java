
class Slave extends Thread {

	private Resource sharedResource;
	private long id;
	private int slaveIndex;

	public Slave(Resource rcs, long givenId, int slaveIndex) {
		sharedResource = rcs;
		id = givenId;
		this.slaveIndex = slaveIndex;
	}

    public void run() {
    	if(Master.maxIdsSeenSoFar.contains(id))
    		sharedResource.sendMessage(this.slaveIndex, Flag.out, Master.round);
    	
    }
}
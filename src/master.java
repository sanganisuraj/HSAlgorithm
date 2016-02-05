public class Master {
	public Master(long count, ArrayList<long> givenIds) {
		private long slaveCount = count;
		private Resource res = new Resource();
		private Slave[] slaves = new Slave[slaveCount];
		private ArrayList ids = givenIds;
	}
    public void run() {
   	  // create slaves:
      for(int i = 0; i < slaveCount; i++) {
         slaves[i] = new Slave(res, ids[i]);
      }
      // start slaves:
      for(int i = 0; i < slaveCount; i++) {
         slaves[i].start();
      }
      // wait for slaves to die:
      for(int i = 0; i < slaveCount; i++) {
         try {
            slaves[i].join();
         } catch(InterruptedException ie) {
               System.err.println(ie.getMessage());
         } finally {
            System.out.println(slaves[i].getName() + " has died");
         }
      }
      System.out.println("The master will now die ... ");
   }
}
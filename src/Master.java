import java.util.ArrayList;

public class Master {
	private int slaveCount;
	private Slave[] slaves;
	private Resource res;
	private ArrayList<Long> ids;
	
	public Master(int count, ArrayList<Long> givenIds) {
		slaveCount = count;
		res = new Resource();
		slaves = new Slave[slaveCount];
		ids = givenIds;
	}
    
	public void run() {
   	  // create slaves:
      for(int i = 0; i < slaveCount; i++) {
         slaves[i] = new Slave(res, ids.get(i));
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
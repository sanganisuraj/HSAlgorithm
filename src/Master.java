import java.util.ArrayList;

public class Master extends Thread {

	public static int slaveCount;
	public static Slave[] slaves;
	private Resource res;
	private ArrayList<Long> ids;
	public static int round = 0;
	public static ArrayList<Long> maxIdsSeenSoFar = new ArrayList<Long>();
	
	public Master(int count, ArrayList<Long> givenIds) {
		slaveCount = count;
		res = new Resource();
		slaves = new Slave[slaveCount];
		ids = givenIds;
		maxIdsSeenSoFar.addAll(ids);
	}
    
	public void run() {
   	  // create slaves:
      for(int i = 0; i < slaveCount; i++) {
         slaves[i] = new Slave(res, ids.get(i), i);
      }
      
      round++;
      
      // start slaves:
//      for(int i = 0; i < slaveCount; i++) {
//    	  System.out.println("Starting" + slaves[i].getName());
         slaves[0].start();
         
//      }
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
/*
@ Authors
1. Suraj Sangani  (sns140230@utdallas.edu)
2. Raghul Gandhi  (rxg150230@utdallas.edu)
3. Abinav Sridhar (axs143632@utdallas.edu)
*/
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class MainClass {

	public static void main(String args[]) throws FileNotFoundException {
		ReadingInput readingInput = new ReadingInput("input.txt");
		int N = readingInput.getN();
		ArrayList<Long> processIds = readingInput.getProcessIds();
		Thread master = new Thread(new Master(N, processIds));
		master.start();
	}
}

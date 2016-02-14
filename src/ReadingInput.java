/*
@ Authors
1. Suraj Sangani  (sns140230@utdallas.edu)
2. Raghul Gandhi  (rxg150230@utdallas.edu)
3. Abinav Sridhar (axs143632@utdallas.edu)
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class ReadingInput {

	private int N;
	private ArrayList<Long> processIds;
	
	public ReadingInput(String fileToRead) throws FileNotFoundException {
		N = 0;
		processIds = new ArrayList<Long>();
		Scanner sc;
		File file = new File(fileToRead);
		sc = new Scanner(file);
		N = Integer.parseInt(sc.nextLine());
        
        while (sc.hasNextLine()) {
            StringTokenizer ids = new StringTokenizer(sc.nextLine());
            while(ids.hasMoreTokens()) {
            	processIds.add(Long.parseLong(ids.nextToken()));
            }
        }
        sc.close();
	}

	public int getN() {
		return N;
	}
	public ArrayList<Long> getProcessIds() {
		return processIds;
	}
}

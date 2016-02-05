import java.io.FileNotFoundException;
import java.util.ArrayList;


public class MainClass {

	public static void main(String args[]) throws FileNotFoundException {
		ReadingInput readingInput = new ReadingInput("input.txt");
		int N = readingInput.getN();
		ArrayList<Long> processIds = readingInput.getProcessIds();
		Master master = new Master(N, processIds);
		master.start();
	}
}

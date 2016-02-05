import java.io.FileNotFoundException;


public class MainClass {

	public static void main(String args[]) throws FileNotFoundException {
		ReadingInput readingInput = new ReadingInput("input.txt");
		System.out.println(readingInput.getN());
		System.out.println(readingInput.getProcessIds().get(5));
	}
}

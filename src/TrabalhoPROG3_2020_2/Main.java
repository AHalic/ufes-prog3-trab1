package TrabalhoPROG3_2020_2;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.nio.charset.StandardCharsets;

public class Main {
	public static void main(String[] args) {

		try {
		      File myObj = new File("filename.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		  
		  try (FileInputStream fis = new FileInputStream(file);
		       InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
		       BufferedReader reader = new BufferedReader(isr)
		  ) {

		      String str;
		      while ((str = reader.readLine()) != null) {
		          System.out.println(str);
		      }

		  } catch (IOException e) {
		      e.printStackTrace();
		  }
	}
}

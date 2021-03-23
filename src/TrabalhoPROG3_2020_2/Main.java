package TrabalhoPROG3_2020_2;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		
		throw IOException {
			InputStream is = new FileInputStream(args[1]);
			int b = is.read();
			System.out.println(b);
			is.close();
		} 

		// try {
		//       File myObj = new File("filename.txt");
		//       Scanner myReader = new Scanner(myObj);
		//       while (myReader.hasNextLine()) {
		//         String data = myReader.nextLine();
		//         System.out.println(data);
		//       }
		//       myReader.close();
		//     } catch (FileNotFoundException e) {
		//       System.out.println("An error occurred.");
		//       e.printStackTrace();
		//     }
		  
		//   try (FileInputStream fis = new FileInputStream(file);
		//        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
		//        BufferedReader reader = new BufferedReader(isr)
		//   ) {

		//       String str;
		//       while ((str = reader.readLine()) != null) {
		//           System.out.println(str);
		//       }

		//   } catch (IOException e) {
		//       e.printStackTrace();
		//   }
	};
}

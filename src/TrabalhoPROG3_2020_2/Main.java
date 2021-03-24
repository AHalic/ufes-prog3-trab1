package TrabalhoPROG3_2020_2;

public class Main {
	public static void main(String[] args) {
		System.out.println("oi");

		String caminhoCandidatos = new String();
		caminhoCandidatos = args[0];
		String caminhoPartidos = new String();

		caminhoPartidos = args[1];
		String dataEleicaoStr = args[2];
	
		// DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd-mm-yyyy");
        // LocalDate dataEleicao =  LocalDate.parse(dataEleicaoStr, formatoData);
		System.out.println(caminhoCandidatos);

//		 System.out.println(caminhoCandidatos + "\n" + caminhoPartidos + "\n" + dataEleicaoStr + "\n" + dataEleicao);
		
		// throw IOException {
		// 	InputStream is = new FileInputStream(caminhoCandidatos);
		// 	int b = is.read();
		// 	System.out.println(b);
		// 	is.close();
		// };

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
	}
}

package utility;

import java.awt.List;
import java.io.*;

public class ReadingFromTextfil {

	public List readFromFile(String filePath) {

		List file = new List();
		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(filePath);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				file.add(line);
				// System.out.println(line);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + filePath + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + filePath + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		return file;
	}

	public static void main(String[] args) {
		ReadingFromTextfil readFile = new ReadingFromTextfil();
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\textFile.txt";

		String[] fromFile = readFile.readFromFile(filePath).getItems();

		for (int i = 0; i < fromFile.length; i++) {
			System.out.println(fromFile[i]);

		}
	}
}

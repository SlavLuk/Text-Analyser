package ie.gmit.java2;

import java.io.*;

import java.util.*;

public class FileProcessor extends TextParser {

	// declare instance variables
	private BufferedReader br = null;
	private List<String> list = getList();
	private File file = getFile();

	// constructor with file
	public FileProcessor(File f) {
		super(f);

	}

	// default constructor
	public FileProcessor() {

	}

	// implement this method from interface
	public void readFile() throws FileNotFoundException, IOException {

		// process file

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

			StringBuilder sb = new StringBuilder();
			String s;

			while ((s = br.readLine()) != null) {

				sb.append(s);
				sb.append("\n");

			}

			String[] strArr = sb.toString().trim().split("[\\s\\p{Punct}]+");

			for (int i = 0; i < strArr.length; i++) {

				list.add(strArr[i].trim());

				System.out.println(list.get(i));

			}

		} catch (FileNotFoundException e) {
			// re-throw exception
			throw new FileNotFoundException();

		} catch (IOException ex) {
			// re-throw exception
			throw new IOException();

			// always runs
		} finally {

			// close stream
			if (br != null) {

				br.close();

			}

		}

	}

}

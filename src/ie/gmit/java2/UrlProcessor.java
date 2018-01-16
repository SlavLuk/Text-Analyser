package ie.gmit.java2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class UrlProcessor extends TextParser {

	// declare instance variables
	private BufferedReader br = null;
	private URL textUrl = null;
	private List<String> list = getList();
	private String url = getUrl();

	// constructor with string
	public UrlProcessor(String u) {
		super(u);

	}

	public UrlProcessor() {

	}

	// implement this method from interface
	public void readFile() throws IOException {

		try {
			textUrl = new URL(url);

			br = new BufferedReader(new InputStreamReader(textUrl.openStream(), "UTF-8"));

			StringBuilder sb = new StringBuilder();
			String s;

			while ((s = br.readLine()) != null) {

				sb.append(s);
				sb.append("\n");

			}

			String[] strArr = sb.toString().split("[\\s\\p{Punct}]+");

			for (int i = 0; i < strArr.length; i++) {

				list.add(strArr[i].trim());
				System.out.println(list.get(i));

			}

		} catch (IOException ex) {

			throw new IOException();

			// always runs
		} finally {

			// close the stream
			if (br != null) {

				br.close();

			}

		}

	}

}

package ie.gmit.java2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//abstract class can not be instantiated
public abstract class TextParser implements Parseable {

	// declare instance variables
	private File file;
	private String url;
	private List<String> list = new ArrayList<>();

	public TextParser() {

	}

	public TextParser(File f) {

		setFile(f);
	}

	public TextParser(String s) {

		setUrl(s);

	}

	// abstract method must be implemented by concrete class
	public abstract void readFile() throws FileNotFoundException, IOException;

	// returns true if s exists in the array.
	boolean contains(String s) {

		return list.contains(s);
	}

	// returns the total number of elements in the array.
	int count() {

		return list.size();
	}

	// returns the number of occurrences of s in the array.
	int countOccurrences(String str) {

		int counter = 0;

		for (String s : list) {

			if (s.equals(str)) {

				counter++;

			}

		}

		return counter;
	}

	// returns the index of the 1st occurrence of s in the array.
	int getFirstIndex(String s) {

		return list.indexOf(s);
	}

	// returns the index of the last occurrence of s in the array.
	int getLastIndex(String s) {

		return list.lastIndexOf(s);
	}

	// return an array of the indices of all occurrences of s in the array.
	private int[] getAllIndices(String s) {

		List<Integer> index = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).equals(s)) {

				index.add(i);

			}

		}

		int[] indeces = new int[index.size()];

		for (int i = 0; i < indeces.length; i++) {

			indeces[i] = index.get(i);
		}

		return indeces;
	}

	// return a String of the indices of all occurrences of s in the array.
	public String getAllIndicesToString(String str) {

		int[] indx = getAllIndices(str);

		StringBuilder s = new StringBuilder();

		if (indx.length == 0) {

			s.append(0);
		}

		for (int i = 0; i < indx.length; i++) {

			s.append(indx[i] + " ");

		}

		return s.toString();

	}

	// deletes all occurrences of s from the array.
	void delete(String s) {

		while (list.remove(s)) {

		}

	}

	// deletes the string at a given index in the array.
	void delete(int index) {

		list.remove(index);

	}

	// getter setter methods
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

package ie.gmit.java2;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Parseable {

	//must be implemented by concrete class
	void readFile() throws FileNotFoundException, IOException;

}

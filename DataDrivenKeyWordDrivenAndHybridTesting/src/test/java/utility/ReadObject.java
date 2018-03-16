package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObject {
	static Properties p = new Properties();

	public static Properties getObjectRepository(String objectFilePath) throws IOException {
		// Read object repository file
		InputStream stream = new FileInputStream(new File(objectFilePath));
		// load all objects
		p.load(stream);

		return p;
	}

}

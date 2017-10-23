package DAL.yaml;

//import org.yaml.snakeyaml.Yaml;

import java.io.File;

// Map<Integer, Map<String, Object>>
// datatype for item

public class YamlParser {
	private File file;
	//private Yaml yaml;

	public YamlParser(File file) {
		this.file = file;
		//yaml = new Yaml();
	}
}

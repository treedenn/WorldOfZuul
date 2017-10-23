package DAL.yaml;

//import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public abstract class YamlParser {
	private File file;
	//private Yaml yaml;

	public YamlParser(File file) {
		this.file = file;
		//yaml = new Yaml();
	}

	public File getFile() {
		return file;
	}

	public Yaml getYaml() {
		return yaml;
	}
}

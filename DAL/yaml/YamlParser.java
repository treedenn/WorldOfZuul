package DAL.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.File;

public class YamlParser {
	private File file;
	private Yaml yaml;

	public YamlParser(File file) {
		this.file = file;
		yaml = new Yaml();
	}

	public File getFile() {
		return file;
	}

	public Yaml getYaml() {
		return yaml;
	}
}


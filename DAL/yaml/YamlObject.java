package DAL.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.File;

public class YamlObject {
	private File file;
	private Yaml yaml;

	public YamlObject(File file) {
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
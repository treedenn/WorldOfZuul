package DAL.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.File;

/**
 * YamlObject is connected to {@link Yaml} and a file.
 * Yaml is used to save/load from a yaml file.
 */
public class YamlObject {
	private File file;
	private Yaml yaml;

	public YamlObject(File file) {
		this.file = file;
		yaml = new Yaml();
	}

	/**
	 * Gets the File.
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Gets the Yaml.
	 * @return the yaml
	 */
	public Yaml getYaml() {
		return yaml;
	}
}
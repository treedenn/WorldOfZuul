package DAL;

import DAL.ACQ.Loadable;
import DAL.yaml.YamlObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Handles the messages/localization. It loads the localization.
 */
public class MessageHandler implements Loadable {
	private YamlObject yamlObject;
	private Map<String, String> messages;

	/**
	 * Constructs a new Message handler.
	 * @param file the location of the localization
	 */
	MessageHandler(File file) {
		this.yamlObject = new YamlObject(file);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void load() throws IOException {
		messages = yamlObject.getYaml().load(new FileReader(yamlObject.getFile()));
	}

	/**
	 * Gets the message from a key.
	 * @param key
	 * @return null, if key does not match, else the message
	 */
	String getMessage(String key) {
		if(messages.containsKey(key)) {
			return messages.get(key);
		}

		return null;
	}
}

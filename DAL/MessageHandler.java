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
	private Map<String, String> messages;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void load() throws IOException {
		YamlObject parser = new YamlObject(new File("./src/DAL/resource/localization.yaml"));
		messages = parser.getYaml().load(new FileReader(parser.getFile()));
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

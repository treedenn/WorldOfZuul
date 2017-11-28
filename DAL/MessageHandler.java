package DAL;

import DAL.ACQ.Loadable;
import DAL.yaml.YamlObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class MessageHandler implements Loadable {
	private Map<String, String> messages;

	@Override
	public void load() throws IOException {
		YamlObject parser = new YamlObject(new File("./src/DAL/resource/localization.yaml"));
		messages = parser.getYaml().load(new FileReader(parser.getFile()));
	}

	String getMessage(String key) {
		if(messages.containsKey(key)) {
			return messages.get(key);
		}

		return null;
	}
}

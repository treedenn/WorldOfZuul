package DAL.yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class ItemParser extends YamlParser {
	private static ItemParser ourInstance = new ItemParser();

	public static ItemParser getInstance() {
		return ourInstance;
	}

	public ItemParser() {
		super(new File("./src/DAL/resource/itemdatabase.yaml"));
	}

	public Map<Integer, Map<String, Object>> getDatabase() throws IOException {
		FileReader reader = new FileReader(getFile());
		Map<Integer, Map<String, Object>> map = getYaml().load(reader);
		reader.close();

		return map;
	}
}

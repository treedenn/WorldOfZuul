package DAL;

import BLL.item.Color;
import BLL.item.Item;
import BLL.item.ItemType;
import BLL.item.State;
import DAL.ACQ.Loadable;
import DAL.yaml.YamlObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class DatabaseHandler implements Loadable {
	private List<Item> database;

	DatabaseHandler() {
		database = null;
	}

	Item getItemById(int index) {
		return database.get(index);
	}

	@Override
	public void load() throws IOException {
		YamlObject parser = new YamlObject(new File("./src/DAL/resource/itemdatabase.yaml"));

		Map<Integer, Map<String, Object>> map = parser.getYaml().load(new FileReader(parser.getFile()));

		if(!map.isEmpty()) {
			database = new ArrayList<>(map.size());

			String name;
			String description;
			ItemType type;
			Color color;
			State state;
			boolean pickupable;
			boolean dropable;
			double weight;

			for (Map<String, Object> o : map.values()) {
				name = (String) o.get("name");
				color = Color.valueOf((String) o.get("color"));
				state = State.valueOf((String) o.get("state"));
				description = (String) o.get("description");
				type = ItemType.valueOf((String) o.get("itemType"));
				pickupable = (boolean) o.get("pickupable");
				dropable = (boolean) o.get("dropable");
				weight = (double) o.get("weight");

				database.add(new Item(name, description, type, color, state, weight, pickupable, dropable));
			}
		}
	}
}

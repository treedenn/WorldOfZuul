package DAL;

import BLL.UsableHandler;
import BLL.item.*;
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
	private UsableHandler usableHandler;

	DatabaseHandler() {
		database = null;
	}

	public void setUsableHandler(UsableHandler usableHandler) {
		this.usableHandler = usableHandler;
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

			Item item;

			for (Map<String, Object> o : map.values()) {
				switch(ItemType.valueOf((String) o.get("itemType"))) {
					case DEFAULT: item = getItem(o); break;
					case COMPONENT: item = getComponent(o); break;
					default: item = getItem(o);
				}

				if(o.containsKey("usableId")) {
					item.setUsable(usableHandler.getUsable((Integer) o.get("usableId")));
				}

				database.add(item);
			}
		}
	}

	private Item getItem(Map<String, Object> o) {
		return new Item((String) o.get("name"),
				(String) o.get("description"),
				(double) o.get("weight"),
				(boolean) o.get("pickupable"),
				(boolean) o.get("dropable")
		);
	}

	private Item getComponent(Map<String, Object> o) {
		return new ItemComponent((String) o.get("name"),
				(String) o.get("description"),
				(double) o.get("weight"),
				ComponentType.valueOf((String) o.get("componentType")),
				Color.valueOf((String) o.get("color")),
				State.valueOf((String) o.get("state"))
		);
	}
}

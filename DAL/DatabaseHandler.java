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

	/**
	 * Sets the usable handler given by business layer.
	 * @param usableHandler
	 */
	public void setUsableHandler(UsableHandler usableHandler) {
		this.usableHandler = usableHandler;
	}

	/**
	 * Gets the item at given index from the database.
	 * @param index
	 * @return item
	 */
	Item getItemById(int index) {
		return database.get(index);
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * Gets an item based on the Yaml given.
	 * Only invokes by {@link DatabaseHandler}.
	 * @param o the map from Yaml
	 * @return an item
	 */
	private Item getItem(Map<String, Object> o) {
		return new Item((String) o.get("name"),
				(String) o.get("description"),
				(double) o.get("weight"),
				(boolean) o.get("pickupable"),
				(boolean) o.get("dropable")
		);
	}

	/**
	 * Gets an component based on the Yaml given.
	 * Only invokes by {@link DatabaseHandler}.
	 * @param o the map from Yaml
	 * @return a component
	 */
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
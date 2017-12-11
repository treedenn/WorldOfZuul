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
	private YamlObject yamlObject;
	private List<Item> database;
	private UsableHandler usableHandler;

	DatabaseHandler(File file) {
		this.yamlObject = new YamlObject(file);
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
		Map<Integer, Map<String, Object>> map = yamlObject.getYaml().load(new FileReader(yamlObject.getFile()));

		if(!map.isEmpty()) {
			database = new ArrayList<>(map.size());

			Item item;
			int id;

			for(Map.Entry<Integer, Map<String, Object>> entry : map.entrySet()) {
				id = entry.getKey();

				switch(ItemType.valueOf((String) entry.getValue().get("itemType"))) {
					case DEFAULT: item = getItem(id, entry.getValue()); break;
					case COMPONENT: item = getComponent(id, entry.getValue()); break;
					case CLUE: item = getClue(id, entry.getValue()); break;
					default: item = getItem(id, entry.getValue());
				}

				if(entry.getValue().containsKey("usableId")) {
					item.setUsable(usableHandler.getUsable((Integer) entry.getValue().get("usableId")));
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
	private Item getItem(int id, Map<String, Object> o) {
		return new Item(id,
				(String) o.get("name"),
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
	private Item getComponent(int id, Map<String, Object> o) {
		return new ItemComponent(id, (String) o.get("name"),
				(String) o.get("description"),
				(double) o.get("weight"),
				(boolean) o.get("pickupable"),
				(boolean) o.get("dropable"),
				ComponentType.valueOf((String) o.get("componentType")),
				Color.valueOf((String) o.get("color")),
				State.valueOf((String) o.get("state"))
		);
	}

	/**
	 * Gets a clue based on the Yaml given.
	 * Only invokes by {@link DatabaseHandler}.
	 * @param o the map from Yaml
	 * @return a no-use clue
	 */
	private Item getClue(int id, Map<String, Object> o) {
		return new ItemClue(id, (String) o.get("name"),
				(String) o.get("description"),
				(double) o.get("weight"),
				(boolean) o.get("pickupable"),
				(boolean) o.get("dropable"),
				ComponentType.valueOf((String) o.get("componentType")),
				Color.valueOf((String) o.get("color")),
				State.valueOf((String) o.get("state"))
		);
	}
}
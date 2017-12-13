package DAL;

import BLL.Game;
import BLL.entity.Inventory;
import BLL.item.Item;
import BLL.item.ItemStack;
import BLL.item.ItemType;
import BLL.world.Planet;
import DAL.ACQ.Loadable;
import DAL.ACQ.Savable;
import DAL.data.PlanetData;
import DAL.data.PlayerData;
import DAL.data.WorldData;
import DAL.yaml.YamlObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the save/load mechanism the game can do.
 */
public class GameStateHandler implements Loadable, Savable {
	private Model model;

	private YamlObject yamlObject;

	private WorldData worldData;
	private PlayerData playerData;
	private PlanetData planetData;

	GameStateHandler(Model model, File file) {
		this.model = model;
		this.yamlObject = new YamlObject(file);

		worldData = new WorldData(model);
		playerData = new PlayerData(model);
		planetData = new PlanetData(model);
	}

	WorldData getWorldData() {
		return worldData;
	}

	PlayerData getPlayerData() {
		return playerData;
	}

	PlanetData getPlanetData() {
		return planetData;
	}

	@Override
	public void load() throws IOException {
		Map<String, Map<?, ?>> map = yamlObject.getYaml().load(new FileReader(yamlObject.getFile()));

		if(!map.isEmpty()) {
			Map<String, Object> playerMap = (Map<String, Object>) map.get("player");
			Map<String, Object> worldMap = (Map<String, Object>) map.get("world");
			Map<Integer, Object> planetsMap = (Map<Integer, Object>) map.get("planets");

			playerData.load(playerMap);
			worldData.load(worldMap);
			planetData.load(planetsMap);
		}
	}

	@Override
	public void save() throws IOException {
		Map<String, Map<?, ?>> map = new HashMap<>();

		map.put("player", playerData.save());
		map.put("world", worldData.save());
		map.put("planets", planetData.save());

		yamlObject.getYaml().dump(map, new FileWriter(yamlObject.getFile()));
	}

	public Map<Integer, Object> turnInventoryToMap(ItemStack[] itemStacks) {
		Map<Integer, Object> map = new HashMap<>();

		for(ItemStack itemStack : itemStacks) {
			map.put(itemStack.getItem().getId(), itemStack.getQuantity());
		}

		return map;
	}

	public ItemStack[] turnMapToInventory(Map<Integer, Object> map) {
		ItemStack[] inventory = new ItemStack[map.size()];

		// System.out.println(map.keySet());

		int i = 0;
		for(Map.Entry<Integer, Object> entry : map.entrySet()) {
			inventory[i] = new ItemStack(model.getItemById(entry.getKey()), (int) entry.getValue());
			i++;
		}

		return inventory;
	}
}

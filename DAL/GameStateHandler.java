package DAL;

import BLL.Game;
import BLL.entity.Inventory;
import BLL.item.ItemStack;
import BLL.world.Planet;
import DAL.ACQ.Loadable;
import DAL.ACQ.Savable;
import DAL.data.PlanetData;
import DAL.data.PlayerData;
import DAL.data.WorldData;
import DAL.yaml.YamlObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the save/load mechanism the game can do.
 */
public class GameStateHandler implements Loadable, Savable {
	private YamlObject yamlObject;

	private WorldData worldData;
	private PlayerData playerData;
	private PlanetData planetData;

	GameStateHandler(File file) {
		this.yamlObject = new YamlObject(file);

		worldData = new WorldData();
		playerData = new PlayerData();
		planetData = new PlanetData();
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

	}

	@Override
	public void save() throws IOException {
		Map<String, Map<?, ?>> map = new HashMap<>();

		map.put("player", playerData.getPlayerMap());
		map.put("world", worldData.getWorldMap());
		map.put("planets", planetData.getPlanetMap());

		yamlObject.getYaml().dump(map, new FileWriter(yamlObject.getFile()));
	}

	public static Map<Integer, Object> turnInventoryToMap(ItemStack[] itemStacks) {
		Map<Integer, Object> map = new HashMap<>();

		for(ItemStack itemStack : itemStacks) {
			//System.out.println(itemStack.getItem().getId());
			map.put(itemStack.getItem().getId(), itemStack.getQuantity());
		}

		return map;
	}
}

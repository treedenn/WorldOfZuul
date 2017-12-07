package DAL;

import BLL.Game;
import BLL.entity.Inventory;
import BLL.entity.player.Player;
import BLL.item.ItemPortalGun;
import BLL.item.ItemStack;
import BLL.world.Planet;
import DAL.ACQ.Loadable;
import DAL.ACQ.Savable;
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
	private Game gameInstance;

	GameStateHandler(File file) {
		this.yamlObject = new YamlObject(file);
	}

	public void setGameInstance(Game gameInstance) {
		this.gameInstance = gameInstance;
	}

	@Override
	public void load() throws IOException, NullPointerException {
//		if(gameInstance == null) {
//			throw new NullPointerException("The Game Instance is null. Game cannot be loaded.");
//		} else {
//
//		}
	}

	@Override
	public void save() throws IOException, NullPointerException {
		if(gameInstance == null) {
			throw new NullPointerException("The Game Instance is null. Game cannot be saved.");
		} else {
			Map<String, Map<String, ?>> map = new HashMap<>();

			Player player = (Player) gameInstance.getPlayer();

			Map<String, Object> worldMap = new HashMap<>();

			worldMap.put("time-elapsed", String.valueOf(gameInstance.getScoreHandler().calculateTimeElapsed()));

			for(ItemStack is : player.getInventory().getContent()) {
				if(is.getItem() instanceof ItemPortalGun) {
					ItemPortalGun pg = (ItemPortalGun) is.getItem();
					worldMap.put("portalgun-broken", pg.isBroken());

					break;
				}
			}

			Map<String, Object> playerMap = new HashMap<>();

			playerMap.put("current-planet", player.getCurrentPlanet().getName());
			playerMap.put("inventory", player.getIInventory());
			playerMap.put("buffs", player.getBuffs());
			playerMap.put("fuel", player.getFuel());
			playerMap.put("fuel-consumption", player.getTotalFuelConsumption());

			Map<String, Map<String, Object>> planetsMap = new HashMap<>();

			Map<String, Planet> planets = player.getPlanets();

			for(Map.Entry<String, Planet> entry : planets.entrySet()) {
				planetsMap.put(entry.getKey(), turnPlanetToMap(entry.getValue()));
			}

			map.put("player", worldMap);
			map.put("world", playerMap);
			map.put("planets", planetsMap);

			yamlObject.getYaml().dump(map, new FileWriter(yamlObject.getFile()));
		}
	}

	private Map<String, Object> turnPlanetToMap(Planet planet) {
		Map<String, Object> map = new HashMap<>();

		map.put("x-coordinate", planet.getCoordinates().getX());
		map.put("y-coordinate", planet.getCoordinates().getY());

		return map;
	}

	private Map<String, Object> turnInventoryToMap(Inventory inventory) {
		Map<String, Object> map = new HashMap<>();

		for(ItemStack itemStack : inventory.getContent()) {
			map.put(String.valueOf(itemStack.getItem().getId()), itemStack.getQuantity());
		}

		return map;
	}
}

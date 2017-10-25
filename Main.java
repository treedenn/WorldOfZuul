
import BLL.Game;
import DAL.item.Color;
import DAL.item.Item;
import DAL.item.ItemType;
<<<<<<< HEAD
=======
import DAL.item.State;
import DAL.yaml.ItemParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
>>>>>>> dc2e479b572423a433e77c62987b1174126f552a

public class Main {
	/* runs the game */
	public static void main(String[] args) {
		new Game().start();
<<<<<<< HEAD
=======

		/* Testing the item parser to obtain the item database from text
		ItemParser parser = ItemParser.getInstance();

		try {
			Map<Integer, Map<String, Object>> map = parser.getDatabase();
			System.out.println(map.get(0).get("name"));
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		*/




>>>>>>> dc2e479b572423a433e77c62987b1174126f552a
	}

	private static void itemTemplate(int id, String name, ItemType type, Color color, State state) {
		System.out.println(id + ":");
		System.out.println(String.format("  name: \"%s\"", name));
		System.out.println("  description: \"...\"");
		System.out.println("  itemType: " + type.ordinal());
		System.out.println("  color: " + color.toString());
		System.out.println("  state: " + state.name());
		System.out.println("  pickupable: true");
		System.out.println("  dropable: true");
		System.out.println("  weight: 1.0");
	}
}
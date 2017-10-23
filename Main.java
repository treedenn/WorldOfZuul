
import BLL.Game;
import DAL.item.Item;
import DAL.item.ItemType;
import DAL.item.State;
import DAL.yaml.ItemParser;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
	/* runs the game */
	public static void main(String[] args) {


		String[] liquidNames = new String[] {
				"Kerisa", "Molt", "Sedarus", "Gouliiq", "Cryptic", "Amanzi", "Zuur",
				"Eaux", "Hunajar", "Mielepe", "Fusilia Ferrox", "Latex", "Kalideca", "Ambrusia"
		};

		String[] canisterNames = new String[] {
				"Karedu", "Loqoo", "Daupyra", "Gonazi", "Pikarq", "Zajanj",
				"Jukohra", "Miplim", "Rexirn", "Tedox", "Akelos", "Doih"
		};

		String[] gearNames = new String[] {
				"Rekar", "Oltam", "Edauss", "Qiigol", "Rypicas", "Zinama", "Ruukaz",
				"Xaruja", "Unjaroh", "Lemipe", "Feroxis", "Texio", "Doim", "Aldakk"
		};

		String[] cpuNames = new String[] {
				"Tek XX", "Tek XXVI", "MOLT IV", "MOLT -V(D)",
				"CX Titanium 4", "CX Titanium 8", "FIX Ferocity 1", "FIX Ferocity 3",
				"i11 X2017", "i13 II5290", "i13 IV8525", "i15 7750",
				"CSP 6M2T", "CSP 10MT", "CSP MXV", "CSP M2X1V"
		};

		Item[] liquids = new Item[liquidNames.length];
		Item[] canisters = new Item[canisterNames.length];
		Item[] gears = new Item[gearNames.length];
		Item[] cpus = new Item[cpuNames.length];

		for(int i = 0; i < liquids.length; i++) {
			itemTemplate(i,liquidNames[i], ItemType.LIQUID, Color.BLACK, State.NORMAL);
		}

		for(int i = 0; i < canisters.length; i++) {
			itemTemplate(i + liquids.length,canisterNames[i], ItemType.CANISTER, Color.BLACK, State.NORMAL);
		}

		for(int i = 0; i < gears.length; i++) {
			itemTemplate(i + liquids.length + canisters.length,gearNames[i], ItemType.GEARS, Color.BLACK, State.NORMAL);
		}

		for(int i = 0; i < cpus.length; i++) {
			itemTemplate(i + liquids.length + canisters.length + gears.length,cpuNames[i], ItemType.CPU, Color.BLACK, State.NORMAL);
		}


		new Game().start();

		/* Testing the item parser to obtain the item database from text
		ItemParser parser = ItemParser.getInstance();

		try {
			Map<Integer, Map<String, Object>> map = parser.getDatabase();
			System.out.println(map.get(0).get("name"));
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		*/




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
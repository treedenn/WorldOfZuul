package DAL.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Item {
	private static List<Item> database;

	static {
		initalizeDatabase();
	}

	private String name;
	private String description;
	private ItemType itemType;
	private boolean pickupable;
	private boolean dropable;
	private double weight;

	public Item(Item item) {
		this(item.getName(), item.getDescription(), item.getItemType(), item.getWeight(), item.isPickupable(), item.isDropable());
	}

	public Item(String name, String description, ItemType itemType, double weight, boolean isPickupable, boolean isDropable) {
		this.name = name;
		this.description = description;
		this.itemType = itemType;
		this.pickupable = isPickupable;
		this.dropable = isDropable;
		this.weight = weight;
	}

	public Item(String name, String description, ItemType itemType, double weight) {
		this(name, description, itemType, weight, true, true);
	}

	public Item(String name, String description, ItemType itemType) {
		this(name, description, itemType, 1, true, true);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public boolean isPickupable() {
		return pickupable;
	}

	public boolean isDropable() {
		return dropable;
	}

	public double getWeight() {
		return weight;
	}

	@Override
	public boolean equals(Object object) {
		if(object instanceof Item) {
			Item item = (Item) object;

			return this.getName().equals(item.getName()) && this.getDescription().equals(item.getDescription()) && this.getItemType().equals(item.getItemType());
		}

		return false;
	}

	@Override
	public String toString() {
		return String.format("%s [%s]", getName(), getItemType().name());
	}

	// Database related

	public static Item getItemById(int id) {
		return new Item(database.get(id));
	}

	public static Item getItemById(Item.id itemId) {
		return getItemById(itemId.ordinal());
	}

	public static int getDatabaseSize() {
		return database.size();
	}

	private static void initalizeDatabase() {
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

		database = new ArrayList<>(liquids.length + gears.length + canisters.length + cpus.length);

		for(int i = 0; i < liquids.length; i++) {
			liquids[i] = new Item(liquidNames[i], "", ItemType.LIQUID);
		}

		for(int i = 0; i < canisters.length; i++) {
			canisters[i] = new Item(canisterNames[i], "", ItemType.CANISTER);
		}

		for(int i = 0; i < gears.length; i++) {
			gears[i] = new Item(gearNames[i], "", ItemType.GEARS);
		}

		for(int i = 0; i < cpus.length; i++) {
			cpus[i] = new Item(cpuNames[i], "", ItemType.CPU);
		}

		database.addAll(Arrays.asList(liquids));
		database.addAll(Arrays.asList(canisters));
		database.addAll(Arrays.asList(gears));
		database.addAll(Arrays.asList(cpus));
	}

	public enum id {
		LQ_KERISA, LQ_MOLT, LQ_SEDARUS, LQ_GOULIIQ, LQ_CRYPTIC, LQ_AMANZI, LQ_ZUUR, LQ_EAUX, LQ_HUNAJAR, LQ_MIELEPE, LQ_FUSILIA_FERROX, LQ_LATEX, KALIDECA, LQ_AMBRUSIA, // LIQUIDS
		CN_REKAR, CN_OLTAM, CN_EDAUSS, CN_QIIGOL, CN_RYPICAS, CN_ZINAMA, CN_RUUKAZ, CN_XARUJA, CN_UNJAROH, CN_LEMIPE, CN_FEROXIS, CN_TEXIO, CN_DOIM, CN_ALDAKK, // CANISTERS
		GR_KAREDU, GR_LOQOO, GR_DAUPYRA, GR_GONAZI, GR_PIKARQ, GR_ZAJANJ, GR_JUKOHRA, GR_MIPLIM, GR_REXIRN, GR_TEDOX, GR_AKELOS, GR_DOIH, // GEARS
		CPU_TEK_XX, CPU_TEK_XXVI, CPU_MOLT_IV, CPU_MOLT_VD, CPU_CX_TITANIUM_4, CPU_CX_TITANIUM_8, CPU_FIX_FEROCITY_1, CPU_FIX_FEROCITY_3, // CPUS
		CPU_I11_X2017, CPU_I13_II5290, CPU_I13_IV8525, CPU_I15_7750, CPU_CSP_6M2T, CPU_CSP_10MT, CPU_CSP_MXV, CPU_CSP_M2X1V
	}
}
package DAL.item;

import DAL.yaml.ItemParser;

import java.io.IOException;
import java.util.*;

public class Item {
	private static List<Item> database;

	static {
		initalizeDatabase();
	}

	private String name;
	private String description;
	private ItemType itemType;
	private Color color;
	private State state;
	private boolean pickupable;
	private boolean dropable;
	private double weight;

	public Item(Item item) {
		this(item.getName(), item.getDescription(), item.getItemType(), item.getColor(), item.getState(), item.getWeight(), item.isPickupable(), item.isDropable());
	}

	public Item(String name, String description, ItemType itemType, Color color, State state, double weight, boolean isPickupable, boolean isDropable) {
		this.name = name;
		this.description = description;
		this.itemType = itemType;
		this.color = color;
		this.state = state;
		this.pickupable = isPickupable;
		this.dropable = isDropable;
		this.weight = weight;
	}

	public Item(String name, String description, ItemType itemType, Color color, State state, double weight) {
		this(name, description, itemType, color, state, weight, true, true);
	}

	public Item(String name, String description, ItemType itemType, Color color, State state) {
		this(name, description, itemType, color, state,1, true, true);
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

	public Color getColor() {
		return color;
	}

	public State getState() {
		return state;
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
		ItemParser parser = ItemParser.getInstance();

		try {
			Map<Integer, Map<String, Object>> map = parser.getDatabase();

			database = new ArrayList<>(map.size());

			String name;
			String description;
			ItemType type;
			Color color;
			State state;
			boolean pickupable;
			boolean dropable;
			double weight;

			Item item;

			for(Map<String, Object> o : map.values()) {
				name = (String) o.get("name");
				description = (String) o.get("description");
				type = ItemType.valueOf((String) o.get("itemType"));
				color = Color.valueOf((String) o.get("color"));
				state = State.valueOf((String) o.get("state"));
				pickupable = (boolean) o.get("pickupable");
				dropable = (boolean) o.get("dropable");
				weight = (double) o.get("weight");

				database.add(new Item(name, description, type, color, state, weight, pickupable, dropable));
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	public enum id {
		LQ_KERISA, LQ_MOLT, LQ_SEDARUS, LQ_GOULIIQ, LQ_CRYPTIC, LQ_AMANZI, LQ_ZUUR, LQ_EAUX, LQ_HUNAJAR, LQ_MIELEPE, LQ_FUSILIA_FERROX, LQ_LATEX, KALIDECA, LQ_AMBRUSIA, // LIQUIDS
		CN_REKAR, CN_OLTAM, CN_EDAUSS, CN_QIIGOL, CN_RYPICAS, CN_ZINAMA, CN_RUUKAZ, CN_XARUJA, CN_UNJAROH, CN_LEMIPE, CN_FEROXIS, CN_TEXIO, CN_DOIM, CN_ALDAKK, // CANISTERS
		GR_KAREDU, GR_LOQOO, GR_DAUPYRA, GR_GONAZI, GR_PIKARQ, GR_ZAJANJ, GR_JUKOHRA, GR_MIPLIM, GR_REXIRN, GR_TEDOX, GR_AKELOS, GR_DOIH, // GEARS
		CPU_TEK_XX, CPU_TEK_XXVI, CPU_MOLT_IV, CPU_MOLT_VD, CPU_CX_TITANIUM_4, CPU_CX_TITANIUM_8, CPU_FIX_FEROCITY_1, CPU_FIX_FEROCITY_3, // CPUS
		CPU_I11_X2017, CPU_I13_II5290, CPU_I13_IV8525, CPU_I15_7750, CPU_CSP_6M2T, CPU_CSP_10MT, CPU_CSP_MXV, CPU_CSP_M2X1V
	}
}
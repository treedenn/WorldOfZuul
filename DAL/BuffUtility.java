package DAL;

import BLL.entity.player.buff.Buff;
import BLL.entity.player.buff.TransformationBuff;

import java.util.HashMap;
import java.util.Map;

public abstract class BuffUtility {
	/**
	 * Creates a buff based on the given id.
	 * @param index id of the buff
	 * @return null, if buff does not exist
	 */
	static Buff getBuffById(int index) {
		switch(index) {
			case 0: return new TransformationBuff();
			default: return null;
		}
	}

	/**
	 * Creates a buff based on the given id and information given.
	 * @param index id of the buff
	 * @return null, if buff does not exist or given information does not match the buff
	 */
	public static Buff getBuffById(int index, Map<String, Object> information) {
		switch(index) {
			case 0: return getTransformationBuff(information);
			default: return null;
		}
	}

	/**
	 * Gets the information of a any buff.
	 * @param buff any buff
	 * @return a map with the information
	 */
	public static Map<String, Object> getBuffInformation(Buff buff) {
		if(buff instanceof TransformationBuff) {
			return getTransformationInformation((TransformationBuff) buff);
		}

		return null;
	}

	/**
	 * Places information from a transformation buff into a map.
	 * @param buff any transformation buff
	 * @return a map
	 */
	private static Map<String, Object> getTransformationInformation(TransformationBuff buff) {
		Map<String, Object> map = new HashMap<>();

		map.put("morph-id", buff.getMorphId());
		map.put("duration", buff.getDuration());

		return map;
	}

	/**
	 * Returns a transformation buff based on the given information in a map.
	 * @param information
	 * @return null, if information does not has the needed information
	 */
	private static TransformationBuff getTransformationBuff(Map<String, Object> information) {
		if(information.containsKey("morph-id") && information.containsKey("duration")) {
			return new TransformationBuff((int) information.get("morph-id"), (long) information.get("duration"));
		}

		return null;
	}
}

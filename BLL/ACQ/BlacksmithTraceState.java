package BLL.ACQ;

/**
 * An enum controlling how many traces the blacksmith can have.
 * In addition it contains the key for the localization.
 */
public enum BlacksmithTraceState {
	ON_PLANET("blacksmith-trace-on-planet"),
	ONE_STEP("blacksmith-trace-one-step"),
	TWO_STEP("blacksmith-trace-two-step"),
	THREE_STEP("blacksmith-trace-three-step"),
	NO_TRACE("blacksmith-trace-no-trace");

	private String key;

	BlacksmithTraceState(String key) {
		this.key = key;
	}

	/**
	 * Gets the key of the enum.
	 * The key is only use is in the localization.
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
}

package BLL.ACQ;

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

	public String getKey() {
		return key;
	}
}

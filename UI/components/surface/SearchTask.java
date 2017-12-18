package UI.components.surface;

import javafx.concurrent.Task;

/**
 * This class is used to animate the search button in the planet view.
 */
public class SearchTask extends Task {
	private boolean isPermSearch;

	public SearchTask(boolean isPermSearch) {
		this.isPermSearch = isPermSearch;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object call() throws Exception {
		final int max = 10;
		long sleep;

		for(int i = 1; i <= max; i++) {
			if(isPermSearch) {
				sleep = (long) (50 + Math.random() * 100); // 50 -> 150 ms
			} else {
				sleep = (long) (100 + Math.random() * 200); // 100 -> 300 ms
			}
			updateProgress(i, max);
			Thread.sleep(Math.abs(sleep));
		}

		return null;
	}
}
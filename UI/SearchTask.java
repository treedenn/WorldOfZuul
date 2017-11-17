package UI;

import javafx.concurrent.Task;

public class SearchTask extends Task {
	private boolean isPermSearch;

	public SearchTask(boolean isPermSearch) {
		this.isPermSearch = isPermSearch;
	}

	@Override
	protected Object call() throws Exception {
		final int max = 10;
		long sleep;

		for(int i = 1; i <= max; i++) {
			// TODO: Maybe go back to the old sleep system

			if(isPermSearch) {
				sleep = (long) (50 + Math.random() * 100); // 50 -> 150 ms
			} else {
				sleep = (long) (100 + Math.random() * 200); // 100 -> 300 ms
			}

//			if(!isPermSearch) {
//				sleep = (long) (100 * (-0.022 * i * i + 0.20 * i + 0.15));
//			} else {
//				sleep = (long) (100 * (-0.004329 * i * i + 0.02165 * i + 0.15));
//			}

			updateProgress(i, max);
			Thread.sleep(Math.abs(sleep));
		}

		return null;
	}
}
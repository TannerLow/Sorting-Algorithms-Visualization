package algos;

import main.ISortingAlgorithm;
import main.SortArray;

public class SelectionSort implements ISortingAlgorithm {
	private long delay = 2;
	
	public void sort(SortArray array) {
		for(int i = 0; i < array.size(); i++) {
			int min = array.size() - 1;
			for(int j = i; j < array.size(); j++) {
				array.update(j, delay);
				if(array.compare(j, min)) {
					min = j;
				}
			}
			array.swap(i, min, delay);
		}
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public String getName() {
		return "Selection Sort";
	}

}

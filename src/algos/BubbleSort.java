package algos;

import main.ISortingAlgorithm;
import main.SortArray;

public class BubbleSort implements ISortingAlgorithm {
	private long delay = 2;
	
	public BubbleSort() {}
	public BubbleSort(long delay) {
		this.delay = delay;
	}
	
	public void sort(SortArray array) {
		boolean swapped;
		for(int i = 0; i < array.size() - 1; i++) {
			swapped = false;
			for(int j = 0; j < array.size() - 1 - i; j++) {
				if(array.compare(j+1, j)) {
					array.swap(j, j+1, delay);
					swapped = true;
				}
			}
			if(!swapped) {
				return;
			}
		}
	}
	
	public long getDelay() {
		return delay;
	}
	
	public void setDelay(long delay) {
		this.delay = delay;
	}
	
	public String getName() {
		return "Bubble Sort";
	}
}

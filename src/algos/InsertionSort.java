package algos;

import main.ISortingAlgorithm;
import main.SortArray;

public class InsertionSort implements ISortingAlgorithm {
	private long delay = 2;
	
	public void sort(SortArray array) {
		for(int i = 1; i < array.size(); i++) {
			for(int j = i; j > 0; j--) {
				//updateGraph(j-1,j);
				if(array.compare(j-1, j))
					break;
				else {
					array.swap(j-1, j, delay);
				}
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
		return "Insertion Sort";
	}

}

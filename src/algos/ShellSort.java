package algos;

import main.ISortingAlgorithm;
import main.SortArray;

public class ShellSort implements ISortingAlgorithm{
	private long delay = 5;

	public void sort(SortArray array) {
		for(int gap = array.size()/2; gap > 0; gap /= 2) {
			for(int i = gap; i < array.size(); i++) {
				for(int j = i; j >= gap; j -= gap) {
					if(array.compare(j, j-gap)) {
						array.swap(j, j-gap, delay);
						//updateGraph(j-gap,j);
					}
					else
						break;
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
		return "Shell Sort";
	}

}

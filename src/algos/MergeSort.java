package algos;

import main.ISortingAlgorithm;
import main.SortArray;

public class MergeSort implements ISortingAlgorithm {
	private long delay = 5;
	SortArray array;
	
	public void sort(SortArray array) {
		this.array = array;
		for(int length = 1; length < array.size(); length *= 2) {
			for(int i = 0; i < array.size() - length; i += length*2) {
				merge(i, i + length-1, Math.min(i + length*2, array.size()));
			}
		}
	}
	
	private void merge(int start, int mid, int end) {
		int left = start, right = mid+1;
		int[] sorted = new int[end-start];
		for(int j = 0; j < sorted.length; j++) {
			if(left > mid) {
				sorted[j] = array.get(right);
				array.update(left,right++, delay);
			}
			else if(right == end) {
				sorted[j] = array.get(left);
				array.update(left,left++, delay);
			}
			else if(array.compare(left, right)) {
				sorted[j] = array.get(left);
				array.update(left,left++, delay);
			}
			else {
				sorted[j] = array.get(right);
				array.update(left,right++, delay);
			}
		}
		for(int j = 0; j < sorted.length; j++) {
			array.set(j+start, sorted[j]);
			array.update(j+start, delay);
		}
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public String getName() {
		return "Merge Sort";
	}

}


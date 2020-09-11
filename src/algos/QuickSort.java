package algos;

import main.ISortingAlgorithm;
import main.SortArray;

public class QuickSort implements ISortingAlgorithm{
	private long delay = 5;
	SortArray array;
	
	public void sort(SortArray array) {
		this.array = array;
		quickSort(0, array.size()-1);
	}
	
	//recursive version called by initial sort method
	private void quickSort(int left, int right) {
		if(left < right) {
			int pos = partition(left,right);
			quickSort(left,pos-1);
			quickSort(pos+1,right);
		}
	}
	private int partition(int left, int right) {
		int pivot = right--;
		while(true) {
			array.update(left, pivot, delay);
			while(array.compare(left, pivot)) {
				array.update(left,right, delay);
				left++;
			}
			while(array.compare(pivot, right) && right  > 0) {
				array.update(right, delay);
				right--;
			}
			if(left < right) {
				array.swap(left, right, delay);
			}
			else {
				array.swap(left, pivot, delay);
				return left;
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
		return "Quick Sort";
	}

}

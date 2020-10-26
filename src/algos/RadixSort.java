package algos;

import java.util.ArrayList;
import java.util.List;

import main.ISortingAlgorithm;
import main.SortArray;

public class RadixSort implements ISortingAlgorithm {
	private long delay = 5;

	public void sort(SortArray array) {
		List<List<Integer>> buckets = new ArrayList<List<Integer>>(10);
		for(int i = 0; i < 10; i++)
			buckets.add(new ArrayList<Integer>());

		int denominator = 1, digit;
		while(denominator < array.size()) {
			for(int j = 0; j < array.size(); j++) {
				digit = (array.get(j) / denominator) % 10;
				buckets.get(digit).add(array.get(j));
			}
			int i = 0;
			for(int j = 0; j < array.size(); j++) {
				if(buckets.get(i).isEmpty())
					i++;
				array.set(j, buckets.get(i).remove(0));
				array.update(j, delay);
			}
			denominator *= 10;
		}
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public String getName() {
		return "Radix Sort";
	}

}

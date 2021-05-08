package algos;

import java.util.Random;

import main.ISortingAlgorithm;
import main.SortArray;


public class BogoSort implements ISortingAlgorithm{
	private long delay = 1;
	Random obj = new Random ();
	public void sort(SortArray array) {
		boolean result = false;
		while (!result)
		{
			int position = obj.nextInt(array.size());
			for (int i = 0; i < array.size(); i++)
			{
				position = obj.nextInt(array.size());
				array.swap(i, position, delay);
			}
			result = true;
			for (int i = 0; i < array.size() - 1; i++)
			{
				if (array.get(i) >= array.get(i+1))
				{
					result = false;
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
		return "Bogo Sort";
	}
}

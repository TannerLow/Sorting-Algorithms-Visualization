package main;
import java.util.Random;

public class SortArray {
	private Display display;
	private int size;
	private int[] array;
	public int comparisons = 0, swaps = 0;
	
	SortArray(Display display, int size){
		this.display = display;
		this.size = size;
		array = new int[size];
		populate();
	}
	
	private void populate() {
		for(int i = 0; i < array.length; i++)
			array[i] = i+1;
	}
	
	public void randomize() {
		swaps = 0; comparisons = 0;
		Random random = new Random();
		for(int i = 0; i < size; i++) {
			int randomIndex = random.nextInt(size);
			int temp = array[i];
			array[i] = array[randomIndex];
			array[randomIndex] = temp;
		}
	}
	
	public void setNewSize(int size) {
		this.size = size;
		array = new int[size];
		populate();
		randomize();
	}
	
	public int size() {
		return size;
	}
	
	public void setAlgorithmInfo(String name, long delay) {
		display.setAlgorithmInfo(name, delay);
	}
	
	public void swap(int indexA, int indexB, long milliSeconds) {
		//Delay for visualization
		try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
		//Swap elements
		int temp      = array[indexA];
		array[indexA] = array[indexB];
		array[indexB] = temp;
		swaps++;
		display.render(array, indexA, indexB, swaps, comparisons);
	}
	
	public boolean compare(int indexA, int indexB) {
		comparisons++;
		return array[indexA] < array[indexB];
	}
	
	public void update(int highlightPos, long delay) {
		update(highlightPos, highlightPos, delay);
	}
	
	public void update(int highlightPos1, int highlightPos2, long delay) {
		//Delay for visualization
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		display.render(array, highlightPos1, highlightPos2, swaps, comparisons);
	}
	
	public int get(int position) {
		return array[position];
	}
	
	public void set(int position, int value) {
		array[position] = value;
	}
}

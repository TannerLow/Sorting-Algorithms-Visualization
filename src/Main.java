import java.util.Random;

public class Main {
	public static int[] array;
	public final static int SIZE = 100;
	public static Display display;
	public static void main(String[] args) {
		display = new Display(1600, 800, "Sorting Algorithms Visualization");
		populate(SIZE);
		randomize();
		display.render();
	}
	public static void populate() {
		array = new int[SIZE];
		for(int i = 0; i < SIZE; i++)
			array[i] = i + 1;
	}
	public static void randomize() {
		Random random = new Random();
		for(int i = 0; i < SIZE; i++) {
			int temp = array[i];
			int newIndex = random.nextInt(SIZE);
			array[i] = array[newIndex];
			array[newIndex] = temp;
		}
	}
	public static void swap(int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	public static void bubbleSort() {
		for(int i = 1; i < SIZE - 1; i++) {
			for(int j = 0; j < SIZE - 1 - i; j++) {
				
			}
			if(array[i] > array[i+1]) {
				swap(i, i + 1);
			}
		}
	}
}

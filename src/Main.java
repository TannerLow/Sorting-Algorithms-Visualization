import java.util.Random;

public class Main {
	static int[] array;
	final static int SIZE = 100;
	static int comparisons = 0, swaps = 0;
	public static Display display;
	public static void main(String[] args) {
		display = new Display(1600, 800, "Sorting Algorithms Visualization");
		populate();
		display.render();
		randomize();
		display.render();
		//bubbleSort();
		display.render();
		//shellSort();
		display.render();
		mergeSort();
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
		System.out.println("==Bubble Sort==");
		comparisons = 0; swaps = 0;
		boolean swapped;
		for(int i = 0; i < SIZE - 1; i++) {
			swapped = false;
			for(int j = 0; j < SIZE - 1 - i; j++) {
				comparisons++;
				if(array[j] > array[j+1]) {
					swap(j, j + 1);
					swaps++;
					swapped = true;
					display.render();
				}
			}
			if(!swapped) {
				System.out.println("Comparisons: " + comparisons);
				System.out.println("Swaps: " + swaps);
				return;
			}
		}
	}
	public static void shellSort() {
		System.out.println("==Shell Sort==");
		comparisons = 0; swaps = 0;
		for(int gap = SIZE / 2; gap > 0; gap /= 2) {
			for(int i = gap; i < SIZE; i++) {
				for(int j = i; j >= gap; j -= gap) {
					comparisons++;
					if(array[j-gap] > array[j]) {
						swap(j-gap, j);
						swaps++;
						display.render();
					}
					else
						break;
				}
			}
		}
		System.out.println("Comparisons: " + comparisons);
		System.out.println("Swaps: " + swaps);
	}
	public static void mergeSort() {
		System.out.println("==Merge Sort==");
		comparisons = 0; swaps = 0;
		
		for(int size = 1; size < SIZE; size *= 2) 
			for(int start = 0; start < SIZE - size; start += size*2)
				merge(start, start+size-1, Math.min(start+size+size-1, SIZE-1));
		
		System.out.println("Comparisons: " + comparisons);
		System.out.println("Swaps: " + swaps);
	}
	public static void merge(int start, int mid, int end) {
		int[] copy = new int[SIZE];
		for(int i = start; i <= end; i++) 
			copy[i] = array[i];
			
		int left = start, right = mid + 1;
		for(int i = start; i <= end; i++) {
			if(left > mid) {
				array[i] = copy[right++];
				comparisons++;
			}
			else if(right > end) {
				array[i] = copy[left++];
				comparisons++;
			}
			else if(copy[left] > copy[right]) {
				array[i] = copy[right++];
				comparisons++;
			}
			else {
				array[i] = copy[left++];
			}
			
			swaps++;
		}
	}
}

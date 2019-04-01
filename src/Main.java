import java.util.Random;

public class Main {
	public static int[] array;
	public static int[] swapIndeces = {-1,-1};
	private final static int SIZE = 400, SORTING_RATE = 1;
	public static int comparisons = 0, swaps = 0, swapTime = 0, waitVar = 0;
	public static Display display;
	
	public static void main(String[] args) throws InterruptedException {
		display = new Display(1600, 800, "Sorting Algorithms Visualization");
		populate();
		display.render();
		while(true) {
			waitToContinue();

			Display.currentSort = "Bubble Sort";
			Thread.sleep(1000);
			randomize();
			bubbleSort();
			display.render();
			waitToContinue();

			Display.currentSort = "Shell Sort";
			Thread.sleep(1000);
			randomize();
			shellSort();
			display.render();
			waitToContinue();

			Display.currentSort = "Merge Sort";
			Thread.sleep(1000);
			randomize();
			mergeSort();
			display.render();
			waitToContinue();

			Display.currentSort = "Quick Sort";
			Thread.sleep(1000);
			randomize();
			quickSort();
			display.render();
		}
	}
	private static void populate() {
		array = new int[SIZE];
		for(int i = 0; i < SIZE; i++)
			array[i] = i + 1;
	}
	private static void randomize() {
		Random random = new Random();
		for(int i = 0; i < SIZE; i++) {
			int temp = array[i];
			int newIndex = random.nextInt(SIZE);
			array[i] = array[newIndex];
			array[newIndex] = temp;
		}
	}
	private static void swap(int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	private static void bubbleSort() throws InterruptedException {
		swapTime = 1 * SORTING_RATE;
		boolean swapped;
		for(int i = 0; i < SIZE - 1; i++) {
			swapped = false;
			for(int j = 0; j < SIZE - 1 - i; j++) {
				comparisons++;
				if(array[j] > array[j+1]) {
					swap(j, j + 1);
					swaps++;
					swapIndeces[0] = j; swapIndeces[1] = j+1;
					swapped = true; 
					Thread.sleep(swapTime);
					display.render();
				}
			}
			if(!swapped) {
				swapIndeces[0] = -1; swapIndeces[1] = -1;
				return;
			}
		}
	}
	private static void shellSort() throws InterruptedException {
		swapTime = 5 * SORTING_RATE; 
		for(int gap = SIZE / 2; gap > 0; gap /= 2) {
			for(int i = gap; i < SIZE; i++) {
				for(int j = i; j >= gap; j -= gap) {
					comparisons++;
					if(array[j-gap] > array[j]) {
						swap(j-gap, j);
						swaps++;
						swapIndeces[0] = j-gap; swapIndeces[1] = j;
						Thread.sleep(swapTime);
						display.render();
					}
					else
						break;
				}
			}
		}
		swapIndeces[0] = -1; swapIndeces[1] = -1;
	}
	private static void mergeSort() throws InterruptedException {
		swapTime = 5 * SORTING_RATE; 
		for(int size = 1; size < SIZE; size *= 2) 
			for(int start = 0; start < SIZE - size; start += size*2)
				merge(start, start+size-1, Math.min(start+size+size-1, SIZE-1));

		swapIndeces[0] = -1; swapIndeces[1] = -1;
	}
	private static void merge(int start, int mid, int end) throws InterruptedException {
		int[] copy = new int[SIZE];
		for(int i = start; i <= end; i++) 
			copy[i] = array[i];
			
		int left = start, right = mid + 1;
		for(int i = start; i <= end; i++) {
			if(left > mid) {
				array[i] = copy[right];
				swapIndeces[0] = i; swapIndeces[1] = right++;
				comparisons++;
			}
			else if(right > end) {
				array[i] = copy[left];
				swapIndeces[0] = i; swapIndeces[1] = left++;
				comparisons++;
			}
			else if(copy[left] > copy[right]) {
				array[i] = copy[right];
				swapIndeces[0] = i; swapIndeces[1] = right++;
				comparisons++;
			}
			else {
				array[i] = copy[left];
				swapIndeces[0] = i; swapIndeces[1] = left++;
			}
			Thread.sleep(swapTime);
			display.render();
			swaps++;
		}
	}
	private static void quickSort() throws InterruptedException {
		swapTime = 5 * SORTING_RATE;
		quickSort(0, SIZE-1);
		swapIndeces[0] = -1; swapIndeces[1] = -1;
	}
	private static void quickSort(int start, int end) throws InterruptedException {
		if(start < end) {
			int pivot = partition(start, end);
			quickSort(start, pivot-1);
	        quickSort(pivot+1, end);
		}
	}
	private static int partition(int start, int end) throws InterruptedException {
		int pivot = array[end];
		int i = start - 1;
		for(int j = start; j <= end-1; j++) {
			comparisons++;
			if(array[j] <= pivot) {
				swap(++i, j);
				Thread.sleep(swapTime);
				display.render();
				swaps++;
				swapIndeces[0] = i; swapIndeces[1] = j;
			}
		}
		swap(++i, end);
		Thread.sleep(swapTime);
		display.render();
		swaps++;
		swapIndeces[0] = i; swapIndeces[1] = end;
		return i;
	}
	private static void waitToContinue() throws InterruptedException {
		Display.done = true;
		waitVar = 0;
		do {
			Thread.sleep(200);
		}while(waitVar == 0);
		Display.done = false;
		comparisons = 0; swaps = 0;
		display.render();
	}
}
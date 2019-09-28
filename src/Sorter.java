import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class Sorter {
	private String nameOfSort = "";
	private int size, sortingRate, swapTime;
	private int swaps = 0, comparisons = 0;
	private int[] array, swapIndeces = {-1,-1};
	private Display display;
	private boolean displaySet = false;
	
	public Sorter(int size, int sortingRate) {
		this.size = size;
		this.sortingRate = sortingRate;
		array = new int[size];
		populate();
	}
	public void setDisplay(Display display) {
		this.display = display;
		displaySet = true;
	}
	public void setSize(int size) {
		this.size = size;
		array = new int[size];
		populate();
	}
	public int getSize() {
		return size;
	}
	public void setSortingRate(int rate) {
		sortingRate = rate;
	}
	public int getSortingRate() {
		return sortingRate;
	}
	public String getName() {
		return nameOfSort;
	}
	public int getSwapTime() {
		return swapTime;
	}
	public int at(int i) {
		return array[i];
	}
	public int getSwapIndex(int i) {
		return swapIndeces[i];
	}
	public int getSwaps() {
		return swaps;
	}
	public int getComparisons() {
		return comparisons;
	}
	public void swap(int a, int b) {
		swaps++;
		if(a == b) return;
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
		swaps++;
	}
	public boolean compare(int a, int b) {
		comparisons++;
		return a < b; 
	}
	public void populate() {
		for(int i = 0; i < array.length; i++)
			array[i] = i+1;
	}
	public void randomize() {
		Random random = new Random();
		 for(int i = 0; i < size; i++) {
			 int randomIndex = random.nextInt(size);
			 swap(i,randomIndex);
		 }
	}
	public void updateGraph(int index1, int index2) {
		swapIndeces[0] = index1;
		swapIndeces[1] = index2;
		try {
			Thread.sleep(swapTime * sortingRate);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(displaySet) {
			display.render();
		}
		else {
			System.out.println("NO DISPLAY SPECIFIED TO SORTER!");
			System.out.println("Use setDisplay(Display display)");
			System.exit(0);
		}
	}
//Sorts below]====================================================
/*
 * To add a new algorithm follow these steps
 * 1. Create a new Sort enum in Sort.java
 * 2. Add it to the sort function below.
 * 		Give it a name, swapTime, and call its method
 * 3. Create its method(s) down below.
 * 		Be sure to use updateGraph() to show the algorithm working
 * 		Use the swap function to swap array indeces
 * 			Use swap(a,a) to increase swaps without changing anything
 * 		Use compare(a,b) to keep track of comparisons
 * 			Returns true when a < b
 */
	public void sort(Sort sort) {
		swaps = 0; comparisons = 0;
		switch(sort) {
		case Bubble:
			nameOfSort = "Bubble Sort";
			swapTime = 1;
			bubbleSort();
			break;
		case Shell:
			nameOfSort = "Shell Sort";
			swapTime = 5;
			shellSort();
			break;
		case Merge:
			nameOfSort = "Merge Sort";
			swapTime = 5;
			mergeSort();
			break;
		case Quick:
			nameOfSort = "Quick Sort";
			swapTime = 5;
			quickSort();
			break;
		case Selection:
			nameOfSort = "Selection Sort";
			swapTime = 1;
			selectionSort();
			break;
		case Radix:
			nameOfSort = "Radix Sort";
			swapTime = 5;
			radixSort();
			break;
		case Insertion:
			nameOfSort = "Insertion Sort";
			swapTime = 1;
			insertionSort();
			break;
		}
		swapIndeces[0] = -1; swapIndeces[1] = -1;
	}
	
	public void bubbleSort() {
		boolean swapped;
		for(int i = 0; i < size - 1; i++) {
			swapped = false;
			for(int j = 0; j < size - 1 - i; j++) {
				if(compare(array[j+1], array[j])) {
					swap(j,j+1);
					swapped = true;
					updateGraph(j,j+1);
				}
			}
			if(!swapped) {
				return;
			}
		}
	}
	public void shellSort() {
		for(int gap = size/2; gap > 0; gap /= 2) {
			for(int i = gap; i < size; i++) {
				for(int j = i; j >= gap; j -= gap) {
					if(compare(array[j], array[j-gap])) {
						swap(j, j-gap);
						updateGraph(j-gap,j);
					}
					else
						break;
				}
			}
		}
	}
	public void mergeSort() {
		for(int length = 1; length < size; length *= 2) {
			for(int i = 0; i < size-length; i += length*2) {
				merge(i, i+length-1, Math.min(i+length*2,size));
			}
		}
	}
	public void merge(int start, int mid, int end) {
		int left = start, right = mid+1;
		int[] sorted = new int[end-start];
		for(int j = 0; j < sorted.length; j++) {
			if(left > mid) {
				sorted[j] = array[right];
				updateGraph(left,right++);
			}
			else if(right == end) {
				sorted[j] = array[left];
				updateGraph(left,left++);
			}
			else if(compare(array[left],array[right])) {
				sorted[j] = array[left];
				updateGraph(left,left++);
			}
			else {
				sorted[j] = array[right];
				updateGraph(left,right++);
			}
		}
		for(int j = 0; j < sorted.length; j++) {
			array[j+start] = sorted[j];
		}
	}
	public void quickSort() {
		
	}
	public void selectionSort() {
		for(int i = 0; i < size; i++) {
			int min = size-1;
			for(int j = i; j < size; j++) {
				updateGraph(j,j);
				if(compare(array[j],array[min])) {
					min = j;
				}
			}
			swap(i,min);
		}
	}
	public void radixSort() {
		ArrayList<Integer> zero  = new ArrayList<Integer>(),
				   		   one   = new ArrayList<Integer>(),
				   		   two   = new ArrayList<Integer>(),
				   		   three = new ArrayList<Integer>(),
				   		   four  = new ArrayList<Integer>(),
				   		   five  = new ArrayList<Integer>(),
				   		   six   = new ArrayList<Integer>(),
				   		   seven = new ArrayList<Integer>(),
				   		   eight = new ArrayList<Integer>(),
				   		   nine  = new ArrayList<Integer>();
		TreeMap<Integer,ArrayList<Integer>> map = new TreeMap<Integer,ArrayList<Integer>>();
		map.put(0, zero);
		map.put(1,  one);
		map.put(2,  two);
		map.put(3,three);
		map.put(4, four);
		map.put(5, five);
		map.put(6,  six);
		map.put(7,seven);
		map.put(8,eight);
		map.put(9, nine);

		int denominator = 1, digit;
		while(denominator < size) {
			for(int j = 0; j < size; j++) {
				digit = (array[j] / denominator) % 10;
				map.get(digit).add(array[j]);
			}
			int i = 0;
			for(int j = 0; j < size; j++) {
				if(map.get(i).isEmpty())
					i++;
				array[j] = map.get(i).remove(0);
				updateGraph(j,j);
			}
			denominator *= 10;
		}
	}
	public void insertionSort() {
		for(int i = 1; i < size; i++) {
			for(int j = i; j > 0; j--) {
				updateGraph(j-1,j);
				if(compare(array[j-1],array[j]))
					break;
				else {
					swap(j-1,j);
				}
			}
		}
	}
}

package main;
import algos.BubbleSort;
import algos.InsertionSort;
import algos.MergeSort;
import algos.QuickSort;
import algos.SelectionSort;
import algos.ShellSort;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Display display = new Display(1600,800,"Sorting Algorithms Visualization");
		SortArray graph = new SortArray(display, 100);
		
		ISortingAlgorithm[] slowAlgorithms = {
				new BubbleSort(),
				new InsertionSort(),
				new SelectionSort()
		};
		
		ISortingAlgorithm[] fastAlgorithms = {
				new QuickSort(),
				new ShellSort(),
				new MergeSort()
		};
		
		while(true) {
//			graph.setNewSize(100);
//			for(ISortingAlgorithm algorithm : slowAlgorithms) {
//				graph.setAlgorithmInfo(algorithm.getName(), algorithm.getDelay());
//				graph.randomize();
//				algorithm.sort(graph);
//				display.pause();
//			}
			graph.setNewSize(400);
			for(ISortingAlgorithm algorithm : fastAlgorithms) {
				graph.setAlgorithmInfo(algorithm.getName(), algorithm.getDelay());
				graph.randomize();
				algorithm.sort(graph);
				display.pause();
			}
		}
	}
}

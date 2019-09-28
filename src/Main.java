public class Main {
	public static boolean waitVar = false;
	public static Display display;
	private static Sorter sorter;
	
	public static void main(String[] args) throws InterruptedException {
		sorter = new Sorter(400, 1);
		display = new Display(1600, 800, "Sorting Algorithms Visualization", sorter);
		sorter.setDisplay(display);
		display.render();
		Sort[] slowerSorts = {Sort.Insertion, Sort.Bubble, Sort.Selection};
		Sort[] fasterSorts = {Sort.Radix, Sort.Shell, Sort.Merge};
		
		while(true) {
			for(Sort sort : slowerSorts) 
				runAlgorithm(sort,200);
			for(Sort sort : fasterSorts)
				runAlgorithm(sort,400);
		}
	}
	private static void runAlgorithm(Sort sort, int size) throws InterruptedException {
		waitToContinue();
		sorter.setSize(size);
		sorter.randomize();
		sorter.sort(sort);
		display.render();
	}
	//Press to continue function, gets called between algorithms
	private static void waitToContinue() throws InterruptedException {
		Display.done = true;
		waitVar = false;
		do {
			Thread.sleep(200);
		}while(!waitVar);
		Display.done = false;
		display.render();
	}
}
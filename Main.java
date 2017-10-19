import java.awt.Color;

import java.util.concurrent.CyclicBarrier;

public class Main {

	public static void main(String[] args) {
		CyclicBarrier gate = new CyclicBarrier(2);
		long Time = System.nanoTime();
		PuzzleDrawer HSDrawer = new PuzzleDrawer("HSDrawer","C:\\Users\\fbpea\\Git_Repositories\\SteamTableDemo\\HeuristicSearch.exe", gate, 512, Color.BLACK, Color.BLUE, Time);
		PuzzleDrawer BFSDrawer = new PuzzleDrawer("BFSDrawer","C:\\Users\\fbpea\\Git_Repositories\\SteamTableDemo\\BreadthFirstSearch.exe", gate, 0, Color.DARK_GRAY, Color.RED, Time);
		
		(new Thread(BFSDrawer)).start();
		(new Thread(HSDrawer)).start();


		
	}

}

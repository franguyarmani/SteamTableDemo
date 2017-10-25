import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.CyclicBarrier;

public class Main {
	/*
	 * The Paths are the paths to the executable files that will be run by each of the visual 
	 * simulator's threads. The canvas is initialized to a specific size and DoubleBuffering is 
	 * enabled for animation. "gate" is a cyclic barrier that synchronizes the steps of each 
	 * search algorithm. Each search executable is initialized as a "PuzzleDrawer" runnable 
	 * and then each is started on its own thread. 
	 */
	public static void main(String[] args) {
		
		String Path1;
		String Path2;
		Path1 = args[0];
		Path2 = args[1];
		
		
		StdDraw.enableDoubleBuffering();
		StdDraw.setCanvasSize(1024, 512);
		StdDraw.setXscale(-100, 300);
		StdDraw.setYscale(-100, 100);
		Font font = new Font("Sans Sarif", Font.BOLD, 40);
		StdDraw.setFont(font);
		
		CyclicBarrier gate = new CyclicBarrier(2);
		long Time = System.nanoTime();
		PuzzleDrawer Sim1Drawer = new PuzzleDrawer("Sim1",Path1, gate, 0, Color.BLACK, Color.BLUE, Time);
		PuzzleDrawer Sim2Drawer = new PuzzleDrawer("Sim2",Path2, gate, 512, Color.DARK_GRAY, Color.RED, Time);
		
		(new Thread(Sim1Drawer)).start();
		(new Thread(Sim2Drawer)).start();


		
	}

}

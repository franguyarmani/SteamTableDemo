import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.CyclicBarrier;

public class Main {

	public static void main(String[] args) {
		String Path1;
		String Path2;
		Path1 = args[0];
		Path2 = args[1];
		CyclicBarrier gate = new CyclicBarrier(2);
		StdDraw.enableDoubleBuffering();
		StdDraw.setCanvasSize(1024, 512);
		StdDraw.setXscale(-100, 300);
		StdDraw.setYscale(-100, 100);
		Font font = new Font("Sans Sarif", Font.BOLD, 40);
		StdDraw.setFont(font);
		long Time = System.nanoTime();
		PuzzleDrawer Sim1Drawer = new PuzzleDrawer("Sim1",Path1, gate, 0, Color.BLACK, Color.BLUE, Time);
		PuzzleDrawer Sim2Drawer = new PuzzleDrawer("Sim2",Path2, gate, 512, Color.DARK_GRAY, Color.RED, Time);
		
		(new Thread(Sim1Drawer)).start();
		(new Thread(Sim2Drawer)).start();


		
	}

}

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;

public class PuzzleDrawer implements Runnable {
	private Thread t;
	private String Name;
	private BufferedReader Source;
	private CountDownLatch StartSignal;
	private double	xshift;
	private int pixelshift;
	private Color color1;
	private Color color2;
	private long Time;
	char c00;
	char c01;
	char c02;
	char c10;
	char c11;
	char c12;
	char c20;
	char c21;
	char c22;
	
	PuzzleDrawer(String nameOfDrawer, String Path, CountDownLatch StartSignal, int shift, Color C1, Color C2, long Time) {
		Name = nameOfDrawer;
		this.Source = getReader(Path);
		this.StartSignal = StartSignal;
		pixelshift = shift;
		color1 = C1;
		color2 = C2;
		xshift = (pixelshift*400)/1024;
		this.Time = Time;
	}
	
	
	public void Start() {
		 if (t == null) {
	         t = new Thread (this, Name);
	         t.run();
		 }
	}
	
	public void run() {
		/*
		 * Setting up initial conditions for search visualizer
		 */
		
		StdDraw.enableDoubleBuffering();
		StdDraw.setCanvasSize(1024, 512);
		StdDraw.setXscale(-100, 300);
		StdDraw.setYscale(-100, 100);
		Font font = new Font("Sans Sarif", Font.BOLD, 40);
		StdDraw.setFont(font);
		StdDraw.setPenColor(color1);
		
		StartSignal.countDown();
		try {
			long starttime = System.nanoTime();
			StartSignal.await();
			long endtime = System.nanoTime();
			System.out.println(Name+": gate hit after "+(starttime-Time));
			System.out.println(Name+": gate passed after "+(endtime-Time));
			String nextLine = Source.readLine();
			while(nextLine != null) {
				String nextNode = nextLine;
				//System.out.println(Name+": "+nextLine);
				ClearVariables();
				StdDraw.clearLocal(0+pixelshift, 0, 512+pixelshift, 512);
				StdDraw.square(0+xshift,0,75);
				StdDraw.line(-75+xshift, -25, 75+xshift, -25);
				StdDraw.line(-75+xshift, 25, 75+xshift, 25);
				StdDraw.line(-25+xshift, -75, -25+xshift, 75);
				StdDraw.line(25+xshift, -75, 25+xshift, 75);
				if(nextNode.length() > 2) {
					c00 = nextNode.charAt(2);
					c01 = nextNode.charAt(3);
					c02 = nextNode.charAt(4);
				}
				if(nextNode.length() > 7) {
					c10 = nextNode.charAt(8);
					c11 = nextNode.charAt(9);
					c12 = nextNode.charAt(10);
				}else {
					StdDraw.setPenColor(color2);
					WriteValues();
					StdDraw.show();
					StdDraw.pause(20);
				}
				
				if(nextNode.length() > 13) {
					c20 = nextNode.charAt(14);
					c21 = nextNode.charAt(15);
					c22 = nextNode.charAt(16);
				}else {
					StdDraw.text(-50+xshift, 50, String.valueOf(c00));
					StdDraw.text(0+xshift, 50, String.valueOf(c01));
					StdDraw.text(50+xshift, 50, String.valueOf(c02));
					StdDraw.setPenColor(color2);
					StdDraw.text(-50+xshift, 0, String.valueOf(c10));
					StdDraw.text(0+xshift, 0, String.valueOf(c11));
					StdDraw.text(50+xshift, 0, String.valueOf(c12));
					StdDraw.show();
					StdDraw.pause(20);
				}
				

				StdDraw.setPenColor(color1);
				WriteValues();
				StdDraw.show();
				StdDraw.pause(20);
				nextLine = Source.readLine();
			}
		}catch(IOException | NullPointerException NoLine){
			System.out.println("No more nodes given to check");
			NoLine.printStackTrace(System.out);
			
		}catch(InterruptedException I) {
			System.out.println("Thread interupted");
		}
		System.out.println(Name+" passed exception");

		StdDraw.setPenColor(StdDraw.GREEN);
		WriteValues();
		StdDraw.setPenColor(color1);
		StdDraw.square(0+xshift,0,75);
		StdDraw.line(-75+xshift, -25, 75+xshift, -25);
		StdDraw.line(-75+xshift, 25, 75+xshift, 25);
		StdDraw.line(-25+xshift, -75, -25+xshift, 75);
		StdDraw.line(25+xshift, -75, 25+xshift, 75);
		StdDraw.show();
	}
	
	public BufferedReader getReader(String Path) {
		Process SearchFunc;
		try {
			SearchFunc = new ProcessBuilder(Path).start();
			BufferedReader r = new BufferedReader(new InputStreamReader(SearchFunc.getInputStream()));
			return r;
		} catch (IOException PB) {
			System.out.println("ProcessBuilder falied on Thread "+Name);
			PB.printStackTrace();
		}return null;
		
		
	}
	public void ClearVariables() {
		c00 = ' ';
		c01 = ' ';
		c02 = ' ';
		c10 = ' ';
		c11 = ' ';
		c12 = ' ';
		c20 = ' ';
		c21 = ' ';
		c22 = ' ';
	}
	public void WriteValues() {
		StdDraw.square(0+xshift,0,75);
		StdDraw.line(-75+xshift, -25, 75+xshift, -25);
		StdDraw.line(-75+xshift, 25, 75+xshift, 25);
		StdDraw.line(-25+xshift, -75, -25+xshift, 75);
		StdDraw.line(25+xshift, -75, 25+xshift, 75);
		StdDraw.text(-50+xshift, 50, String.valueOf(c00));
		StdDraw.text(0+xshift, 50, String.valueOf(c01));
		StdDraw.text(50+xshift, 50, String.valueOf(c02));
		StdDraw.text(-50+xshift, 0, String.valueOf(c10));
		StdDraw.text(0+xshift, 0, String.valueOf(c11));
		StdDraw.text(50+xshift, 0, String.valueOf(c12));
		StdDraw.text(-50+xshift, -50, String.valueOf(c20));
		StdDraw.text(0+xshift, -50, String.valueOf(c21));
		StdDraw.text(50+xshift, -50, String.valueOf(c22));
	}

	
		
	

}

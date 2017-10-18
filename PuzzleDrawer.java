import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PuzzleDrawer implements Runnable {
	private Thread t;
	private String Name;
	private String SourcePath;
	private BufferedReader Source;
	private 
	char c00;
	char c01;
	char c02;
	char c10;
	char c11;
	char c12;
	char c20;
	char c21;
	char c22;
	
	PuzzleDrawer(String nameOfDrawer, String Path) {
		Name = nameOfDrawer;
		SourcePath = Path;
		Source = getReader(Path);
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
		StdDraw.setScale(-100, 100);
		Font font = new Font("Sans Sarif", Font.BOLD, 40);
		StdDraw.setFont(font);
		
		try {
			while(Source.readLine() != null) {
				ClearVariables();
				StdDraw.clear();
				StdDraw.square(0,0,75);
				StdDraw.line(-75, -25, 75, -25);
				StdDraw.line(-75, 25, 75, 25);
				StdDraw.line(-25, -75, -25, 75);
				StdDraw.line(25, -75, 25, 75);
				String nextNode = StdIn.readLine();
				System.out.println(nextNode);
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
					StdDraw.setPenColor(StdDraw.RED);
					WriteValues();
					StdDraw.show();
					StdDraw.pause(20);
				}
				
				if(nextNode.length() > 13) {
					c20 = nextNode.charAt(14);
					c21 = nextNode.charAt(15);
					c22 = nextNode.charAt(16);
				}else {
					StdDraw.text(-50, 50, String.valueOf(c00));
					StdDraw.text(0, 50, String.valueOf(c01));
					StdDraw.text(50, 50, String.valueOf(c02));
					StdDraw.setPenColor(StdDraw.ORANGE);
					StdDraw.text(-50, 0, String.valueOf(c10));
					StdDraw.text(0, 0, String.valueOf(c11));
					StdDraw.text(50, 0, String.valueOf(c12));
					StdDraw.show();
					StdDraw.pause(20);
				}
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.clear();
				WriteValues();
				StdDraw.show();
				StdDraw.pause(5);
			}
		}catch(IOException NoLine){
			System.out.println("No more nodes given to check");
		}
		
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.GREEN);
		WriteValues();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.square(0,0,75);
		StdDraw.line(-75, -25, 75, -25);
		StdDraw.line(-75, 25, 75, 25);
		StdDraw.line(-25, -75, -25, 75);
		StdDraw.line(25, -75, 25, 75);
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
		StdDraw.square(0,0,75);
		StdDraw.line(-75, -25, 75, -25);
		StdDraw.line(-75, 25, 75, 25);
		StdDraw.line(-25, -75, -25, 75);
		StdDraw.line(25, -75, 25, 75);
		StdDraw.text(-50, 50, String.valueOf(c00));
		StdDraw.text(0, 50, String.valueOf(c01));
		StdDraw.text(50, 50, String.valueOf(c02));
		StdDraw.text(-50, 0, String.valueOf(c10));
		StdDraw.text(0, 0, String.valueOf(c11));
		StdDraw.text(50, 0, String.valueOf(c12));
		StdDraw.text(-50, -50, String.valueOf(c20));
		StdDraw.text(0, -50, String.valueOf(c21));
		StdDraw.text(50, -50, String.valueOf(c22));
	}

	
		
	

}

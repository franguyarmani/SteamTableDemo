import java.awt.Font;

public class PuzzleDrawer {
	static char c00;
	static char c01;
	static char c02;
	static char c10;
	static char c11;
	static char c12;
	static char c20;
	static char c21;
	static char c22;
	public static void main(String[] args) {
		
		//setup
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-100, 100);
		Font font = new Font("Sans Sarif", Font.BOLD, 40);
		StdDraw.setFont(font);
		
		while(StdIn.hasNextLine()) {
			ClearVariables();
			StdDraw.clear();
			StdDraw.square(0,0,75);
			StdDraw.line(-75, -25, 75, -25);
			StdDraw.line(-75, 25, 75, 25);
			StdDraw.line(-25, -75, -25, 75);
			StdDraw.line(25, -75, 25, 75);
			
			String nextNode = StdIn.readLine();
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
	public static void ClearVariables() {
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
	public static void WriteValues() {
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

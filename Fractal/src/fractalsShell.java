import java.awt.*;
import java.lang.*;

class fractalsShells {
	public static void main(String[] args) {
		//This is the code used to create the image you see on the panel.
		
		DrawingPanel panel = new DrawingPanel(900,900); //The Canvas
			panel.setBackground(Color.BLACK);
		Graphics g = panel.getGraphics();//Create the paintbrush
		
		//Everything else in main is for adding aspects to the canvas
		
		g.setColor(Color.RED); //Setting the paintbrush to the color red
		g.drawString("Hello, world!", 20, 50); //create a RED string onto the canvas
		
		g.setColor(Color.BLACK);//Setting the paintbrush to the color BLACK
		
		//Now I need to assign the points of my equilateral triangle.
		Point bottomLeft = new Point(150, 750);
		Point topMiddle = new Point(450, 150);
		Point bottomRight = new Point(750, 750);

		//This calls the draw triangle method and it draws the initial triangle
		drawTriangle(g, bottomLeft, topMiddle, bottomRight, 0, 0);
		recur(g, bottomLeft, topMiddle, bottomRight, 0, 5);
	}

	//this method draws a triangle with the three points p1 p2 and p3
	public static void drawTriangle(Graphics g, Point p1, Point p2, Point p3, int over, int up)
	{
		/* 
			Since the Graphics class doesn't contain a drawTriangle method,
			I decided to create my own using the Polygon class, which will
			basically trace the shape starting from the first point to the
			next point and so on and so on. It will then close the shape by
			tracing from the last point to the first point.
		*/

		//If you want to create a custom shape, edit this function to fit the
			//your design.
		
		Polygon p = new Polygon();
		p.addPoint(p1.x, p1.y);
		p.addPoint(p2.x, p2.y);
		p.addPoint(p3.x, p3.y);
		g.drawPolygon(p);
		//or you can use:   g.drawPolygon(p);
		
  	}
  	//this method finds the midpoint between 2 points on the triangle
  	public static Point midpoint(Point p1, Point p2)
  	{
  		//This method should give us the midpoint of 2 coordinates
		  	//Use this site as a guide: https://www.mathsisfun.com/algebra/line-midpoint.html
  		
  		Point mid = new Point();
  		mid.x = (p1.x + p2.x)/2;
  		mid.y = (p1.y + p2.y)/2;
  		
  		return mid;
  	}
  	
  	//this method recurs y times and it is used to recursively draw triangles using p1, p2, p3, 
	public static void recur(Graphics g, Point p1, Point p2, Point p3, int x, int y)
	{
		//this sets the color of the triangle to a random color
		
		int ov = x * ((p2.x - p1.x)/20);
		int up = x * ((p2.y - p1.y)/20);
		g.setColor(Color.WHITE);
		//this calls the drawTriangle method in order to draw a triangle
		drawTriangle(g, midpoint(p1, p3), midpoint(p1, p2), midpoint(p2, p3), ov, up);
		//this is the base case for the recur method and it will stop recurring when x becomes equal to y
		if(x < y) {
			//these call the recur method in order to recursively draw new triangles adjacent to the 3 sides of the previous triangle
			recur(g, midpoint(p2, p3), midpoint(p1, p2), midpoint(p1, p3), x + 1, y);
		}	
	}
}
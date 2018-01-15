import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 *  Class to display a Triangle
 *
 *  @version 2015-11-18
 *  @author Manfred Kerber
 */
public class Triangle extends JPanel{


    @Override
    /**
     *    Override the paintComponent method from the JPanel class.  
     *    A Triangle is put on the panel. 
     *    @param g The graphics component used for painting.
     */
    public void paintComponent(Graphics h){
	int[] xPoints = {100, 100, 150};
	int[] yPoints = {100, 200, 150};
    int vertices = 3;

	/* A polyline is like a polygon, except that the last and the
	 * first point are not automatically connected.
	 */
        //g.drawPolyline(xPoints,yPoints,vertices);
    h.fillPolygon(xPoints, yPoints, vertices);
    //h.drawLine(400, 200, 200, 200);
    //h.drawLine(10, 10, 100, 10);
    h.drawLine(10, 10, 10, 100);
    h.fillRect(10, 10, 10, 100);
    }

    /*
     *  We create a frame with a panel on which we draw all the objects above.
     */
    public static void main(String[] args) { 
        Triangle panel = new Triangle();
        JFrame frame = new JFrame();
	
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final int FRAME_WIDTH = 800;
        final int FRAME_HEIGHT = 600;

        //creates a window of size 800 x 600 pixels
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT); 
        frame.add(panel); 
        frame.setVisible(true); // makes the application visible.
    }
}




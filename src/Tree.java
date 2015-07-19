import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * 
 * @author Vadim Reutskiy
 * Innopolis University
 * Summer School 2015
 *
 */

public class Tree {
	int x;
	int y;
	double angle;
	double angleAlpha;
	int depthAlpha;
	int depthMax;
	int depth;
	Color trunkColor;
	Color berryColor;
	
	
	public Tree(int x, int y, double angle, int depth) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.depth = depth;
		
		this.depthAlpha = 1;
		this.depthMax = 9;
		this.angleAlpha = 5.0;
		
		trunkColor = Color.BLACK;
		berryColor = Color.BLUE;
	}
	
	private void draw(Graphics surface, int x, int y, double angle, int depth) {
        if (depth == 0) {
       	
        	surface.setColor(berryColor);
        	surface.drawOval(x, y, 5, 5);
        	
        	return;
        }
        
        int x2 = x + (int) (Math.cos(Math.toRadians(angle)) * depth * 10.0);
        int y2 = y + (int) (Math.sin(Math.toRadians(angle)) * depth * 10.0);
        
        surface.setColor(this.trunkColor);
        surface.drawLine(x, y, x2, y2);
                
        draw(surface, x2, y2, angle - 20, depth - 1);
        draw(surface, x2, y2, angle + 20, depth - 1);
	}
	
    public void draw(Graphics surface) {
        draw(surface, this.x, this.y, this.angle, this.depth);
        
        angle += 5;
        
        depth += depthAlpha;
        
        if (depth > depthMax || depth < 1) {
        	depthAlpha = -depthAlpha;
        	if (depth == 0) {
        		Random random = new Random();
        		berryColor = Color.getHSBColor(random.nextFloat(), random.nextFloat(), 0.7f);
        	}
        }
    }
    
    public void setPosition(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
    public void setAngle(double angle) {
    	this.angle = angle;
    }
    
    public void setDepth(int depth) {
    	this.depth = depth;
    }
}

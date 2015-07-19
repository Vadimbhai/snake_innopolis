import java.awt.Graphics;

/**
 * 
 * @author Vadim Reutskiy
 * Innopolis University
 * Summer School 2015
 *
 */

public class DrawGraphics {
    private Snake snake;
    private Map map;
    private static int mapWidth = 30;
    private static int mapHeight = 30;
    
    
    /** Initializes this class for drawing. */
    public DrawGraphics() {
    	map = new Map(mapWidth, mapHeight);
    	snake = new Snake(map, 15, 15, 5);
    	
    	map.addPlayObject(snake);
    	
    	map.addFood();
    	
    	map.addObstacles(20);
    }
    
    public void setMoveVector(int deltaX, int deltaY) {
    	snake.setMovementVector(deltaX, deltaY);
    }

    /** Draw the contents of the window on surface. Called 20 times per second. */
    public void draw(Graphics surface) {
    	map.draw(surface);
    }
    
    
}
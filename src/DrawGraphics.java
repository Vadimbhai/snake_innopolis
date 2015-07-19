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
    	
    	SnakeBot snakeBot = new SnakeBot(map, 20, 20, 15);
    	map.addPlayObject(snakeBot);
    	
    	snakeBot = new SnakeBot(map, 25, 20, 15);
    	map.addPlayObject(snakeBot);    	
    	    	
    	snakeBot = new SnakeBot(map, 10, 20, 15);
    	map.addPlayObject(snakeBot);    	
    	
    	map.addFood();
    	
    	map.addObstacles(5);
    }
    
    public void setMoveVector(int deltaX, int deltaY) {
    	snake.setMovementVector(deltaX, deltaY);
    }

    /** Draw the contents of the window on surface. Called 20 times per second. */
    public void draw(Graphics surface) {
    	map.draw(surface);
    }
    
    
}
import java.awt.Graphics;
import java.util.Random;


/**
 * 
 * @author Vadim Reutskiy
 * Innopolis University
 * Summer School 2015
 *
 */

public class DrawGraphics {
    private Snake snake;
    private World world;
    private static int mapWidth = 30;
    private static int mapHeight = 30;
    
    
    /** Initializes this class for drawing. */
    public DrawGraphics() {
    	world = new World(mapWidth, mapHeight);
    	snake = new Snake(world, 15, 15, 5);
    	world.addPlayObject(snake);
    	
    	addEaterBot();  	
    	addHunterBot();
    	
    	world.addFood();
    	
    	world.addObstacles(5);
    }
    
    public void setMoveVector(int deltaX, int deltaY) {
    	snake.setMovementVector(deltaX, deltaY);
    }
    
    public void addEaterBot() {
    	Random random = new Random();
    	SnakeBot snakeBot = 
    			new SnakeBot(world, random.nextInt(mapWidth), random.nextInt(mapHeight), 3, SnakeBot.BrainType.EATER);
    	world.addPlayObject(snakeBot); 
    }
    
    public void addHunterBot() {
    	Random random = new Random();
    	SnakeBot snakeBot = 
    			new SnakeBot(world, random.nextInt(mapWidth), random.nextInt(mapHeight), 3, SnakeBot.BrainType.HUNTER);
    	world.addPlayObject(snakeBot); 
    }

    /** Draw the contents of the window on surface. Called 20 times per second. */
    public void draw(Graphics surface) {
    	world.draw(surface);
    }
    
    
}
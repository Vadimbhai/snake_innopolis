import java.awt.Graphics;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * 
 * @author Vadim Reutskiy
 * Innopolis University
 * Summer School 2015
 *
 */

public class World {
	private ArrayList<Tile> tiles;
	private ArrayList<PlayObject> playObjects;
	private int mapWidth;
	private int mapHeight;

	
	public World(int width, int height) {
		if (width > 100 || width < 1 || height > 100 || height < 1) {
			throw new InvalidParameterException("Map must be between 1x1 and 100x100");
		}
		
		tiles = new ArrayList<Tile>();
		
		mapWidth = width;
		mapHeight = height;
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				tiles.add(new Tile(i, j));
			}
		}
		
		playObjects = new ArrayList<PlayObject>();
	}
	
	public void addPlayObject(PlayObject playObject) {
		this.playObjects.add(playObject);
	}
	
	public void removePlayObject(PlayObject playObject) {
		playObject.setJunk();
	}
	
	private void checkOverboard() {
		for (PlayObject playObject : playObjects) {
			for (Tile tile : playObject.getTiles()) {
				if(tile.getX() > mapWidth - 1) {
					tile.setX(0);
				}
				
				if(tile.getX() < 0) {
					tile.setX(mapWidth - 1);
				}
				
				if(tile.getY() > mapHeight - 1) {
					tile.setY(0);
				}
				
				if(tile.getY() < 0) {
					tile.setY(mapHeight - 1);
				}
			}
		}
	}
	
	public PlayObject getObjectByCoord(int x, int y) {
		for (PlayObject playObject : playObjects) {
			for (Tile tile : playObject.getTiles()) {
				if (tile.getX() == x && tile.getY() == y) {
					return playObject;
				}
			}
		}
		
		return null;
	}
	
	public void addFood() {
		Random random = new Random();
		int x;
		int y;
		do {
			x = random.nextInt(mapWidth);
			y = random.nextInt(mapWidth);
		} while (getObjectByCoord(x, y) != null);
    	Food food = new Food(x, y);
    	this.addPlayObject(food);
	}
	
	public ArrayList<PlayObject> getFood() {
		ArrayList<PlayObject> foodList = new ArrayList<PlayObject>();
		
		for (PlayObject playObject : playObjects) {
			if(playObject.isEatable()) {
				foodList.add(playObject);
			}
		}
		
		return foodList;
	}
	
	public ArrayList<PlayObject> getSnakes() {
		ArrayList<PlayObject> snakeList = new ArrayList<PlayObject>();
		
		for (PlayObject playObject : playObjects) {
			if(playObject.getClass() == Snake.class) {
				snakeList.add(playObject);
			}
		}
		
		return snakeList;
	}
	
	public void addObstacles(int count) {
		Random random = new Random();
		int x;
		int y;

		
		for (int i = 0; i < count; i++) {
			do {
				x = random.nextInt(mapWidth);
				y = random.nextInt(mapWidth);
			} while (getObjectByCoord(x, y) != null);
			
			Obstacle obstacle = new Obstacle(x, y);
			this.addPlayObject(obstacle);
		}
	}
	
	public void draw(Graphics surface) {
		for (Tile tile : tiles) {
			tile.draw(surface);
		}
		
		boolean needFood = false;
		for (Iterator<PlayObject> iterator = playObjects.iterator(); iterator.hasNext();) {
			PlayObject playObject = (PlayObject) iterator.next();
			if (playObject.isJunk()) {
				if (playObject.isEatable()) {
					needFood = true;
				} else if (playObject.getClass() == Snake.class) {
					System.exit(0);
				}
				iterator.remove();
				continue;
			}
			playObject.draw(surface);
		}
		
		if(needFood) {
			addFood();
		}
	
		checkOverboard();
	}
}

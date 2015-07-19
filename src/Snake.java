import java.awt.Graphics;


/**
 * 
 * @author Vadim Reutskiy
 * Innopolis University
 * Summer School 2015
 *
 */

public class Snake extends PlayObject{
	private Map world;
	private int deltaX;
	private int deltaY;
	private int length;
	private int speed;
	private int drawCounter;
	private static int drawPerSecond = 20;
	
	
	public Snake(Map map, int x, int y, int length) {
		this.world = map;
		this.deltaX = 1;
		this.deltaY = 0;
		this.speed = 3;
		this.length = length;
		
		for (int i = 0; i < length; i++) {
			Tile tile = new Tile(x--, y);
			tile.setDeadful(true);
			tiles.add(tile);
		}
	}
	
	public void setMovementVector(int x, int y) {
		if (x == -deltaX || y == -deltaY) {
			return;
		}
		
		if (world.getObjectByCoord(tiles.get(0).getX() + x, tiles.get(0).getY() + y) == this) {
			return;
		}
		
		deltaX = x;
		deltaY = y;
	}
	
	private void move() {
		drawCounter++;
		
		if (drawCounter > (drawPerSecond - (3 * speed))) {
			drawCounter = 0;
			
			int nextX = tiles.get(0).getX() + deltaX;
			int nextY = tiles.get(0).getY() + deltaY;
			
			PlayObject aheadObject = world.getObjectByCoord(nextX, nextY);
			if (aheadObject != null) {
				if (aheadObject.getTiles().get(0).isEatable) {
					increaseLength();
					world.removePlayObject(aheadObject);
				} else if (aheadObject.getTiles().get(0).isDeadful) {
					world.removePlayObject(this);
				}
			}
			
			nextX = tiles.get(0).setX(nextX);
			nextY = tiles.get(0).setY(nextY);
			for (int i = 1; i < tiles.size(); i++) {
				nextX = tiles.get(i).setX(nextX);
				nextY = tiles.get(i).setY(nextY);
			}
		}
	}
	
	@Override
	public void draw(Graphics surface) {
		move();
		
		super.draw(surface);
	}
	
	public void increaseLength() {
		this.length++;
		
		this.speed = length / 3;
		
		Tile tile = new Tile(tiles.get(tiles.size() - 1).getX(), tiles.get(tiles.size() - 1).getY());
		tile.setDeadful(true);
		tiles.add(tile);
	}
	
	public void decreaseLength() {
		this.length--;
		this.tiles.remove(this.tiles.size() - 1);
	}
	
}

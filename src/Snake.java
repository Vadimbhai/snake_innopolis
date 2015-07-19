import java.awt.Color;
import java.awt.Graphics;


/**
 * 
 * @author Vadim Reutskiy
 * Innopolis University
 * Summer School 2015
 *
 */

public class Snake extends PlayObject {
	private static int drawPerSecond = 20;
	protected Color bodyColor = Color.ORANGE;
	private Map world;
	private int deltaX;
	private int deltaY;
	private int length;
	protected int speed;
	private int drawCounter;
	
	
	public Snake(Map map, int x, int y, int length) {
		this.world = map;
		this.deltaX = 1;
		this.deltaY = 0;
		this.speed = 3;
		this.length = length;
		
		for (int i = 0; i < length; i++) {
			addBodyTile(x--, y);
		}
	}
	
	private void addBodyTile(int x, int y) {
		Tile tile = new Tile(x, y);
		tile.setDeadful(true);
		tile.setColor(bodyColor);
		tiles.add(tile);
	}
	
	public void decreaseLength() {
		this.length--;
		this.tiles.remove(this.tiles.size() - 1);
	}
	
	@Override
	public void draw(Graphics surface) {
		move();
		
		super.draw(surface);
	}
	
	public void increaseLength() {
		this.length++;
		
		this.speed = length / 3;
		
		addBodyTile(tiles.get(tiles.size() - 1).getX(), tiles.get(tiles.size() - 1).getY());
	}
	
	protected PlayObject getAneadObject() {
		int nextX = tiles.get(0).getX() + deltaX;
		int nextY = tiles.get(0).getY() + deltaY;
		
		PlayObject aheadObject = world.getObjectByCoord(nextX, nextY);
		
		return aheadObject;
	}

	protected void move() {
		drawCounter++;
		
		if (isTimeToMove()) {
			int nextX = tiles.get(0).getX() + deltaX;
			int nextY = tiles.get(0).getY() + deltaY;
			
			PlayObject aheadObject = getAneadObject();
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

	private boolean isTimeToMove() {
		if (drawCounter > (drawPerSecond - (3 * speed))) {
			drawCounter = 0;
			return true;
		}
		
		return false;
	}
	
	public void setMovementVector(int x, int y) {
		if (x == -deltaX || y == -deltaY) {
			return;
		}
		
		PlayObject aheadObject = (world.getObjectByCoord(tiles.get(0).getX() + x, tiles.get(0).getY() + y));
		if (aheadObject != null &&
				aheadObject.isDeadful()) {
			return;
		}
		
		deltaX = x;
		deltaY = y;
	}
	
	protected void setBodyColor(Color bodyColor) {
		this.bodyColor = bodyColor;
		for (Tile tile : tiles) {
			tile.setColor(bodyColor);
		}
	}
	
}

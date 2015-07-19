import java.awt.Graphics;
import java.util.ArrayList;


public class PlayObject {
	protected ArrayList<Tile> tiles;
	private boolean isJunk;
	
	public PlayObject() {
		isJunk = false;
		tiles = new ArrayList<Tile>();
	}
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public void draw(Graphics surface) {
		for (Tile tile : tiles) {
			tile.draw(surface);
		}
	}
	
	public void setJunk() {
		isJunk = true;
	}
	
	public boolean isJunk() {
		return isJunk;
	}
	
	public boolean isDeadful() {
		return tiles.get(0).isDeadful();
	}
	
}

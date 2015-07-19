/**
 * 
 * @author Vadim Reutskiy
 * Innopolis University
 * Summer School 2015
 *
 */

public class Obstacle extends PlayObject {
	public Obstacle(int x, int y) {
		Tile tile = new Tile(x, y);
		tile.setDeadful(true);
		tiles.add(tile);
	}
}

import java.awt.Color;

/**
 * 
 * @author Vadim Reutskiy
 * Innopolis University
 * Summer School 2015
 *
 */

public class SnakeBot extends Snake {
	BotBrains brains;

	public SnakeBot(Map map, int x, int y, int length) {
		super(map, x, y, length);
		
		setBodyColor(Color.MAGENTA);
		
		brains = new BotBrains(map, this);
		brains.start();
	}
	
	public PlayObject getAheadObject() {
		return super.getAneadObject();
	}
	
	@Override
	protected void move() {
		brains.think();
		super.move();
	}

}

import java.awt.Color;

/**
 * 
 * @author Vadim Reutskiy
 * Innopolis University
 * Summer School 2015
 *
 */

public class SnakeBot extends Snake {
	public enum BrainType {STUPID, EATER, HUNTER};
	
	StupidBrains brains;

	public SnakeBot(World map, int x, int y, int length, BrainType brainType) {
		super(map, x, y, length);

		switch (brainType) {
		case EATER:
			setBodyColor(Color.CYAN);
			brains = new EaterBrains(map, this);
			break;
			
		case HUNTER:
			setBodyColor(new Color(33, 0, 127));
			brains = new HunterBrains(map, this);
			break;

		default:
			setBodyColor(Color.MAGENTA);
			brains = new StupidBrains(map, this);
			break;
		}
		
		brains.start();
	}
	
	public PlayObject aheadObject() {
		return super.getAheadObject();
	}
	
	@Override
	protected void move() {
		brains.think();
		super.move();
	}

}

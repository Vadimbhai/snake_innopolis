
import java.util.ArrayList;

/**
 * 
 * @author Vadim Reutskiy
 * Innopolis University
 * Summer School 2015
 *
 */
public class HunterBrains extends StupidBrains {

	public HunterBrains(World world, SnakeBot snake) {
		super(world, snake);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void decideMovement() {
		ArrayList<PlayObject> snakeList = world.getSnakes();
		
		if (snakeList.size() == 0) {
			return;
		}
		
		moveToClosestObject(snakeList);
	}
}

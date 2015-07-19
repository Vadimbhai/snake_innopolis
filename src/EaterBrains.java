import java.util.ArrayList;

/**
 * 
 * @author Vadim Reutskiy
 * Innopolis University
 * Summer School 2015
 *
 */

public class EaterBrains extends StupidBrains {

	public EaterBrains(World world, SnakeBot snake) {
		super(world, snake);
	}
	
	@Override
	protected void decideMovement() {
		ArrayList<PlayObject> foodList = world.getFood();
		
		if (foodList.size() == 0) {
			return;
		}
		
		moveToClosestObject(foodList);
	}


}

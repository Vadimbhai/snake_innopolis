import java.util.ArrayList;

/**
 * 
 * @author Vadim Reutskiy
 * Innopolis University
 * Summer School 2015
 *
 */

public class EaterBrains extends StupidBrains {

	public EaterBrains(Map world, SnakeBot snake) {
		super(world, snake);
	}
	
	protected double getDistanceToObject(PlayObject object) {
		double distance =
				Math.sqrt((snake.tiles.get(0).getX() + object.tiles.get(0).getX()) * (snake.tiles.get(0).getX() + object.tiles.get(0).getX()) + 
						(snake.tiles.get(0).getY() + object.tiles.get(0).getY()) * (snake.tiles.get(0).getY() + object.tiles.get(0).getY()));
		
		return distance;				
	}
	
	@Override
	protected void decideMovement() {
		ArrayList<PlayObject> food = world.getFood();
		
		PlayObject nearestFood = food.get(0);
		double minDistance = 0.0;
		for (PlayObject currentFood : food) {
			double distance = getDistanceToObject(currentFood);
			if (minDistance == 0.0 ||
					minDistance > distance) {
				minDistance = distance;
				nearestFood = currentFood;
			}
		}
		
		System.out.println("Nearest food at " + nearestFood.tiles.get(0).getX() + ";" + nearestFood.tiles.get(0).getY());
		
		int deltaX = nearestFood.tiles.get(0).getX() - snake.tiles.get(0).getX();
		int deltaY = nearestFood.tiles.get(0).getY() - snake.tiles.get(0).getY();
		if (Math.abs(deltaX) > Math.abs(deltaY)) {
			deltaX /= Math.abs(deltaX);
			deltaY = 0;
		} else if (deltaY != 0) {
			deltaX = 0;
			deltaY /= Math.abs(deltaY);
		} else {
			return;
		}
		
		System.out.println("Movemet vectro set " + deltaX + ";" + deltaY);
		
		
		snake.setMovementVector(deltaX, deltaY);
	}
}

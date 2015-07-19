import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Vadim Reutskiy
 * Innopolis University
 * Summer School 2015
 *
 */

public class StupidBrains extends Thread {
	protected SnakeBot snake;
	protected World world;
	private volatile boolean isDead;
	protected Object thinkLock;
	
	public StupidBrains(World world, SnakeBot snake) {
		this.world = world;
		this.snake = snake;
		
		this.thinkLock = new Object();
		
		isDead = false;
	}
	
	protected void decideMovement() {
		setRandomMovement();
	}

	protected double getDistanceToObject(PlayObject object) {
		double distance =
				Math.sqrt((snake.tiles.get(0).getX() + object.tiles.get(0).getX()) * (snake.tiles.get(0).getX() + object.tiles.get(0).getX()) + 
						(snake.tiles.get(0).getY() + object.tiles.get(0).getY()) * (snake.tiles.get(0).getY() + object.tiles.get(0).getY()));
		
		return distance;				
	}
	
	protected void moveToClosestObject(ArrayList<PlayObject> targetObjects) {
		PlayObject nearestFood = targetObjects.get(0);
		double minDistance = 0.0;
		for (PlayObject currentFood : targetObjects) {
			double distance = getDistanceToObject(currentFood);
			if (minDistance == 0.0 ||
					minDistance > distance) {
				minDistance = distance;
				nearestFood = currentFood;
			}
		}
		
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
		
		snake.setMovementVector(deltaX, deltaY);
	}
	
	@Override
	public void run() {
		synchronized (thinkLock) {
			while (!isDead) {
				try {
					thinkLock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				decideMovement();
				
				saveFromDeath();
			}
		}
	}
	
	private void saveFromDeath() {
		int iterationsCount = 0;
		while (true) {
			iterationsCount++;
			
			PlayObject aheadObject = snake.getAheadObject();
			if (aheadObject == null ||
					(aheadObject != null && 
					!aheadObject.isDeadful()) ||
					iterationsCount > 20) {
				break;
			}
			
			setRandomMovement();
		}
	}
	
	public void setDead() {
		this.isDead = true;
	}

	private void setRandomMovement() {
		Random random = new Random();

		switch (random.nextInt(4)) {
		case 0:
			snake.setMovementVector(1, 0);
			break;

		case 1:
			snake.setMovementVector(-1, 0);
			break;

		case 2:
			snake.setMovementVector(0, 1);
			break;

		case 3:
			snake.setMovementVector(0, -1);
			break;

		default:
			break;
		}
	}
	
	public void think() {
		synchronized (thinkLock) {
			thinkLock.notifyAll();
		}
	}
	
}

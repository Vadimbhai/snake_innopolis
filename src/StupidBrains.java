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
	protected Map world;
	private volatile boolean isDead;
	protected Object thinkLock;
	
	public StupidBrains(Map world, SnakeBot snake) {
		this.world = world;
		this.snake = snake;
		
		this.thinkLock = new Object();
		
		isDead = false;
	}
	
	protected void decideMovement() {
		setRandomMovement();
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
	
	private void saveFromDeath() {
		int iterationsCount = 0;
		while (true) {
			iterationsCount++;
			
			if (snake.getAheadObject() == null ||
					(snake.getAheadObject() != null && 
					!snake.getAheadObject().isDeadful()) ||
					iterationsCount > 20) {
				break;
			}
			
			setRandomMovement();
		}
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

	public void setDead() {
		this.isDead = true;
	}
	
	public void think() {
		synchronized (thinkLock) {
			thinkLock.notifyAll();
		}
	}
	
}

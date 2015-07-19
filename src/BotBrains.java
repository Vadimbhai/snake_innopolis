import java.util.Random;


public class BotBrains extends Thread {
	protected SnakeBot snake;
	protected Map world;
	private volatile boolean isDead;
	protected Object thinkLock;
	
	public BotBrains(Map world, SnakeBot snake) {
		this.world = world;
		this.snake = snake;
		
		this.thinkLock = new Object();
		
		isDead = false;
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
				
				int iterationsCount = 0;
				while (true) {
					Random random = new Random();

					switch (random.nextInt(20)) {
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
					
					iterationsCount++;
					
					if (snake.getAheadObject() == null ||
							(snake.getAheadObject() != null && 
							!snake.getAheadObject().isDeadful()) ||
							iterationsCount > 20) {
						break;
					}
				} 

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

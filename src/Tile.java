import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author Vadim Reutskiy
 * Innopolis University
 * Summer School 2015
 *
 */

public class Tile {
	protected int x;
	protected int y;
	protected boolean isDeadful;
	protected boolean isEatable;
	protected Color color;
	
	private int tileWidth = 20;
	private int tileHeight = 20;

	private int margin = 1;
	
	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.isDeadful = false;
		this.isEatable = false;
		
		this.color = Color.CYAN;
	}
	
	public void draw(Graphics surface) {
		if (isDeadful == false &&
				isEatable == false)
		{
			return;
		}
		
		draw(surface, color);
	}

	protected void draw(Graphics surface, Color color) {
		surface.setColor(color);
		surface.fillOval(
				x * tileWidth + margin, 
				y * tileHeight + margin, 
				tileWidth - margin * 2, 
				tileHeight - margin * 2);
//        surface.setColor(Color.BLACK);
//        ((Graphics2D) surface).setStroke(new BasicStroke(3.0f));
//        surface.drawRect(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isDeadful() {
		return isDeadful;
		
	}

	public boolean isEatable() {
		return isEatable;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setDeadful(boolean isDeadful) {
		if (isDeadful == true) {
			color = Color.BLACK;
		}
		this.isDeadful = isDeadful;
	}

	public void setEatable(boolean isEatable) {
		if (isEatable == true) {
			color = Color.GREEN;
		}
		this.isEatable = isEatable;
	}

	public int setX(int x) {
		int prevX = this.x;
		this.x = x;
		return prevX;
	}

	public int setY(int y) {
		int prevY = this.y;
		this.y = y;
		return prevY;
	}
}

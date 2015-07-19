import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Vadim Reutskiy
 * Innopolis University
 * Summer School 2015
 *
 */

/** Displays a window and delegates drawing to DrawGraphics. */
public class SnakeGame extends JPanel implements Runnable {
    private static final long serialVersionUID = -7469734580960165754L;
    private boolean animate = true;
    private final int FRAME_DELAY = 10; // 10 ms = 100 FPS
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private DrawGraphics draw;

    public SnakeGame(DrawGraphics drawer) {
        this.draw = drawer;
    }

    /** Paint callback from Swing. Draw graphics using g. */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Enable anti-aliasing for better looking graphics
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        draw.draw(g2);
    }

    /** Enables periodic repaint calls. */
    public synchronized void start() {
        animate = true;
    }

    /** Pauses animation. */
    public synchronized void stop() {
        animate = false;
    }

    private synchronized boolean animationEnabled() {
        return animate;
    }

    public void run() {
        while (true) {
            if (animationEnabled()) {
                repaint();
            }
            try {
                Thread.sleep(FRAME_DELAY);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public static void main(String args[]) {
        final SnakeGame content = new SnakeGame(new DrawGraphics());
        JFrame frame = new JFrame("Graphics!");
        Color bgColor = Color.white;
        frame.setBackground(bgColor);
        content.setBackground(bgColor);
        content.setSize(WIDTH, HEIGHT);
        content.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        content.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setSize(WIDTH, HEIGHT);
        frame.setContentPane(content);
        frame.setResizable(false);
        frame.pack();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            public void windowDeiconified(WindowEvent e) {
                content.start();
            }

            public void windowIconified(WindowEvent e) {
                content.stop();
            }
            

        });
        
        frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					content.draw.setMoveVector(0, -1);
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					content.draw.setMoveVector(0, 1);
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					content.draw.setMoveVector(-1, 0);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					content.draw.setMoveVector(1, 0);
				} else if (e.getKeyCode() == KeyEvent.VK_Z) {
					content.draw.addEaterBot();
				} else if (e.getKeyCode() == KeyEvent.VK_X) {
					content.draw.addHunterBot();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});

        new Thread(content).start();
        frame.setVisible(true);
    }

}
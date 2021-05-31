package baelle;
import java.awt.Dimension;
import java.awt.Graphics;

//import java.util.Set;
import java.lang.Thread;

import javax.swing.JPanel;

/**
 * ein hüpfender Ball
 * @author Doro
 *
 */
public class Ball{
	/**
	 * erstellt einen Ball, der in das angegebene Panel gezeichnet wird
	 * @param b Die Zeichenfläche
	 */
	public Ball(JPanel b) {
		box = b;
	}

	/**a
	 * zeichnet den Ball an seiner aktuellen Position
	 */
	public void draw() {
		Graphics g = box.getGraphics();
		g.fillOval(x, y, XSIZE, YSIZE);
		g.dispose();
	}
	
	/**
	 * löscht den Ball von der Oberfläche
	 */
	public void loeschen()
	{
		Graphics g = box.getGraphics();
		g.setXORMode(box.getBackground());
		g.fillOval(x, y, XSIZE, YSIZE);
		
	}

	/**
	 * bewegt den Ball einen Schritt weiter
	 */
	public void move() {
		if (!box.isVisible())
			return;
		Graphics g = box.getGraphics();
		g.setXORMode(box.getBackground());
		g.fillOval(x, y, XSIZE, YSIZE);
		x += dx;
		y += dy;
		Dimension d = box.getSize();
		if (x < 0) {
			x = 0;
			dx = -dx;
		}
		if (x + XSIZE >= d.width) {
			x = d.width - XSIZE;
			dx = -dx;
		}
		if (y < 0) {
			y = 0;
			dy = -dy;
		}
		if (y + YSIZE >= d.height) {
			y = d.height - YSIZE;
			dy = -dy;
		}
		g.fillOval(x, y, XSIZE, YSIZE);
		g.dispose();
	}
	
	/**
	 * bewegt den Ball dauer viele Schritte weiter in der Oberfläche. Um eine angenehme Animation
	 * zu erhalten, wird nach jedem Schritt eine Pause eingelegt.
	 * @param dauer Anzahl der Schritte
	 */
	public void huepfen(int dauer)
	{
		Runnable task = () -> {
			this.draw();

			for (int i = 1; i <= dauer; i++) {
				synchronized (this) {
					deleteBalls = false;
					if(interruptBalls) {
						try {
							this.wait();
						} catch (InterruptedException e) {}
					} else if(!interruptBalls) {
						try {
							this.move();
							Thread.sleep(15);
						} catch (InterruptedException e) {}
					} 
					if(deleteBalls) {
//						System.out.println("hier");
						this.loeschen();
						return;
					}				
				}
			}
		};
		thread = new Thread(task);
		thread.start();
		// this.loeschen();
	}
	


	public void stopBall() {
		interruptBalls = true;
		thread.interrupt();
	}
	
	public void continueBall() {
		interruptBalls = false;
		thread.interrupt();
	}
	
	public void deleteBall() {
		deleteBalls = true;
		thread.interrupt();
	}

	

	private Thread thread;
	private static boolean interruptBalls = false;
	private static boolean deleteBalls = false;
	private JPanel box;
	private static final int XSIZE = 10;
	private static final int YSIZE = 10;
	private int x = 0;
	private int y = 0;
	private int dx = 2;
	private int dy = 2;

	
	
//	public int getDx() {
//	return dx;
//}
//
//public void setDx(int dx) {
//	this.dx = dx;
//}
//
//public int getDy() {
//	return dy;
//}
//
//public void setDy(int dy) {
//	this.dy = dy;
//}
//



	
}
package Game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BasicTrail extends GameObject{

	private float alpha = 1;
	private Handler handler;
	private HUD hud;
	private Color color;
	private int width, height;
	private double life;
	
	public BasicTrail(int x, int y, ID id, Color color, Handler handler, HUD hud, double life, int width, int height) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.height = height;
		this.width = width;
		this.life = life;
	}

	public void tick() {
		if (alpha > life) {		
			alpha -= life - 0.05;
		} else {
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillRect(x,  y, width, height);
		g2d.setComposite(makeTransparent(1));
	}

	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}
	
	public Rectangle getBounds() {
		return null;
	}

}

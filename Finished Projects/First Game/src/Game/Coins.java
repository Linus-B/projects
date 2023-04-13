package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Coins extends GameObject{

	private Handler handler;
	private HUD hud;
	
	public Coins(int x, int y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, 8, 8);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 8, 8);
	}

}

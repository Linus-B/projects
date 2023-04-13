package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealthPack extends GameObject{

	private Handler handler;
	private HUD hud;
	private int LifeTime = 500;
	
	public HealthPack(int x, int y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
	}

	public void tick() {
		if (hud.getWin()) {
			LifeTime--;
			if (LifeTime <= 0) {
				handler.removeObject(this);
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 8, 8);
		g.setColor(Color.red);
		g.fillRect(x + 2, y, 4, 8);
		g.fillRect(x, y + 2, 8, 4);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 8, 8);
	}

	public int getLifeTime() {
		return LifeTime;
	}
	public void setLifeTime(int LifeTime) {
		this.LifeTime = LifeTime;
	}
	
}

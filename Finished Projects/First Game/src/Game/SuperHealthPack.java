package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SuperHealthPack extends GameObject{

	private Handler handler;
	private HUD hud;
	private int LifeTime = 400;
	
	public SuperHealthPack(int x, int y, ID id, Handler handler, HUD hud, int velX, int velY) {
		super(x, y, id);
		this.velX = velX;
		this.velY = velY;
		this.handler = handler;
		this.hud = hud;
	}

	public void tick() {
		if (LifeTime < 390) {
			if (x + 16 >= Game.WIDTH || x <= 0) {
				velX *= -1;
			}
			if (y + 32 >= Game.HEIGHT || y <= 0) {
				velY *= -1;
			}
			
			if (hud.getWin()) {
				if (LifeTime <= 0) {
					handler.removeObject(this);
				}
				x += velX;
				y += velY;
			}
		}
		LifeTime--;
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 16, 16);
		g.setColor(Color.red);
		g.fillRect(x + 4, y, 8, 16);
		g.fillRect(x, y + 4, 16, 8);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}
	
	public int getLifeTime() {
		return LifeTime;
	}
	public void setLifeTime(int LifeTime) {
		this.LifeTime = LifeTime;
	}
}

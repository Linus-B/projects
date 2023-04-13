package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{

	private Handler handler;
	private HUD hud;
	
	public Player(int x, int y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 32);
		y = Game.clamp(y,  0,  Game.HEIGHT - 54);
		
		collision();
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.BasicEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					hud.HEALTH -= 4;
				}
			}
			else if (tempObject.getID() == ID.Coin) {
				if (getBounds().intersects(tempObject.getBounds())) {
					hud.setCoinScore(hud.getCoinScore() + 200);
					handler.object.remove(i);
				}
			}
			else if (tempObject.getID() == ID.HealthPack) {
				if (getBounds().intersects(tempObject.getBounds())) {
					hud.setHEALTH(hud.getHEALTH() + 20);
					handler.object.remove(i);
				}
			}
			else if (tempObject.getID() == ID.SuperHealthPack) {
				if (getBounds().intersects(tempObject.getBounds())) {
					hud.HEALTH = 100;
					handler.object.remove(i);
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}
}

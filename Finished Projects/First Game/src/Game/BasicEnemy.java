package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BasicEnemy extends GameObject{

	private Handler handler;
	private Random r;
	private HUD hud;
	public BasicEnemy(int x, int y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.hud = hud;
		r = new Random();
		this.handler = handler;
		if (r.nextInt(2) == 1) {
			velX = -r.nextInt(5) - 3;
		}
		else {
			velX = r.nextInt(5) + 3;
		}
		
		if (r.nextInt(2) == 1) {
			velY = -r.nextInt(5) - 3;
		}
		else {
			velY = r.nextInt(5) + 3;
		}
		this.handler = handler;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

	public void tick() {
		if (hud.getWin()) {
			x += velX;
			y += velY;
		}
		
		if (x + 16 >= Game.WIDTH || x <= 0) {
			velX *= -1;
		}
		if (y + 32 >= Game.HEIGHT || y <= 0) {
			velY *= -1;
		}
		/*x = Game.Wrappingclamp(x, 0, Game.WIDTH - 16);
		y = Game.Wrappingclamp(y, 0, Game.HEIGHT - 20);*/
			
		handler.addObject(new BasicTrail(x, y, ID.Trail, Color.red, handler, hud, 0.2, 16, 16));
		
	}


	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 16, 16);
		
	}
	
	

}

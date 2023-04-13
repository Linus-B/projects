package Game;

import java.util.Random;

public class Spawner {

	private Handler handler;
	private HUD hud;
	private int level;
	private Random r;
	
	public Spawner(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		this.level = hud.getLevel();
		r = new Random();
	}
	
	public void tick() {
		if (hud.getWin()) {
			if (hud.getLevel() > level) {
				handler.addObject(new BasicEnemy(Game.WIDTH / 2  - 16, Game.HEIGHT / 2 - 16, ID.BasicEnemy, handler, hud));
				level = hud.getLevel();
			}
			else if (hud.getLevel() < level) {
				level = hud.getLevel();
			}
			
			if (r.nextInt(1500) == 0) {
				for (int i = 0; i < 10000; i++) {
					GameObject tempObject = handler.object.get(r.nextInt(handler.object.size()));
					if (tempObject.id == ID.BasicEnemy) {
						handler.addObject(new SuperHealthPack(tempObject.getX(), tempObject.getY(), ID.SuperHealthPack, handler, hud, tempObject.getVelX(), tempObject.getVelY()));
						break;
					}
				}
			}
			else if (r.nextInt(1000) == 0) {
				handler.addObject(new HealthPack(r.nextInt(Game.WIDTH - 8), r.nextInt(Game.HEIGHT - 8), ID.HealthPack, handler, hud));
			}
			else if (r.nextInt(700) == 0) {
				handler.addObject(new Coins(r.nextInt(Game.WIDTH - 8), r.nextInt(Game.HEIGHT - 8), ID.Coin, handler, hud));
			}
		}
	}
	
	
	
	
	
	
	
	
}

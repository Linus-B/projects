package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private HUD hud;
	public KeyInput(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getID() == ID.Player) {
				if (hud.getWin()) {
					if (key == KeyEvent.VK_W) {
						tempObject.SetVelY(-5);
					}
					if (key == KeyEvent.VK_S) {
						tempObject.SetVelY(5);
					}
					if (key == KeyEvent.VK_A) {
						tempObject.SetVelX(-5);
					}
					if (key == KeyEvent.VK_D) {
						tempObject.SetVelX(5);
					}
				}
			}
			
		}
		
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
		if (key == KeyEvent.VK_R) {
			hud.setScore(0);
			hud.setCoinScore(0);
			hud.setHEALTH(100);
			hud.setWin(true);
			hud.setLevel(1);
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.id != ID.Player) {
					handler.object.remove(i);
				}
			}
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.id != ID.Player) {
					handler.object.remove(i);
				}
			}
			handler.addObject(new BasicEnemy(Game.WIDTH / 2  - 16, Game.HEIGHT / 2 - 16, ID.BasicEnemy, handler, hud));
		}
		if (key == KeyEvent.VK_P) {
			if (hud.getWin()) {
				hud.setWin(false);
			}else {
				if (hud.getHEALTH() == 0) {
				}else {
					hud.setWin(true);
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getID() == ID.Player) {
				if (key == KeyEvent.VK_W) {
					tempObject.SetVelY(0);
				}
				if (key == KeyEvent.VK_S) {
					tempObject.SetVelY(0);
				}
				if (key == KeyEvent.VK_A) {
					tempObject.SetVelX(0);
				}
				if (key == KeyEvent.VK_D) {
					tempObject.SetVelX(0);
				}
			}
			
		}
	}
	
}

package Game;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public  int HEALTH = 100;
	private boolean Win = true;
	private int score = 0;
	private int level = 1;
	private int CoinScore = 0;
	
	public void tick() {
		if (HEALTH == 0) {
			Win = false;
		}
		if (Win) {
			score ++;
		}
		
		HEALTH = Game.clamp(HEALTH,  0,  100);
		
		if (score >= (500 * level)) {
			level += 1;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(16, 16, 200, 30);
		
		g.setColor(Color.red);
		g.fillRect(16, 16, 200, 30);
		g.setColor(Color.green);
		g.fillRect(16, 16, HEALTH * 2, 30);
		
		g.drawString("Score: " + (score + CoinScore), 15, 64);
		g.drawString("Level: " + level, 15, 80);
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	public void setHEALTH(int HEALTH) {
		this.HEALTH = HEALTH;
	}
	public int getHEALTH() {
		return HEALTH;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setWin(Boolean Win) {
		this.Win = Win;
	}
	public Boolean getWin() {
		return Win;
	}
	public int getCoinScore() {
		return CoinScore;
	}
	public void setCoinScore(int CoinScore) {
		this.CoinScore = CoinScore;
	}
	
}

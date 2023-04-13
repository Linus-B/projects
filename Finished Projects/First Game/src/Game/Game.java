package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1291256718606791229L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 4 * 3;
	
	private Thread thread;
	private boolean running = false;
	private Random r;
	
	private Handler handler;
	private HUD hud;
	private Spawner spawner;
	public Game() {
		
		r = new Random();
		handler = new Handler();
		hud = new HUD();
		spawner = new Spawner(handler, hud);
		this.addKeyListener(new KeyInput(handler, hud));
		new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);
		
		handler.addObject(new Player(WIDTH / 2  - 16, HEIGHT / 2 - 16, ID.Player, handler, hud));
		handler.addObject(new BasicEnemy(r.nextInt(HEIGHT), r.nextInt(HEIGHT), ID.BasicEnemy, handler, hud));
	
		
		
		
	}
	
	
	
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
				thread.join();
				running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
		spawner.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.blue);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	public static int clamp(int var, int min, int max){
		if (var >= max) {
			return var = max;
		}
		else if (var <= min) {
			return var = min;
		}
		else {
			return var;
		}
	}
	
	public static int Wrappingclamp(int var, int min, int max) {
		if (var >= max) {
			return var = min;
		}
		if (var <= min) {
			return var = max;
		}
		else {
			return var;
		}
	}
	
	public static void main(String args[]) {
		new Game();
	}
}

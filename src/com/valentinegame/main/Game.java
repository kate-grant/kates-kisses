package com.valentinegame.main;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	
	private static final long serialVersionUID = 1058701710870469691L;
	
	public static final int Width = 640, Height = Width / 12 * 9;
	
	private boolean running = false;
	private Thread thread;
	private Handler handler;
	private HUD hud;
	private Spawn spawn;
	private Menu menu;
	
	
	public static State gameState = State.Menu;
	public static boolean paused = false;
	public static boolean hard = true;
	
	static Random n = new Random();
	public static int num = n.nextInt(20) + 0;
	public int i = num;
	
	
	public Game() {
		hud = new HUD();
		handler = new Handler();
		menu = new Menu(handler, hud);
		spawn = new Spawn(hud, handler);
		
		// listeners for mouse and keyboard 
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);

		


		
		
		// game window
		
		new Window(Width, Height, "Happy Valentine's Day from Kate", this);
		
		if (gameState == State.Menu) {
			Random r = new Random();
			for(int i = 0; i < 50; i++) {
				handler.addObject(new MenuHearts(r.nextInt(Width), r.nextInt(Height), ID.MenuHearts, handler));
		
			}
		}
		
		
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
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/ amountOfTicks; //nsec per second
		double delta = 0;
		long timer = System.currentTimeMillis(); 
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns; //ntnow - ntlast / const(nsec/sec)
			lastTime = now; 
			while(delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		if (gameState == State.Game) {

				

					handler.tick();//for GameObjects
					hud.tick();
					spawn.tick();

					if (HUD.score > 300) {
						handler.clearEnemies1();
						handler.clearPlayer();				
						
						menu.tick();
						gameState = State.Valentine;
					
				}
			
		} else if (gameState == State.Valentine) {
			handler.tick();
			menu.tick();
			HUD.score = 300;
			HUD.HEALTH = 300;
			spawn.setKeepScore(0);
			
			
		
		} else if (gameState == State.Menu || gameState == State.GameOver) {
			handler.tick();
			menu.tick();
			HUD.HEALTH = 100;
			HUD.HEALTH = 100;
			spawn.setKeepScore(0); 
			
		}
			
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.red);
		g.fillRect(0,  0, Width, Height);
		
	
		handler.render(g);
		
		if (gameState == State.Game) {
			hud.render(g);
			
		} else if (gameState == State.Menu || gameState == State.Valentine || gameState == State.GameOver) 
			menu.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if (var <= min)
			return min;
		else if (var >= max)
			return max;
		else
			return var;
	}
	
	public static void main(String[] args) {
		
	       
		new Game();
	}

}

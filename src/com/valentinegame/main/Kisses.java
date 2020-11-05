package com.valentinegame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Kisses extends GameObject{

	private Handler handler;
	private Random r = new Random();
	
	public Kisses(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = r.nextInt(10);
		velY = r.nextInt(4) -2;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(y <=0 || y >= Game.Height-40)
			velY *= -1;		
		if(x <=0 || x >= Game.Width-20)
			velX *= -1;
		
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect(x, y, 32, 32);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 40, 30);
	}

}

	


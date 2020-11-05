package com.valentinegame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuHearts extends GameObject {

    private Handler handler;
	private Random r = new Random();

	public MenuHearts(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = r.nextInt(4) + 1;
		velY = r.nextInt(4);
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
		g.setColor(Color.white);
		int[] xp = { x, x + 40, x + 20 };
	    int[] yp = { y + 14, y + 14, y + 30 };
	    g.fillOval(x, y, 20, 20); //left circle
	    g.fillOval(x + 20, y, 20, 20); //to cover middle spot
	    g.fillOval(x + 13, y + 10, 10, 10); //right circle
	    g.fillPolygon(xp, yp, xp.length); //bottom triangle
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 20, 20);
	}
	
}


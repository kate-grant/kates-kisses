package com.valentinegame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Player extends GameObject {

	Handler handler;
	
	private int w = 32;
	private int h = 32;
	private Color color = Color.white;
	

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		
	}

	
	public void tick() {
		x += velX;
		y += velY;

		x = Game.clamp(x, 0, Game.Width - 38);
		y = Game.clamp(y, 0, Game.Height - 60);

		decollision();

	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, w, h);
	}

	private void decollision() {
		for (GameObject tempObj : handler.getObject()) {

			if (tempObj.getId() != ID.Player) { 
				
			
				if (getBounds() != null && tempObj.getBounds() != null) {
					
					if (getBounds().intersects(tempObj.getBounds())) {
						
						if(tempObj.getId() == ID.Kisses)
							HUD.score++;
							HUD.HEALTH++;
											
					}
				}
				
			}
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, w, h);
	}

}


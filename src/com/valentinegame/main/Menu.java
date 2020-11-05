package com.valentinegame.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random; 

public class Menu extends MouseAdapter {

	private Handler handler;
	private HUD hud;
	int num = Game.num;

	public Menu(Handler handler, HUD hud) {
		
		this.handler = handler;
		this.hud = hud;
		
	}

	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (Game.gameState == State.Menu) {

			//play button
			if (mouseOver(mx, my, Game.Width / 2 - 150, 120, 300, 70)) {
				Game.gameState = State.Game;
				
				handler.addObject(new Player(Game.WIDTH / 2 - 25, Game.HEIGHT / 2 - 25, ID.Player, handler));
				handler.clearEnemies();
				
				Random r = new Random();
				
				for(int i = 0; i <= 14; i++) {
			
					handler.addObject(new Kisses(r.nextInt(Game.Width), r.nextInt(Game.Height), ID.Kisses, handler));
				}
				
				
				
			}
			//exit
			else if (mouseOver(mx, my, Game.Width / 2 - 150, 200, 300, 70)) {
				System.exit(1);
			}
			
		}

		if (Game.gameState == State.Valentine) {
			handler.clearEnemies1();
			handler.clearPlayer();
			if (mouseOver(mx, my, Game.Width / 2 - 150, 360, 300, 70)) {
				Game.gameState = State.GameOver;
				hud.setLevel(1);
				hud.setScore(0);
				hud.setBounds(0);
				HUD.HEALTH = 100;
				hud.setMaxHealth(HUD.HEALTH);

			}
		}
		else if (Game.gameState == State.GameOver) {
			if (mouseOver(mx, my, Game.Width / 2 - 150, 360, 300, 70)) {
				Game.gameState = State.Menu;
				hud.setLevel(1);
				hud.setScore(0);
				hud.setBounds(0);
				HUD.HEALTH = 100;
				hud.setMaxHealth(HUD.HEALTH);
				if (Game.num < 19) {
				Game.num++;
					
				} else if (Game.num > 19) {
					Game.num--;
				}

			}
		}

	}

	public void tick() {

	}

	public void render(Graphics g) {
		Font big = new Font("Courier New", 1, 50);
		Font normal = new Font("Courier New", 1, 40);
		Font small = new Font("Courier New", 1, 25);
		g.setColor(Color.white);

		if (Game.gameState == State.Menu) {

			g.setFont(big);
			g.drawString("Kate's Kisses", Game.Width / 2 - 200, 75);

			g.setFont(normal);
			g.drawRect(Game.Width / 2 - 150, 120, 300, 70);
			g.drawString("Play", Game.Width / 2 - 40, 170);

			g.drawRect(Game.Width / 2 - 150, 200, 300, 70);
			g.drawString("Exit", Game.Width / 2 - 40, 250);

		} else if (Game.gameState == State.Valentine) {
	
				String [] loveYou = new String [20];
				
				loveYou[0] = "your smile makes me smile";
				loveYou[1] = "you give the best high fives";
				loveYou[2] = "you always have the right words";
				loveYou[3] = "you are a good friend";
				loveYou[4] = "you follow my cat on instagram";
				loveYou[5] = "you are snazzy";
				loveYou[6] = "you check in on me";
				loveYou[7] = "you are kind to everyone";
				loveYou[8] = "you try my experimental baking";
				loveYou[9] = "you send me cute cat photos";
				loveYou[10] = "you always makes time";
				loveYou[11] = "you share good reads";
				loveYou[12] = "you include me";
				loveYou[13] = "you share your joys";
				loveYou[14] = "you are a cutie";
				loveYou[15] = "you are always learning something new";
				loveYou[16] = "you make fire playlists";
				loveYou[17] = "you care about my success";
				loveYou[18] = "your love for what you do is contagious";
				loveYou[19] = "you make me feel cherished";
				
				int i = Game.num;
				
				g.setColor(Color.pink);
				g.setFont(small);
				String s = loveYou[i];
				g.drawString(s, Game.Width / 2 - 300, 175);
				
				g.setColor(Color.pink);
				g.setFont(small);
				g.drawString("(play to read more)", Game.Width / 2 - 150, 260);
				
				
		
			g.setFont(normal);
			g.setColor(Color.white);
			g.drawRect(Game.Width / 2 - 150, 360, 300, 70);
			g.drawString("  -->", Game.Width / 2 - 90, 410);

		
			g.setColor(Color.pink);
			g.setFont(small);
			g.drawString("I love you because", Game.Width / 2 - 300, 30 + 30 + 30);

			g.setColor(Color.white);
			int x = Game.Width / 2 - 20;
			int y = 300;
			int[] xp = { x, x + 40, x + 20 };
		    int[] yp = { y + 14, y + 14, y + 30 };
		    g.fillOval(x, y, 20, 20); //left-circle
		    g.fillOval(x + 20, y, 20, 20); //middle
		    g.fillOval(x + 13, y + 10, 10, 10); //right-circle
		    g.fillPolygon(xp, yp, xp.length); //bottom-triangle
			
		} else if (Game.gameState == State.GameOver) {
			g.setColor(Color.white);
			g.setFont(big);
			g.drawString("Happy Valentines Day", Game.Width / 2 - 300, 150);

			g.setFont(normal);
			g.drawString("xoxo", Game.Width / 2 - 175, 250);
			g.drawString("Kate ", Game.Width / 2 - 160, 285);

			g.setFont(normal);
			g.drawRect(Game.Width / 2 - 150, 360, 300, 70);
			g.drawString("Play again", Game.Width / 2 - 120, 410);

		}
		
	}

	public boolean mouseOver(int mx, int my, int x, int y, int Width, int height) {
		if (mx > x && mx < x + Width)
			if (my > y && my < y + height)
				return true;
		return false;
	}

}

package com.valentinegame.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	private int bounds = 0;
	public static int HEALTH = 0;
	private int maxScore = 500;
	private int greenValue = 100;
	private int redValue = 0;
	public static int score = 0;
	private int level = 1;
	
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 140 + bounds);	

		
		greenValue = (HEALTH * 255)/maxScore;
		greenValue = Game.clamp(greenValue, 0, 255);
		
		redValue = Math.abs(greenValue -255);		
		redValue = Game.clamp(redValue, 0, 255);
		
		
		
	}
	
	public void render(Graphics g) {
		if (Game.gameState == State.Game) {
		g.setColor(Color.white);
		
		if (score <= 300)
		g.fillRect(15, 15, (int)score*2, 35);
		else
			g.fillRect(15, 15, 600, 35);	
		
		g.setColor(Color.white);
		
		if (score < 300)
			g.drawString("Score: " + (int)score, 15, 68);
		else if (score >= 300)
			g.drawString("Score: " + "300", 15, 68);
		g.drawString("Level: " + "LOVE", 15, 84);
		if (score < 20)
		g.drawString("Use arrow keys to get kisses", Game.Width/2 - 80, Game.Height/2);
		
		}
	}


	public float getScore() {
		return score;
	}

	public void setScore(int score) {
		if (score <= 500)
		HUD.score = score;
		else
			score = 500;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBounds() {
		return bounds;
	}

	public void setBounds(int bounds) {
		this.bounds = bounds;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxHealth(int maxScore) {
		this.maxScore = maxScore;
	}
	

}
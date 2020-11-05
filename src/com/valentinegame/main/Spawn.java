package com.valentinegame.main;

import java.util.Random;

public class Spawn {

	private HUD hud;
	private Handler handler;
	private Random random;
	private int limit = 100;

	private int keepScore = 0;

	public Spawn(HUD hud, Handler handler) {
		this.hud = hud;
		this.handler = handler;

		random = new Random();

	}

	public void tick() {

		if (keepScore <= limit) {

					limit = 14;
				
				if (keepScore == limit) 
				handler.clearEnemies();
			    
		}

	}

	public void setKeepScore(int keepScore) {
		this.keepScore = keepScore;
	}

}
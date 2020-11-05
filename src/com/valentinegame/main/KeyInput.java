package com.valentinegame.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private int speed = 4;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		
		// move player
		for (int i = 0; i < handler.getObject().size(); i++) {
			GameObject tempObject = handler.getObject().get(i);

			if (tempObject.getId() == ID.Player) {
				
				// key events for player

				if (key == KeyEvent.VK_UP)
					tempObject.setVelY(tempObject.getVelY() - handler.getSpeed());
				if (key == KeyEvent.VK_DOWN)
					tempObject.setVelY(tempObject.getVelY() + handler.getSpeed());
				if (key == KeyEvent.VK_LEFT)
					tempObject.setVelX(tempObject.getVelX() - handler.getSpeed());
				if (key == KeyEvent.VK_RIGHT)
					tempObject.setVelX(tempObject.getVelX() + handler.getSpeed());
				tempObject.setVelY(Game.clamp(tempObject.getVelY(), -handler.getSpeed(), handler.getSpeed()));
				tempObject.setVelX(Game.clamp(tempObject.getVelX(), -handler.getSpeed(), handler.getSpeed()));
			}
		}

		//pause 
		if (Game.gameState == State.Game)
			if (key == KeyEvent.VK_P) {
				if (Game.paused)
					Game.paused = false;
				else
					Game.paused = true;
			}

		//exit
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(1);
		
		

	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	
	
}

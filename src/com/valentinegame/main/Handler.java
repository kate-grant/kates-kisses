package com.valentinegame.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Handler {

	List<GameObject> object = new ArrayList<>();
	private int speed = 4;
	
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		try {
			for (int i = 0; i < object.size(); i++) {
				GameObject tempObject = object.get(i);

				tempObject.render(g);
			}
		} catch (Exception e) {

			System.out.println("Error: " + e.toString());

		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void removeTempObject(GameObject tempObj) {
		object.remove(object.indexOf(tempObj));
	}
	
	public void clearEnemies() {
		for (GameObject tempObj : object) {
			if (tempObj.getId() == ID.MenuHearts) {
				object.clear();
				addObject(new Player((int) tempObj.getX(), tempObj.getY(), ID.Player, this));
				break;
			}

		}

	}
	
	public void clearPlayer() {
		for (GameObject tempObj : object) {
			if (tempObj.getId() == ID.Player) {
				object.clear();
				break;
			}

		}

	}

	
	public void clearEnemies1() {
		for (GameObject tempObj : object) {
			if (tempObj.getId() == ID.Kisses) {
				object.clear();
				addObject(new Player((int) tempObj.getX(), tempObj.getY(), ID.Player, this));
				break;
			}

		}

	}
	
	public List<GameObject> getObject() {
		return object;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	

}

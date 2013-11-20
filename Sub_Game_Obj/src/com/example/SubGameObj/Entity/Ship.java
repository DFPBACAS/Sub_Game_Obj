package com.example.SubGameObj.Entity;

import com.example.SubGameObj.GameMap;
import com.example.SubGameObj.Event.Event;
import com.example.SubGameObj.Utils.Health;
import com.example.SubGameObj.Utils.ObjectListener;
import com.example.SubGameObj.Utils.Position;

public abstract class Ship {

	private Position mPosition = null;
	private Health mHealth = null;
	private Position mDestination = null;
	
	private ObjectListener listener = null;
	
	public Ship () {
		init();
	}
	
	public Ship(int xPos, int yPos) {
		this.setPosition(new Position(xPos, yPos));
		init();
	}
	
	private void init () {
		this.listener = GameMap.getInstance();
		this.listener.createShip(this);
	}
	
	public void onTurn () {
		if (this.mDestination != null && !this.mPosition.equals(mDestination)) {
			this.getPosition().moveToward(this.mDestination, 30);	//TODO: the '30' is the speed at which the ship will move
		}
	}
	
	/**
	 * This method acts as a hook to notify the shipController of an event that is acting on the ship.
	 * @param event
	 */
	public void notifyOfEvent (Event event) {
		
	}
	
	public void receiveDamage (int damage) {
		this.getHealth().reduceHealth(damage);
		if (this.isDestroyed()) {
			this.listener.removeShip(this);
		}
	}
	
	public boolean isDestroyed() {
		return this.getHealth().isDestroyed();
	}

	public abstract void onDraw(Object canvasObj);
	
	public void setDestination(Position destination) {
		this.mDestination = destination;
	}

	public void setHealth(Health health) {
		this.mHealth = health;
	}

	public Health getHealth() {
		return this.mHealth;
	}

	public void setPosition(Position pos) {
		this.mPosition = pos;
	}

	public Position getPosition() {
		return this.mPosition;
	}
}

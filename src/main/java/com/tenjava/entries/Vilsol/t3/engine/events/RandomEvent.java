package com.tenjava.entries.Vilsol.t3.engine.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.tenjava.entries.Vilsol.t3.engine.enums.LocationType;

public abstract class RandomEvent {
	
	protected LocationType locationType = LocationType.RANDOM;
	protected boolean broadcastMessage = true;
	protected String messageFormat = "";
	
	public RandomEvent(LocationType locationType, boolean broadcastMessage, String messageFormat){
		this.locationType = locationType;
		this.broadcastMessage = broadcastMessage;
		this.messageFormat = messageFormat;
	}
	
	/**
	 * Calls the event, and broadcasts if allowed
	 * @param l The location of the event
	 */
	public void callEvent(Location l){
		if(broadcastMessage){
			Bukkit.broadcastMessage(String.format(messageFormat, l.getWorld().getName(), l.getBlockX(), l.getBlockY(), l.getBlockZ()));
		}
		onEvent(l);
	}
	
	/**
	 * Gets called when the event is happening
	 * @param l The location of the event
	 */
	protected abstract void onEvent(Location l);
	
	/**
	 * @return The location type for this event
	 */
	public LocationType getLocationType() {
		return locationType;
	}
	
}

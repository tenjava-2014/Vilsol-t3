package com.tenjava.entries.Vilsol.t3.engine.events;

import org.bukkit.Location;

public abstract class RandomEvent {
	
	protected boolean finished = false;
	protected boolean requireLocation = false;
	
	public abstract void onEvent(Location l);
	
	public boolean doesRequireLocation(){
		return requireLocation;
	}
	
	public boolean isFinished(){
		return finished;
	}
	
}

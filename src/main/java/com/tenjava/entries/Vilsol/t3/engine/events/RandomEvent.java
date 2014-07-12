package com.tenjava.entries.Vilsol.t3.engine.events;

import org.bukkit.Location;

import com.tenjava.entries.Vilsol.t3.engine.enums.LocationType;

public abstract class RandomEvent {
	
	protected boolean finished = false;
	protected boolean requireLocation = false;
	protected LocationType locationType;
	
	public abstract void onEvent(Location l);
	
	public boolean doesRequireLocation(){
		return requireLocation;
	}
	
	public boolean isFinished(){
		return finished;
	}

	public LocationType getLocationType() {
		return locationType;
	}
	
}

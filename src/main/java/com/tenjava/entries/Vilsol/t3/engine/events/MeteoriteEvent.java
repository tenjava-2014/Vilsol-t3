package com.tenjava.entries.Vilsol.t3.engine.events;

import org.bukkit.Location;

public class MeteoriteEvent extends RandomEvent {

	public MeteoriteEvent() {
		requireLocation = true;
	}
	
	@Override
	public void onEvent(Location l) {
		
	}
	
}

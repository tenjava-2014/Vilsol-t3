package com.tenjava.entries.Vilsol.t3.engine.events;

import org.bukkit.Location;
import org.bukkit.Material;

import com.tenjava.entries.Vilsol.t3.Config;

public class MeteoriteEvent extends RandomEvent {

	public MeteoriteEvent() {
		requireLocation = true;
		locationType = Config.meteoriteLocationType;
	}
	
	@Override
	public void onEvent(Location l) {
		l.getBlock().setType(Material.DIAMOND_ORE);
		l.getWorld().strikeLightning(l);
	}
	
}

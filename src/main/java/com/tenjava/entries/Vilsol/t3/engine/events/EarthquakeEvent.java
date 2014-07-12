package com.tenjava.entries.Vilsol.t3.engine.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import com.tenjava.entries.Vilsol.t3.Config;
import com.tenjava.entries.Vilsol.t3.Utils;

public class EarthquakeEvent extends RandomEvent {

	public EarthquakeEvent() {
		super(Config.earthquakeLocationType, Config.earthquakeBroadcast, ChatColor.DARK_RED + "The land has split in " + ChatColor.RED + "%s" + ChatColor.DARK_RED + " at " + ChatColor.RED + "%d, %d, %d" + ChatColor.DARK_RED + "!");
	}

	@Override
	protected void onEvent(Location l) {
		Utils.generateEarthquake(l);
	}
	
}

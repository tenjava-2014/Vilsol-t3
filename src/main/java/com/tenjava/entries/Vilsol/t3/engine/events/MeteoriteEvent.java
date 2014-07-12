package com.tenjava.entries.Vilsol.t3.engine.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.Vilsol.t3.Config;
import com.tenjava.entries.Vilsol.t3.TenJava;
import com.tenjava.entries.Vilsol.t3.Utils;

public class MeteoriteEvent extends RandomEvent {
	
	public MeteoriteEvent() {
		locationType = Config.meteoriteLocationType;
	}
	
	@Override
	public void onEvent(final Location l) {
		final Location spawn = l.clone();
		spawn.setY(l.getWorld().getMaxHeight());
		
		Utils.spawnFireballAtFrom(l, spawn, 5, 10, 50);
		new BukkitRunnable() {
			@Override
			public void run() {
				Utils.spawnFireballAtFrom(l, spawn, 5, 10, 25);
			}
		}.runTaskLater(TenJava.plugin, 20L);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				// TODO Generate actual meteorite
			
				if(Config.meteoriteBroadcast) Bukkit.broadcastMessage(ChatColor.DARK_RED + "A meteorite has landed in '" + ChatColor.RED + l.getWorld().getName() + ChatColor.DARK_RED + "' at " + ChatColor.RED + l.getX() + ", " + l.getY() + ", " + l.getZ() + ChatColor.DARK_RED + "!");
			}
		}.runTaskLater(TenJava.plugin, 100L);
		
	}
	
}

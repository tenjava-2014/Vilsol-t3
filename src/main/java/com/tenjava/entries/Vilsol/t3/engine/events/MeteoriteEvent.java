package com.tenjava.entries.Vilsol.t3.engine.events;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.Vilsol.t3.Config;
import com.tenjava.entries.Vilsol.t3.TenJava;
import com.tenjava.entries.Vilsol.t3.Utils;

public class MeteoriteEvent extends RandomEvent {
	
	public MeteoriteEvent() {
		super(Config.meteoriteLocationType, Config.meteoriteBroadcast, ChatColor.DARK_RED + "A meteorite has landed in '" + ChatColor.RED + "%s" + ChatColor.DARK_RED + "' at " + ChatColor.RED + "%d" + ", " + "%d" + ", " + "%d" + ChatColor.DARK_RED + "!");
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
				l.setY(l.getWorld().getHighestBlockYAt(l));
				Utils.generateSphere(l.getWorld(), l.toVector(), Material.OBSIDIAN, new Random().nextInt(4) + 3);
				l.setY(l.getWorld().getHighestBlockYAt(l));
				l.getBlock().setType(Material.LAVA);
			}
		}.runTaskLater(TenJava.plugin, 100L);
		
	}
	
}

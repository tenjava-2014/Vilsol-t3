package com.tenjava.entries.Vilsol.t3.engine.events;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.Vilsol.t3.Config;
import com.tenjava.entries.Vilsol.t3.TenJava;

public class HailstormEvent extends RandomEvent {

	public HailstormEvent() {
		super(Config.hailstormLocationType, Config.hailstormBroadcast, ChatColor.DARK_RED + "A hailstorm has started in '" + ChatColor.RED + "%s" + ChatColor.DARK_RED + "' at " + ChatColor.RED + "%d" + ", " + "%d" + ", " + "%d" + ChatColor.DARK_RED + "!");
	}

	@Override
	protected void onEvent(final Location l) {
		l.setY(256);
		new BukkitRunnable() {
			
			private int count = 0;
			private int max = new Random().nextInt(100) + 100;
			
			@Override
			public void run() {
				storm(l);
				if(count >= max) cancel();
				count++;
			}
		}.runTaskTimer(TenJava.plugin, 5L, 5l);
	}

	@SuppressWarnings("deprecation")
	private void storm(Location l){
		Random r = new Random();
		for(int x = 0; x < 25; x++){
			int randX = r.nextInt(100) - 50;
			int randZ = r.nextInt(100) - 50;
			Location n = l.clone().add(randX, 0, randZ);
			
			l.getWorld().spawnFallingBlock(n, Material.PACKED_ICE, (byte) 0);
		}
	}
	
}

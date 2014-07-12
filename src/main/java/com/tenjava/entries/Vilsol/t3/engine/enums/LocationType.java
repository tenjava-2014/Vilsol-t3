package com.tenjava.entries.Vilsol.t3.engine.enums;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.tenjava.entries.Vilsol.t3.Utils;

public enum LocationType {
	
	RANDOM, PLAYER, NEAR_SPAWN, LOADED_CHUNK;
	
	public Location generateLocation(){
		Location l = null;
		Random r = new Random();
		switch(this) {
			case RANDOM:
				l = new Location(Utils.getRandomAvailableWorld(), r.nextInt(r.nextInt(200000)) - 100000, 0, r.nextInt(r.nextInt(200000)) - 100000);
				break;
			case PLAYER:
				Player p = Bukkit.getOnlinePlayers()[r.nextInt(Bukkit.getOnlinePlayers().length)];
				l = p.getLocation().add(r.nextInt(r.nextInt(160)) - 80, r.nextInt(r.nextInt(160)) - 80, r.nextInt(r.nextInt(160)) - 80);
				break;
			case LOADED_CHUNK:
				World w = Utils.getRandomAvailableWorld();
				Chunk c = w.getLoadedChunks()[r.nextInt(w.getLoadedChunks().length)];
				l = c.getBlock(0, 0, 0).getLocation().add(r.nextInt(r.nextInt(16)) - 8, r.nextInt(r.nextInt(16)) - 8, r.nextInt(r.nextInt(16)) - 8);
				break;
			default:
			case NEAR_SPAWN:
				l = Utils.getRandomAvailableWorld().getSpawnLocation().add(r.nextInt(r.nextInt(160)) - 80, r.nextInt(r.nextInt(160)) - 80, r.nextInt(r.nextInt(160)) - 80);
				break;
		}
		return l;
	}
	
}

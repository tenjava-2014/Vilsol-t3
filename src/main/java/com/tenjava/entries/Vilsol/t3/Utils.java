package com.tenjava.entries.Vilsol.t3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Fireball;
import org.bukkit.util.Vector;

public class Utils {
	
	public static World getRandomAvailableWorld() {
		return Config.availableWorlds.get(new Random().nextInt(Config.availableWorlds.size()));
	}
	
	public static List<Fireball> spawnFireballAtFrom(Location target, Location source, int jitter, int count, int power){
		Random r = new Random();
		
		List<Fireball> fireballs = new ArrayList<Fireball>();
		for(int i = 0; i < count; i++){
			Location l = source.clone().add(r.nextInt(jitter*2) - jitter, r.nextInt(jitter*2) - jitter, r.nextInt(jitter*2) - jitter);
			Vector v = target.toVector().subtract(l.toVector()).normalize();
			Fireball fireball = l.getWorld().spawn(l, Fireball.class);
			fireball.setDirection(v);
			fireball.setYield(power);
			fireballs.add(fireball);
		}
		
		return fireballs;
	}
	
}

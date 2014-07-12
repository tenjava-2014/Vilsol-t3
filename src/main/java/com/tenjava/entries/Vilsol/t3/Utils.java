package com.tenjava.entries.Vilsol.t3;

import java.util.Random;

import org.bukkit.World;

public class Utils {
	
	public static World getRandomAvailableWorld(){
		return Config.availableWorlds.get(new Random().nextInt(Config.availableWorlds.size()));
	}
	
}

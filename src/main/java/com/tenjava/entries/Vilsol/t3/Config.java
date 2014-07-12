package com.tenjava.entries.Vilsol.t3;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;

import com.tenjava.entries.Vilsol.t3.engine.enums.LocationType;

public class Config {
	
	public static int eventChance = 5;
	public static int eventRepeatDelay = 300;
	
	public static List<World> availableWorlds = new ArrayList<World>();
	
	public static boolean meteoriteBroadcast = true;
	public static LocationType meteoriteLocationType = LocationType.NEAR_SPAWN;
	
}

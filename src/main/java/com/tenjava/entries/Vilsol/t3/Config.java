package com.tenjava.entries.Vilsol.t3;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;

import com.tenjava.entries.Vilsol.t3.engine.enums.LocationType;
import com.tenjava.entries.Vilsol.t3.engine.events.RandomEvent;

public class Config {

	public static List<Class<? extends RandomEvent>> availableEvents = new ArrayList<Class<? extends RandomEvent>>();
	
	public static int eventChance = 5;
	public static int eventRepeatDelay = 300;
	
	public static List<World> availableWorlds = new ArrayList<World>();
	
	public static boolean meteoriteBroadcast = true;
	public static LocationType meteoriteLocationType = LocationType.NEAR_SPAWN;
	
	public static boolean earthquakeBroadcast = true;
	public static LocationType earthquakeLocationType = LocationType.NEAR_SPAWN;
	
	public static boolean hailstormBroadcast = true;
	public static LocationType hailstormLocationType = LocationType.NEAR_SPAWN;
	
}

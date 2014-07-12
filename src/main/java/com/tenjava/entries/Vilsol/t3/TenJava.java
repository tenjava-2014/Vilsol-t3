package com.tenjava.entries.Vilsol.t3;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.Vilsol.t3.engine.EventManager;
import com.tenjava.entries.Vilsol.t3.engine.enums.LocationType;
import com.tenjava.entries.Vilsol.t3.engine.events.MeteoriteEvent;

public class TenJava extends JavaPlugin {
	
	public static TenJava plugin;
	public static Logger logger;
	public static EventManager eventManager;
	
	public void onEnable() {
		saveDefaultConfig();
		readConfig();
		
		plugin = this;
		logger = this.getLogger();
		eventManager = new EventManager();
		
		logger.info("Successfully Loaded!");
	}
	
	public void onDisable() {
		logger.info("Successfully Unloaded!");
	}
	
	private void readConfig() {
		FileConfiguration c = this.getConfig();
		Config.eventChance = c.getInt("Events.Global.EventChance");
		Config.eventRepeatDelay = c.getInt("Events.Global.EventRepeatDelay");
		
		if(!c.getBoolean("Events.Meteorite.Enabled")) Reference.availableEvents.remove(MeteoriteEvent.class);
		if(LocationType.valueOf(c.getString("Events.Meteorite.LocationType")) != null) Config.meteoriteLocationType = LocationType.valueOf(c.getString("Events.Meteorite.LocationType"));
		
		if(c.isList("Events.Global.Worlds")){
			for(String s : c.getStringList("Events.Global.Worlds")) {
				World w = Bukkit.getWorld(s);
				if(w != null){
					Config.availableWorlds.add(w);
				}
			}
		}
		
		if(Config.availableWorlds.size() == 0) Config.availableWorlds.add(Bukkit.getWorlds().get(0));
	}
	
}

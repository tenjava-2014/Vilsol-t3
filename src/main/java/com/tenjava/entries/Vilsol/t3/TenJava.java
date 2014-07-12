package com.tenjava.entries.Vilsol.t3;

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {
	
	public static TenJava plugin;
	public static Logger logger;
	
	public void onEnable(){
		saveDefaultConfig();
		readConfig();
		
		plugin = this;
		logger = this.getLogger();
		
		logger.info("Successfully Loaded!");
	}

	public void onDisable(){
		logger.info("Successfully Unloaded!");
	}
	
	private void readConfig() {
		FileConfiguration c = this.getConfig();
		Config.eventChance = c.getInt("Events.Global.EventChance");
		Config.eventRepeatDelay = c.getInt("Events.Global.EventRepeatDelay");
	}
	
}

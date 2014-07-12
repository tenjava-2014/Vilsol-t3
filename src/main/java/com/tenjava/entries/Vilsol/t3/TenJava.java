package com.tenjava.entries.Vilsol.t3;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {
	
	public static TenJava plugin;
	public static Logger logger;
	
	public void onEnable(){
		logger = this.getLogger();
		plugin = this;
		
		
		logger.info("Successfully Loaded!");
	}
	
	public void onDisable(){
		logger.info("Successfully Unloaded!");
	}
	
}

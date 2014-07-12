package com.tenjava.entries.Vilsol.t3;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tenjava.entries.Vilsol.t3.engine.EventManager;
import com.tenjava.entries.Vilsol.t3.engine.events.EarthquakeEvent;
import com.tenjava.entries.Vilsol.t3.engine.events.GRBEvent;
import com.tenjava.entries.Vilsol.t3.engine.events.HailstormEvent;
import com.tenjava.entries.Vilsol.t3.engine.events.MeteoriteEvent;
import com.tenjava.entries.Vilsol.t3.engine.events.RandomEvent;

public class CommandEvent implements CommandExecutor {

	private static final String prefix = ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "ND" + ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "] " + ChatColor.AQUA;
	private static final String eprefix = prefix + ChatColor.RED;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length < 2) return sendHelp(sender, label);
		boolean success = true;
		switch(args[1].toLowerCase()) {
			case "grb":
				EventManager.callEvent(GRBEvent.class);
				break;
			case "hailstorm":
				EventManager.callEvent(HailstormEvent.class);
				break;
			case "meteorite":
				EventManager.callEvent(MeteoriteEvent.class);
				break;
			case "earthquake":
				EventManager.callEvent(EarthquakeEvent.class);
				break;
			default:
				success = false;
				break;
		}
		
		if(!success){
			sender.sendMessage(eprefix + "Failed to spawn event " + ChatColor.DARK_RED + args[1]);
		}
		return true;
	}
	
	private boolean sendHelp(CommandSender s, String l){
		String available = "";
		
		for(Class<? extends RandomEvent> r : Config.availableEvents) {
			if(!available.equals("")) available += ", ";
			String name = r.getSimpleName();
			name = name.substring(0, name.length() - 5);
			available += name;
		}
		
		s.sendMessage(prefix + "Available Commands:");
		s.sendMessage(prefix + "/" + l + ChatColor.GREEN + " spawn <event>");
		s.sendMessage(prefix + "Available Events: " + ChatColor.GREEN + available);
		return true;
	}
	
}

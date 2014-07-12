package com.tenjava.entries.Vilsol.t3.engine.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import com.tenjava.entries.Vilsol.t3.Config;

public class GRBEvent extends RandomEvent {
	
	public GRBEvent() {
		super(Config.grbLocationType, Config.grbBroadcast, ChatColor.DARK_RED + "A Gamma-ray Burst has happened in '" + ChatColor.RED + "%s" + ChatColor.DARK_RED + "' at " + ChatColor.RED + "%d" + ", " + "%d" + ", " + "%d" + ChatColor.DARK_RED + "!");
	}
	
	@Override
	protected void onEvent(Location l) {
		for(int x = l.getBlockX(); x <= l.getBlockX() + 100; x++){
			int pX = x - 50;
			for(int z = l.getBlockZ(); z <= l.getBlockZ() + 100; z++){
				int pZ = z - 50;
				convertBlocks(pX, pZ, l.getWorld());
			}
		}
	}
	
	/**
	 * Converts specific blocks to other blocks, as if GRB had struck
	 * @param x X Coordinate
	 * @param z Z Coordinate
	 * @param w World
	 */
	private void convertBlocks(int x, int z, World w){
		int start = w.getHighestBlockYAt(x, z);
		int end = start - 6;
		for(int y = start; y >= end; y--){
			Block b = new Location(w, x, y, z).getBlock();
			switch(b.getType()) {
				case ICE:
				case PACKED_ICE:
				case SNOW:
				case SNOW_BLOCK:
					b.setType(Material.AIR);
					break;
				case WATER:
					b.setType(Material.LAVA);
					break;
				case SAND:
					b.setType(Material.GLASS);
					break;
				case GRASS:
				case DIRT:
					b.setType(Material.NETHERRACK);
					break;
				default:
					break;
			}
		}
	}
	
}

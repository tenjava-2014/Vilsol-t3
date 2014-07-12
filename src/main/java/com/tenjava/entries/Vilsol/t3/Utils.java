package com.tenjava.entries.Vilsol.t3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Fireball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Utils {
	
	public static World getRandomAvailableWorld() {
		return Config.availableWorlds.get(new Random().nextInt(Config.availableWorlds.size()));
	}
	
	public static List<Fireball> spawnFireballAtFrom(Location target, Location source, int jitter, int count, int power) {
		Random r = new Random();
		
		List<Fireball> fireballs = new ArrayList<Fireball>();
		for(int i = 0; i < count; i++) {
			Location l = source.clone().add(r.nextInt(jitter * 2) - jitter, r.nextInt(jitter * 2) - jitter, r.nextInt(jitter * 2) - jitter);
			Vector v = target.toVector().subtract(l.toVector()).normalize();
			Fireball fireball = l.getWorld().spawn(l, Fireball.class);
			fireball.setDirection(v);
			fireball.setYield(power);
			fireballs.add(fireball);
		}
		
		return fireballs;
	}
	
	public static void generateSphere(World w, Vector v, Material type, double radius) {
		radius += 0.5;
		
		final double invRadius = 1 / radius;
		
		final int ceilRadius = (int) Math.ceil(radius);
		
		double nextXn = 0;
		forX: for(int x = 0; x <= radius; ++x) {
			final double xn = nextXn;
			nextXn = (x + 1) * invRadius;
			double nextYn = 0;
			forY: for(int y = 0; y <= ceilRadius; ++y) {
				final double yn = nextYn;
				nextYn = (y + 1) * invRadius;
				double nextZn = 0;
				forZ: for(int z = 0; z <= ceilRadius; ++z) {
					final double zn = nextZn;
					nextZn = (z + 1) * invRadius;
					
					double distanceSq = lengthSquared(xn, yn, zn);
					if(distanceSq > 1) {
						if(z == 0) {
							if(y == 0) {
								break forX;
							}
							break forY;
						}
						break forZ;
					}
					
					setBlock(w, v, type, x, y, z);
					setBlock(w, v, type, -x, -y, -z);
					
					setBlock(w, v, type, -x, y, z);
					setBlock(w, v, type, x, -y, z);
					setBlock(w, v, type, x, y, -z);
					
					setBlock(w, v, type, -x, -y, z);
					setBlock(w, v, type, -x, y, -z);
					setBlock(w, v, type, x, -y, -z);
				}
			}
		}
	}
	
	private static void setBlock(World w, Vector v, Material m, int x, int y, int z) {
		Vector ve = v.clone().add(new Vector(x, y, z));
		new Location(w, ve.getX(), ve.getY(), ve.getZ()).getBlock().setType(m);
	}
	
	public static double lengthSquared(double x, double y, double z) {
		return (x * x) + (y * y) + (z * z);
	}
	
	public static void generateLootChest(Location sphereC) {
		Block b = sphereC.getBlock();
		b.setType(Material.CHEST);
		Chest c = (Chest) b.getState();
		c.getInventory().setContents((ItemStack[]) Arrays.asList(new ItemStack(Material.DIAMOND)).toArray());
	}
	
	public static void generateEarthquake(Location l){
		Random r = new Random();
		Vector pos1 = l.toVector();
		for(int x = 0; x <= r.nextInt(5); x++){
			int rx = r.nextInt(160) - 80;
			int rz = r.nextInt(160) - 80;
			
			Vector pos2 = l.toVector().add(new Vector(rx, 0, rz));
			HashSet<Vector> blocks = generateLine(l.getWorld(), pos1, pos2, r.nextInt(3) + 2);
			blocks = removeDuplicates(blocks);
			
			for(Vector v : blocks) {
				clearFromSkyToBedrock(new Location(l.getWorld(), v.getX(), v.getY(), v.getZ()));
			}
		}
	}
	
	private static void clearFromSkyToBedrock(Location l){
		int max = l.getWorld().getHighestBlockYAt(l);
		for(int y = max; y > 0; y--){
			l.subtract(0, 1, 0).getBlock().setType(Material.AIR);
		}
	}
	
	public static HashSet<Vector> generateLine(World w, Vector pos1, Vector pos2, double radius) {
		boolean drawn = false;
		
		int x1 = pos1.getBlockX(), y1 = pos1.getBlockY(), z1 = pos1.getBlockZ();
		int x2 = pos2.getBlockX(), y2 = pos2.getBlockY(), z2 = pos2.getBlockZ();
		int tipx = x1, tipy = y1, tipz = z1;
		double dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1), dz = Math.abs(z2 - z1);
		
		HashSet<Vector> vset = new HashSet<Vector>();
		
		if(dx + dy + dz == 0) {
			vset.add(new Vector(tipx, tipy, tipz));
			drawn = true;
		}
		
		if(Math.max(Math.max(dx, dy), dz) == dx && !drawn) {
			for(int s = 0; s <= dx; s++) {
				tipx = x1 + s * (x2 - x1 > 0 ? 1 : -1);
				tipy = (int) Math.round(y1 + s * (dy) / (dx) * (y2 - y1 > 0 ? 1 : -1));
				tipz = (int) Math.round(z1 + s * (dz) / (dx) * (z2 - z1 > 0 ? 1 : -1));
				vset.add(new Vector(tipx, tipy, tipz));
			}
			drawn = true;
		}
		
		if(Math.max(Math.max(dx, dy), dz) == dy && !drawn) {
			for(int s = 0; s <= dx; s++) {
				tipx = (int) Math.round(x1 + s * (dx) / (dy) * (x2 - x1 > 0 ? 1 : -1));
				tipy = y1 + s * (y2 - y1 > 0 ? 1 : -1);
				tipz = (int) Math.round(z1 + s * (dz) / (dy) * (z2 - z1 > 0 ? 1 : -1));
				vset.add(new Vector(tipx, tipy, tipz));
			}
			drawn = true;
		}
		
		if(Math.max(Math.max(dx, dy), dz) == dz && !drawn) {
			for(int s = 0; s <= dx; s++) {
				tipx = (int) Math.round(x1 + s * (dx) / (dz) * (x2 - x1 > 0 ? 1 : -1));
				tipy = (int) Math.round(y1 + s * (dy) / (dz) * (y2 - y1 > 0 ? 1 : -1));
				tipz = z1 + s * (z2 - z1 > 0 ? 1 : -1);
				vset.add(new Vector(tipx, tipy, tipz));
			}
			drawn = true;
		}
		
		vset = getBallooned(vset, radius);
		
		return vset;
	}
	
	private static HashSet<Vector> getBallooned(HashSet<Vector> vset, double radius) {
		HashSet<Vector> returnset = new HashSet<Vector>();
		int ceilrad = (int) Math.ceil(radius);
		
		for(Vector v : vset) {
			int tipx = v.getBlockX(), tipy = v.getBlockY(), tipz = v.getBlockZ();
			
			for(int loopx = tipx - ceilrad; loopx <= tipx + ceilrad; loopx++) {
				for(int loopy = tipy - ceilrad; loopy <= tipy + ceilrad; loopy++) {
					for(int loopz = tipz - ceilrad; loopz <= tipz + ceilrad; loopz++) {
						if(hypot(loopx - tipx, loopy - tipy, loopz - tipz) <= radius) {
							returnset.add(new Vector(loopx, loopy, loopz));
						}
					}
				}
			}
		}
		return returnset;
	}
	
	private static double hypot(double... pars) {
		double sum = 0;
		for(double d : pars) {
			sum += Math.pow(d, 2);
		}
		return Math.sqrt(sum);
	}
	
	public static HashSet<Vector> cloneSet(HashSet<Vector> l){
		HashSet<Vector> set = new HashSet<Vector>();
		for(Vector object : l) {
			set.add(object);
		}
		return set;
	}
	
	public static HashSet<Vector> removeDuplicates(HashSet<Vector> l){
		// TODO Fix This
		HashSet<Vector> clone = cloneSet(l);
		Iterator<Vector> i = l.iterator();
		while(i.hasNext()){
			Iterator<Vector> h = clone.iterator();
			while(h.hasNext()){
				if(!i.hasNext()) break;
				Vector hN = h.next();
				Vector iN = i.next();
				if(hN == null || iN == null) break;
				if(hN.toString().equals(iN.toString())){
					i.remove();
					break;
				}
			}
		}
		return l;
	}
	
}

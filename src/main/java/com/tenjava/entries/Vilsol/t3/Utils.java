package com.tenjava.entries.Vilsol.t3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
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
	
	public static void generateSphere(World w, Vector v, Material type, double radius) {
        radius += 0.5;

        final double invRadius = 1 / radius;

        final int ceilRadius = (int) Math.ceil(radius);

        double nextXn = 0;
        forX: for (int x = 0; x <= radius; ++x) {
            final double xn = nextXn;
            nextXn = (x + 1) * invRadius;
            double nextYn = 0;
            forY: for (int y = 0; y <= ceilRadius; ++y) {
                final double yn = nextYn;
                nextYn = (y + 1) * invRadius;
                double nextZn = 0;
                forZ: for (int z = 0; z <= ceilRadius; ++z) {
                    final double zn = nextZn;
                    nextZn = (z + 1) * invRadius;

                    double distanceSq = lengthSquared(xn, yn, zn);
                    if (distanceSq > 1) {
                        if (z == 0) {
                            if (y == 0) {
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
	
	private static void setBlock(World w, Vector v, Material m, int x, int y, int z){
		Vector ve = v.clone().add(new Vector(x, y, z));
		new Location(w, ve.getX(), ve.getY(), ve.getZ()).getBlock().setType(m);
	}
	
	public static double lengthSquared(double x, double y, double z){
		return (x * x) + (y * y) + (z * z);
	}
	
}

package com.tenjava.entries.Vilsol.t3.engine;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.Vilsol.t3.Config;
import com.tenjava.entries.Vilsol.t3.Reference;
import com.tenjava.entries.Vilsol.t3.TenJava;
import com.tenjava.entries.Vilsol.t3.engine.events.RandomEvent;

public class EventManager {
	
	private static HashMap<Class<? extends RandomEvent>, RandomEvent> loadedEvents = new HashMap<Class<? extends RandomEvent>, RandomEvent>();
	private static BukkitRunnable eventCaller;
	
	public EventManager() {
		eventCaller = new BukkitRunnable() {
			@Override
			public void run() {
				Random r = new Random();
				if(r.nextInt(101) <= Config.eventChance) {
					int theChosenEventNumber = r.nextInt(Reference.availableEvents.size());
					Class<? extends RandomEvent> theChosenEvent = Reference.availableEvents.get(theChosenEventNumber);
					callEvent(theChosenEvent);
				}
			}
		};
		
		eventCaller.runTaskTimer(TenJava.plugin, Config.eventRepeatDelay * 20, Config.eventRepeatDelay * 20L);
		
		loadEvents();
	}
	
	private void loadEvents() {
		for(Class<? extends RandomEvent> e : Reference.availableEvents) {
			try {
				RandomEvent event = e.newInstance();
				loadedEvents.put(e, event);
			} catch(InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private void callEvent(Class<? extends RandomEvent> event) {
		RandomEvent eventObject = loadedEvents.get(event);
		if(eventObject == null) return;
		Location l = eventObject.getLocationType().generateLocation();
		l.setY(l.getWorld().getHighestBlockYAt(l));
		eventObject.callEvent(l);
	}
	
}

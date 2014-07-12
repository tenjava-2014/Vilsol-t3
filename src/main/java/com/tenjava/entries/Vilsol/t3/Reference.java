package com.tenjava.entries.Vilsol.t3;

import java.util.Arrays;
import java.util.List;

import com.tenjava.entries.Vilsol.t3.engine.events.MeteoriteEvent;
import com.tenjava.entries.Vilsol.t3.engine.events.RandomEvent;
import com.tenjava.entries.Vilsol.t3.engine.events.TornadoEvent;

public class Reference {
	
	public static final List<Class<? extends RandomEvent>> availableEvents = Arrays.asList(MeteoriteEvent.class, TornadoEvent.class);
	
}

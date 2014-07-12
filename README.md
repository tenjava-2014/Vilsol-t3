Vilsol's ten.java submission
==============================

[![ten.java](https://cdn.mediacru.sh/hu4CJqRD7AiB.svg)](https://tenjava.com/)

This is a submission for the 2014 ten.java contest.

- __Theme:__ What random events can occur in Minecraft?
- __Time:__ Time 3 (7/12/2014 14:00 to 7/13/2014 00:00 UTC)
- __MC Version:__ 1.7.9 (latest Bukkit beta)
- __Stream URL:__ https://twitch.tv/Vilsol

<!-- put chosen theme above -->

---------------------------------------

Compilation
-----------

- Download & Install [Maven 3](http://maven.apache.org/download.html)
- Clone the repository: `git clone https://github.com/tenjava/Vilsol-t3`
- Compile and create the plugin package using Maven: `mvn`

Maven will download all required dependencies and build a ready-for-use plugin package!

---------------------------------------

Usage
-----

This plugin creates random disasters that can happen in the real world. 

Currently available disasters are:
- Earthquake
- Gamma-ray Burst
- Meteorite
- Hailstorm

There is a configuration for all of those events and each one can be separately turned on and off.

I have also implemented 4 types of location searching for the events:
- RANDOM: Will spawn at a completely random location between -100 000 and +100 000.
- PLAYER: Will choose a random player and spawn near that player.
- NEAR_SPAWN: Will spawn at a location that is near the spawn.
- LOADED_CHUNK:  Will spawn in a loaded chunk.

This plugin also has a command ```/event```, which allows you to spawn any event you want.

Future Ideas
------------

In the future I want to expand the plugin so it supports a lot more disasters, like tornadoes, epidemics, etc. It would also support a lot more customization, for example, you would be able to customize what block types are created by the disasters etc. 
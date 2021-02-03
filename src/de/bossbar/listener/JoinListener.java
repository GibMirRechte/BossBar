package de.bossbar.listener;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.bossbar.main.Main;

public class JoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		for(Integer index : Main.color.keySet()) {
			if(getColor(Main.color.get(index)) == null) {
				BossBar bossBar = Bukkit.createBossBar("§cThis color is not available!", BarColor.WHITE, BarStyle.SOLID);
				bossBar.addPlayer(e.getPlayer());
			}else {
				BossBar bossBar = Bukkit.createBossBar(Main.text.get(index), getColor(Main.color.get(index)), BarStyle.SOLID);
				bossBar.addPlayer(e.getPlayer());
			}
		}
	}
	
	public BarColor getColor(String color_as_string) {
		if(color_as_string.equalsIgnoreCase("blue")) {
			return BarColor.BLUE;
		}else if(color_as_string.equalsIgnoreCase("green")) {
			return BarColor.GREEN;
		}else if(color_as_string.equalsIgnoreCase("pink")) {
			return BarColor.PINK;
		}else if(color_as_string.equalsIgnoreCase("purple")) {
			return BarColor.PURPLE;
		}else if(color_as_string.equalsIgnoreCase("red")) {
			return BarColor.RED;
		}else if(color_as_string.equalsIgnoreCase("white")) {
			return BarColor.WHITE;
		}else if(color_as_string.equalsIgnoreCase("yellow")) {
			return BarColor.YELLOW;
		}else {
			return null;
		}
	}

}

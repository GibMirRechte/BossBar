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
				
				if(getBarStyle(Main.style.get(index)) == null) {
					BossBar bossBar = Bukkit.createBossBar("§cThis BarType is not available!", BarColor.WHITE, BarStyle.SOLID);
					bossBar.addPlayer(e.getPlayer());
					return;
				}
				
				BossBar bossBar = Bukkit.createBossBar(Main.text.get(index), getColor(Main.color.get(index)), getBarStyle(Main.style.get(index)));
				bossBar.addPlayer(e.getPlayer());
			}
		}
		
		if(e.getPlayer().hasPermission("bossbar.notification") && Main.update_available) {
			e.getPlayer().sendMessage("§7[§cBossBar§7] An Update is available!");
			e.getPlayer().sendMessage("§7[§cBossBar§7] §cDownload now: §bhttps://www.spigotmc.org/resources/1-15-x-1-16-x-bossbar-by-gibmirrechte.88606/");
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
	
	public BarStyle getBarStyle(String barstyle_as_string) {
		if(barstyle_as_string.equalsIgnoreCase("solid")) {
			return BarStyle.SOLID;
		}else if(barstyle_as_string.equalsIgnoreCase("segmented_6")) {
			return BarStyle.SEGMENTED_6;
		}else if(barstyle_as_string.equalsIgnoreCase("segmented_20")) {
			return BarStyle.SEGMENTED_20;
		}else if(barstyle_as_string.equalsIgnoreCase("segmented_12")) {
			return BarStyle.SEGMENTED_12;
		}else if(barstyle_as_string.equalsIgnoreCase("segmented_10")) {
			return BarStyle.SEGMENTED_10;
		}else {
			return null;
		}
	}

}

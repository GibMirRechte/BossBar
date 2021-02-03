package de.bossbar.main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.bossbar.listener.JoinListener;

public class Main extends JavaPlugin {
	
	public static HashMap<Integer, String> color = new HashMap<>();
	public static HashMap<Integer, String> text = new HashMap<>();
	
	File file = new File("plugins//BossBar//config.yml");
	YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
	
	public void onEnable() {
		
		if(!file.exists()) {
			yaml.set("Bar1.Text", "&cThis is an example bossbar!");
			yaml.set("Bar2.Text", "&cYou can use the following colors:");
			yaml.set("Bar3.Text", "&cYellow, Red, Pink, Purple, Green, Blue and White");
			yaml.set("Bar4.Text", "&cYou can also use Colorcodes");
			yaml.set("Bar5.Text", "&cYou can create more bars!");
			yaml.set("Bar6.Text", "&a&lJoin our Discord for support: https://discord.gg/yhBX6KT");
			yaml.set("Bar1.Color", "purple");
			yaml.set("Bar2.Color", "red");
			yaml.set("Bar3.Color", "green");
			yaml.set("Bar4.Color", "white");
			yaml.set("Bar5.Color", "pink");
			yaml.set("Bar6.Color", "blue");
			try {
				yaml.save(file);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		int i = 1;
		while(yaml.getString("Bar" + i + ".Text") != null) {
			System.out.println("Initalized bar " + i);
			color.put(i, yaml.getString("Bar" + i + ".Color").replace("&", "§"));
			text.put(i, yaml.getString("Bar" + i + ".Text").replace("&", "§"));
			i++;
		}
		
		Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
	}

}

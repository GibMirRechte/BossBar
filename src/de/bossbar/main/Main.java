package de.bossbar.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.bossbar.listener.JoinListener;

public class Main extends JavaPlugin {
	
	public static HashMap<Integer, String> color = new HashMap<>();
	public static HashMap<Integer, String> text = new HashMap<>();
	public static HashMap<Integer, String> style = new HashMap<>();
	
	public static boolean update_available = false;
	
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
			yaml.set("Bar1.Type", "segmented_6");
			yaml.set("Bar2.Type", "segmented_12");
			yaml.set("Bar3.Type", "segmented_20");
			yaml.set("Bar4.Type", "segmented_10");
			yaml.set("Bar5.Type", "solid");
			yaml.set("Bar6.Type", "solid");
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
			style.put(i, yaml.getString("Bar" + i + ".Type"));
			i++;
		}
		
		check();
		if(isAvailable) {
			update_available = true;
		}
		
		Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
	}
	
	   private String url = "https://api.spigotmc.org/legacy/update.php?resource=88606";

	    private boolean isAvailable;
	
	public void check() {
        isAvailable = checkUpdate();
    }

    private boolean checkUpdate() {
        System.out.println("§7[§cBossBar§7] §aChecking for updates from the spigotmc api...");
        try {
            String localVersion = Bukkit.getPluginManager().getPlugin("BossBar").getDescription().getVersion();
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            String raw = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

            String remoteVersion;
            if(raw.contains("-")) {
                remoteVersion = raw.split("-")[0].trim();
            } else {
                remoteVersion = raw;
            }

            if(!localVersion.equalsIgnoreCase(remoteVersion))
                return true;

        } catch (IOException e) {
            return false;
        }
        return false;
    }

}

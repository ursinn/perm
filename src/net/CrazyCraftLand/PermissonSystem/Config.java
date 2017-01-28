package net.CrazyCraftLand.PermissonSystem;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * 
 * Permisson System
 * 
 * @author ursinn (Ursin Filli)
 * @Copyright (c) 2017 Ursin Filli
 * @license CC-BY-SA 4.0 International
 *
 */
public class Config {

	public File configFile = new File("plugins/PermSystem", "config.yml");
	public FileConfiguration cfg = YamlConfiguration.loadConfiguration(configFile);

	public void setDefaults() {
		cfg.addDefault("Prefix", "§7[§aPerm§7]§f ");
		cfg.addDefault("Debug", false);
		cfg.addDefault("UseMySQL", false);
		cfg.addDefault("NotifyDeveloper", true);
		cfg.addDefault("UseChat", true);

		cfg.addDefault("MySQL.Host", "localhost");
		cfg.addDefault("MySQL.User", "User");
		cfg.addDefault("MySQL.Password", "Password");
		cfg.addDefault("MySQL.DB", "Datenbank");
		cfg.addDefault("MySQL.Port", "3306");

		cfg.options().copyDefaults(true);
		save();
	}

	public void save() {
		try {
			cfg.save(configFile);
		} catch (IOException e) {
			Main.getInstance().sendMessage(SendMessageEnum.CONSOLE,
					Main.getInstance().Prefix + "§cConfig - Save Error");
		}
	}

	public void load() {
		Main.getInstance().Prefix = cfg.getString("Prefix");
		Main.getInstance().Debug = cfg.getBoolean("Debug");
		Main.getInstance().UseMySQL = cfg.getBoolean("UseMySQL");
		Main.getInstance().NotifyDeveloper = cfg.getBoolean("NotifyDeveloper");
		Main.getInstance().UseChat = cfg.getBoolean("UseChat");

		Main.getInstance().MySQL_Host = cfg.getString("MySQL.Host");
		Main.getInstance().MySQL_Username = cfg.getString("MySQL.User");
		Main.getInstance().MySQL_Password = cfg.getString("MySQL.Password");
		Main.getInstance().MySQL_Database = cfg.getString("MySQL.DB");
		Main.getInstance().MySQL_Port = cfg.getString("MySQL.Port");
	}

}

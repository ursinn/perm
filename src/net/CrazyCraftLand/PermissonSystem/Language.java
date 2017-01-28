package net.CrazyCraftLand.PermissonSystem;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * 
 * Permisson System
 * @author ursinn (Ursin Filli)
 * @Copyright (c) 2017 Ursin Filli
 * @license CC-BY-SA 4.0 International
 *
 */
public class Language {

	public File messageFile = new File("plugins/PermSystem", "messages.yml");
	public FileConfiguration cfg = YamlConfiguration.loadConfiguration(messageFile);
	
	public static String Language_Prefix = "de_DE";
	
	public void setDefaults() {
		cfg.addDefault("de_DE.NoPermission", "%Prefix%§cDu hast hierzu Keine Berechtigung!");
		cfg.addDefault("de_DE.NoConsole", "%Prefix%§cDie Console daf dies nicht tun!");
		cfg.addDefault("de_DE.GroupAdd", "%Prefix%§aDu hast die Gruppe Erfolgreich Hinzugefügt!");
		cfg.addDefault("de_DE.GroupAddFAILED", "%Prefix%§cOps da gabs einen Fehler beim Hinzufügen der Gruppe!");
		cfg.addDefault("de_DE.UserGroupSet", "%Prefix%§aDu hast den Spieler Erfolgeich in die Gruppe Gesetzt!");
		cfg.addDefault("de_DE.UserGroupSetFAILED", "%Prefix%§cDie Gruppe Exestiert nicht!");
		
		cfg.options().copyDefaults(true);
		save();
	}

	public void save() {
		try {
			cfg.save(messageFile);
		} catch (IOException e) {
			Main.getInstance().sendMessage(SendMessageEnum.CONSOLE,
					Main.getInstance().Prefix + "§cConfig - Save Error");
		}
	}

	public void load() {
		Main.getInstance().NoPerm = cfg.getString(Language_Prefix + ".NoPermission").replaceAll("%Prefix%", Main.getInstance().Prefix);
		Main.getInstance().NoConsole = cfg.getString(Language_Prefix + ".NoConsole").replaceAll("%Prefix%", Main.getInstance().Prefix);
		Main.getInstance().Message_GroupAdd = cfg.getString(Language_Prefix + ".GroupAdd").replaceAll("%Prefix%", Main.getInstance().Prefix);
		Main.getInstance().Message_GroupAddFAILED = cfg.getString(Language_Prefix + ".GroupAddFAILED").replaceAll("%Prefix%", Main.getInstance().Prefix);
		Main.getInstance().Message_UserGroupSet = cfg.getString(Language_Prefix + ".UserGroupSet").replaceAll("%Prefix%", Main.getInstance().Prefix);
		Main.getInstance().Message_UserGroupSetFAILED = cfg.getString(Language_Prefix + ".UserGroupSetFAILED").replaceAll("%Prefix%", Main.getInstance().Prefix);
	}
	
}

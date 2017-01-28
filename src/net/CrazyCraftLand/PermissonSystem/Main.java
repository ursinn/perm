package net.CrazyCraftLand.PermissonSystem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.CrazyCraftLand.PermissonSystem.Listener.ChatListener;
import net.CrazyCraftLand.PermissonSystem.Listener.JoinListener;

/**
 * 
 * Permisson System
 * 
 * @author ursinn (Ursin Filli)
 * @Copyright (c) 2017 Ursin Filli
 * @license CC-BY-SA 4.0 International
 *
 */
public class Main extends JavaPlugin {

	private static Main intsance;
	private Config Config;
	private MySQL MySQL;

	public String Prefix;
	public String TabelPrefix;

	public boolean Debug;
	public boolean UseMySQL;
	public boolean NotifyDeveloper;
	public boolean UseChat;
	public boolean ConsoleOnly;

	public String MySQL_Host;
	public String MySQL_Username;
	public String MySQL_Password;
	public String MySQL_Database;
	public String MySQL_Port;

	public String NoPerm;
	public String NoConsole;

	public String Message_GroupAdd;
	public String Message_GroupAddFAILED;
	public String Message_UserGroupSet;
	public String Message_UserGroupSetFAILED;

	public void onEnable() {
		intsance = this;
		Config = new Config();
		MySQL = new MySQL();
		PermUtils permUtils = new PermUtils();
		Config.setDefaults();
		Config.load();
		sendMessage(SendMessageEnum.CONSOLE, Prefix + "§aEnabled!");
		if (UseMySQL)
			MySQL.connect();
		permUtils.createDefaultTabels();
		Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
		setCustomPermissibleBaseAll();
	}

	private void setCustomPermissibleBaseAll() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			try {
				CustomPermissibleBase.inject(p);
			} catch (Exception e) {
				if (Debug) {
					System.err.println(e.getMessage());
				}
				System.err.println(
						"ERROR!!! - setCustomPermissibleBaseAll Exeption - Contact the developer for more informations!");
			}
		}
	}

	public void onDisable() {
		sendMessage(SendMessageEnum.CONSOLE, Prefix + "§cDisabled!");
		if (UseMySQL)
			MySQL.close();
	}

	/**
	 * @return the intsance
	 */
	public static Main getInstance() {
		return intsance;
	}

	/**
	 * @return the mySQL
	 */
	public MySQL getMySQL() {
		return MySQL;
	}

	public void sendMessage(SendMessageEnum e, String Message) {
		if (e == SendMessageEnum.CONSOLE) {
			Bukkit.getConsoleSender().sendMessage(Message);
		} else if (e == SendMessageEnum.ALL_PLAYERS) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				all.sendMessage(Message);
			}
		}
	}

	/**
	 * @param b
	 */
	public void updateConsoleOnly(boolean b) {
		// TODO Main:updateConsoleOnly Settings
		ConsoleOnly = b;
		if (b) {
			//
		} else {
			//
		}
	}

}

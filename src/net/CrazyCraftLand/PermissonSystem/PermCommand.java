package net.CrazyCraftLand.PermissonSystem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.CrazyCraftLand.PermissonSystem.PermUtils.PermAPI;
import net.CrazyCraftLand.PermissonSystem.PermUtils.PlayerAPI;

/**
 * 
 * Permisson System
 * @author ursinn (Ursin Filli)
 * @Copyright (c) 2017 Ursin Filli
 * @license CC-BY-SA 4.0 International
 *
 */
public class PermCommand implements CommandExecutor {

	PermUtils permUtils = new PermUtils();
	
	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("perm")) {
			if (sender.hasPermission("ccl.permission.command")) {
				if (args.length == 0) {
					/* /perm */
					String HelpText = "For Help: /perm help";
					if(Language.Language_Prefix == "de_DE") {
						HelpText = "Für Hilfe: /perm help";
					}
					sender.sendMessage("§a-§b-§c-§d-§e-");
					sender.sendMessage("§ePermission Plugin by ursinn");
					sender.sendMessage("§e" + HelpText);
					sender.sendMessage("§a-§b-§c-§d-§e-");
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help")) {
						/* /perm help */ //TODO
						sender.sendMessage("§a-§b-§c-§d-§e-");
						sender.sendMessage("§e/perm : ");
						sender.sendMessage("§e/perm help : ");
						sender.sendMessage("§e/perm info : ");
						sender.sendMessage(" ");
						sender.sendMessage("§e/perm group info <GroupName> : ");
						sender.sendMessage("§e/perm group list : ");
						sender.sendMessage("§e/perm group add <GroupName> <Prefix> : ");
						sender.sendMessage("§e/perm group remove <GroupName> : ");
						sender.sendMessage("§e/perm group userlist <GroupName> : ");
						sender.sendMessage(" ");
						sender.sendMessage("§e/perm user info <Username> : ");
						sender.sendMessage("§e/perm user set group <Username> <GroupName> : ");
						sender.sendMessage("§e/perm user addperm <Username> <Permission> : ");
						sender.sendMessage("§e/perm user remperm <Username> <Permission> : ");
						sender.sendMessage(" ");
						sender.sendMessage("§e/perm settings info : ");
						sender.sendMessage("§e/perm settings console : ");
						sender.sendMessage("§a-§b-§c-§d-§e-");
					} else if (args[0].equalsIgnoreCase("info")) {
						/* /perm info */
						sender.sendMessage("§a-§b-§c-§d-§e-");
						sender.sendMessage("§ePermission Plugin by ursinn");
						sender.sendMessage("§eVersion: §6" + Main.getInstance().getDescription().getVersion());
						sender.sendMessage("§eWebseite: https://dev.crazycraftland.net/Plugin/Spigot/PermSystem");
						sender.sendMessage("§a-§b-§c-§d-§e-");
					} else {
						sender.sendMessage("§c/perm");
					}
				} else if (args.length == 2) {
					if (args[0].equalsIgnoreCase("group")) {
						if (args[1].equalsIgnoreCase("list")) {
							/* /perm group list */
							PermAPI permAPI = permUtils.PermAPI();
							//TODO
						} else {
							sender.sendMessage("§c/perm");
						}
					} if (args[0].equalsIgnoreCase("settings")) {
						if (args[1].equalsIgnoreCase("info")) {
							/* /perm settings info */
							String Type = "§cFalse";
							if(Main.getInstance().ConsoleOnly) {
								Type = "§aTrue";
							}
							sender.sendMessage("§a-§b-§c-§d-§e-");
							sender.sendMessage("§eConsole Only: " + Type);
							sender.sendMessage("§a-§b-§c-§d-§e-");
						} else if (args[1].equalsIgnoreCase("console")) {
							/* /perm settings console */
							if (Main.getInstance().ConsoleOnly) {
								Main.getInstance().updateConsoleOnly(false);
								sender.sendMessage("§a-§b-§c-§d-§e-");
								sender.sendMessage("§eConsole Only §Disabled");
								sender.sendMessage("§a-§b-§c-§d-§e-");
							} else {
								Main.getInstance().updateConsoleOnly(true);
								sender.sendMessage("§a-§b-§c-§d-§e-");
								sender.sendMessage("§eConsole Only §aEnabled");
								sender.sendMessage("§a-§b-§c-§d-§e-");
							}
						} else {
							sender.sendMessage("§c/perm");
						}
					} else {
						sender.sendMessage("§c/perm");
					}
				} else if (args.length == 3) {
					if (args[0].equalsIgnoreCase("group")) {
						if (args[1].equalsIgnoreCase("info")) {
							/* /perm group info <GroupName> */
							String GroupName = args[2];
							PermAPI permAPI = permUtils.PermAPI();
							PermAPI permAPI2 = permUtils.PermAPI(permAPI.getGroupID(GroupName));
							sender.sendMessage("§a-§b-§c-§d-§e-");
							sender.sendMessage("§eName: §6" + GroupName);
							sender.sendMessage("§eID: §6" + permAPI.getGroupID(GroupName));
							sender.sendMessage("§ePrefix: §6" + permAPI2.getPrefix());
							sender.sendMessage("§a-§b-§c-§d-§e-");
						} else if (args[1].equalsIgnoreCase("remove")) {
							/* /perm group remove <GroupName> */
							String GroupName = args[2];
							PermAPI permAPI = permUtils.PermAPI();
							PermAPI permAPI2 = permUtils.PermAPI(permAPI.getGroupID(GroupName));
							//TODO
						} else if (args[1].equalsIgnoreCase("userlist")) {
							/* /perm group userlist <GroupName> */
							String GroupName = args[2];
							PermAPI permAPI = permUtils.PermAPI();
							PermAPI permAPI2 = permUtils.PermAPI(permAPI.getGroupID(GroupName));
							//TODO
						} else {
							sender.sendMessage("§c/perm");
						}
					} else if (args[0].equalsIgnoreCase("user")) {
						if (args[1].equalsIgnoreCase("info")) {
							/* /perm user info <Username> */
							String Username = args[2];
							Player t = Bukkit.getPlayer(Username);
							PlayerAPI playerAPI = permUtils.PlayerAPI(t.getUniqueId());
							PermAPI permAPI = permUtils.PermAPI(playerAPI.getGroup());
							sender.sendMessage("§a-§b-§c-§d-§e-");
							sender.sendMessage("§eUsername: §6" + Username);
							sender.sendMessage("§eID: §6" + playerAPI.getID());
							sender.sendMessage("§eGroup ID: §6" + playerAPI.getGroup());
							sender.sendMessage("§eGroup Name: §6" + permAPI.getName());
							sender.sendMessage("§a-§b-§c-§d-§e-");
						} else {
							sender.sendMessage("§c/perm");
						}
					} else {
						sender.sendMessage("§c/perm");
					}
				} else if (args.length == 4) {
					if (args[0].equalsIgnoreCase("user")) {
						if (args[1].equalsIgnoreCase("addperm")) {
							/* /perm user addperm <Username> <Permission> */
							String Username = args[2];
							String Permission = args[3];
							Player t = Bukkit.getPlayer(Username);
							PlayerAPI playerAPI = permUtils.PlayerAPI(t.getUniqueId());
							playerAPI.addSpecialPermission(Permission);
							sender.sendMessage("TEXT");//TODO
						} else if (args[1].equalsIgnoreCase("remperm")) {
							/* /perm user remperm <Username> <Permission> */
							String Username = args[2];
							String Permission = args[3];
							Player t = Bukkit.getPlayer(Username);
							PlayerAPI playerAPI = permUtils.PlayerAPI(t.getUniqueId());
							//TODO
						} else {
							sender.sendMessage("§c/perm");
						}
					} else if (args[0].equalsIgnoreCase("group")) {
						if (args[1].equalsIgnoreCase("add")) {
							/* /perm group add <GroupName> <Prefix> */
							String GroupName = args[2];
							String Prefix = args[3];
							PermAPI permAPI = permUtils.PermAPI();
							if (permAPI.registerGroup(GroupName, Prefix)) {
								sender.sendMessage(Main.getInstance().Message_GroupAdd);
							} else {
								sender.sendMessage(Main.getInstance().Message_GroupAddFAILED);
							}
						} else {
							sender.sendMessage("§c/perm");
						}
					} else {
						sender.sendMessage("§c/perm");
					}
				} else if (args.length == 5) {
					if (args[0].equalsIgnoreCase("user")) {
						if (args[1].equalsIgnoreCase("set")) {
							if (args[2].equalsIgnoreCase("group")) {
								/* /perm user set group <Username> <GroupName> */
								String Username = args[3];
								String GroupName = args[4];
								Player t = Bukkit.getPlayer(Username);
								PlayerAPI playerAPI = permUtils.PlayerAPI(t.getUniqueId());
								PermAPI permAPI = permUtils.PermAPI();
								if(permAPI.ExistGroupName(GroupName)) {
									int GroupID = permAPI.getGroupID(GroupName);
									playerAPI.setGroup(GroupID);
									sender.sendMessage(Main.getInstance().Message_UserGroupSet);
								} else {
									sender.sendMessage(Main.getInstance().Message_UserGroupSetFAILED);
								}
							} else {
								sender.sendMessage("§c/perm");
							}
						} else {
							sender.sendMessage("§c/perm");
						}
					} else {
						sender.sendMessage("§c/perm");
					}
				} else {
					sender.sendMessage("§c/perm");
				}
			} else {
				sender.sendMessage(Main.getInstance().NoPerm);
			}
		}
		return true;
	}

	
	
}

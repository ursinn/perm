package net.CrazyCraftLand.PermissonSystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * 
 * Permisson System
 * @author ursinn (Ursin Filli)
 * @Copyright (c) 2017 Ursin Filli
 * @license CC-BY-SA 4.0 International
 *
 */
public class PermCommand implements CommandExecutor {

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
							//TODO
						} else {
							sender.sendMessage("§c/perm");
						}
					} if (args[0].equalsIgnoreCase("settings")) {
						if (args[1].equalsIgnoreCase("info")) {
							/* /perm settings info */
							//TODO 
						} else if (args[1].equalsIgnoreCase("console")) {
							/* /perm settings console */
							//TODO 
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
							//TODO
						} else if (args[1].equalsIgnoreCase("remove")) {
							/* /perm group remove <GroupName> */
							String GroupName = args[2];
							//TODO 
						} else if (args[1].equalsIgnoreCase("userlist")) {
							/* /perm group userlist <GroupName> */
							String GroupName = args[2];
							//TODO
						} else {
							sender.sendMessage("§c/perm");
						}
					} else if (args[0].equalsIgnoreCase("user")) {
						if (args[1].equalsIgnoreCase("info")) {
							/* /perm user info <Username> */
							String Username = args[2];
							//TODO
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
							//TODO
						} else if (args[1].equalsIgnoreCase("remperm")) {
							/* /perm user remperm <Username> <Permission> */
							String Username = args[2];
							String Permission = args[3];
							//TODO
						} else {
							sender.sendMessage("§c/perm");
						}
					} else if (args[0].equalsIgnoreCase("group")) {
						if (args[1].equalsIgnoreCase("add")) {
							/* /perm group add <GroupName> <Prefix> */
							String GroupName = args[2];
							String Prefix = args[3];
							//TODO
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
								//TODO
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

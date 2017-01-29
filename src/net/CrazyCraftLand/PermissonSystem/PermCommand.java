package net.CrazyCraftLand.PermissonSystem;

import java.util.List;
import java.util.UUID;

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
 * 
 * @author ursinn (Ursin Filli)
 * @Copyright (c) 2017 Ursin Filli
 * @license CC-BY-SA 4.0 International
 *
 */
public class PermCommand implements CommandExecutor {

	PermUtils permUtils = new PermUtils();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("perm")) {
			if (sender.hasPermission("ccl.permission.command") || sender.hasPermission("ccl.permission.command.*")) {
				if (args.length == 0) {
					/* /perm */
					sender.sendMessage("§a-§b-§c-§d-§e-");
					sender.sendMessage("§ePermission Plugin by ursinn");
					sender.sendMessage("§e" + GetTextLang("HelpText"));
					sender.sendMessage("§a-§b-§c-§d-§e-");
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help")) {
						/* /perm help */
						sender.sendMessage("§a-§b-§c-§d-§e-");
						sender.sendMessage("§e/perm : " + GetTextLang("PermText"));
						sender.sendMessage("§e/perm help : " + GetTextLang("PermHelpText"));
						sender.sendMessage("§e/perm info : " + GetTextLang("PermInfoText"));
						sender.sendMessage(" ");
						sender.sendMessage("§e/perm group info <GroupName> : " + GetTextLang("PermGroupInfoText"));
						sender.sendMessage("§e/perm group list : " + GetTextLang("PermGroupListText"));
						sender.sendMessage(
								"§e/perm group add <GroupName> <Prefix> : " + GetTextLang("PermGroupAddText"));
						sender.sendMessage("§e/perm group remove <GroupName> : " + GetTextLang("PermGroupRemoveText"));
						sender.sendMessage(
								"§e/perm group userlist <GroupName> : " + GetTextLang("PermGroupUserlistText"));
						sender.sendMessage(" ");
						sender.sendMessage("§e/perm user info <Username> : " + GetTextLang("PermUserInfoText"));
						sender.sendMessage("§e/perm user set group <Username> <GroupName> : "
								+ GetTextLang("PermUserSetGroupText"));
						sender.sendMessage(
								"§e/perm user addperm <Username> <Permission> : " + GetTextLang("PermUserAddpermText"));
						sender.sendMessage(
								"§e/perm user remperm <Username> <Permission> : " + GetTextLang("PermUserRenpermText"));
						sender.sendMessage(" ");
						sender.sendMessage("§e/perm settings info : " + GetTextLang("PermSettingsInfoText"));
						sender.sendMessage("§e/perm settings console : " + GetTextLang("PermSettingsConsoleText"));
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
							if (!sender.hasPermission("ccl.permission.command.group.list")
									&& !sender.hasPermission("ccl.permission.command.*")
									&& !sender.hasPermission("ccl.permission.command.group.*")) {
								sender.sendMessage(Main.getInstance().NoPerm);
								return true;
							}
							PermAPI permAPI = permUtils.PermAPI();
							List<String> list = permAPI.getGroups();
							sender.sendMessage("§a-§b-§c-§d-§e-");
							if (!list.isEmpty()) {
								for (String s : list) {
									sender.sendMessage("§e" + s);
								}
							} else {
								sender.sendMessage(GetTextLang("NonGroupExist_PermGroupList"));
							}
							sender.sendMessage("§a-§b-§c-§d-§e-");
						} else {
							sender.sendMessage("§c/perm");
						}
					}
					if (args[0].equalsIgnoreCase("settings")) {
						if (args[1].equalsIgnoreCase("info")) {
							/* /perm settings info */
							if (!sender.hasPermission("ccl.permission.command.settings.info")
									&& !sender.hasPermission("ccl.permission.command.*")
									&& !sender.hasPermission("ccl.permission.command.settings.*")) {
								sender.sendMessage(Main.getInstance().NoPerm);
								return true;
							}
							String Type = "§cFalse";
							if (Main.getInstance().ConsoleOnly) {
								Type = "§aTrue";
							}
							sender.sendMessage("§a-§b-§c-§d-§e-");
							sender.sendMessage("§eConsole Only: " + Type);
							sender.sendMessage("§a-§b-§c-§d-§e-");
						} else if (args[1].equalsIgnoreCase("console")) {
							/* /perm settings console */
							if (!sender.hasPermission("ccl.permission.command.settings.console")
									&& !sender.hasPermission("ccl.permission.command.*")
									&& !sender.hasPermission("ccl.permission.command.settings.*")) {
								sender.sendMessage(Main.getInstance().NoPerm);
								return true;
							}
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
							if (!sender.hasPermission("ccl.permission.command.group.info")
									&& !sender.hasPermission("ccl.permission.command.*")
									&& !sender.hasPermission("ccl.permission.command.group.*")) {
								sender.sendMessage(Main.getInstance().NoPerm);
								return true;
							}
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
							if (!sender.hasPermission("ccl.permission.command.group.remove")
									&& !sender.hasPermission("ccl.permission.command.*")
									&& !sender.hasPermission("ccl.permission.command.group.*")) {
								sender.sendMessage(Main.getInstance().NoPerm);
								return true;
							}
							String GroupName = args[2];
							PermAPI permAPI = permUtils.PermAPI();
							PermAPI permAPI2 = permUtils.PermAPI(permAPI.getGroupID(GroupName));
							permAPI2.DeleteGroup();
							sender.sendMessage(GetTextLang("GroupDeletet_PermGroupRemove"));
						} else if (args[1].equalsIgnoreCase("userlist")) {
							/* /perm group userlist <GroupName> */
							if (!sender.hasPermission("ccl.permission.command.group.userlist")
									&& !sender.hasPermission("ccl.permission.command.*")
									&& !sender.hasPermission("ccl.permission.command.group.*")) {
								sender.sendMessage(Main.getInstance().NoPerm);
								return true;
							}
							String GroupName = args[2];
							PermAPI permAPI = permUtils.PermAPI();
							if (permAPI.ExistGroupName(GroupName)) {
								PermAPI permAPI2 = permUtils.PermAPI(permAPI.getGroupID(GroupName));
								List<String> list = permAPI2.getUsersGroupList();
								if (!list.isEmpty()) {
									sender.sendMessage("§a-§b-§c-§d-§e-");
									for (String s : list) {
										UUID uuid = UUID.fromString(s);
										sender.sendMessage("§e" + Bukkit.getPlayer(uuid).getName());
									}
									sender.sendMessage("§a-§b-§c-§d-§e-");
								} else {
									sender.sendMessage(GetTextLang("NoUserinGroup_PermGroupUserlist"));
								}
							} else {
								sender.sendMessage(GetTextLang("GroupExistNot_PermGroupUserlist"));
							}
						} else {
							sender.sendMessage("§c/perm");
						}
					} else if (args[0].equalsIgnoreCase("user")) {
						if (args[1].equalsIgnoreCase("info")) {
							/* /perm user info <Username> */
							if (!sender.hasPermission("ccl.permission.command.user.info")
									&& !sender.hasPermission("ccl.permission.command.*")
									&& !sender.hasPermission("ccl.permission.command.user.*")) {
								sender.sendMessage(Main.getInstance().NoPerm);
								return true;
							}
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
							if (!sender.hasPermission("ccl.permission.command.user.addperm")
									&& !sender.hasPermission("ccl.permission.command.*")
									&& !sender.hasPermission("ccl.permission.command.user.*")) {
								sender.sendMessage(Main.getInstance().NoPerm);
								return true;
							}
							String Username = args[2];
							String Permission = args[3];
							Player t = Bukkit.getPlayer(Username);
							PlayerAPI playerAPI = permUtils.PlayerAPI(t.getUniqueId());
							playerAPI.addSpecialPermission(Permission);
							sender.sendMessage(GetTextLang("PermissionAdded_PermUserAddperm"));
						} else if (args[1].equalsIgnoreCase("remperm")) {
							/* /perm user remperm <Username> <Permission> */
							if (!sender.hasPermission("ccl.permission.command.user.remperm")
									&& !sender.hasPermission("ccl.permission.command.*")
									&& !sender.hasPermission("ccl.permission.command.user.*")) {
								sender.sendMessage(Main.getInstance().NoPerm);
								return true;
							}
							String Username = args[2];
							String Permission = args[3];
							Player t = Bukkit.getPlayer(Username);
							PlayerAPI playerAPI = permUtils.PlayerAPI(t.getUniqueId());
							playerAPI.removeSpecialPermission(Permission);
						} else {
							sender.sendMessage("§c/perm");
						}
					} else if (args[0].equalsIgnoreCase("group")) {
						if (args[1].equalsIgnoreCase("add")) {
							/* /perm group add <GroupName> <Prefix> */
							if (!sender.hasPermission("ccl.permission.command.group.add")
									&& !sender.hasPermission("ccl.permission.command.*")
									&& !sender.hasPermission("ccl.permission.command.group.*")) {
								sender.sendMessage(Main.getInstance().NoPerm);
								return true;
							}
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
								/*
								 * /perm user set group <Username> <GroupName>
								 */
								if (!sender.hasPermission("ccl.permission.command.user.setgroup")
										&& !sender.hasPermission("ccl.permission.command.*")
										&& !sender.hasPermission("ccl.permission.command.user.*")) {
									sender.sendMessage(Main.getInstance().NoPerm);
									return true;
								}
								String Username = args[3];
								String GroupName = args[4];
								Player t = Bukkit.getPlayer(Username);
								PlayerAPI playerAPI = permUtils.PlayerAPI(t.getUniqueId());
								PermAPI permAPI = permUtils.PermAPI();
								if (permAPI.ExistGroupName(GroupName)) {
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

	/**
	 * @param string
	 * @return
	 */
	private String GetTextLang(String string) {
		String s = "";
		boolean de_DE = false;
		if (Language.Language_Prefix == "de_DE")
			de_DE = true;

		/* --- */ // XXX Translation Commands

		if (string == "HelpText") {
			if (de_DE)
				s = "Für Hilfe: /perm help";
			else
				s = "For Help: /perm help";
		} else if (string == "PermText") {
			if (de_DE)
				s = "Hauptbehfel des Permission Systems";
			else
				s = "";
		} else if (string == "PermHelpText") {
			if (de_DE)
				s = "Zeigt Alle Behfele des Permission Systems";
			else
				s = "";
		} else if (string == "PermInfoText") {
			if (de_DE)
				s = "Zeigt Infos über das Permission System";
			else
				s = "";
		} else if (string == "PermGroupInfoText") {
			if (de_DE)
				s = "Zeigt Infos über die Gruppe <GroupName>";
			else
				s = "";
		} else if (string == "PermGroupListText") {
			if (de_DE)
				s = "Zeigt Alle Gruppen";
			else
				s = "";
		} else if (string == "PermGroupAddText") {
			if (de_DE)
				s = "Füght die Gruppe <GroupName> Hinzuh";
			else
				s = "";
		} else if (string == "PermGroupRemoveText") {
			if (de_DE)
				s = "Entfernt die Gruppe <GroupName>";
			else
				s = "";
		} else if (string == "PermGroupUserlistText") {
			if (de_DE)
				s = "Zeigt Alle User der Gruppe <GroupName>";
			else
				s = "";
		} else if (string == "PermUserInfoText") {
			if (de_DE)
				s = "Zeigt Infos über den User <Username>";
			else
				s = "";
		} else if (string == "PermUserSetGroupText") {
			if (de_DE)
				s = "Setzt den Spieler <Username> in die Gruppe <GroupName>";
			else
				s = "";
		} else if (string == "PermUserAddpermText") {
			if (de_DE)
				s = "Füght dem User <Username> Speziel Rechte Hinzuh";
			else
				s = "";
		} else if (string == "PermUserRenpermText") {
			if (de_DE)
				s = "Entfenrt dem User <Username> Speziele Rechte";
			else
				s = "";
		} else if (string == "PermSettingsInfoText") {
			if (de_DE)
				s = "Zeigt die Einstellungen";
			else
				s = "";
		} else if (string == "PermSettingsConsoleText") {
			if (de_DE)
				s = "Ändert den ConsoleOnly Modus";
			else
				s = "";
		} else if (string == "NonGroupExist_PermGroupList") {
			if (de_DE)
				s = Main.getInstance().Prefix + "§cEs gibt noch Keine Gruppen!";
			else
				s = "";
		} else if (string == "PermissionAdded_PermUserAddperm") {
			if (de_DE)
				s = Main.getInstance().Prefix + "§aPermission dem Spieler Erfolgreich Hinzugefügt!";
			else
				s = "";
		} else if (string == "GroupDeletet_PermGroupRemove") {
			if (de_DE)
				s = Main.getInstance().Prefix + "§aGruppe Erfolgreich Gelöscht!";
			else
				s = "";
		} else if (string == "NoUserinGroup_PermGroupUserlist") {
			if (de_DE)
				s = Main.getInstance().Prefix + "§cIn dieser Gruppe ist Kein User!";
			else
				s = "";
		} else if (string == "GroupExistNot_PermGroupUserlist") {
			if (de_DE)
				s = Main.getInstance().Prefix + "§cDiese Gruppe Exestiert nicht!";
			else
				s = "";
		}
		return s;
	}

}

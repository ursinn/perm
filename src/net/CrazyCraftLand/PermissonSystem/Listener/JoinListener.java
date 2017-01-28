package net.CrazyCraftLand.PermissonSystem.Listener;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import net.CrazyCraftLand.PermissonSystem.CustomPermissibleBase;
import net.CrazyCraftLand.PermissonSystem.Main;
import net.CrazyCraftLand.PermissonSystem.PermUtils;
import net.CrazyCraftLand.PermissonSystem.PermUtils.PermAPI;
import net.CrazyCraftLand.PermissonSystem.PermUtils.PlayerAPI;
import net.CrazyCraftLand.PermissonSystem.SendMessageEnum;

/**
 * 
 * Permisson System
 * 
 * @author ursinn (Ursin Filli)
 * @Copyright (c) 2017 Ursin Filli
 * @license CC-BY-SA 4.0 International
 *
 */
public class JoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		setCustomPermissibleBase(p);
		checkNotifiyDeveloper(p);
		setPermissons(p);
	}

	/**
	 * @param p
	 */
	private void setPermissons(Player p) {
		PermUtils utils = new PermUtils();
		PlayerAPI playerAPI = utils.PlayerAPI(p.getUniqueId());
		int GroupID = playerAPI.getGroup();
		PermAPI permAPI = utils.PermAPI(GroupID);
		PermissionAttachment attachment = p.addAttachment(Main.getInstance());
		for (String Perms : permAPI.getGroupPermisonsOrdet()) {
			attachment.setPermission(Perms, true);
		}
		for (String Perms : playerAPI.getSpecialPermissions()) {
			attachment.setPermission(Perms, true);
		}
	}

	/**
	 * @param p
	 */
	private void checkNotifiyDeveloper(Player p) {
		if (p.getName() == "ursinn" && p.getUniqueId() == UUID.fromString("84ec3ae6-fed0-426d-b714-2c368eafbbb3")) {
			if (Main.getInstance().NotifyDeveloper) {
				p.sendMessage("§eDieser Server Nutzt dein PermissonSystem für Spigot in der Version v"
						+ Main.getInstance().getDescription().getVersion());
			} else {
				Main.getInstance().sendMessage(SendMessageEnum.CONSOLE, Main.getInstance().Prefix
						+ "Schade das ich nicht wissen darf das ihr mein PermissonSystem Nutzt - ursinn (The developer of the PermissonSystem Plugin)");
			}
		}
	}

	/**
	 * @param p
	 */
	private void setCustomPermissibleBase(Player p) {
		try {
			CustomPermissibleBase.inject(p);
		} catch (Exception e) {
			if (Main.getInstance().Debug) {
				System.err.println(e.getMessage());
			}
			System.err.println(
					"ERROR!!! - setCustomPermissibleBaseAll Exeption - Contact the developer for more informations!");
		}
	}

}

package net.CrazyCraftLand.PermissonSystem.Listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.CrazyCraftLand.PermissonSystem.Main;
import net.CrazyCraftLand.PermissonSystem.PermUtils;
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
public class ChatListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if(Main.getInstance().UseChat) {
			PermUtils permUtils = new PermUtils();
			PlayerAPI playerAPI = permUtils.PlayerAPI(e.getPlayer().getUniqueId());
			PermAPI permAPI = permUtils.PermAPI(playerAPI.getGroup());
			e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
			e.setFormat(permAPI.getPrefix() + " §7: §f" + e.getMessage());
		}
	}
	
}

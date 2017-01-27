package net.CrazyCraftLand.PermissonSystem;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.ServerOperator;

/**
 * 
 * Permisson System
 * 
 * @author ursinn (Ursin Filli)
 * @Copyright (c) 2017 Ursin Filli
 * @license CC-BY-SA 4.0 International
 *
 */
public class CustomPermissibleBase extends PermissibleBase {

	public CustomPermissibleBase(ServerOperator op) {
		super(op);
	}

	@Override
	public boolean hasPermission(String inName) {
		if (super.hasPermission("-" + inName) || super.hasPermission("-*")) {
			return false;
		}
		if (super.hasPermission("*")) {
			return true;
		}
		return super.hasPermission(inName);
	}

	public static void inject(Player p) throws Exception {
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		Field field = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftHumanEntity")
				.getDeclaredField("perm");
		field.setAccessible(true);
		Field modifiersField = field.getClass().getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
		field.set(p, new CustomPermissibleBase(p));
	}

}

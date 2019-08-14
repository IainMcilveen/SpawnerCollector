package com.iain.spawnercollector;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static final ItemStack crowbar = new ItemStack(Material.GOLDEN_HOE);

	static {
		ItemMeta crowbarMeta = crowbar.getItemMeta();
		crowbarMeta.setDisplayName("Crowbar");
		crowbarMeta.setLore(Arrays.asList("", "Can be used to break spawners."));
		crowbar.setItemMeta(crowbarMeta);
	}

	@Override
	public void onEnable() {

		Bukkit.getPluginManager().registerEvents(new EventHandle(this), this);
		Bukkit.getOnlinePlayers().forEach((player) -> {
			player.getInventory().addItem(crowbar);
		});

	}

	@Override
	public void onDisable() {

	}

}

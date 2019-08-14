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
		crowbarMeta.setLore(Arrays.asList("", "Can be used to break spawners.","","Uses left: 5"));
		crowbar.setItemMeta(crowbarMeta);
	}
	
	
	public static ItemStack createCrowbar(int uses) {
		ItemStack newCrowbar = new ItemStack(Material.GOLDEN_HOE);
		ItemMeta crowbarMeta = newCrowbar.getItemMeta();
		crowbarMeta.setDisplayName("Crowbar");
		crowbarMeta.setLore(Arrays.asList("", "Can be used to break spawners.","","Uses left: "+uses));
		newCrowbar.setItemMeta(crowbarMeta);
		return newCrowbar;
	}

	@Override
	public void onEnable() {
		
		Bukkit.getPluginManager().registerEvents(new EventHandle(this), this);
		
		
		CommandHandle commandhandle = new CommandHandle();
		this.getCommand("crowbar").setExecutor(commandhandle);

	}
	
	
	@Override
	public void onDisable() {

	}

}

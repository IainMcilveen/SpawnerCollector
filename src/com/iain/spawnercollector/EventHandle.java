package com.iain.spawnercollector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class EventHandle implements Listener {

	private Main main;

	public EventHandle(Main main) {
		this.main = main;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void startBreaking(PlayerInteractEvent event) {
		
		if (event.getAction() == Action.LEFT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.SPAWNER) {
			Player player = event.getPlayer();
			//checking lore to see item
			List<String> itemLore = player.getInventory().getItemInMainHand().getItemMeta().getLore();
			String loreSentence = itemLore.get(1);
			
			if (loreSentence.equals("Can be used to break spawners.")) {
	
				//dealing with the block
				Block block = event.getClickedBlock();
				CreatureSpawner spawner = (CreatureSpawner) block.getState();
				EntityType type = spawner.getSpawnedType();
				ItemStack item = new ItemStack(Material.SPAWNER);
				NamespacedKey key = new NamespacedKey(main, "type");
				ItemMeta meta = item.getItemMeta();
				meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, type.getName());
				item.setItemMeta(meta);
				block.breakNaturally();
				spawner.getWorld().dropItem(spawner.getLocation(), item);
				
				//decreasing the uses
				ItemStack playerCrowbar = player.getInventory().getItemInMainHand();
				ItemMeta playerCrowbarMeta = playerCrowbar.getItemMeta();
				List<String> lore = playerCrowbarMeta.getLore();
				String usesBeforeParse = lore.get(3);
				
				//parsing uses from lore
				char[] chars = usesBeforeParse.toCharArray();
				int passedSpaces = 2;
				String usesBeforeString = "";
				for(char letter : chars) {
					if(String.valueOf(letter).equals(" ")) {
						passedSpaces -= 1;
					}else if(passedSpaces == 0) {
						usesBeforeString += String.valueOf(letter);
					}
					
				}
					
				int usesLeft = Integer.parseInt(usesBeforeString)-1;
				
				//setting new uses or removing
				if(usesLeft < 1) {
					player.getInventory().remove(playerCrowbar);
				}else {
					lore.set(3,"Uses left: "+usesLeft);
					playerCrowbarMeta.setLore(lore);
					playerCrowbar.setItemMeta(playerCrowbarMeta);
				}
				
				
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		Block block = event.getBlock();
		if (block.getType() == Material.SPAWNER) {

			ItemStack item = event.getItemInHand();
			NamespacedKey key = new NamespacedKey(main, "type");
			EntityType entity = EntityType
					.fromName(item.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING));
			CreatureSpawner state = (CreatureSpawner) block.getState();
			if(entity != null) {
				state.setSpawnedType(entity);
				state.update();
			}
		}
	}


}

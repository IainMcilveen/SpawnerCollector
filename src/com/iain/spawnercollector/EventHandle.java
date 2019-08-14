package com.iain.spawnercollector;

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
			if (player.getInventory().getItemInMainHand().isSimilar(Main.crowbar)) {
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
			state.setSpawnedType(entity);
			state.update();
		}
	}

	// event player animation event, see if the hand moving event holding the
	// crowbar

	// event for breaking blocks, specifically the spawner and the player holding
	// the crowbar

}

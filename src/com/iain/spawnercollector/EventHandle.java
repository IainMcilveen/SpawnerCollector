package com.iain.spawnercollector;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;

public class EventHandle implements Listener {
	
	
	@EventHandler
	public void startBreaking(PlayerAnimationEvent event) {
		if(event.getAnimationType() == PlayerAnimationType.ARM_SWING) {
			Player player = event.getPlayer();
		}
	
	}
	
	@EventHandler
	public void finishedBreaking(BlockBreakEvent event) {
		
	}
	
		
	
	//event player animation event, see if the hand moving event holding the crowbar
	
	//event for breaking blocks, specifically the spawner and the player holding the crowbar
	
	
}

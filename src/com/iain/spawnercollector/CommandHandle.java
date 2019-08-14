package com.iain.spawnercollector;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandHandle implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		int uses;
		boolean playerExists = false;
		Player recipient = null;
		
		if (label.equals("crowbar")) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player.getDisplayName().equals(args[0])) {
					playerExists = true;
					recipient = player;
				}
			}
			
			if(playerExists){
				try {
					uses = Integer.parseInt(args[1]);
				} catch (Exception e) {
					return false;
				}
				recipient.getInventory().addItem(Main.createCrowbar(uses));	
				return true;
			}

		}
		return false;
	}

}

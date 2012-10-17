package org.maxgamer.maxbans.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.maxgamer.maxbans.MaxBans;
import org.maxgamer.maxbans.banmanager.Mute;
import org.maxgamer.maxbans.banmanager.TempMute;

/**
 * 
 * @author Netherfoam
 *
 */
public class ChatCommandListener implements Listener{
	MaxBans plugin;
	
	public ChatCommandListener(MaxBans plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onCommand(PlayerCommandPreprocessEvent e){
		if(e.isCancelled()) return;
		String cmd = e.getMessage().split(" ")[0].replaceFirst("/", "");
		
		if(plugin.getBanManager().isChatCommand(cmd)){
			Player p = e.getPlayer();
	        Mute mute = plugin.getBanManager().getMute(p.getName());
	        if (mute != null) {
	        	if(mute instanceof TempMute){
	        		TempMute tMute = (TempMute) mute;
	        		p.sendMessage(ChatColor.RED+"You're muted for another " + plugin.getBanManager().getTimeUntil(tMute.getExpires()));
	        	}
	        	else{
	        		p.sendMessage(ChatColor.RED+"You're muted!");
	        	}
	        	
	            e.setCancelled(true);
	        }
		}
	}
}

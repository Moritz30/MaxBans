package org.maxgamer.maxbans.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.maxgamer.maxbans.MaxBans;

public class TempMuteCommand implements CommandExecutor{
    private MaxBans plugin;
    public TempMuteCommand(MaxBans plugin){
        this.plugin = plugin;
    }
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("maxbans.tempmute")){
			sender.sendMessage(ChatColor.RED + "You don't have permission to do that");
			return true;
		}
		String usage = ChatColor.RED + "Usage: /tempmute <player>";
		
		if(args.length > 0){
			String name = args[0];
			name = plugin.getBanManager().match(name);
			if(name == null){
				name = args[0]; //Use exact name then.
			}
			
			String banner;
			
			if(sender instanceof Player){
				banner = ((Player) sender).getName();
			}
			else{
				banner = "Console";
			}
			
			long time = plugin.getBanManager().getTime(args);
			
			if(time <= 0){
				sender.sendMessage(usage);
				return true;
			}
			time += System.currentTimeMillis();
			
			plugin.getBanManager().tempmute(name, banner, time);
			return true;
		}
		else{
			sender.sendMessage(usage);
			return true;
		}
	}
}

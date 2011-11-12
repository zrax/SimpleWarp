package com.bukkit.JamesArchuleta.Warp;


import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class CmdListWarps extends Command {

	protected CmdListWarps(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Boolean run(CommandSender sender,
			org.bukkit.command.Command command, String commandLabel,
			String[] args, Server server) {

		if (!hasPermissions((Player) sender)) {
			((Player) sender)
					.sendMessage("You do not have permission to use this command.");
			return true;
		}

		Player player = (Player) sender;
		
		String filter = args.length >= 1 ? args[0] : "";
		String[] locs = Locations.getList(filter);
		if (locs != null && locs.length >= 1) {
			for (String i : locs) {
				player.sendMessage(i);
			}
		} else {
			player.sendMessage("No Warps Found");
		}
		

		System.out.println(player.getName() + " listed warps ");
		return true;

	}
	

}

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

        if (sender instanceof Player && !hasPermissions((Player) sender)) {
            sender.sendMessage("You do not have permission to use this command.");
            return true;
        }

        String filter = args.length >= 1 ? args[0] : "";
        String[] locs = Locations.getList(filter, !(sender instanceof Player));
        if (locs != null && locs.length >= 1) {
            for (String i : locs) {
                sender.sendMessage(i);
            }
        } else {
            sender.sendMessage("No Warps Found");
        }

        if (sender instanceof Player) {
            System.out.println(((Player) sender).getName() + " listed warps ");
        }
        return true;

    }

}

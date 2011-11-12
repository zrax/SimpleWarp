package com.bukkit.JamesArchuleta.Warp;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdRemoveWarp extends Command {

    protected CmdRemoveWarp(String name) {
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

        if (args.length == 1 && hasPermissions((Player) sender)) {
            Player player = (Player) sender;

            if (Locations.removeLineFromFile(args[0])) {
                player.sendMessage("Warp removed: " + args[0]);
                System.out.println(player.getName() + " removed warp: "
                        + args[0]);
            } else {
                player.sendMessage("Warp not found: " + args[0]);
                System.out.println(player.getName()
                        + " tried to remove warp (not found): " + args[0]);
            }
            return true;
        }
        return false;

    }

}

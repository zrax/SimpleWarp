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

        if (sender instanceof Player && !hasPermissions((Player) sender)) {
            sender.sendMessage("You do not have permission to use this command.");
            return true;
        }

        if (args.length == 1) {
            if (Locations.removeLineFromFile(args[0])) {
                sender.sendMessage("Warp removed: " + args[0]);
                if (sender instanceof Player) {
                    System.out.println(((Player) sender).getName() + " removed warp: "
                            + args[0]);
                }
            } else {
                sender.sendMessage("Warp not found: " + args[0]);
                if (sender instanceof Player) {
                    System.out.println(((Player) sender).getName()
                            + " tried to remove warp (not found): " + args[0]);
                }
            }
            return true;
        }
        return false;

    }

}

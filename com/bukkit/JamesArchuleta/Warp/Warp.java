package com.bukkit.JamesArchuleta.Warp;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.swing.ListCellRenderer;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;





/**
 * Warp for Bukkit
 *
 * @author James Archuleta
 */
public class Warp extends JavaPlugin {

    //private final WarpPlayerListener playerListener = new WarpPlayerListener(this);
    public static Object Permissions = null;
    public static Boolean hasPermissions;
    private Logger log =  Logger.getLogger("Minecraft");

    Command cmdList[] = new Command[] {
            new CmdWarp("warp"),
            new CmdWarpTo("warpto"),
            new CmdSetWarp("setwarp"),
            new CmdListWarps("listwarps"),
            new CmdRemoveWarp("removewarp")};


    public Warp() {

        Warp.hasPermissions = false;
    }


    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events
        PluginDescriptionFile pdfFile = this.getDescription();

        log.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled" );

        setupPermission();

    }

    private void setupPermission() {
        try{
            Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");

            if(Warp.Permissions == null) {
                if(test != null) {
                    Warp.Permissions = ((Permissions)test).getHandler();
                    Warp.hasPermissions = true;
                }else{
                    //log.info(Messaging.bracketize(pdfFile.getName() + " Permission system not enabled. Disabling plugin."));
                    //this.getServer().getPluginManager().disablePlugin(this);
                }
            }
        }catch (Exception e){
            //do nothing
        }
    }
    public void onDisable() {
        // TODO: Place any custom disable code here

        // NOTE: All registered events are automatically unregistered when a plugin is disabled

        // EXAMPLE: Custom code,{ here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is disabled" );
    }


    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String commandLabel, String[] args) {

        Boolean b = false;

        for(Command c : this.cmdList){

            if ( command.getName().toLowerCase().equalsIgnoreCase(c.getName().toLowerCase())   ){
                b = c.run(sender, command, commandLabel, args, getServer());

                if (b) {break;}
            }
        }

        return b;

    }

}

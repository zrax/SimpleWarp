package com.bukkit.JamesArchuleta.Warp;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.nijikokun.bukkit.Permissions.Permissions;


import com.nijiko.permissions.PermissionHandler;



public abstract class Command implements ICommand
{

  private final String name;
   

  protected Command(String name)
     {
       this.name = name;
    }
  
     public String getName()
   {
      return this.name;
    }
     
    
  

protected Boolean hasPermissions(Player player){
	Boolean b = true;
	
	if (Warp.hasPermissions){
	
		try{
			 b=((PermissionHandler)Warp.Permissions).has(player, "SimpleWarp." + name.toLowerCase());	
		}
		catch(Exception e){
			b=true;
		}
	}else		{
			b = true;
		}
	
	
	return b; 
}

public Boolean run(CommandSender sender, org.bukkit.command.Command command, String commandLabel,
		String[] args, Server server)
		 {
	// TODO Auto-generated method stub
	return null;
}

 
}
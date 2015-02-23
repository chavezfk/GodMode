package nmt.minecraft.chavezfk.GodMode;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * 
 * @author chavezfk (Franz Chavez)
 * @version 1.00
 * Saves a player's stats and inventory when they are in survival and changes the player's game mode to creative
 * then when the player returns to creative, the stats and inventory for their survival mode is restored to them. 
 */
public class GodMode extends JavaPlugin implements Listener {
	private Map<UUID, Inventory> godInventories; //initialize godInventories hash map
	private Map<UUID, Double> godExp;
	private Map<UUID, Integer> godLevel;
	@Override 
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
		getLogger().info("You have implemented God Mode! Non Gods BEWARE! New Edition");
		godInventories = new HashMap<UUID,Inventory>();
		godExp = new HashMap<UUID,Double>();
		godLevel = new HashMap<UUID,Integer>();
	}
	/**
	 * Handles the command for god mode
	 * @param sender is the sender of the command
	 * @param cmd is the command
	 * @param label is the command label
	 * @param args is the arguments sent with the command
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
		if(cmd.getName().equalsIgnoreCase("god") && sender instanceof Player && args.length == 0){
			Player p = (Player) sender;
			Metadatable m = (Metadatable) p;
			if(m.hasMetadata("god")){ //test to see if the sender is already in god mode
				
				p.getInventory().clear(); //clears creative inventory
				p.getInventory().setContents(godInventories.get(p.getUniqueId()).getContents());  //sets the inventory from the Map
				
				p.setExp(godExp.get(p.getUniqueId()).floatValue()); //sets the xp and level back
				p.setLevel(godLevel.get(p.getUniqueId()).intValue());
				
				godInventories.remove(p.getUniqueId());  //removes that specific inventory for that player to save memory.
				m.removeMetadata("god", this); //removes the Metadata value that tests for god mode
				
				p.setGameMode(GameMode.SURVIVAL); //changes game mode
				p.sendMessage("No one wants to be mortal... youll be back...");
				
				
			}else{ //else set god mode true.
				//p.sendMessage("working on it");  // debugging to allow the programmer to see that the program is working
				godInventories.put(p.getUniqueId(), p.getInventory()); //stores current inventory in inventory Map
				
				godExp.put(p.getUniqueId(),Double.valueOf((double)p.getExp()));//stores xp in the xp map
				
				godLevel.put(p.getUniqueId(), Integer.valueOf(p.getLevel())); //stores level in the level map
				
				p.getInventory().clear();
				
				m.setMetadata("god", new FixedMetadataValue(this,1)); //sets that the player is in god mode
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage("Enjoy your new found godliness! :D");
				}
			return true;
		}else{
			if(args.length == 0)sender.sendMessage("Incorrect usage. usage: /god");
			else sender.sendMessage("you must be a player! No console commands here buddy!");
		}
		return false;
	}
}

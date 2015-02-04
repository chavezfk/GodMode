package nmt.minecraft.chavezfk.GodMode;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
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
	private ItemStack[] inv; 
	@Override 
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
		getLogger().info("You have implemented God Mode! Non Gods BEWARE!");
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
		if(cmd.getName().equalsIgnoreCase("god") && sender instanceof Player && args.length >= 0){
			Player p = (Player) sender;
			p.sendMessage("we are working" + args[0]);
			Metadatable m = (Metadatable) p;
			//PlayerSave savedPlayer = new PlayerSave();
			if(args[0] == "1"){
				if(m.hasMetadata("god")){
					if(m.getMetadata("god").get(0).asInt() == 1)
						p.sendMessage("youre already in god mode");
				}else{
					
				p.sendMessage("working on it");
				//savedPlayer.saveStats(p);
				inv = p.getInventory().getContents();
				m.setMetadata("god", new FixedMetadataValue(this,1));
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage("saved...");
				}
			}
			if(args[0] == "0"){
				p.sendMessage("working on it");
				//savedPlayer.saveStats(p);
				p.getInventory().setContents(inv);
				p.setGameMode(GameMode.SURVIVAL);
				p.sendMessage("saved...");
			}
			return true;
		}else{
			if(args.length <=0)sender.sendMessage("Incorrect usage. usage: /god [1 or 0]");
			else sender.sendMessage("you must be a player! No console commands here buddy!");
		}
		return false;
	}
}

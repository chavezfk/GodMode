package nmt.minecraft.chavezfk.GodMode;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * 
 * @author chavezfk (Franz Chavez)
 * @version 1.0
 * Saves a player's stats and inventory when they are in survival and changes the player's game mode to creative
 * then when the player returns to creative, the stats and inventory for their survival mode is restored to them. 
 */
public class GodMode extends JavaPlugin implements Listener {
	@Override 
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
		getLogger().info("You have implemented God Mode! Non Gods BEWARE!");
	}
}

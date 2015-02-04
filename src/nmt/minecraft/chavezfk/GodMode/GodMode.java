package nmt.minecraft.chavezfk.GodMode;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class GodMode extends JavaPlugin implements Listener {
	@Override 
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
		getLogger().info("You have implemented God Mode! Non Gods BEWARE!");
	}
}

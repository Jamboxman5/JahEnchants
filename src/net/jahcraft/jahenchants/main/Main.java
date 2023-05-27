package net.jahcraft.jahenchants.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.jahcraft.jahenchants.enchantments.CustomEnchantments;
import net.jahcraft.jahenchants.listeners.AdhesionListener;
import net.jahcraft.jahenchants.listeners.AnvilListener;
import net.jahcraft.jahenchants.listeners.CombustionListener;
import net.jahcraft.jahenchants.listeners.EnchantingListener;
import net.jahcraft.jahenchants.listeners.FloatingListener;
import net.jahcraft.jahenchants.listeners.TooExpensiveListener;
import net.jahcraft.jahenchants.listeners.TunnelingListener;
import net.jahcraft.jahenchants.listeners.VeinMinerListener;
import net.jahcraft.jahenchants.runnables.BookThread;
import net.jahcraft.jahenchants.runnables.LoreThread;

public class Main extends JavaPlugin {
	
	public static boolean updateBooksModel = true;
	public static boolean updateEnchantsLore = true;

	@Override
	public void onEnable() {
		
		//JAHENCHANTS
				try {
					
					//RUNNABLES
					Bukkit.getScheduler().runTaskAsynchronously(this, new LoreThread()).getTaskId();
					Bukkit.getScheduler().runTaskAsynchronously(this, new BookThread()).getTaskId();
					
					//LISTENERS
					getServer().getPluginManager().registerEvents(new AdhesionListener(), this);
					getServer().getPluginManager().registerEvents(new FloatingListener(), this);
					getServer().getPluginManager().registerEvents(new CombustionListener(), this);
					getServer().getPluginManager().registerEvents(new TunnelingListener(), this);
					getServer().getPluginManager().registerEvents(new VeinMinerListener(this), this);
					getServer().getPluginManager().registerEvents(new EnchantingListener(), this);
					getServer().getPluginManager().registerEvents(new AnvilListener(), this);
					getServer().getPluginManager().registerEvents(new TooExpensiveListener(), this);
					
					//ENCHANTS
					CustomEnchantments.register();
					
				} catch (Exception e) {

					Bukkit.getLogger().warning("Failed to load JahEnchants!");
					e.printStackTrace();
					
				}
	}
	
	@Override 
	public void onDisable() {
		
		updateEnchantsLore = false;
		updateBooksModel = false;

		Bukkit.getLogger().info("JahEnchants Unloaded and Disabled!");
		
	}

}

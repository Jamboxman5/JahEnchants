package net.jahcraft.jahenchants.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.jahcraft.jahenchants.main.Main;
import net.jahcraft.jahenchants.util.EnchantmentMath;

public class LoreThread implements Runnable {

	@Override
	public void run() {

		while (Main.updateEnchantsLore) {
									
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				
				for (ItemStack i : p.getInventory().getContents()) {
					
					if (i != null && i.getItemMeta() != null) {
						
						EnchantmentMath.updateLore(i);
						
					}
					
				}
				
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}

	

}

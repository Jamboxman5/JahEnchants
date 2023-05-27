package net.jahcraft.jahenchants.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.jahcraft.jahenchants.enchantments.CustomEnchantments;
import net.jahcraft.jahenchants.util.FloatingEffect;

public class FloatingListener implements Listener {
	
	@EventHandler
	public void onFall(PlayerMoveEvent e) {
		
		if (e.getPlayer() == null) return;
		if (e.getPlayer().getInventory() == null) return;
		if (e.getPlayer().getInventory().getBoots() == null) return;
		if (e.getPlayer().getInventory().getBoots().getItemMeta() == null) return;
		if (!e.getPlayer().getInventory().getBoots().getItemMeta().hasEnchant(CustomEnchantments.FLOATING)) return;

		if (e.getPlayer().getVelocity().getY() > -.30) return;
		
		int duration = 0;
		int level = e.getPlayer().getInventory().getBoots().getItemMeta().getEnchantLevel(CustomEnchantments.FLOATING);
		
		if (level <= 1) {
			duration = 2;
		}
		if (level == 2) {
			duration = 5;
		}
		if (level == 3) {
			duration = 10;
		}
		if (level >= 4) {
			duration = 20;
		}
		
		e.getPlayer().addPotionEffect(new FloatingEffect(duration));
		
	}

}

package net.jahcraft.jahenchants.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.meta.ItemMeta;

import net.jahcraft.jahenchants.enchantments.CustomEnchantments;
import net.jahcraft.jahenchants.util.EnchantmentMath;
import net.md_5.bungee.api.ChatColor;

public class EnchantingListener implements Listener {
	
	@EventHandler
	public void onEnchant(EnchantItemEvent e) {
		
		if (e.getItem().getType() == Material.BOW) enchantBows(e);
		if (e.getItem().getType().toString().contains("PICKAXE")) enchantPickaxe(e);
		if (e.getItem().getType().toString().contains("BOOTS")) enchantBoots(e);
		
	}

	private void enchantPickaxe(EnchantItemEvent e) {

		if (e.getExpLevelCost() >= 30) {
			
			int VeinTunnelRandom = (int) (Math.random() * 2);
			
			if (VeinTunnelRandom == 0) {
				
				int odds = (int) (Math.random() * 100) + 1;
				
				if (odds >= 90) {
					e.getItem().addUnsafeEnchantment(CustomEnchantments.TUNNELING, 1);
					EnchantmentMath.updateLore(e.getItem());	
					
				}
			}
			if (VeinTunnelRandom == 1) {
				
				int odds = (int) (Math.random() * 100) + 1;
				
				if (odds >= 80) {
					e.getItem().addUnsafeEnchantment(CustomEnchantments.VEINMINER, 1);
					ItemMeta meta = e.getItem().getItemMeta();
					List<String> lore;
					if (meta.hasLore()) {
						lore = meta.getLore();
					} else {
						lore = new ArrayList<String>();
					}
					lore.add(0, ChatColor.GRAY + "Vein Miner");
					meta.setLore(lore);
					e.getItem().setItemMeta(meta);
					
				}
			}
				
			int odds = (int) (Math.random() * 100) + 1;
				
			if (odds >= 85) {
				e.getItem().addUnsafeEnchantment(CustomEnchantments.ADHESION, 1);
				EnchantmentMath.updateLore(e.getItem());	
					
			}
			
			
			
		}
		
	}

	private void enchantBoots(EnchantItemEvent e) {
		
		if (e.getExpLevelCost() >= 30) {
			
			if (!e.getEnchantsToAdd().containsKey(Enchantment.PROTECTION_FALL)) {
				
				int odds = (int) (Math.random() * 100) + 1;
				
				if (odds >= 75) {
					e.getItem().addUnsafeEnchantment(CustomEnchantments.FLOATING, 1);
					EnchantmentMath.updateLore(e.getItem());			
				}
			}
		}
	}
	
	private void enchantBows(EnchantItemEvent e) {
		
		if (e.getExpLevelCost() >= 30) {
			
			if (!e.getEnchantsToAdd().containsKey(Enchantment.ARROW_FIRE)) {
				
				int odds = (int) (Math.random() * 100) + 1;
				
				if (odds >= 90) {
					e.getItem().addUnsafeEnchantment(CustomEnchantments.COMBUSTION, (int)(Math.random()*3)+1);
					EnchantmentMath.updateLore(e.getItem());	
					
				}
			}
		}
	}
	

}

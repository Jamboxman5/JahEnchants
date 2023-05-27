package net.jahcraft.jahenchants.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.jahcraft.jahenchants.enchantments.CustomEnchantments;
import net.jahcraft.jahenchants.util.EnchantmentMath;
import net.md_5.bungee.api.ChatColor;

public class AnvilListener implements Listener {

	@EventHandler
	public void onAnvilExchange(PrepareAnvilEvent e) {
		
		HashMap<Enchantment, Integer> enchs = new HashMap<>();
		ArrayList<Enchantment> conflictingenchs = new ArrayList<>();
				
		ItemStack[] reactants = e.getInventory().getContents();
		
		TooExpensiveListener.costStorage.put(e.getView().getPlayer().getUniqueId(), e.getInventory().getRepairCost());
		
		if (reactants[0] == null || reactants[1] == null) return;
		if (reactants[0].getType() != reactants[1].getType()) return;
		if (!(hasCustomEnchant(reactants[0]) || hasCustomEnchant(reactants[1]))) return;
		
		
		if (hasCustomEnchant(reactants[0])) {
			for (Enchantment ench : reactants[0].getItemMeta().getEnchants().keySet()) {
				if (CustomEnchantments.getEnchantsList().contains(ench)) {
					enchs.put(ench, reactants[0].getItemMeta().getEnchantLevel(ench));
				}
			}
		}
		
		TooExpensiveListener.costStorage.put(e.getView().getPlayer().getUniqueId(), e.getInventory().getRepairCost());

		
		if (hasCustomEnchant(reactants[1])) {
			
			for (Enchantment ench : reactants[1].getItemMeta().getEnchants().keySet()) {
				if (CustomEnchantments.getEnchantsList().contains(ench)) {
					
					enchs.put(ench, reactants[1].getItemMeta().getEnchantLevel(ench));
					
					for (Enchantment estench : enchs.keySet()) {
						
						if (ench.conflictsWith(estench)) {
							conflictingenchs.add(ench);
						} else {
							enchs.putIfAbsent(ench, reactants[1].getItemMeta().getEnchantLevel(ench));
							
						}
					}
				}
			}
			
		}
		TooExpensiveListener.costStorage.put(e.getView().getPlayer().getUniqueId(), e.getInventory().getRepairCost());

		if ((hasCustomEnchant(reactants[0]) || hasCustomEnchant(reactants[1])) && e.getResult() == null) {
			
			ItemStack i = new ItemStack(reactants[0]);
			ItemMeta meta = i.getItemMeta();
			if (e.getInventory().getRenameText() != "") meta.setDisplayName(ChatColor.ITALIC + e.getInventory().getRenameText());
			i.setItemMeta(meta);
			i.addUnsafeEnchantments(enchs);
			for (Enchantment ench : conflictingenchs) {
				i.removeEnchantment(ench);
			}
			EnchantmentMath.updateLore(i);
			
			e.getInventory().setRepairCost(23);
			e.setResult(i);		
			TooExpensiveListener.costStorage.put(e.getView().getPlayer().getUniqueId(), e.getInventory().getRepairCost());

		} else {
			TooExpensiveListener.costStorage.put(e.getView().getPlayer().getUniqueId(), e.getInventory().getRepairCost());

			if (e.getResult() != null) {
				ItemStack i = new ItemStack(e.getResult());
				EnchantmentMath.updateLore(i);
				i.addUnsafeEnchantments(enchs);
				e.setResult(i);

			}
			

		}
		
		
		
	}
	
	private boolean hasCustomEnchant(ItemStack i) {
		
		if (i == null) return false;
		
		return (i.getItemMeta().hasEnchant(CustomEnchantments.ADHESION) ||
				i.getItemMeta().hasEnchant(CustomEnchantments.TUNNELING) ||
				i.getItemMeta().hasEnchant(CustomEnchantments.VEINMINER) ||
				i.getItemMeta().hasEnchant(CustomEnchantments.COMBUSTION));
		
	}
	
}

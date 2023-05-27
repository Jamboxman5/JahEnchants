package net.jahcraft.jahenchants.runnables;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import net.jahcraft.jahenchants.enchantments.CustomEnchantments;
import net.jahcraft.jahenchants.main.Main;

public class BookThread implements Runnable {

	@Override
	public void run() {

		while (Main.updateBooksModel) {
			
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				
				for (ItemStack i : p.getInventory().getContents()) {
					
					if (i != null && i.getType() == Material.ENCHANTED_BOOK) {
						
						if (getModelData(i) > 0) {
							int modelData = getModelData(i);
							
							if (!i.getItemMeta().hasCustomModelData()) {
								ItemMeta meta = i.getItemMeta();
								meta.setCustomModelData(modelData);
								i.setItemMeta(meta);
							} else if (i.getItemMeta().getCustomModelData() != modelData){
								ItemMeta meta = i.getItemMeta();
								meta.setCustomModelData(modelData);
								i.setItemMeta(meta);
							}
						}
						
					}
					
				}
				
			
				if (p.getOpenInventory() != null) {
					for (ItemStack i : p.getOpenInventory().getTopInventory().getContents()) {
						
						if (i != null && i.getType() == Material.ENCHANTED_BOOK) {
							
							if (getModelData(i) > 0) {
								int modelData = getModelData(i);
								
								if (!i.getItemMeta().hasCustomModelData()) {
									ItemMeta meta = i.getItemMeta();
									meta.setCustomModelData(modelData);
									i.setItemMeta(meta);
								} else if (i.getItemMeta().getCustomModelData() != modelData){
									ItemMeta meta = i.getItemMeta();
									meta.setCustomModelData(modelData);
									i.setItemMeta(meta);
								}
							}
							
							
							
						}
						
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
	
	private int getModelData(ItemStack i) {
		
		if (i.getType() != Material.ENCHANTED_BOOK) return -1;
		
		EnchantmentStorageMeta eMeta = (EnchantmentStorageMeta) i.getItemMeta();
		
		Map<Enchantment, Integer> enchants = eMeta.getStoredEnchants();
		
		if (enchants.containsKey(Enchantment.BINDING_CURSE) ||
			enchants.containsKey(Enchantment.VANISHING_CURSE)) return 8;
		
		if (enchants.containsKey(Enchantment.LUCK) ||
			enchants.containsKey(Enchantment.LURE)) return 7;
		
		if (enchants.containsKey(Enchantment.CHANNELING) ||
			enchants.containsKey(Enchantment.LOYALTY) ||
			enchants.containsKey(Enchantment.IMPALING) ||
			enchants.containsKey(Enchantment.RIPTIDE)) return 4;
		
		if (enchants.containsKey(Enchantment.MULTISHOT) ||
			enchants.containsKey(Enchantment.QUICK_CHARGE) ||
			enchants.containsKey(Enchantment.PIERCING)) return 6;
		
		if (enchants.containsKey(Enchantment.ARROW_DAMAGE) ||
			enchants.containsKey(Enchantment.ARROW_FIRE) ||
			enchants.containsKey(Enchantment.ARROW_KNOCKBACK) ||
			enchants.containsKey(CustomEnchantments.COMBUSTION) ||
			enchants.containsKey(Enchantment.ARROW_INFINITE)) return 5;
		
		if (enchants.containsKey(Enchantment.DIG_SPEED) ||
			enchants.containsKey(Enchantment.SILK_TOUCH) ||
			enchants.containsKey(Enchantment.LOOT_BONUS_BLOCKS) ||
			enchants.containsKey(CustomEnchantments.TUNNELING) ||
			enchants.containsKey(CustomEnchantments.VEINMINER) ||
			enchants.containsKey(CustomEnchantments.ADHESION)) return 3;
		
		if (enchants.containsKey(Enchantment.DAMAGE_ALL) ||
			enchants.containsKey(Enchantment.DAMAGE_ARTHROPODS) ||
			enchants.containsKey(Enchantment.DAMAGE_UNDEAD) ||
			enchants.containsKey(Enchantment.FIRE_ASPECT) ||
			enchants.containsKey(Enchantment.SWEEPING_EDGE) ||
			enchants.containsKey(Enchantment.LOOT_BONUS_MOBS) ||
			enchants.containsKey(Enchantment.KNOCKBACK)) return 2;
		
		if (enchants.containsKey(Enchantment.PROTECTION_ENVIRONMENTAL) ||
			enchants.containsKey(Enchantment.PROTECTION_EXPLOSIONS) ||
			enchants.containsKey(Enchantment.PROTECTION_FALL) ||
			enchants.containsKey(Enchantment.PROTECTION_FIRE) ||
			enchants.containsKey(Enchantment.PROTECTION_PROJECTILE) ||
			enchants.containsKey(Enchantment.DEPTH_STRIDER) ||
			enchants.containsKey(Enchantment.WATER_WORKER) ||
			enchants.containsKey(Enchantment.FROST_WALKER) ||
			enchants.containsKey(Enchantment.SWIFT_SNEAK) ||
			enchants.containsKey(Enchantment.SOUL_SPEED) ||
			enchants.containsKey(Enchantment.THORNS) ||
			enchants.containsKey(Enchantment.OXYGEN)) return 1;
		
		return 0;
		
	}
	
//	{ "predicate": { "custom_model_data": 1 }, "model": "item/custom/books/black" },
//	{ "predicate": { "custom_model_data": 2 }, "model": "item/custom/books/red" },
//	{ "predicate": { "custom_model_data": 3 }, "model": "item/custom/books/purple" },
//	{ "predicate": { "custom_model_data": 4 }, "model": "item/custom/books/blue" },
//	{ "predicate": { "custom_model_data": 5 }, "model": "item/custom/books/tan" },
//	{ "predicate": { "custom_model_data": 6 }, "model": "item/custom/books/green" },
//	{ "predicate": { "custom_model_data": 7 }, "model": "item/custom/books/darkblue" },
//	{ "predicate": { "custom_model_data": 8 }, "model": "item/custom/books/darkblack" },

}

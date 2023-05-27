package net.jahcraft.jahenchants.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import net.jahcraft.jahenchants.enchantments.CustomEnchantments;
import net.md_5.bungee.api.ChatColor;

public class EnchantmentMath {
	
	public static void updateLore(ItemStack i) {
		
		checkCombustion(i);
		checkTunneling(i);
		checkVeinMiner(i);
		checkAdhesion(i);
		checkFloating(i);
		
	}
	
	private static void checkAdhesion(ItemStack i) {

		EnchantmentStorageMeta eMeta = null;
		
		if (i.getItemMeta() instanceof EnchantmentStorageMeta) {
			eMeta = (EnchantmentStorageMeta) i.getItemMeta();
		}
		
		if (eMeta != null && eMeta.getStoredEnchants().containsKey(CustomEnchantments.ADHESION)) {
			
			//ENCHANTED BOOK
			if (eMeta.hasLore()) {
				
				List<String> lore = eMeta.getLore();
				int index = 0;
				boolean hasEnchant = false;
				for (String s : lore) {
					if (s.contains("Adhesion")) {
						index = lore.indexOf(s);
						hasEnchant = true;
					}
				}
				if (!hasEnchant) {
					lore.add(index, ChatColor.GRAY + "Adhesion");
				} else {
					lore.set(index, ChatColor.GRAY + "Adhesion");
				}
				eMeta.setLore(lore);
				i.setItemMeta(eMeta);								
			} else {
				ArrayList<String> lore = new ArrayList<>();
				lore.add(ChatColor.GRAY + "Adhesion");
				eMeta.setLore(lore);
				i.setItemMeta(eMeta);
			}
			
		} else if (i.getItemMeta().hasEnchant(CustomEnchantments.ADHESION)) {

			//TOOL
			if (i.getItemMeta().hasLore()) {
				
				ItemMeta meta = i.getItemMeta();
				List<String> lore = meta.getLore();
				int index = 0;
				boolean hasEnchant = false;
				for (String s : lore) {
					if (s.contains("Adhesion")) {
						index = lore.indexOf(s);
						hasEnchant = true;
					}
				}
				if (!hasEnchant) {
					lore.add(index, ChatColor.GRAY + "Adhesion");
				} else {
					lore.set(index, ChatColor.GRAY + "Adhesion");
				}
				meta.setLore(lore);
				i.setItemMeta(meta);								
			} else {
				ItemMeta meta = i.getItemMeta();
				ArrayList<String> lore = new ArrayList<>();
				lore.add(ChatColor.GRAY + "Adhesion");
				meta.setLore(lore);
				i.setItemMeta(meta);
			}
		} else {
			if (i.getItemMeta().hasLore()) {
				ItemMeta meta = i.getItemMeta();
				List<String> lore = meta.getLore();
				int index = 0;
				boolean hasEnchant = false;
				for (String s : lore) {
					if (s.contains("Adhesion")) {
						index = lore.indexOf(s);
						hasEnchant = true;
					}
				}
				if (hasEnchant) {
					lore.remove(index);
				}
				meta.setLore(lore);
				i.setItemMeta(meta);
			}
			
		}
		
	}
	
	private static void checkFloating(ItemStack i) {

		EnchantmentStorageMeta eMeta = null;
		
		if (i.getItemMeta() instanceof EnchantmentStorageMeta) {
			eMeta = (EnchantmentStorageMeta) i.getItemMeta();
		}
		
		if (eMeta != null && eMeta.getStoredEnchants().containsKey(CustomEnchantments.FLOATING)) {
			
			//ENCHANTED BOOK
			if (eMeta.hasLore()) {
				
				List<String> lore = eMeta.getLore();
				int index = 0;
				boolean hasEnchant = false;
				for (String s : lore) {
					if (s.contains("Floating")) {
						index = lore.indexOf(s);
						hasEnchant = true;
					}
				}
				if (!hasEnchant) {
					lore.add(index, ChatColor.GRAY + "Floating");
				} else {
					lore.set(index, ChatColor.GRAY + "Floating");
				}
				eMeta.setLore(lore);
				i.setItemMeta(eMeta);								
			} else {
				ArrayList<String> lore = new ArrayList<>();
				lore.add(ChatColor.GRAY + "Floating");
				eMeta.setLore(lore);
				i.setItemMeta(eMeta);
			}
			
		} else if (i.getItemMeta().hasEnchant(CustomEnchantments.FLOATING)) {

			if (i.getItemMeta().hasLore()) {
				
				ItemMeta meta = i.getItemMeta();
				List<String> lore = meta.getLore();
				int index = 0;
				boolean hasEnchant = false;
				for (String s : lore) {
					if (s.contains("Floating")) {
						index = lore.indexOf(s);
						hasEnchant = true;
					}
				}
				if (!hasEnchant) {
					lore.add(index, ChatColor.GRAY + "Floating");
				} else {
					lore.set(index, ChatColor.GRAY + "Floating");
				}
				meta.setLore(lore);
				i.setItemMeta(meta);								
			} else {
				ItemMeta meta = i.getItemMeta();
				ArrayList<String> lore = new ArrayList<>();
				lore.add(ChatColor.GRAY + "Floating");
				meta.setLore(lore);
				i.setItemMeta(meta);
			}
		} else {
			if (i.getItemMeta().hasLore()) {
				ItemMeta meta = i.getItemMeta();
				List<String> lore = meta.getLore();
				int index = 0;
				boolean hasEnchant = false;
				for (String s : lore) {
					if (s.contains("Floating")) {
						index = lore.indexOf(s);
						hasEnchant = true;
					}
				}
				if (hasEnchant) {
					lore.remove(index);
				}
				meta.setLore(lore);
				i.setItemMeta(meta);
			}
			
		}
		
	}

	private static void checkVeinMiner(ItemStack i) {
		EnchantmentStorageMeta eMeta = null;
		
		if (i.getItemMeta() instanceof EnchantmentStorageMeta) {
			eMeta = (EnchantmentStorageMeta) i.getItemMeta();
		}
		
		if (eMeta != null && eMeta.getStoredEnchants().containsKey(CustomEnchantments.VEINMINER)) {
			
			//ENCHANTED BOOK
			if (eMeta.hasLore()) {
				
				List<String> lore = eMeta.getLore();
				int index = 0;
				boolean hasEnchant = false;
				for (String s : lore) {
					if (s.contains("Vein Miner")) {
						index = lore.indexOf(s);
						hasEnchant = true;
					}
				}
				if (!hasEnchant) {
					lore.add(index, ChatColor.GRAY + "Vein Miner");
				} else {
					lore.set(index, ChatColor.GRAY + "Vein Miner");
				}
				eMeta.setLore(lore);
				i.setItemMeta(eMeta);								
			} else {
				ArrayList<String> lore = new ArrayList<>();
				lore.add(ChatColor.GRAY + "Vein Miner");
				eMeta.setLore(lore);
				i.setItemMeta(eMeta);
			}
			
		} else if (i.getItemMeta().hasEnchant(CustomEnchantments.VEINMINER)) {
			
			
			
			if (i.getItemMeta().hasLore()) {
				
				ItemMeta meta = i.getItemMeta();
				List<String> lore = meta.getLore();
				int index = 0;
				boolean hasEnchant = false;
				for (String s : lore) {
					if (s.contains("Vein Miner")) {
						index = lore.indexOf(s);
						hasEnchant = true;
					}
				}
				if (!hasEnchant) {
					lore.add(index, ChatColor.GRAY + "Vein Miner");
				} else {
					lore.set(index, ChatColor.GRAY + "Vein Miner");
				}
				meta.setLore(lore);
				i.setItemMeta(meta);								
			} else {
				ItemMeta meta = i.getItemMeta();
				ArrayList<String> lore = new ArrayList<>();
				lore.add(ChatColor.GRAY + "Vein Miner");
				meta.setLore(lore);
				i.setItemMeta(meta);
			}
		} else {
			if (i.getItemMeta().hasLore()) {
				ItemMeta meta = i.getItemMeta();
				List<String> lore = meta.getLore();
				int index = 0;
				boolean hasEnchant = false;
				for (String s : lore) {
					if (s.contains("Vein Miner")) {
						index = lore.indexOf(s);
						hasEnchant = true;
					}
				}
				if (hasEnchant) {
					lore.remove(index);
				}
				meta.setLore(lore);
				i.setItemMeta(meta);
			}
			
		}
		
	}
	
	private static void checkTunneling(ItemStack i) {
		EnchantmentStorageMeta eMeta = null;
		
		if (i.getItemMeta() instanceof EnchantmentStorageMeta) {
			eMeta = (EnchantmentStorageMeta) i.getItemMeta();
		}
		
		if (eMeta != null && eMeta.getStoredEnchants().containsKey(CustomEnchantments.TUNNELING)) {
			
			//ENCHANTED BOOK
			if (eMeta.hasLore()) {
				
				List<String> lore = eMeta.getLore();
				int index = 0;
				boolean hasEnchant = false;
				for (String s : lore) {
					if (s.contains("Tunneling")) {
						index = lore.indexOf(s);
						hasEnchant = true;
					}
				}
				if (!hasEnchant) {
					lore.add(index, ChatColor.GRAY + "Tunneling");
				} else {
					lore.set(index, ChatColor.GRAY + "Tunneling");
				}
				eMeta.setLore(lore);
				i.setItemMeta(eMeta);								
			} else {
				ArrayList<String> lore = new ArrayList<>();
				lore.add(ChatColor.GRAY + "Tunneling");
				eMeta.setLore(lore);
				i.setItemMeta(eMeta);
			}
			
		} else if (i.getItemMeta().hasEnchant(CustomEnchantments.TUNNELING)) {
			
			
			
			if (i.getItemMeta().hasLore()) {
				
				ItemMeta meta = i.getItemMeta();
				List<String> lore = meta.getLore();
				int index = 0;
				boolean hasEnchant = false;
				for (String s : lore) {
					if (s.contains("Tunneling")) {
						index = lore.indexOf(s);
						hasEnchant = true;
					}
				}
				if (!hasEnchant) {
					lore.add(index, ChatColor.GRAY + "Tunneling");
				} else {
					lore.set(index, ChatColor.GRAY + "Tunneling");
				}
				meta.setLore(lore);
				i.setItemMeta(meta);								
			} else {
				ItemMeta meta = i.getItemMeta();
				ArrayList<String> lore = new ArrayList<>();
				lore.add(ChatColor.GRAY + "Tunneling");
				meta.setLore(lore);
				i.setItemMeta(meta);
			}
		} else {
			if (i.getItemMeta().hasLore()) {
				ItemMeta meta = i.getItemMeta();
				List<String> lore = meta.getLore();
				int index = 0;
				boolean hasEnchant = false;
				for (String s : lore) {
					if (s.contains("Tunneling")) {
						index = lore.indexOf(s);
						hasEnchant = true;
					}
				}
				if (hasEnchant) {
					lore.remove(index);
				}
				meta.setLore(lore);
				i.setItemMeta(meta);
			}
			
		}
		
	}
	
	private static void checkCombustion(ItemStack i) {
		
		EnchantmentStorageMeta eMeta = null;
		
		if (i.getItemMeta() instanceof EnchantmentStorageMeta) {
			eMeta = (EnchantmentStorageMeta) i.getItemMeta();
		}
		
		if (eMeta != null && eMeta.getStoredEnchants().containsKey(CustomEnchantments.COMBUSTION)) {
			
			//ENCHANTED BOOK
			if (eMeta.hasLore()) {
				
				List<String> lore = eMeta.getLore();
				int index = 0;
				boolean hasEnchant = false;
				for (String s : lore) {
					if (s.contains("Combustion")) {
						index = lore.indexOf(s);
						hasEnchant = true;
					}
				}
				if (!hasEnchant) {
					lore.add(index, ChatColor.GRAY + "Combustion " + getFormattedLevel(eMeta.getStoredEnchants().get(CustomEnchantments.COMBUSTION)));
				} else {
					lore.set(index, ChatColor.GRAY + "Combustion " + getFormattedLevel(eMeta.getStoredEnchants().get(CustomEnchantments.COMBUSTION)));
				}
				eMeta.setLore(lore);
				i.setItemMeta(eMeta);								
			} else {
				ArrayList<String> lore = new ArrayList<>();
				lore.add(ChatColor.GRAY + "Combustion " + getFormattedLevel(eMeta.getStoredEnchants().get(CustomEnchantments.COMBUSTION)));
				eMeta.setLore(lore);
				i.setItemMeta(eMeta);
			}
			
		} else if (i.getItemMeta().hasEnchant(CustomEnchantments.COMBUSTION)) {
			
			
			
			if (i.getItemMeta().hasLore()) {
				
				ItemMeta meta = i.getItemMeta();
				List<String> lore = meta.getLore();
				int index = 0;
				boolean hasEnchant = false;
				for (String s : lore) {
					if (s.contains("Combustion")) {
						index = lore.indexOf(s);
						hasEnchant = true;
					}
				}
				if (!hasEnchant) {
					lore.add(index, ChatColor.GRAY + "Combustion " + getFormattedLevel(i.getItemMeta().getEnchantLevel(CustomEnchantments.COMBUSTION)));
				} else {
					lore.set(index, ChatColor.GRAY + "Combustion " + getFormattedLevel(i.getItemMeta().getEnchantLevel(CustomEnchantments.COMBUSTION)));
				}
				meta.setLore(lore);
				i.setItemMeta(meta);								
			} else {
				ItemMeta meta = i.getItemMeta();
				ArrayList<String> lore = new ArrayList<>();
				lore.add(ChatColor.GRAY + "Combustion " + getFormattedLevel(i.getItemMeta().getEnchantLevel(CustomEnchantments.COMBUSTION)));
				meta.setLore(lore);
				i.setItemMeta(meta);
			}
		} else {
			if (i.getItemMeta().hasLore()) {
				ItemMeta meta = i.getItemMeta();
				List<String> lore = meta.getLore();
				int index = 0;
				boolean hasEnchant = false;
				for (String s : lore) {
					if (s.contains("Combustion")) {
						index = lore.indexOf(s);
						hasEnchant = true;
					}
				}
				if (hasEnchant) {
					lore.remove(index);
				}
				meta.setLore(lore);
				i.setItemMeta(meta);
			}
			
		}
		
	}

	private static String getFormattedLevel(int enchantmentLevel) {
		if (enchantmentLevel == 1) {
			return "I";
		}
		if (enchantmentLevel == 2) {
			return "II";
		}
		if (enchantmentLevel == 3) {
			return "III";
		}
		if (enchantmentLevel == 4) {
			return "IV";
		}
		if (enchantmentLevel == 5) {
			return "V";
		}
		if (enchantmentLevel == 6) {
			return "VI";
		}
		if (enchantmentLevel == 7) {
			return "VII";
		}
		if (enchantmentLevel == 8) {
			return "VIII";
		}
		if (enchantmentLevel == 9) {
			return "IX";
		}
		if (enchantmentLevel == 10) {
			return "X";
		}
		
		return Integer.toString(enchantmentLevel);
	}
}

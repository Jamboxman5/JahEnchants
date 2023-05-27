package net.jahcraft.jahenchants.enchantments;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;

import net.jahcraft.jahenchants.util.EnchantmentWrapper;

public class CustomEnchantments {

	public static final Enchantment ADHESION = new EnchantmentWrapper("adhesion", "Adhesion", 1);
	public static final Enchantment TUNNELING = new EnchantmentWrapper("tunneling", "Tunneling", 1);
	public static final Enchantment COMBUSTION = new EnchantmentWrapper("combustion", "Combustion", 3);
	public static final Enchantment VEINMINER = new EnchantmentWrapper("veinminer", "Vein Miner", 1);
	public static final Enchantment FLOATING = new EnchantmentWrapper("floating", "Floating", 1);
	
	public static ArrayList<Enchantment> getEnchantsList() {
		
		ArrayList<Enchantment> enchants = new ArrayList<>();
		
		enchants.add(ADHESION);
		enchants.add(TUNNELING);
		enchants.add(COMBUSTION);
		enchants.add(VEINMINER);
		enchants.add(FLOATING);
		
		return enchants;
		
	}
	
	public static void register() {
		boolean adhesionRegistered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(ADHESION);
		if (!adhesionRegistered) {
			registerEnchantment(ADHESION);
		}
		boolean combustionRegistered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(COMBUSTION);
		if (!combustionRegistered) {
			registerEnchantment(COMBUSTION);
		}
		boolean tunnelingRegistered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(TUNNELING);
		if (!tunnelingRegistered) {
			registerEnchantment(TUNNELING);
		}
		boolean veinMinerRegistered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(VEINMINER);
		if (!veinMinerRegistered) {
			registerEnchantment(VEINMINER);
		}
		boolean floatingRegistered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(FLOATING);
		if (!floatingRegistered) {
			registerEnchantment(FLOATING);
		}
		
	}
	
	public static void registerEnchantment(Enchantment enchant) {
		boolean registered = true;
		try {
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
			Enchantment.registerEnchantment(enchant);
		} catch (Exception e) {
			registered = false;
			e.printStackTrace();
		}
		if (registered) {
			Bukkit.getLogger().info("Enchantment registered!");
		}
	}
	
}

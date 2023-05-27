package net.jahcraft.jahenchants.util;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import net.jahcraft.jahenchants.enchantments.CustomEnchantments;

public class EnchantmentWrapper extends Enchantment {

	private final String name;
	private final String namespace;
	private final int maxLvl;
	
	public EnchantmentWrapper(String namespace, String name, int lvl) {
		super(NamespacedKey.minecraft(namespace));
		this.namespace = namespace;
		this.name = name;
		this.maxLvl = lvl;
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean conflictsWith(Enchantment e) {
		if (namespace == "veinminer") {
			if (e == CustomEnchantments.TUNNELING) return true;
		}
		if (namespace == "tunneling") {
			if (e == CustomEnchantments.VEINMINER) return true;
		}
		if (namespace == "combustion") {
			if (e == Enchantment.ARROW_FIRE) return true;
		}
		if (namespace == "floating") {
			if (e == Enchantment.PROTECTION_FALL) return true;
		}
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return maxLvl;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getStartLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCursed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTreasure() {
		// TODO Auto-generated method stub
		return false;
	}

}

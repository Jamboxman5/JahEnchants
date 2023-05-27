package net.jahcraft.jahenchants.listeners;

import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import net.jahcraft.jahenchants.enchantments.CustomEnchantments;

public class AdhesionListener implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		
		if (e.getPlayer() == null) return;
		if (e.getPlayer().getInventory() == null) return;
		if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
		if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null) return;
		if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants() == null) return;
		if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants().containsKey(CustomEnchantments.ADHESION)) return;
		if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants().containsKey(CustomEnchantments.TUNNELING)) return;
		if (e.getBlock().getState() instanceof Container) return;
		if (e.getPlayer().getInventory().firstEmpty() == -1) return;
		
		if (e.getBlock().getType().toString().contains("ORE")) return;
		
		e.setDropItems(false);
		
		Player p = e.getPlayer();
		Block b = e.getBlock();
		ItemStack tool = e.getPlayer().getInventory().getItemInMainHand();
		Collection<ItemStack> drops = b.getDrops(tool);
		
		
		if (drops.isEmpty()) return;
		
		if (p.getGameMode() != GameMode.CREATIVE) {
			p.getInventory().addItem(drops.iterator().next());
		}
		
	
	}

}

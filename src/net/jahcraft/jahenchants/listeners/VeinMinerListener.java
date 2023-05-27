package net.jahcraft.jahenchants.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;

import net.jahcraft.jahenchants.enchantments.CustomEnchantments;
import net.jahcraft.jahenchants.main.Main;

public class VeinMinerListener implements Listener {

	Main plugin;
	
	public VeinMinerListener(Main main) {
		plugin = main;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		
		if (e.getPlayer() == null) return;
		if (e.getPlayer().getInventory() == null) return;
		if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
		if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null) return;
		if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants() == null) return;
		if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants().containsKey(CustomEnchantments.VEINMINER)) return;
		if (!PlayerCacheUtil.getCachePermission(e.getPlayer(), e.getBlock().getLocation(), e.getBlock().getType(), TownyPermission.ActionType.DESTROY)) return;

		Block b = e.getBlock();
		Player p = e.getPlayer();
		ItemStack tool = e.getPlayer().getInventory().getItemInMainHand();
		
		int xpPerBlock = e.getExpToDrop();
		
		if (!b.getType().toString().contains("ORE")) return;
		
		e.setDropItems(false);
		
		if (tool.getItemMeta().hasEnchant(CustomEnchantments.ADHESION)) {
			adhesionVeinMine(b, xpPerBlock,  b.getType(), p, tool);
		} else {
			veinMine(b, xpPerBlock, b.getType(), p, tool);
		}
		
		
	}
	
	private void adhesionVeinMine(Block b, int xp, Material m, Player p, ItemStack tool) {

		if (b.getType() == m) {
			
			if (p.getInventory().firstEmpty() == -1) {
				b.breakNaturally(tool);
			} else {
				if (p.getGameMode() != GameMode.CREATIVE) {
					p.getInventory().addItem(b.getDrops(tool).iterator().next());
				}
				
				b.setType(Material.AIR);
				p.giveExp(xp);
			}
			
			
			Damageable meta = (Damageable) tool.getItemMeta();
			meta.setDamage(meta.getDamage()+1);
			boolean isBroken = (meta.getDamage() >= tool.getType().getMaxDurability());
			tool.setItemMeta(meta);
			if (isBroken) {
				p.getInventory().remove(tool);
				p.playSound(p, Sound.ENTITY_ITEM_BREAK, 1, 1);
				return;
			}
			if (b.getRelative(BlockFace.UP).getType() == m) adhesionVeinMine(b.getRelative(BlockFace.UP), xp, m, p, tool);
			if (b.getRelative(BlockFace.DOWN).getType() == m) adhesionVeinMine(b.getRelative(BlockFace.DOWN), xp, m, p, tool);
			if (b.getRelative(BlockFace.NORTH).getType() == m) adhesionVeinMine(b.getRelative(BlockFace.NORTH), xp, m, p, tool);
			if (b.getRelative(BlockFace.SOUTH).getType() == m) adhesionVeinMine(b.getRelative(BlockFace.SOUTH), xp, m, p, tool);
			if (b.getRelative(BlockFace.EAST).getType() == m) adhesionVeinMine(b.getRelative(BlockFace.EAST), xp, m, p, tool);
			if (b.getRelative(BlockFace.WEST).getType() == m) adhesionVeinMine(b.getRelative(BlockFace.WEST), xp, m, p, tool);
		}
		
	}

	private void veinMine(Block b, int xp, Material m, Player p, ItemStack tool) {
		
		if (b.getType() == m) {
			b.breakNaturally(tool);
			p.giveExp(xp);
			Damageable meta = (Damageable) tool.getItemMeta();
			meta.setDamage(meta.getDamage()+1);
			boolean isBroken = (meta.getDamage() >= tool.getType().getMaxDurability());
			tool.setItemMeta(meta);
			if (isBroken) {
				p.getInventory().remove(tool);
				p.playSound(p, Sound.ENTITY_ITEM_BREAK, 1, 1);
				return;
			}
			if (b.getRelative(BlockFace.UP).getType() == m) veinMine(b.getRelative(BlockFace.UP), xp, m, p, tool);
			if (b.getRelative(BlockFace.DOWN).getType() == m) veinMine(b.getRelative(BlockFace.DOWN), xp, m, p, tool);
			if (b.getRelative(BlockFace.NORTH).getType() == m) veinMine(b.getRelative(BlockFace.NORTH), xp, m, p, tool);
			if (b.getRelative(BlockFace.SOUTH).getType() == m) veinMine(b.getRelative(BlockFace.SOUTH), xp, m, p, tool);
			if (b.getRelative(BlockFace.EAST).getType() == m) veinMine(b.getRelative(BlockFace.EAST), xp, m, p, tool);
			if (b.getRelative(BlockFace.WEST).getType() == m) veinMine(b.getRelative(BlockFace.WEST), xp, m, p, tool);
		}
		
		
	
	}
	
}

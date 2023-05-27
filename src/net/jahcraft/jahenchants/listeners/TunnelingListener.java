package net.jahcraft.jahenchants.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;

import net.jahcraft.jahenchants.enchantments.CustomEnchantments;

public class TunnelingListener implements Listener {
	
	private HashMap<Player, BlockFace> blockFaceStorage = new HashMap<>();
	
	TownyAPI towny = TownyAPI.getInstance();
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		
		if (blockFaceStorage.get(e.getPlayer()) == null) return;
		if (e.getPlayer() == null) return;
		if (e.getPlayer().getInventory() == null) return;
		if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
		if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null) return;
		if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants() == null) return;
		if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants().containsKey(CustomEnchantments.TUNNELING)) return;
		if (!PlayerCacheUtil.getCachePermission(e.getPlayer(), e.getBlock().getLocation(), e.getBlock().getType(), TownyPermission.ActionType.DESTROY)) return;
		
		Block b = e.getBlock();
		
		if (!(b.getType().toString().contains("STONE") ||
				b.getType().toString().contains("DEEPSLATE") ||
				b.getType().toString().contains("NETHERRACK") ||
				b.getType().toString().contains("ORE") ||
				b.getType().toString().contains("GRANITE") ||
				b.getType().toString().contains("ANDESITE") ||
				b.getType().toString().contains("TUFF") ||
				b.getType().toString().contains("BASALT") ||
				b.getType().toString().contains("DIORITE"))) return;
		
		if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants().containsKey(CustomEnchantments.ADHESION)) {
			e.setDropItems(false);
			adhesionTunnel(b, e.getPlayer(), e.getPlayer().getInventory().getItemInMainHand());
		} else {
			tunnel(b, e.getPlayer(), e.getPlayer().getInventory().getItemInMainHand());
		}
		
		
	}
	
	private void tunnel(Block block, Player p, ItemStack i) {
		
		Location loc = block.getLocation();
		
		if (blockFaceStorage.get(p).equals(BlockFace.DOWN) || blockFaceStorage.get(p).equals(BlockFace.UP)) {
			
			ArrayList<Block> blocks = new ArrayList<>();
			
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()+1, loc.getY(), loc.getZ()+1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()+1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()-1, loc.getY(), loc.getZ()+1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()+1, loc.getY(), loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()-1, loc.getY(), loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()+1, loc.getY(), loc.getZ()-1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()-1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()-1, loc.getY(), loc.getZ()-1)));
			
			for (Block b : blocks) {
				if (b.getType().toString().contains("STONE") ||
					b.getType().toString().contains("DEEPSLATE") ||
					b.getType().toString().contains("NETHERRACK") ||
					b.getType().toString().contains("ORE") ||
					b.getType().toString().contains("GRANITE") ||
					b.getType().toString().contains("ANDESITE") ||
					b.getType().toString().contains("TUFF") ||
					b.getType().toString().contains("BASALT") ||
					b.getType().toString().contains("DIORITE")) {
					
					b.breakNaturally(i);
					Damageable meta = (Damageable) i.getItemMeta();
					meta.setDamage(meta.getDamage()+1);
					boolean isBroken = (meta.getDamage() >= i.getType().getMaxDurability());
					i.setItemMeta(meta);
					if (isBroken) {
						p.getInventory().remove(i);
						p.playSound(p, Sound.ENTITY_ITEM_BREAK, 1, 1);
						return;
					}
				}
			}
		}
		
		if (blockFaceStorage.get(p).equals(BlockFace.EAST) || blockFaceStorage.get(p).equals(BlockFace.WEST)) {
			ArrayList<Block> blocks = new ArrayList<>();
			
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()+1, loc.getZ()+1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()+1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()-1, loc.getZ()+1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()+1, loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()-1, loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()+1, loc.getZ()-1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()-1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()-1, loc.getZ()-1)));
			
			for (Block b : blocks) {
				if (b.getType().toString().contains("STONE") ||
					b.getType().toString().contains("DEEPSLATE") ||
					b.getType().toString().contains("NETHERRACK") ||
					b.getType().toString().contains("ORE") ||
					b.getType().toString().contains("GRANITE") ||
					b.getType().toString().contains("ANDESITE") ||
					b.getType().toString().contains("TUFF") ||
					b.getType().toString().contains("BASALT") ||
					b.getType().toString().contains("DIORITE")) {
					
					b.breakNaturally(i);
					Damageable meta = (Damageable) i.getItemMeta();
					meta.setDamage(meta.getDamage()+1);
					boolean isBroken = (meta.getDamage() >= i.getType().getMaxDurability());
					i.setItemMeta(meta);
					if (isBroken) {
						p.getInventory().remove(i);
						p.playSound(p, Sound.ENTITY_ITEM_BREAK, 1, 1);
						return;
					}
				}
			}
		}
		
		if (blockFaceStorage.get(p).equals(BlockFace.NORTH) || blockFaceStorage.get(p).equals(BlockFace.SOUTH)) {
			
			ArrayList<Block> blocks = new ArrayList<>();
			
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()-1, loc.getY()+1, loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()-1, loc.getY(), loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()-1, loc.getY()-1, loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()+1, loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()-1, loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()+1, loc.getY()+1, loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()+1, loc.getY(), loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()+1, loc.getY()-1, loc.getZ())));
			
			for (Block b : blocks) {
				if (b.getType().toString().contains("STONE") ||
					b.getType().toString().contains("DEEPSLATE") ||
					b.getType().toString().contains("NETHERRACK") ||
					b.getType().toString().contains("ORE") ||
					b.getType().toString().contains("GRANITE") ||
					b.getType().toString().contains("ANDESITE") ||
					b.getType().toString().contains("TUFF") ||
					b.getType().toString().contains("BASALT") ||
					b.getType().toString().contains("DIORITE")) {
					
					b.breakNaturally(i);
					Damageable meta = (Damageable) i.getItemMeta();
					meta.setDamage(meta.getDamage()+1);
					boolean isBroken = (meta.getDamage() >= i.getType().getMaxDurability());
					i.setItemMeta(meta);
					if (isBroken) {
						p.getInventory().remove(i);
						p.playSound(p, Sound.ENTITY_ITEM_BREAK, 1, 1);
						return;
					}
				}
			}
			
		}
		
		
		
		
	}
	
	private void adhesionTunnel(Block block, Player p, ItemStack i) {
		
		Location loc = block.getLocation();
		
		if (blockFaceStorage.get(p).equals(BlockFace.DOWN) || blockFaceStorage.get(p).equals(BlockFace.UP)) {
			
			ArrayList<Block> blocks = new ArrayList<>();
			if (p.getGameMode() != GameMode.CREATIVE) {
				p.getInventory().addItem(block.getDrops(i).iterator().next());
			}
			
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()+1, loc.getY(), loc.getZ()+1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()+1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()-1, loc.getY(), loc.getZ()+1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()+1, loc.getY(), loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()-1, loc.getY(), loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()+1, loc.getY(), loc.getZ()-1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()-1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()-1, loc.getY(), loc.getZ()-1)));
			
			for (Block b : blocks) {
				if (b.getType().toString().contains("STONE") ||
					b.getType().toString().contains("DEEPSLATE") ||
					b.getType().toString().contains("NETHERRACK") ||
					b.getType().toString().contains("ORE") ||
					b.getType().toString().contains("GRANITE") ||
					b.getType().toString().contains("ANDESITE") ||
					b.getType().toString().contains("TUFF") ||
					b.getType().toString().contains("BASALT") ||
					b.getType().toString().contains("DIORITE")) {
					
					if (p.getGameMode() != GameMode.CREATIVE) {
						p.getInventory().addItem(b.getDrops(i).iterator().next());
					}
					
					b.setType(Material.AIR);
					
					Damageable meta = (Damageable) i.getItemMeta();
					meta.setDamage(meta.getDamage()+1);
					boolean isBroken = (meta.getDamage() >= i.getType().getMaxDurability());
					i.setItemMeta(meta);
					if (isBroken) {
						p.getInventory().remove(i);
						p.playSound(p, Sound.ENTITY_ITEM_BREAK, 1, 1);
						return;
					}
				}
			}
			
		}
		
		if (blockFaceStorage.get(p).equals(BlockFace.EAST) || blockFaceStorage.get(p).equals(BlockFace.WEST)) {
			ArrayList<Block> blocks = new ArrayList<>();
			if (p.getGameMode() != GameMode.CREATIVE) {
				p.getInventory().addItem(block.getDrops(i).iterator().next());
			}			
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()+1, loc.getZ()+1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()+1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()-1, loc.getZ()+1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()+1, loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()-1, loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()+1, loc.getZ()-1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()-1)));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()-1, loc.getZ()-1)));
			
			for (Block b : blocks) {
				if (b.getType().toString().contains("STONE") ||
					b.getType().toString().contains("DEEPSLATE") ||
					b.getType().toString().contains("NETHERRACK") ||
					b.getType().toString().contains("ORE") ||
					b.getType().toString().contains("GRANITE") ||
					b.getType().toString().contains("ANDESITE") ||
					b.getType().toString().contains("TUFF") ||
					b.getType().toString().contains("BASALT") ||
					b.getType().toString().contains("DIORITE")) {
					
					if (p.getGameMode() != GameMode.CREATIVE) {
						p.getInventory().addItem(b.getDrops(i).iterator().next());
					}
					
					b.setType(Material.AIR);
					
					Damageable meta = (Damageable) i.getItemMeta();
					meta.setDamage(meta.getDamage()+1);
					boolean isBroken = (meta.getDamage() >= i.getType().getMaxDurability());
					i.setItemMeta(meta);
					if (isBroken) {
						p.getInventory().remove(i);
						p.playSound(p, Sound.ENTITY_ITEM_BREAK, 1, 1);
						return;
					}
				}
			}
		}
		
		if (blockFaceStorage.get(p).equals(BlockFace.NORTH) || blockFaceStorage.get(p).equals(BlockFace.SOUTH)) {
			
			ArrayList<Block> blocks = new ArrayList<>();
			if (p.getGameMode() != GameMode.CREATIVE) {
				p.getInventory().addItem(block.getDrops(i).iterator().next());
			}			
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()-1, loc.getY()+1, loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()-1, loc.getY(), loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()-1, loc.getY()-1, loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()+1, loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY()-1, loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()+1, loc.getY()+1, loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()+1, loc.getY(), loc.getZ())));
			blocks.add(loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getX()+1, loc.getY()-1, loc.getZ())));
			
			for (Block b : blocks) {
				if (b.getType().toString().contains("STONE") ||
					b.getType().toString().contains("DEEPSLATE") ||
					b.getType().toString().contains("NETHERRACK") ||
					b.getType().toString().contains("ORE") ||
					b.getType().toString().contains("GRANITE") ||
					b.getType().toString().contains("ANDESITE") ||
					b.getType().toString().contains("TUFF") ||
					b.getType().toString().contains("BASALT") ||
					b.getType().toString().contains("DIORITE")) {
					
					if (p.getGameMode() != GameMode.CREATIVE) {
						p.getInventory().addItem(b.getDrops(i).iterator().next());
					}
					
					b.setType(Material.AIR);
					
					Damageable meta = (Damageable) i.getItemMeta();
					meta.setDamage(meta.getDamage()+1);
					boolean isBroken = (meta.getDamage() >= i.getType().getMaxDurability());
					i.setItemMeta(meta);
					if (isBroken) {
						p.getInventory().remove(i);
						p.playSound(p, Sound.ENTITY_ITEM_BREAK, 1, 1);
						return;
					}
				}
			}
			
		}
		
		
		
		
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		if (e.getPlayer() == null) return;
		if (e.getPlayer().getInventory() == null) return;
		if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
		if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null) return;
		if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants() == null) return;
		if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants().containsKey(CustomEnchantments.TUNNELING)) return;
		
		blockFaceStorage.put(e.getPlayer(), e.getBlockFace());
		
	}
	
}

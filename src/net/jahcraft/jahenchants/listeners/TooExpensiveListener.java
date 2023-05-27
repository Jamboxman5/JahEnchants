package net.jahcraft.jahenchants.listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.AnvilInventory;

import net.md_5.bungee.api.ChatColor;

public class TooExpensiveListener implements Listener {
	
	public static HashMap<UUID, Integer> costStorage = new HashMap<>();
	public static HashMap<Player, BossBar> barStorage = new HashMap<>();
	public static HashMap<Player, GameMode> modeStorage = new HashMap<>();
	
	@EventHandler
	public void onAnvilOpen(InventoryOpenEvent e) {
		
		Player p = (Player) e.getPlayer();
		
		if (e.getInventory() instanceof AnvilInventory) {
			modeStorage.put(p, p.getGameMode());
			p.setGameMode(GameMode.CREATIVE);
			BossBar exp = Bukkit.createBossBar(ChatColor.GREEN + Integer.toString(p.getLevel()), BarColor.GREEN, BarStyle.SEGMENTED_20);
			double progress = ( p.getExp() / (p.getExp() + p.getExpToLevel()));
			exp.setProgress(progress);
			exp.addPlayer(p);
			barStorage.put(p, exp);
		}
		
	}
	
	@EventHandler
	public void onAnvilClose(InventoryCloseEvent e) {
		
		Player p = (Player) e.getPlayer();
		if (e.getInventory() instanceof AnvilInventory) {
			
			if (modeStorage.get(p) != null) {
				p.setGameMode(modeStorage.get(p));
			} else {
				p.setGameMode(GameMode.SURVIVAL);
			}
			barStorage.get(p).removeAll();
			barStorage.remove(p);
		}
		
	}
	
	
	@EventHandler
	public void onDisconnect(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		
		if (p.getOpenInventory() == null) return;
		
		if (p.getOpenInventory() instanceof AnvilInventory) {
			if (modeStorage.get(p) != null) {
				p.setGameMode(modeStorage.get(p));
			} else {
				p.setGameMode(GameMode.SURVIVAL);
			}			barStorage.get(p).removeAll();
			barStorage.remove(p);
		}
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
						
		if (e.getInventory() instanceof AnvilInventory) {
			if (e.getClick() == ClickType.MIDDLE) {
				e.setCancelled(true);
				return;
			}
			if (e.getClickedInventory() instanceof AnvilInventory && e.getSlot() == 2) {
				if (e.getCurrentItem().getType() != Material.AIR) {
					Player p = (Player) e.getWhoClicked();
					if (p.getLevel() - costStorage.get(p.getUniqueId()) < 0) {
						e.setCancelled(true);
					} else {
						p.giveExpLevels(-costStorage.get(p.getUniqueId()));
						barStorage.get(p).setTitle(ChatColor.GREEN + Integer.toString(p.getLevel()));

					}
				}
			}
		}
		
	}

}

package net.jahcraft.jahenchants.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import net.jahcraft.jahenchants.enchantments.CustomEnchantments;

public class CombustionListener implements Listener {
	
public List<String> list = new ArrayList<String>();
	
	private HashMap<Player, ItemStack> bowStorage = new HashMap<>();
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.BOW))
			if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchantments.COMBUSTION)) {
				Player player = (Player) event.getPlayer();
				//Right Click
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if (!bowStorage.containsKey(player))
						bowStorage.put(player, player.getInventory().getItemInMainHand());
					return;
				}
			}
		if (bowStorage.containsKey(event.getPlayer())) {
			bowStorage.remove(event.getPlayer());
		}
	}
	
	@EventHandler()
	public void onLand(ProjectileHitEvent event) {
		if (event.getEntityType() == EntityType.ARROW) {
			if (event.getEntity().getShooter() instanceof Player) {
				Player player = (Player) event.getEntity().getShooter();
				if (bowStorage.containsKey(player)) {

					Location loc = event.getEntity().getLocation();
					ItemStack bow = bowStorage.get(player);
					
					if (bow.getItemMeta().getEnchantLevel(CustomEnchantments.COMBUSTION) == 1) {
						loc.getWorld().createExplosion(loc, 1);
					}
					if (bow.getItemMeta().getEnchantLevel(CustomEnchantments.COMBUSTION) == 2) {
						loc.getWorld().createExplosion(loc, (float) 1.5);
					}
					if (bow.getItemMeta().getEnchantLevel(CustomEnchantments.COMBUSTION) >= 3) {
						loc.getWorld().createExplosion(loc, 2);
					}
					
					Damageable meta = (Damageable) bow.getItemMeta();
					meta.setDamage(meta.getDamage() + 50);
					boolean isBroken = (meta.getDamage() >= bow.getType().getMaxDurability());
					bow.setItemMeta(meta);
					if (isBroken) {
						player.getInventory().remove(bow);
						player.playSound(player, Sound.ENTITY_ITEM_BREAK, 1, 1);
					}
					
					bowStorage.remove(player);
					
					
				}
				
			}
			
		}
		
	}

}

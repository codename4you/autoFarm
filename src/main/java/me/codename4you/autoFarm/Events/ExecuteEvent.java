package me.codename4you.autoFarm.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ExecuteEvent {
    public void execute(Player p, BlockBreakEvent event, Material takeItem, Material placeItem) {
        Block block = event.getBlock();
        if ((!(p.hasPermission("autofarm.replant"))) || p.isSneaking() || !p.getInventory().contains(takeItem)) {
            event.setCancelled(true);
            dropItems(p, event);
            block.setType(Material.AIR);
        } else {
            if (p.getInventory().contains(takeItem)) {
                int piSize = p.getInventory().getSize();
                for (int i = 0; i <= piSize; i++) {
                    ItemStack recieveItem = p.getInventory().getItem(i);
                    if (recieveItem == null || !recieveItem.getType().equals(takeItem))
                        continue;
                    recieveItem.setAmount(recieveItem.getAmount() - 1);
                    break;
                }
                event.setCancelled(true);
                dropItems(p, event);
                block.setType(placeItem);
            }
        }
    }

    private void dropItems(Player p, BlockBreakEvent event) {
        ItemStack item = p.getInventory().getItemInMainHand();
        Block block = event.getBlock();
        if (!(p.hasPermission("autofarm.collect"))) {
            for (ItemStack blockDrops : block.getDrops(item)) {
                p.getWorld().dropItemNaturally(block.getLocation(), blockDrops);
            }
        }else {
            for (ItemStack blockDrops : block.getDrops(item)){
                if(hasInventory(p, blockDrops)){
                    p.getInventory().addItem(new ItemStack(blockDrops));
                }else{
                    p.getWorld().dropItemNaturally(block.getLocation(), blockDrops);
                }
            }
        }
    }

    private boolean hasInventory(Player p, ItemStack blockDrops) {
        PlayerInventory pI = p.getInventory();
        for(int i=0; i<=35; i++) {
            if (pI.getItem(i) == null) {
                return true;
            }
            if (pI.getItem(i).getType() == blockDrops.getType() &&
                    ((p.getInventory().getItem(i).getAmount() + blockDrops.getAmount()) < 64)) {
                return true;
            }
        }
        return false;
    }
}

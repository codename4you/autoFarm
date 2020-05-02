package me.codename4you.autoFarm.Events;

import me.codename4you.autoFarm.AutoFarmMain;;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class blockBreakEvent implements Listener {
    private AutoFarmMain pluginMain;

    public blockBreakEvent(AutoFarmMain pluginMain) {
        this.pluginMain = pluginMain;
    }

    @EventHandler
    private void detectEvent(BlockBreakEvent event) {
        Player p = event.getPlayer();
        ExecuteEvent ee = new ExecuteEvent();
        Block block = event.getBlock();
        Material material = block.getType();

        switch (material){
            case CARROTS:
                ee.execute(p, event, Material.CARROT, Material.CARROTS);
                break;
            case POTATOES:
                ee.execute(p, event, Material.POTATO, Material.POTATOES);
                break;
            case WHEAT:
                ee.execute(p, event, Material.WHEAT_SEEDS, Material.WHEAT);
                break;
            case BEETROOTS:
                ee.execute(p, event, Material.BEETROOT_SEEDS, Material.BEETROOTS);
                break;
            default:
                break;
        }
    }
}
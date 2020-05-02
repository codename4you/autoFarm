package me.codename4you.autoFarm;

import me.codename4you.autoFarm.Events.blockBreakEvent;
import me.codename4you.autoFarm.utli.Colors;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoFarmMain extends JavaPlugin {
    Colors c = new Colors();

    @Override
    public void onLoad() {
        this.getServer().getConsoleSender().sendMessage(c.c("&8[&6Auto&2Farm&8] &f- &8is being loaded!"));
    }

    @Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage(c.c("&8[&6Auto&2Farm&8] &f- &ahas been enabled!"));
        this.getServer().getPluginManager().registerEvents(new blockBreakEvent(this), this);
    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage(c.c("&8[&6Auto&2Farm&8] &f- &chas been disabled!"));
    }
}

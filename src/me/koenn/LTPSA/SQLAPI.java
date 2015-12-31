package me.koenn.LTPSA;

import org.bukkit.plugin.java.JavaPlugin;

public class SQLAPI extends JavaPlugin {

    private static SQLAPI main;

    public void log(String msg) {
        String prefix = "[LTPSQLAPI] ";
        getLogger().info(prefix + msg);
    }

    public static SQLAPI getInstance(){
        return main;
    }

    @Override
    public void onEnable() {
        log("All credits for this plugin go to Koenn");
        main = this;
    }

    @Override
    public void onDisable() {
        log("All credits for this plugin go to Koenn");
    }
}
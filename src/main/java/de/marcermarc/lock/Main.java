package de.marcermarc.lock;

import de.marcermarc.lock.controller.ConfigController;
import de.marcermarc.lock.controller.PluginController;
import de.marcermarc.lock.listener.Command;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private PluginController controller = new PluginController();

    @Override
    public void onEnable() {
        controller.setMain(this);

        PluginManager pM = getServer().getPluginManager();

        registerEvents(pM);

        controller.setConfig(new ConfigController(controller));
    }

    private void registerEvents(PluginManager in_PM) {

        Command c = new Command(controller);

        this.getCommand("marcerLock").setExecutor(c);
    }

    @Override
    public void onDisable() {

    }
}
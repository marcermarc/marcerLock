package de.marcermarc.lock.listener;

import de.marcermarc.lock.controller.PluginController;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command implements CommandExecutor {

    private PluginController controller;

    public Command(PluginController controller) {
        this.controller = controller;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] args) {
        return false;
    }

}
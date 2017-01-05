package de.marcermarc.lock.listener;

import de.marcermarc.lock.controller.PluginController;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {

    private PluginController controller;

    public Command(PluginController controller) {
        this.controller = controller;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] args) {
        if (args.length >= 1) {
            switch (args[0].toLowerCase()) {
                case "own":
                    if (args.length == 4 && isNumeric(args[1]) && isNumeric(args[2]) && isNumeric(args[3]) && commandSender instanceof Player) {
                        Boolean b = controller.getLock().setOwner(((Player) commandSender).getUniqueId(), ((Player) commandSender).getWorld().getBlockAt(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])));

                        if (b) {
                            commandSender.sendMessage("You are now the owner of this chest.");
                        } else {
                            commandSender.sendMessage("Someone is alredy the owner.");
                        }
                        return b;
                    }
                case "unown":

            }
        }
        return false;
    }

    private boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }
}
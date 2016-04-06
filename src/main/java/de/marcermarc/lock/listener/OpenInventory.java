package de.marcermarc.lock.listener;

import de.marcermarc.lock.controller.PluginController;
import de.marcermarc.lock.objects.ProtectionEnum;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.List;
import java.util.UUID;

public class OpenInventory implements Listener {

    private PluginController controller;

    public OpenInventory(PluginController controller) {
        this.controller = controller;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onOpenInventory(InventoryOpenEvent inventoryOpenEvent) {
        if (inventoryOpenEvent.getInventory().getType() == InventoryType.CHEST) {

            UUID player = inventoryOpenEvent.getPlayer().getUniqueId();
            Block block = inventoryOpenEvent.getInventory().getLocation().getBlock();

            List<MetadataValue> marcerLockOwner = block.getMetadata("marcerLockOwner");
            List<MetadataValue> marcerLockProtection = block.getMetadata("marcerLockProtection");

            if (marcerLockOwner.size() == 0) {

// ToDo: owner festlegen
                block.setMetadata("marcerLockOwner", new FixedMetadataValue(controller.getMain(), player));

            } else if (marcerLockOwner.contains(player)) {

            } else if (marcerLockProtection.contains(ProtectionEnum.NOACCESS)) {
                inventoryOpenEvent.setCancelled(true);
            } else if (marcerLockProtection.contains(ProtectionEnum.ONLYLOOK)) {

            }

        }
    }
}

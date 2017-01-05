package de.marcermarc.lock.controller;

import de.marcermarc.lock.objects.ProtectionEnum;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.List;
import java.util.UUID;

public class LockController {

    private final int[][] chestNext = {{0, 0, 1}, {0, 0, -1}, {1, 0, 0}, {-1, 0, 0}};

    private PluginController controller;

    public LockController(PluginController controller) {
        this.controller = controller;
    }

    public boolean setOwner(UUID player, Block block) {
        if (!setOwnerMeta(player, block)) {
            return false;
        }

        if (block.getType() == Material.CHEST || block.getType() == Material.TRAPPED_CHEST) {
            if (!setOwnerTestNext(player, block, chestNext)) {
                return false;
            }
        }

        return true;
    }

    private boolean setOwnerMeta(UUID player, Block block) {
        if (block.hasMetadata("marcerLockOwner")) {
            return false;
        }

        block.setMetadata("marcerLockOwner", new FixedMetadataValue(controller.getMain(), player));

        return true;
    }

    private boolean setOwnerTestNext(UUID player, Block block, int[][] nextTest) {
        for (int[] next : nextTest) {
            Block b = block.getWorld().getBlockAt(block.getX() + next[0], block.getY() + next[1], block.getZ() + next[2]);
            if (b.getType() == block.getType()) {
                if (!setOwnerMeta(player, block)) {
                    return false;
                }

            }
        }

        return true;
    }

    public UUID getOwner(Block block) {
        if (block.hasMetadata("marcerLockOwner")) {
            List<MetadataValue> list = block.getMetadata("marcerLockOwner");
            if (list.size() == 1 && list.get(0).value() instanceof UUID) {
                return (UUID) list.get(0).value();
            }
        }
        return null;
    }

    public boolean unsetOwner(UUID player, Block block, Boolean op) {
        UUID owner = getOwner(block);
        if (owner == null) {
            return true;
        } else if (owner.equals(player) || op) {
            block.removeMetadata("marcerLockOwner", controller.getMain());
            return true;
        }
        return false;
    }

    public boolean setProtection (Block block, ProtectionEnum protection) {
        if (!setProtectionMeta( block,protection)) {
            return false;
        }

        if (block.getType() == Material.CHEST || block.getType() == Material.TRAPPED_CHEST) {
            if (!setProtectionTestNext( block, protection,chestNext)) {
                return false;
            }
        }

        return true;
    }

    private boolean setProtectionMeta(Block block, ProtectionEnum protection) {
        if (block.hasMetadata("marcerLockProtection")) {
            return false;
        }

        block.setMetadata("marcerLockProtection", new FixedMetadataValue(controller.getMain(), protection));

        return true;
    }

    private boolean setProtectionTestNext(Block block, ProtectionEnum protection, int[][] nextTest) {
        for (int[] next : nextTest) {
            Block b = block.getWorld().getBlockAt(block.getX() + next[0], block.getY() + next[1], block.getZ() + next[2]);
            if (b.getType() == block.getType()) {
                if (!setProtectionMeta(block, protection)) {
                    return false;
                }

            }
        }

        return true;
    }
}

package epicjii.shriekreact;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SculkShrieker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ShriekReact implements Listener {

    @EventHandler
    void reactivate(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (item.getType() == Material.ECHO_SHARD) {
                Block clickedBlock = event.getClickedBlock();

                if (clickedBlock == null) {
                    return;
                }

                if (clickedBlock.getType() == Material.SCULK_SHRIEKER) {
                    BlockData data = clickedBlock.getBlockData();
                    ((SculkShrieker) data).setCanSummon(true);

                    clickedBlock.setBlockData(data);
                    
                    player.getInventory().remove(item);
                    player.sendMessage(Component.text("Shrieker Reactivated"));
                }
            }
        }
    }
}

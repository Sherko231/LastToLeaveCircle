package com.sherko.LastToLeaveCircle.Listeners;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.ItemHoeWood;
import cn.nukkit.utils.TextFormat;
import com.sherko.LastToLeaveCircle.SquareBuilder;


public class ShrinkToolListener implements Listener {

    @EventHandler
    public void onItemClick(PlayerInteractEvent e) {
        if (e.getAction() != PlayerInteractEvent.Action.RIGHT_CLICK_AIR) return; //if right click
        if (!(e.getPlayer().getInventory().getItemInHand() instanceof ItemHoeWood)) return; //if item is WoodenHoe

        //if item is ShrinkTool:
        if(e.getPlayer().getInventory().getItemInHand().getName().equalsIgnoreCase(TextFormat.BOLD.toString() + TextFormat.RED + "SHRINK TOOL")){

            SquareBuilder.shrinkSquare();

        }

    }


}


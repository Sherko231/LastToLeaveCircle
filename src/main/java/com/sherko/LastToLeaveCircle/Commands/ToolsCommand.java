package com.sherko.LastToLeaveCircle.Commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.utils.TextFormat;

public class ToolsCommand extends Command {

    public ToolsCommand() {
        super("gettools");
    }

    /** /gettools
     *
     */
    public boolean execute(CommandSender sender, String s, String[] args){
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission("LastToLeaveCircle.use")) {
            player.sendMessage(TextFormat.RED + "NO PERMISSION");
            return false;
        }

        Item shrinkTool = Item.get(ItemID.WOODEN_HOE);
        shrinkTool.setCustomName(TextFormat.BOLD.toString() + TextFormat.RED + "SHRINK TOOL");
        shrinkTool.setLore("Used to shrink the Square","By Right Click in Air");

        player.getInventory().addItem(shrinkTool);

        return false;
    }
}

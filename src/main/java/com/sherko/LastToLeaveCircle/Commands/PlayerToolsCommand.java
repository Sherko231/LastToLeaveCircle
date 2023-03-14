package com.sherko.LastToLeaveCircle.Commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.custom.CustomEntity;
import cn.nukkit.item.ItemStick;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.utils.TextFormat;
import com.sherko.LastToLeaveCircle.Main;

public class PlayerToolsCommand extends Command {
    public PlayerToolsCommand() {
        super("giveplayertools","Gives tools that players should start the event with");
    }

    /** /giveplayertools
     *
     */
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args){

        ItemStick knockbackTool = new ItemStick();
        Enchantment knockback = Enchantment.getEnchantment(12);
        knockback.setLevel(5);
        knockbackTool.addEnchantment(knockback);
        knockbackTool.setCustomName(TextFormat.BOLD.toString() + TextFormat.BLUE + "KockbackTool");

        for (Player player : Main.INSTANCE.getServer().getOnlinePlayers().values()){
            player.getInventory().addItem(knockbackTool);
        }

        return false;
    }

}

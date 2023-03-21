package com.sherko.LastToLeaveCircle.Commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemStick;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.utils.TextFormat;
import com.sherko.LastToLeaveCircle.Main;

public class KockbackToolCommand extends Command {
    public KockbackToolCommand() {
        super("giveplayertools","gives all players knockback tools");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args){
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission("LastToLeaveCircle.use")) {
            player.sendMessage(TextFormat.RED + "NO PERMISSION");
            return false;
        }

        //make knockback tool:
        Item knockBackTool = new ItemStick();
        knockBackTool.setCustomName(TextFormat.BOLD.toString() + TextFormat.BLUE + "KnockBack Tool");
        Enchantment knockback = Enchantment.getEnchantment(Enchantment.ID_KNOCKBACK);
        knockback.setLevel(1);
        knockBackTool.addEnchantment(knockback);

        //give knockback tool:
        for(Player p : Main.INSTANCE.getServer().getOnlinePlayers().values()){
            p.getInventory().addItem(knockBackTool);
        }

        return false;
    }
}

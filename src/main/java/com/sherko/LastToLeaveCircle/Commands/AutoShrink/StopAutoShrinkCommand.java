package com.sherko.LastToLeaveCircle.Commands.AutoShrink;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import com.sherko.LastToLeaveCircle.Main;
import com.sherko.LastToLeaveCircle.SquareBuilder;

public class StopAutoShrinkCommand extends Command {
    public StopAutoShrinkCommand() {
        super("stopautoshrink");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args){
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission("LastToLeaveCircle.use")) {
            player.sendMessage(TextFormat.RED + "NO PERMISSION");
            return false;
        }

        SquareBuilder.stopAutoShrink();

        for(Player p : Main.INSTANCE.getServer().getOnlinePlayers().values()){
            p.sendMessage(TextFormat.RED + "The Game is Stopped by Admin");
        }

        return false;
    }
}

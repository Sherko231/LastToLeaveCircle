package com.sherko.LastToLeaveCircle.Commands.AutoShrink;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.TextFormat;
import com.sherko.LastToLeaveCircle.Main;
import com.sherko.LastToLeaveCircle.SherkoScoreboard;
import com.sherko.LastToLeaveCircle.SquareBuilder;

public class AutoShrinkCommand extends Command {

    public AutoShrinkCommand() {
        super("autoshrink");
        commandParameters.clear();
        CommandParameter par1 = CommandParameter.newType("shrink-rate",false, CommandParamType.INT);
        commandParameters.put("default",new CommandParameter[]{par1});
    }

    /** /autoshrink [shrink-rate]
     *
     */
    @Override
    public boolean execute(CommandSender sender, String s, String[] args){
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission("LastToLeaveCircle.use")) {
            player.sendMessage(TextFormat.RED + "NO PERMISSION");
            return false;
        }

        //Start Shrink :
        int shrinkRate = Integer.parseInt(args[0]);
        SquareBuilder.startAutoShrink(shrinkRate);

        //Shrink Message - Update Scoreboard :
        for(Player p : Main.INSTANCE.getServer().getOnlinePlayers().values()){
            p.sendActionBar(TextFormat.BOLD.toString() + TextFormat.DARK_RED + "The Square is Shrinking !");
            if(p.containTag("Player")) {
                SherkoScoreboard.getRemainingPlayers().add(p);
                SherkoScoreboard.updateRemainingPlayers();
            }
        }

        return false;
    }
    
}

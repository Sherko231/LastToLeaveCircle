package com.sherko.LastToLeaveCircle.Commands.AutoShrink;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import com.sherko.LastToLeaveCircle.Main;
import com.sherko.LastToLeaveCircle.SquareBuilder;
import com.sherko.LastToLeaveCircle.Tasks.WinLoseDetector;

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
        if (SquareBuilder.getSize() <= 2) return false;

        //get shrink rate :
        int shrinkRate = Integer.parseInt(args[0]);

        //start repeating shrink task :
        Main.INSTANCE.getServer().getScheduler().scheduleDelayedRepeatingTask(
                Main.INSTANCE, SquareBuilder::shrinkSquare,shrinkRate,shrinkRate);

        //start repeating loser detection task:
        Main.INSTANCE.getServer().getScheduler().scheduleDelayedRepeatingTask(
                Main.INSTANCE,
                new WinLoseDetector(),
                10,
                10,
                true
        );

        return false;
    }
    
}

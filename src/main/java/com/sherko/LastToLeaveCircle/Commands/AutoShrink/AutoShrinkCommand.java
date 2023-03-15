package com.sherko.LastToLeaveCircle.Commands.AutoShrink;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.scheduler.TaskHandler;
import com.sherko.LastToLeaveCircle.Main;
import com.sherko.LastToLeaveCircle.SherkoScoreboard;
import com.sherko.LastToLeaveCircle.SquareBuilder;
import com.sherko.LastToLeaveCircle.Tasks.WinLoseDetector;

public class AutoShrinkCommand extends Command {

    public AutoShrinkCommand() {
        super("autoshrink");
        commandParameters.clear();
        CommandParameter par1 = CommandParameter.newType("shrink-rate",false, CommandParamType.INT);
        commandParameters.put("default",new CommandParameter[]{par1});
    }

    private static TaskHandler shrinkTask;
    private static TaskHandler winLoseTask;

    public static int getShrinkTaskID() {
        return shrinkTask.getTaskId();
    }
    public static int getWinLoseTaskID() {
        return winLoseTask.getTaskId();
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
        shrinkTask = Main.INSTANCE.getServer().getScheduler().scheduleDelayedRepeatingTask(
                Main.INSTANCE, SquareBuilder::shrinkSquare,shrinkRate,shrinkRate);

        //start repeating loser/playerInSquare detection task:
        winLoseTask = Main.INSTANCE.getServer().getScheduler().scheduleDelayedRepeatingTask(
                Main.INSTANCE,
                new WinLoseDetector(),
                10,
                10,
                true
        );

        return false;
    }
    
}

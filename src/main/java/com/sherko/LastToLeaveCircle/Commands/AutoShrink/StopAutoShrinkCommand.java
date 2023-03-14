package com.sherko.LastToLeaveCircle.Commands.AutoShrink;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.sherko.LastToLeaveCircle.Main;

public class StopAutoShrinkCommand extends Command {
    public StopAutoShrinkCommand() {
        super("stopautoshrink");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args){
        Main.INSTANCE.getServer().getScheduler().cancelAllTasks();
        return false;
    }
}

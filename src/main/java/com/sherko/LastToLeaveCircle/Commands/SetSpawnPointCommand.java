package com.sherko.LastToLeaveCircle.Commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import com.sherko.LastToLeaveCircle.Main;

public class SetSpawnPointCommand extends Command {
    public SetSpawnPointCommand() {
        super("spawnpointall");
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args){

        if(!(sender instanceof Player player)) return false;

        Main.INSTANCE.getConfig().set("SpawnPosX",player.getPosition().x);
        Main.INSTANCE.getConfig().set("SpawnPosY",player.getPosition().y);
        Main.INSTANCE.getConfig().set("SpawnPosZ",player.getPosition().z);
        Main.INSTANCE.getConfig().save();

        player.sendMessage(TextFormat.YELLOW + "Permanent spawn point has been set");

        return false;
    }

}

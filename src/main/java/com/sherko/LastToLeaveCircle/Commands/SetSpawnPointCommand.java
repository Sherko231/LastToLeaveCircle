package com.sherko.LastToLeaveCircle.Commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.math.Vector3;
import cn.nukkit.utils.TextFormat;
import com.sherko.LastToLeaveCircle.Main;

public class SetSpawnPointCommand extends Command {
    public SetSpawnPointCommand() {
        super("spawnpointall");
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args){
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission("LastToLeaveCircle.use")) {
            player.sendMessage(TextFormat.RED + "NO PERMISSION");
            return false;
        }

        //set permanent spawn point in config :
        Main.INSTANCE.getConfig().set("SpawnPosX",player.getPosition().x);
        Main.INSTANCE.getConfig().set("SpawnPosY",player.getPosition().y);
        Main.INSTANCE.getConfig().set("SpawnPosZ",player.getPosition().z);
        Main.INSTANCE.getConfig().save();

        //set world spawn :
        Main.INSTANCE.getServer().getDefaultLevel().setSpawnLocation(
                new Vector3(player.getPosition().x,player.getPosition().y,player.getPosition().z)
        );

        player.sendMessage(TextFormat.YELLOW + "Permanent spawn point has been set");

        return false;
    }

}

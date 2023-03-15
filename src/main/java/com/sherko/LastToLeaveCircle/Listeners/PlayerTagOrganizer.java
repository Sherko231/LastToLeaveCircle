package com.sherko.LastToLeaveCircle.Listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.math.Vector3;

import com.sherko.LastToLeaveCircle.Main;

public class PlayerTagOrganizer implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        //tp to spawn-point:
        Player player = e.getPlayer();
        double SpawnX = Double.parseDouble(Main.INSTANCE.getConfig().get("SpawnPosX").toString());
        double SpawnY = Double.parseDouble(Main.INSTANCE.getConfig().get("SpawnPosY").toString());
        double SpawnZ = Double.parseDouble(Main.INSTANCE.getConfig().get("SpawnPosZ").toString());
        player.teleport(new Vector3(SpawnX,SpawnY,SpawnZ));

        //give tag:
        if(!player.isOp() || player.containTag("Player")) {
            player.addTag("Player");
        }


    }

}

package com.sherko.LastToLeaveCircle.Listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.math.Vector3;

import cn.nukkit.potion.Effect;
import com.sherko.LastToLeaveCircle.Main;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        //tp to permanent spawn-point:
        Player player = e.getPlayer();
        double SpawnX = Double.parseDouble(Main.INSTANCE.getConfig().get("SpawnPosX").toString());
        double SpawnY = Double.parseDouble(Main.INSTANCE.getConfig().get("SpawnPosY").toString());
        double SpawnZ = Double.parseDouble(Main.INSTANCE.getConfig().get("SpawnPosZ").toString());
        player.teleport(new Vector3(SpawnX,SpawnY,SpawnZ));
        player.setSpawn(new Vector3(SpawnX,SpawnY,SpawnZ));

        //give tag:
        if (player.isOp()) return;
        if (!player.containTag("Player")) {
            player.addTag("Player");
        }

        //give effects :
        player.addEffect(Effect.getEffect(Effect.SATURATION).setAmplifier(255).setDuration(9999999).setVisible(false));


    }

}

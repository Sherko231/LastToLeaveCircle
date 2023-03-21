package com.sherko.LastToLeaveCircle.Listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerRespawnEvent;
import cn.nukkit.level.Sound;
import cn.nukkit.math.Vector3;
import cn.nukkit.potion.Effect;
import cn.nukkit.utils.TextFormat;
import com.sherko.LastToLeaveCircle.Main;
import com.sherko.LastToLeaveCircle.SherkoScoreboard;

public class PlayerLoseListener implements Listener {

    @EventHandler
    public void onDeath(PlayerRespawnEvent e){
        Player player = e.getPlayer();
        if (player.containTag("Player") && !player.containTag("lost")){

            //Eliminate Player:
            player.addTag("lost");
            player.sendTitle(TextFormat.BOLD.toString() + TextFormat.RED + "You Lost");
            player.setSubtitle("you fall off the arena");
            player.getLevel().addSound(player.getPosition(), Sound.AMBIENT_WEATHER_THUNDER,1,1,player);
            player.getInventory().clearAll();

            //tp to permanent spawn-point:
            double SpawnX = Double.parseDouble(Main.INSTANCE.getConfig().get("SpawnPosX").toString());
            double SpawnY = Double.parseDouble(Main.INSTANCE.getConfig().get("SpawnPosY").toString());
            double SpawnZ = Double.parseDouble(Main.INSTANCE.getConfig().get("SpawnPosZ").toString());
            player.teleport(new Vector3(SpawnX,SpawnY,SpawnZ));
            player.setSpawn(new Vector3(SpawnX,SpawnY,SpawnZ));

            //give effects :
            player.addEffect(Effect.getEffect(Effect.SATURATION).setAmplifier(255).setDuration(9999999).setVisible(false));

            //remove from scoreboard:
            SherkoScoreboard.getRemainingPlayers().remove(player);
            SherkoScoreboard.updateRemainingPlayers();
        }
    }

}

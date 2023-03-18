package com.sherko.LastToLeaveCircle.Listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.level.Sound;
import cn.nukkit.utils.TextFormat;
import com.sherko.LastToLeaveCircle.Main;
import com.sherko.LastToLeaveCircle.SherkoScoreboard;

public class PlayerLoseListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player player = e.getEntity();
        if (player.containTag("Player") && !player.containTag("lost")){
            player.addTag("lost");
            player.sendTitle(TextFormat.BOLD.toString() + TextFormat.RED + "You Lost");
            player.setSubtitle("you fall off the arena");
            player.getLevel().addSound(player.getPosition(), Sound.AMBIENT_WEATHER_THUNDER,1,1,player);
            SherkoScoreboard.getRemainingPlayers().remove(player);
            SherkoScoreboard.updateRemainingPlayers();
        }
    }

}

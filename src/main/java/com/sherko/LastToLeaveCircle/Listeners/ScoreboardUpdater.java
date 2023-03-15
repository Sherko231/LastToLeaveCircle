package com.sherko.LastToLeaveCircle.Listeners;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.math.Vector3;
import com.sherko.LastToLeaveCircle.Main;
import com.sherko.LastToLeaveCircle.SherkoScoreboard;

public class ScoreboardUpdater implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        SherkoScoreboard.updateOnlinePlayersScorer();
        SherkoScoreboard.updateRemainingPlayers();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Main.INSTANCE.getServer().getScheduler().scheduleDelayedTask(Main.INSTANCE, SherkoScoreboard::updateOnlinePlayersScorer, 10, true);
        Main.INSTANCE.getServer().getScheduler().scheduleDelayedTask(Main.INSTANCE, SherkoScoreboard::updateRemainingPlayers, 10, true);
    }



}

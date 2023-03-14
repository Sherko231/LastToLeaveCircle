package com.sherko.LastToLeaveCircle.Listeners;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import com.sherko.LastToLeaveCircle.Main;
import com.sherko.LastToLeaveCircle.SherkoScoreboard;

public class ScoreboardUpdater implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        SherkoScoreboard.updateOnlineScorer();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Main.INSTANCE.getServer().getScheduler().scheduleDelayedTask(Main.INSTANCE, SherkoScoreboard::updateOnlineScorer, 10, true);
    }



}

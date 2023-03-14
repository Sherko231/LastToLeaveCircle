package com.sherko.LastToLeaveCircle.Listeners;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.scoreboard.scoreboard.Scoreboard;
import com.sherko.LastToLeaveCircle.Main;

public class OnlinePlayersListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        int onlinePlayersCount = Main.INSTANCE.getServer().getOnlinePlayers().values().size();
        Scoreboard scoreboard = Main.INSTANCE.getScoreboard();

        updateOnlinePlayers(scoreboard,onlinePlayersCount);

    }

    void updateOnlinePlayers(Scoreboard scoreboard, int amount){

        //TODO: make update online players function.

    }

}

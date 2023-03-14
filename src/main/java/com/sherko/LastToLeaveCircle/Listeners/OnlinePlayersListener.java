package com.sherko.LastToLeaveCircle.Listeners;

import cn.nukkit.Player;
import cn.nukkit.command.selector.args.impl.M;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.scoreboard.data.ScorerType;
import cn.nukkit.scoreboard.scoreboard.IScoreboardLine;
import cn.nukkit.scoreboard.scoreboard.ScoreboardLine;
import cn.nukkit.scoreboard.scorer.FakeScorer;
import cn.nukkit.utils.TextFormat;
import com.sherko.LastToLeaveCircle.Main;

import java.util.Collection;

public class OnlinePlayersListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        updateOnlineScorer();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Main.INSTANCE.getServer().getScheduler().scheduleDelayedTask(Main.INSTANCE, this::updateOnlineScorer, 10, true);
    }

    public void updateOnlineScorer(){

        int playersOnline = Main.INSTANCE.getServer().getOnlinePlayers().size();

        Main.INSTANCE.getScoreboard().removeLine(Main.INSTANCE.getOnlineScorer());

        Main.INSTANCE.setOnlineScorer(
                new FakeScorer(TextFormat.GREEN + "■ Players Online : " + TextFormat.YELLOW + playersOnline)
        );

        Main.INSTANCE.getScoreboard().addLine(Main.INSTANCE.getOnlineScorer(), 2);

        System.out.println("players online = " + playersOnline);
    }

}

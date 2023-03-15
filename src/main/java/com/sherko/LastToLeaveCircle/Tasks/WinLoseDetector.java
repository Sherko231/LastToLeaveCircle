package com.sherko.LastToLeaveCircle.Tasks;

import cn.nukkit.Player;
import cn.nukkit.level.Sound;
import cn.nukkit.utils.TextFormat;
import com.sherko.LastToLeaveCircle.Main;
import com.sherko.LastToLeaveCircle.SherkoScoreboard;

import java.util.ArrayList;
import java.util.Collection;

public class WinLoseDetector implements Runnable {

    @Override
    public void run() {

        double y = Double.parseDouble(Main.INSTANCE.getConfig().get("CurrentPosY").toString()) ; //get arena Height
        Collection<Player> playersOnline = new ArrayList<>(Main.INSTANCE.getServer().getOnlinePlayers().values()); //players online


        for (Player player : playersOnline){
            //Detect Losers:
            if(player.getPosition().y <= y - 3 && player.containTag("Player") && !player.containTag("lost")){
                player.addTag("lost");
                player.sendTitle(TextFormat.BOLD.toString() + TextFormat.RED + "You Lost");
                player.setSubtitle("you fall off the arena");
                player.setGamemode(3);
                player.setGamemode(3,true);
                player.getLevel().addSound(player.getPosition(), Sound.AMBIENT_WEATHER_THUNDER,1,1,player);
                SherkoScoreboard.getRemainingPlayers().remove(player);
                SherkoScoreboard.updateRemainingPlayers();
            }
            //Update RemainingPlayers Scoreboard:
            if(player.getPosition().y >= y && player.containTag("Player") && !player.containTag("lost")){
                if(SherkoScoreboard.getRemainingPlayers().contains(player)) return;
                SherkoScoreboard.getRemainingPlayers().add(player);
                SherkoScoreboard.updateRemainingPlayers();
            }
        }




    }
}

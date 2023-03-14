package com.sherko.LastToLeaveCircle;

import cn.nukkit.Player;
import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.scoreboard.data.SortOrder;
import cn.nukkit.scoreboard.scoreboard.Scoreboard;
import cn.nukkit.scoreboard.scoreboard.ScoreboardLine;
import cn.nukkit.scoreboard.scorer.FakeScorer;
import cn.nukkit.utils.TextFormat;

import java.util.ArrayList;
import java.util.List;

public class SherkoScoreboard {

    //============Fields============
    private static Scoreboard scoreboard;
    private static FakeScorer onlinePlayersScorer;

    private static final List<Player> remainingPlayers = new ArrayList<>();
    private static FakeScorer remainingPlayersScorer;

    //============Get-Set============
    public static Scoreboard getScoreboard(){
        return scoreboard;
    }
    public static FakeScorer getOnlinePlayersScorer(){
        return onlinePlayersScorer;
    }
    public static FakeScorer getRemainingPlayersScorer(){
        return remainingPlayersScorer;
    }
    public static List<Player> getRemainingPlayers() {
        return remainingPlayers;
    }
    public static void setOnlinePlayersScorer(FakeScorer newScorer){
        onlinePlayersScorer = newScorer;
    }
    public static void setRemainingPlayersScorer(FakeScorer newScorer){
        remainingPlayersScorer = newScorer;
    }

    //============Functions============
    public static void makeScoreboard(){

        int playersOnline = Main.INSTANCE.getServer().getOnlinePlayers().size();
        int remainingPlayersCount = remainingPlayers.size();
        //==========================================
        scoreboard = new Scoreboard(
                "main",
                TextFormat.BOLD.toString() + TextFormat.OBFUSCATED + "||" +TextFormat.RESET +
                        TextFormat.BOLD + TextFormat.GOLD + "SYRKING " + TextFormat.AQUA + "EVENT" +
                        TextFormat.BOLD + TextFormat.OBFUSCATED + TextFormat.WHITE + "||" +TextFormat.RESET,
                "dummy",
                SortOrder.ASCENDING);

        //==========================================
        onlinePlayersScorer = new FakeScorer(TextFormat.GREEN + "■ Players Online : " + TextFormat.YELLOW + playersOnline);
        remainingPlayersScorer = new FakeScorer(TextFormat.RED + "■ Players In Square : " + TextFormat.YELLOW + remainingPlayersCount);
        //==========================================
        ScoreboardLine line1 = new ScoreboardLine(scoreboard, new FakeScorer("--------------------") ,1);
        ScoreboardLine line2 = new ScoreboardLine(scoreboard, remainingPlayersScorer, 2);
        ScoreboardLine line3 = new ScoreboardLine(scoreboard, onlinePlayersScorer, 3);
        ScoreboardLine line4 = new ScoreboardLine(scoreboard, new FakeScorer("-------------------- ") ,4);
        ScoreboardLine line5 = new ScoreboardLine(scoreboard, new FakeScorer(TextFormat.LIGHT_PURPLE + "discord.gg/syk") ,5);
        scoreboard.addLine(line1);
        scoreboard.addLine(line2);
        scoreboard.addLine(line3);
        scoreboard.addLine(line4);
        scoreboard.addLine(line5);

        //==========================================
        Main.INSTANCE.getServer().getScoreboardManager().setDisplay(DisplaySlot.SIDEBAR,scoreboard);

    }

    public static void updateOnlinePlayersScorer(){

        int playersOnline = Main.INSTANCE.getServer().getOnlinePlayers().size();

        scoreboard.removeLine(onlinePlayersScorer);

        onlinePlayersScorer = new FakeScorer(TextFormat.GREEN + "■ Players Online : " + TextFormat.YELLOW + playersOnline);

        scoreboard.addLine(onlinePlayersScorer, 3);

        System.out.println("players online = " + playersOnline);
    }

    public static void updateRemainingPlayers(){

        int remainingPlayersCount = remainingPlayers.size();
        scoreboard.removeLine(remainingPlayersScorer);

        remainingPlayersScorer = new FakeScorer(TextFormat.RED + "■ Players In Square : " + TextFormat.YELLOW + remainingPlayersCount);

        scoreboard.addLine(remainingPlayersScorer, 2);
        System.out.println("players in square = " + remainingPlayersCount);

    }

}

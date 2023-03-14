package com.sherko.LastToLeaveCircle;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.scoreboard.data.SortOrder;
import cn.nukkit.scoreboard.scoreboard.Scoreboard;
import cn.nukkit.scoreboard.scoreboard.ScoreboardLine;
import cn.nukkit.scoreboard.scorer.FakeScorer;
import cn.nukkit.utils.TextFormat;
import com.sherko.LastToLeaveCircle.Commands.AutoShrink.AutoShrinkCommand;
import com.sherko.LastToLeaveCircle.Commands.AutoShrink.StopAutoShrinkCommand;
import com.sherko.LastToLeaveCircle.Commands.BuildSquareCommand;
import com.sherko.LastToLeaveCircle.Commands.PlayerToolsCommand;
import com.sherko.LastToLeaveCircle.Commands.ToolsCommand;
import com.sherko.LastToLeaveCircle.Listeners.HitListener;
import com.sherko.LastToLeaveCircle.Listeners.OnlinePlayersListener;
import com.sherko.LastToLeaveCircle.Listeners.ShrinkListener;
import org.w3c.dom.Text;

public class Main extends PluginBase{
    public static Main INSTANCE;

    //======================================
    private Scoreboard scoreboard;
    private FakeScorer onlineScorer;
    //======================================
    public Scoreboard getScoreboard(){
        return this.scoreboard;
    }

    public FakeScorer getOnlineScorer(){
        return this.onlineScorer;
    }

    public void setOnlineScorer(FakeScorer newScorer){
        this.onlineScorer = newScorer;
    }
    //======================================
    @Override
    public void onLoad(){
        INSTANCE = this;
    }

    @Override
    public void onEnable(){
        registerCommands();
        registerListeners();

        int playersOnline = getServer().getOnlinePlayers().size();

        //==========================================
        scoreboard = new Scoreboard(
                "main",
                TextFormat.BOLD.toString() + TextFormat.GOLD + "SYRKING " + TextFormat.AQUA + "EVENT",
                "dummy",
                SortOrder.ASCENDING);
        //==========================================
        FakeScorer lineScorer = new FakeScorer("----------------");
        FakeScorer lineScorer2 = new FakeScorer("---------------- ");
        onlineScorer = new FakeScorer(TextFormat.GREEN + "■ Players Online : " + TextFormat.YELLOW + playersOnline);
        FakeScorer discordScorer = new FakeScorer(TextFormat.LIGHT_PURPLE + "discord.gg/syk");

        //==========================================
        ScoreboardLine line1 = new ScoreboardLine(scoreboard, lineScorer ,1);
        ScoreboardLine line2 = new ScoreboardLine(scoreboard, onlineScorer, 2);
        ScoreboardLine line3 = new ScoreboardLine(scoreboard, lineScorer2 ,3);
        ScoreboardLine line4 = new ScoreboardLine(scoreboard, discordScorer ,4);

        scoreboard.addLine(line1);
        scoreboard.addLine(line2);
        scoreboard.addLine(line3);
        scoreboard.addLine(line4);

        //==========================================



        //==========================================
        getServer().getScoreboardManager().setDisplay(DisplaySlot.SIDEBAR,scoreboard);


        saveDefaultConfig();
    }

    private void registerCommands() {
        getServer().getCommandMap().register("buildsquare",new BuildSquareCommand());
        getServer().getCommandMap().register("gettools",new ToolsCommand());
        getServer().getCommandMap().register("giveplayertools",new PlayerToolsCommand());
        getServer().getCommandMap().register("autoshrink", new AutoShrinkCommand());
        getServer().getCommandMap().register("stopautoshrink", new StopAutoShrinkCommand());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new ShrinkListener(),this);
        getServer().getPluginManager().registerEvents(new HitListener(),this);
        getServer().getPluginManager().registerEvents(new OnlinePlayersListener(),this);
    }



}

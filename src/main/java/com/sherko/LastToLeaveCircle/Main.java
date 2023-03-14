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

    private Scoreboard scoreboard;

    public Scoreboard getScoreboard(){
        return this.scoreboard;
    }

    @Override
    public void onLoad(){
        INSTANCE = this;
    }

    @Override
    public void onEnable(){
        registerCommands();
        registerListeners();

        scoreboard = new Scoreboard(
                "main",
                TextFormat.BOLD.toString() + TextFormat.GOLD + "SYRKING " + TextFormat.AQUA + "EVENT",
                "dummy",
                SortOrder.ASCENDING);


        ScoreboardLine playerOnline = new ScoreboardLine(
                scoreboard,
                new FakeScorer(TextFormat.GREEN + "Players Online : " + TextFormat.YELLOW + "0   "),
                1);
        ScoreboardLine emptyLine2 = new ScoreboardLine(scoreboard, new FakeScorer(""),2);
        ScoreboardLine discord = new ScoreboardLine(scoreboard, new FakeScorer(TextFormat.LIGHT_PURPLE + "discord.gg/syk"),3);


        scoreboard.addLine(playerOnline);
        scoreboard.addLine(emptyLine2);
        scoreboard.addLine(discord);

        getServer().getScoreboardManager().setDisplay(DisplaySlot.SIDEBAR,scoreboard);


        saveDefaultConfig();
    }

    @Override
    public void onDisable(){
        getServer().getScoreboardManager().removeScoreboard(scoreboard);
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

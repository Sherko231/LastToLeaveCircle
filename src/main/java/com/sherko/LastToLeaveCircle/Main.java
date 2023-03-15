package com.sherko.LastToLeaveCircle;

import cn.nukkit.Player;
import cn.nukkit.plugin.PluginBase;
import com.sherko.LastToLeaveCircle.Commands.AutoShrink.AutoShrinkCommand;
import com.sherko.LastToLeaveCircle.Commands.AutoShrink.StopAutoShrinkCommand;
import com.sherko.LastToLeaveCircle.Commands.BuildSquareCommand;
import com.sherko.LastToLeaveCircle.Commands.PlayerToolsCommand;
import com.sherko.LastToLeaveCircle.Commands.SetSpawnPointCommand;
import com.sherko.LastToLeaveCircle.Commands.ToolsCommand;
import com.sherko.LastToLeaveCircle.Listeners.HitListener;
import com.sherko.LastToLeaveCircle.Listeners.PlayerTagOrganizer;
import com.sherko.LastToLeaveCircle.Listeners.ScoreboardUpdater;
import com.sherko.LastToLeaveCircle.Listeners.ShrinkListener;

public class Main extends PluginBase{
    public static Main INSTANCE;


    @Override
    public void onLoad(){
        INSTANCE = this;
    }

    @Override
    public void onEnable(){
        registerCommands();
        registerListeners();

        SherkoScoreboard.makeScoreboard();

        saveDefaultConfig();
    }


    private void registerCommands() {
        getServer().getCommandMap().register("buildsquare",new BuildSquareCommand());
        getServer().getCommandMap().register("gettools",new ToolsCommand());
        getServer().getCommandMap().register("giveplayertools",new PlayerToolsCommand());
        getServer().getCommandMap().register("autoshrink", new AutoShrinkCommand());
        getServer().getCommandMap().register("stopautoshrink", new StopAutoShrinkCommand());
        getServer().getCommandMap().register("spawnpointsherko",new SetSpawnPointCommand());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new ShrinkListener(),this);
        getServer().getPluginManager().registerEvents(new HitListener(),this);
        getServer().getPluginManager().registerEvents(new ScoreboardUpdater(),this);
        getServer().getPluginManager().registerEvents(new PlayerTagOrganizer(),this);
    }



}

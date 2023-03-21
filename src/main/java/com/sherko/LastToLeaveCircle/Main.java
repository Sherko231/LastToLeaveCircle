package com.sherko.LastToLeaveCircle;

import cn.nukkit.level.GameRule;
import cn.nukkit.plugin.PluginBase;
import com.sherko.LastToLeaveCircle.Commands.AutoShrink.AutoShrinkCommand;
import com.sherko.LastToLeaveCircle.Commands.AutoShrink.StopAutoShrinkCommand;
import com.sherko.LastToLeaveCircle.Commands.BuildSquareCommand;
import com.sherko.LastToLeaveCircle.Commands.KockbackToolCommand;
import com.sherko.LastToLeaveCircle.Commands.SetSpawnPointCommand;
import com.sherko.LastToLeaveCircle.Commands.ToolsCommand;
import com.sherko.LastToLeaveCircle.Listeners.PlayerJoinListener;
import com.sherko.LastToLeaveCircle.Listeners.PlayerLoseListener;
import com.sherko.LastToLeaveCircle.Listeners.ScoreboardUpdater;
import com.sherko.LastToLeaveCircle.Listeners.ShrinkToolListener;

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
        getServer().getDefaultLevel().getGameRules().setGameRule(GameRule.DO_IMMEDIATE_RESPAWN,true);

        saveDefaultConfig();
    }


    private void registerCommands() {
        getServer().getCommandMap().register("buildsquare",new BuildSquareCommand());
        getServer().getCommandMap().register("gettools",new ToolsCommand());
        getServer().getCommandMap().register("autoshrink", new AutoShrinkCommand());
        getServer().getCommandMap().register("stopautoshrink", new StopAutoShrinkCommand());
        getServer().getCommandMap().register("spawnpointall",new SetSpawnPointCommand());
        getServer().getCommandMap().register("giveplayertools",new KockbackToolCommand());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new ShrinkToolListener(),this);
        getServer().getPluginManager().registerEvents(new ScoreboardUpdater(),this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerLoseListener(),this);
    }



}

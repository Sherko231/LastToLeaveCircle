package com.sherko.LastToLeaveCircle.Commands;


import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.level.Position;
import com.sherko.LastToLeaveCircle.Main;
import com.sherko.LastToLeaveCircle.SherkoScoreboard;
import com.sherko.LastToLeaveCircle.SquareBuilder;


public class BuildSquareCommand extends Command{

    public BuildSquareCommand() {
        super("buildsquare","builds square with a specific size");
        commandParameters.clear();
        CommandParameter par1 = CommandParameter.newType("start-location",false, CommandParamType.BLOCK_POSITION);
        CommandParameter par2 = CommandParameter.newType("size",false,CommandParamType.INT);
        commandParameters.put("default",new CommandParameter[]{par1,par2});
    }

    /** /buildblock [x y z] [size]
     *
     */
    @Override
    public boolean execute(CommandSender sender, String s, String[] args){
        if(!(sender instanceof Player)) return false;

        //--------Resets Remaining Players Count on the Scoreboard:
        SherkoScoreboard.getRemainingPlayers().clear();
        SherkoScoreboard.updateRemainingPlayers();

        //--------get StartPos from Command:
        double x = Double.parseDouble(args[0]);
        Main.INSTANCE.getConfig().set("CurrentPosX",x);
        double y = Double.parseDouble(args[1]);
        Main.INSTANCE.getConfig().set("CurrentPosY",y);
        double z = Double.parseDouble(args[2]);
        Main.INSTANCE.getConfig().set("CurrentPosZ",z);
        Position startPos = new Position(x,y,z);

        //--------get square size from Command:
        int size = Integer.parseInt(args[3]);
        SquareBuilder.setSize(size);
        Main.INSTANCE.getConfig().set("Size",size);

        //--------build the square:
        SquareBuilder.makeSquare(startPos,size,true);

        Main.INSTANCE.getConfig().save();
        return false;
    }




}

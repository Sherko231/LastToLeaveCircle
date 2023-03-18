package com.sherko.LastToLeaveCircle;

import cn.nukkit.Player;
import cn.nukkit.block.BlockAir;
import cn.nukkit.block.BlockConcrete;
import cn.nukkit.level.GameRule;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.level.Sound;
import cn.nukkit.level.particle.DustParticle;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.TaskHandler;

public class SquareBuilder {

    //============Fields============
    private static int size;

    private static TaskHandler shrinkTask;

    //============Get-Set============
    public static int getSize(){
        return size;
    }
    public static void setSize(int size){
        SquareBuilder.size = size;
    }

    //============Functions============
    public static void makeSquare(Position startPos, int size, boolean withParticles){
        Level level = Main.INSTANCE.getServer().getDefaultLevel();

        //SFX:
        for(Player player : Main.INSTANCE.getServer().getOnlinePlayers().values()){
            level.addSound(player.getPosition(),
                    Sound.MOB_WITHER_SPAWN,0.2f,1f,player);
        }

        //Build the square:
        for (double x = startPos.x; x < startPos.x + size; x++){
            for (double z = startPos.z; z < startPos.z + size; z++){

                Vector3 currentPos = new Vector3(x, startPos.y, z);
                Vector3 currentPosOffset = new Vector3(x + 0.5,startPos.y + 1.4,z + 0.5);

                boolean isBorder =
                        (x == startPos.x) ||
                                (x == startPos.x + size - 1) ||
                                (z == startPos.z) ||
                                (z == startPos.z + size - 1);

                //Place red blocks on the borders
                if (isBorder){
                    level.setBlock(currentPos , new BlockConcrete(14));

                    if (withParticles)
                        level.addParticle(new DustParticle(currentPosOffset,255,0,0));
                }
                else { //place white block otherwise
                    level.setBlock(new Vector3(x, startPos.y,z), new BlockConcrete());
                }

            }
        }
    }

    public static void shrinkSquare(){

        //--------get the previous start position & size from config file
        Position startPos = new Position();
        startPos.x = Double.parseDouble(Main.INSTANCE.getConfig().get("CurrentPosX").toString());
        startPos.y = Double.parseDouble(Main.INSTANCE.getConfig().get("CurrentPosY").toString());
        startPos.z = Double.parseDouble(Main.INSTANCE.getConfig().get("CurrentPosZ").toString());
        size = Integer.parseInt(Main.INSTANCE.getConfig().get("Size").toString());

        if(size == 0) return;

        deleteSquare(startPos,size);
        startPos.x++;
        startPos.z++;
        size -= 2;

        //--------set the new position & size to config
        Main.INSTANCE.getConfig().set("CurrentPosX", startPos.x);
        Main.INSTANCE.getConfig().set("CurrentPosY", startPos.y);
        Main.INSTANCE.getConfig().set("CurrentPosZ", startPos.z);
        Main.INSTANCE.getConfig().set("Size",size);
        Main.INSTANCE.getConfig().save();

        //--------make the square with new properties
        makeSquare(startPos, size, true);

        //--------when shrinking is finished:
        if (size < 2){

            //pvp false:
            Main.INSTANCE.getServer().getDefaultLevel().getGameRules().setGameRule(GameRule.PVP,false);

            //cancel all tasks :
            Main.INSTANCE.getServer().getScheduler().cancelTask(shrinkTask.getTaskId());

        }

    }

    public static void startAutoShrink(int shrinkRate){
        //size must be > 2:
        if (SquareBuilder.getSize() <= 2) return;

        //turn on pvp:
        Main.INSTANCE.getServer().getDefaultLevel().getGameRules().setGameRule(GameRule.PVP,true);

        //start repeating shrink task :
        shrinkTask = Main.INSTANCE.getServer().getScheduler().scheduleDelayedRepeatingTask(
                Main.INSTANCE, SquareBuilder::shrinkSquare, shrinkRate, shrinkRate);

    }

    public static void stopAutoShrink(){
        Main.INSTANCE.getServer().getScheduler().cancelAllTasks();
        Main.INSTANCE.getServer().getDefaultLevel().getGameRules().setGameRule(GameRule.PVP,false);
    }

    static void deleteSquare(Position startPosition,int size){
        Level level = Main.INSTANCE.getServer().getDefaultLevel();

        for (double x = startPosition.x; x < startPosition.x + size; x++){
            for (double z = startPosition.z; z < startPosition.z + size; z++){
                level.setBlock(new Vector3(x, startPosition.y, z) , new BlockAir());
            }
        }
    }



}

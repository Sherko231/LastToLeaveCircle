package com.sherko.LastToLeaveCircle;

import cn.nukkit.Player;
import cn.nukkit.block.BlockAir;
import cn.nukkit.block.BlockConcrete;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.level.Sound;
import cn.nukkit.level.particle.DustParticle;
import cn.nukkit.math.Vector3;

public class SquareBuilder {

    //============Fields============
    static Position startPos;
    static int size;

    //============Get-Set============
    public static Position getStartPos(){
        return startPos;
    }
    public static int getSize(){
        return size;
    }
    public static void setSize(int size){
        SquareBuilder.size = size;
    }

    //============Functions============
    public static void makeSquare(Position startPos, int size, boolean withParticles){
        Level level = Main.INSTANCE.getServer().getDefaultLevel();
        Player player = Main.INSTANCE.getServer().getPlayer("SYRKING231");

        level.addSound(player.getPosition(),
                Sound.MOB_WITHER_SPAWN,0.4f,1f);

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

        //--------get the previous start position from config file
        startPos = new Position();
        startPos.x = Double.parseDouble(Main.INSTANCE.getConfig().get("CurrentPosX").toString());
        startPos.y = Double.parseDouble(Main.INSTANCE.getConfig().get("CurrentPosY").toString());
        startPos.z = Double.parseDouble(Main.INSTANCE.getConfig().get("CurrentPosZ").toString());
        size = Integer.parseInt(Main.INSTANCE.getConfig().get("Size").toString());

        if(size == 0) return;

        deleteSquare(startPos,size);
        startPos.x++;
        startPos.z++;
        size -= 2;

        Main.INSTANCE.getConfig().set("CurrentPosX",startPos.x);
        Main.INSTANCE.getConfig().set("CurrentPosY",startPos.y);
        Main.INSTANCE.getConfig().set("CurrentPosZ",startPos.z);
        Main.INSTANCE.getConfig().set("Size",size);
        Main.INSTANCE.getConfig().save();

        makeSquare(startPos, size, true);

        //--------when shrinking is finished:
        if (size < 2){
            Main.INSTANCE.getServer().getScheduler().scheduleDelayedTask(
                    Main.INSTANCE,
                    ()-> Main.INSTANCE.getServer().getScheduler().cancelAllTasks(),
                    30,true);

        }
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

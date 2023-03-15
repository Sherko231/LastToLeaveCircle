package com.sherko.LastToLeaveCircle.Listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.level.GameRule;
import cn.nukkit.level.Position;
import cn.nukkit.level.particle.DustParticle;
import cn.nukkit.scheduler.TaskHandler;
import cn.nukkit.utils.TextFormat;
import com.sherko.LastToLeaveCircle.Main;

public class HitListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){

        if(!(e.getDamager() instanceof Player attacker)) return; //Damager is Player
        if(!GameRule.PVP.isDeprecated()) return;

        attacker = (Player) e.getDamager();
        String itemName = attacker.getInventory().getItemInHand().getName();
        if(!(itemName.equalsIgnoreCase(TextFormat.BOLD.toString() + TextFormat.BLUE + "KockbackTool"))) return; //item is KnockbackTool

        TaskHandler task =
                Main.INSTANCE.getServer().getScheduler().scheduleDelayedRepeatingTask(
                Main.INSTANCE, () -> {
                            Position entityPos = e.getEntity().getPosition();
                            entityPos.y += 1;
                            e.getDamager().getLevel().addParticle(new DustParticle(entityPos,255,163,26));
                },
                0,
                1,
                true);

        Main.INSTANCE.getServer().getScheduler().scheduleDelayedTask(Main.INSTANCE, task::cancel ,16);


        



    }
}

package me.gallowsdove.foxymachines.implementation.mobs;

import me.gallowsdove.foxymachines.abstracts.CustomMob;
import me.gallowsdove.foxymachines.utils.Utils;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;

import javax.annotation.Nonnull;

public class Pixie extends CustomMob {
    public Pixie() {
        super("PIXIE", "精靈", EntityType.VEX, 5);
    }

    @Override
    public void onSpawn(LivingEntity spawned) {
        spawned.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(24);
        spawned.setRemoveWhenFarAway(true);
    }

    @Override
    protected void onAttack(@Nonnull EntityDamageByEntityEvent e) {
        if (!e.isCancelled()) {
            Utils.dealDamageBypassingArmor((LivingEntity) e.getEntity(), (e.getDamage() - e.getFinalDamage()) * 0.2);
        }
    }

    @Override
    protected void onTarget(@Nonnull EntityTargetEvent e) {
        if (!(e.getTarget() instanceof Player)) {
            e.setCancelled(true);
        }
    }

    @Override
    protected int getSpawnChance() {
        return 0;
    }
}

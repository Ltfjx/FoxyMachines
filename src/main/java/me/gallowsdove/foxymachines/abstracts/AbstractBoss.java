package me.gallowsdove.foxymachines.abstracts;

import io.github.mooy1.infinitylib.mobs.AbstractMob;
import me.gallowsdove.foxymachines.FoxyMachines;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBoss extends AbstractMob {

    private static final NamespacedKey KEY = new NamespacedKey(FoxyMachines.getInstance(), "boss");

    private final Map<LivingEntity, BossBar> instances = new HashMap<>();

    private int num = 0;

    public AbstractBoss(@Nonnull String id, @Nonnull String name, @Nonnull EntityType type, int health) {
        super(id, name, type, health);
    }

    protected static final class BossBarStyle {
        private final String name;
        private final BarColor color;
        private final BarStyle style;
        private final BarFlag[] flags;

        public BossBarStyle(String name, BarColor color, BarStyle style, BarFlag... flags) {
            this.name = name;
            this.color = color;
            this.style = style;
            this.flags = flags;
        }
    }

    @Nonnull
    protected abstract BossBarStyle getBossBarStyle();

    public abstract int getPatternInterval();

    protected int getBossBarDistance() {
        return 20;
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void onSpawn(@Nonnull LivingEntity spawned) {
        BossBarStyle style = getBossBarStyle();
        BossBar bossbar = Bukkit.createBossBar(KEY, style.name, style.color, style.style, style.flags);
        bossbar.setVisible(true);
        bossbar.setProgress(1.0);

        spawned.setRemoveWhenFarAway(false);

        this.instances.put(spawned, bossbar);
    }

    @Override
    public final void onDamaged(@Nonnull EntityDamageEvent e) {
        this.onBossDamaged(e);

        if (!e.isCancelled() && e.getEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) e.getEntity();
            BossBar bossbar = this.instances.get(entity);

            double finalHealth = entity.getHealth() - e.getFinalDamage();
            if (finalHealth > 0) {
                bossbar.setProgress(finalHealth / entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
            }
        }
    }

    protected void onBossDamaged(@Nonnull EntityDamageEvent e) { }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void onDeath(@Nonnull EntityDeathEvent e) {
        BossBar bossbar = this.instances.get(e.getEntity());
        bossbar.removeAll();
        this.instances.remove(e.getEntity());
    }

    @OverridingMethodsMustInvokeSuper
    public void onBossPattern(@Nonnull LivingEntity mob) {
        this.num++;
        if (this.num >= 10) {
            Location l = mob.getLocation();
            long dist = (long) getBossBarDistance() * getBossBarDistance();

            BossBar bossbar = this.instances.get(mob);
            if (bossbar != null) {
                List<Player> players = bossbar.getPlayers();

                for (Player player : mob.getWorld().getPlayers()) {
                    double distSquared = l.distanceSquared(player.getLocation());

                    if (distSquared <= dist && !players.contains(player)) {
                        bossbar.addPlayer(player);
                    } else if (distSquared > dist && players.contains(player)) {
                        bossbar.removePlayer(player);
                    }
                }
            }

            this.num = 0;
        }
    }

    @Nullable
    protected final BossBar getBossBarForEntity(LivingEntity entity) {
        return this.instances.get(entity);
    }
}
package me.gallowsdove.foxymachines.types;

import me.gallowsdove.foxymachines.FoxyMachines;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

public class FoxyRecipeType {
    private static final CustomItem QUEST_ITEM = new CustomItem(new CustomItem(Material.MOJANG_BANNER_PATTERN, "&6任务奖励", "", "&e&o通过剑来完成此任务.",
            "&e&o使用 &c/foxy quest &e&o来查看你当前的任务."));
    static {
        ItemMeta meta = QUEST_ITEM.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        QUEST_ITEM.setItemMeta(meta);
    }

    public static RecipeType SACRIFICIAL_ALTAR = new RecipeType(new NamespacedKey(FoxyMachines.getInstance(), "sacrificial_altar"),
            new CustomItem(Material.POLISHED_BLACKSTONE_BRICKS, "&c献祭祭坛", "", "&e&o献祭怪物在献祭祭坛中",
                    "&e&o使用 &c/foxy altar &e&o来查看多方块结构的建造方法."));
    public static RecipeType FISHING = new RecipeType(new NamespacedKey(FoxyMachines.getInstance(), "fishing"),
            new CustomItem(Material.FISHING_ROD, "&b钓鱼", "", "&e&o通过钓鱼获得."));
    public static RecipeType QUEST = new RecipeType(new NamespacedKey(FoxyMachines.getInstance(), "quest"), QUEST_ITEM);
    public static RecipeType CUSTOM_MOB_DROP = new RecipeType(new NamespacedKey(FoxyMachines.getInstance(), "mob_drop"),
            new CustomItem(Material.DIAMOND_SWORD, "&a独特怪物掉落", "", "&e&o通过杀死指定怪物获得."));
}

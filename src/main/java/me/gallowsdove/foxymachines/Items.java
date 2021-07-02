package me.gallowsdove.foxymachines;

import io.github.mooy1.infinitylib.presets.LorePreset;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ColoredFireworkStar;
import me.gallowsdove.foxymachines.implementation.machines.ElectricGoldRefinery;
import me.gallowsdove.foxymachines.implementation.machines.ForcefieldDome;
import me.gallowsdove.foxymachines.implementation.machines.ImprovementForge;
import me.gallowsdove.foxymachines.implementation.machines.PotionMixer;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;


public final class Items{

    public static Category CATEGORY = new Category(
            new NamespacedKey(FoxyMachines.getInstance(), "foxy_machines"),
            new CustomItem(Material.SHEARS, "&4Foxy Machines(Foxy机器)", "", "&a> 单击打开"));

    public static Category GHOST_BLOCKS_CATEGORY = new Category(
            new NamespacedKey(FoxyMachines.getInstance(), "ghost_blocks"),
            new CustomItem(Material.GLASS, "&5幽灵方块", "", "&a> 单击打开"));

    public static final SlimefunItemStack ELECTRIC_WIND_STAFF = new SlimefunItemStack(
            "ELECTRIC_WIND_STAFF",
            Material.BLAZE_ROD,
            "&b电力风法杖",
            "",
            "&7乘风飞行.",
            "",
            "&c&o&8\u21E8 &e\u26A1 &70 / 100 J"
    );

    static {
        ELECTRIC_WIND_STAFF.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = ELECTRIC_WIND_STAFF.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ELECTRIC_WIND_STAFF.setItemMeta(meta);
    }

    public static final SlimefunItemStack ELECTRIC_FIRE_STAFF = new SlimefunItemStack(
            "ELECTRIC_FIRE_STAFF",
            Material.BLAZE_ROD,
            "&4电力火法杖",
            "",
            "&7制造火海.",
            "",
            "&c&o&8\u21E8 &e\u26A1 &70 / 100 J"
    );
    static {
        ELECTRIC_FIRE_STAFF.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = ELECTRIC_FIRE_STAFF.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ELECTRIC_FIRE_STAFF.setItemMeta(meta);
    }

    public static final SlimefunItemStack ELECTRIC_FIRE_STAFF_II = new SlimefunItemStack(
            "ELECTRIC_FIRE_STAFF_II",
            Material.BLAZE_ROD,
            "&4电力火法杖 &7- &eII",
            "",
            "&7火, 火, 更多火!",
            "",
            "&c&o&8\u21E8 &e\u26A1 &70 / 200 J"
    );
    static {
        ELECTRIC_FIRE_STAFF_II.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = ELECTRIC_FIRE_STAFF_II.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ELECTRIC_FIRE_STAFF_II.setItemMeta(meta);
    }

    public static final SlimefunItemStack HEALING_BOW = new SlimefunItemStack(
            "HEALING_BOW",
            Material.BOW,
            "&4治疗弓",
            "&c治疗 II",
            "",
            "&8终于有支援武器了."
    );

    public static final SlimefunItemStack REINFORCED_STRING = new SlimefunItemStack(
            "REINFORCED_STRING",
            Material.STRING,
            "&b强化线"
    );

    public static final SlimefunItemStack IMPROVEMENT_FORGE = new SlimefunItemStack(
            "IMPROVEMENT_FORGE",
            Material.SMITHING_TABLE,
            "&b改进锻造机",
            "",
            "&7用於改善粘液科技的工具,武器与盔甲.",
            "",
            LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
            LoreBuilder.powerPerSecond(ImprovementForge.ENERGY_CONSUMPTION)
    );

    public static final SlimefunItemStack IMPROVEMENT_CORE = new SlimefunItemStack(
            "IMPROVEMENT_CORE",
            "faff2eb498e5c6a04484f0c9f785b448479ab213df95ec91176a308a12add70",
            "&a改进核心",
            "",
            "&7将其与工具放置进改进锻造机,以改进它."
    );

    public static final SlimefunItemStack POTION_MIXER = new SlimefunItemStack(
            "POTION_MIXER",
            Material.BREWING_STAND	,
            "&b药水混合器",
            "",
            "&7用于混合药水.",
            "",
            LoreBuilder.machine(MachineTier.GOOD, MachineType.MACHINE),
            LoreBuilder.powerPerSecond(PotionMixer.ENERGY_CONSUMPTION)
    );

    public static final SlimefunItemStack ELECTRIC_GOLD_REFINERY = new SlimefunItemStack(
            "ELECTRIC_GOLD_REFINERY",
            Material.GOLD_BLOCK	,
            "&b电动精炼机",
            "",
            "&7将金粉精炼成金锭.",
            "",
            LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
            LoreBuilder.powerPerSecond(ElectricGoldRefinery.ENERGY_CONSUMPTION)
    );

    public static final SlimefunItemStack CHUNK_LOADER = new SlimefunItemStack(
            "CHUNK_LOADER",
            Material.BEACON,
            "&b区块加载器",
            "",
            "&7保持区块加载."
    );

    public static final SlimefunItemStack STABILIZED_BLISTERING_BLOCK = new SlimefunItemStack(
            "STABILIZED_BLISTERING_BLOCK",
            Material.SNOW_BLOCK,
            "&b稳定起泡砖",
            "",
            "&7稳定的材料."
    );

    public static final SlimefunItemStack BOOSTED_RAIL = new SlimefunItemStack(
            "BOOSTED_RAIL",
            Material.RAIL,
            "&f增强铁轨",
            "",
            "&7支持2.5x速度."
    );

    public static final SlimefunItemStack BOOSTED_ACTIVATOR_RAIL = new SlimefunItemStack(
            "BOOSTED_ACTIVATOR_RAIL",
            Material.ACTIVATOR_RAIL,
            "&f增强触发铁轨",
            "",
            "&7支持2.5x速度."
    );

    public static final SlimefunItemStack BOOSTED_DETECTOR_RAIL = new SlimefunItemStack(
            "BOOSTED_DETECTOR_RAIL",
            Material.DETECTOR_RAIL,
            "&f增强感测铁轨",
            "",
            "&7支持2.5x速度."
    );

    public static final SlimefunItemStack BOOSTED_POWERED_RAIL = new SlimefunItemStack(
            "BOOSTED_POWERED_RAIL",
            Material.POWERED_RAIL,
            "&f增强动力铁轨",
            "",
            "&7支持2.5x速度."
    );

    public static final SlimefunItemStack BERRY_BUSH_TRIMMER = new SlimefunItemStack(
            "BERRY_BUSH_TRIMMER",
            Material.SHEARS,
            "&e甜莓灌木丛修剪刀",
            "",
            "&7去除甜莓灌木丛中的刺."
    );

    public static final SlimefunItemStack FORCEFIELD_DOME = new SlimefunItemStack(
            "FORCEFIELD_DOME",
            Material.OBSERVER,
            "&4穹顶力场",
            "",
            "&7当通电时, 会形成保护屏障",
            "&7在半径32格内.",
            "&7断电或破坏后还原",
            "",
            LorePreset.energyPerSecond(ForcefieldDome.ENERGY_CONSUMPTION)
    );

    public static final SlimefunItemStack REMOTE_CONTROLLER = new SlimefunItemStack(
            "REMOTE_CONTROLLER",
            Material.NAME_TAG,
            "&c遥控装置",
            "",
            "&7允许你在远处控制穹顶力场-",
            "&7使用 Shift + 右键 来绑定.",
            "",
            "&c&o&8\u21E8 &e\u26A1 &70 / 1000 J"
    );

    public static final SlimefunItemStack FORCEFIELD_ENGINE = new SlimefunItemStack(
            "FORCEFIELD_ENGINE",
            Material.STRUCTURE_BLOCK,
            "&f力场引擎"
    );

    public static final SlimefunItemStack FORCEFIELD_STABILIZER = new SlimefunItemStack(
            "FORCEFIELD_STABILIZER",
            Material.STRUCTURE_VOID,
            "&f力场稳定器"
    );

    public static final SlimefunItemStack WIRELESS_TRANSMITTER = new SlimefunItemStack(
            "WIRELESS_TRANSMITTER",
            Material.REPEATER,
            "&f无线发讯器"
    );

    public static final SlimefunItemStack DEMONIC_INGOT = new SlimefunItemStack(
            "DEMONIC_INGOT",
            Material.GOLD_INGOT,
            "&c恶魔锭"
    );

    public static final SlimefunItemStack DEMONIC_PLATE = new SlimefunItemStack(
            "DEMONIC_PLATE",
            Material.LIGHT_WEIGHTED_PRESSURE_PLATE,
            "&c恶魔板"
    );

    public static final SlimefunItemStack AQUATIC_NETHERITE_INGOT = new SlimefunItemStack(
            "AQUATIC_NETHERITE_INGOT",
            Material.NETHERITE_INGOT,
            "&b水灵狱髓锭"
    );

    public static final SlimefunItemStack DAMIENIUM = new SlimefunItemStack(
            "DAMIENIUM",
            Material.GOLD_INGOT,
            "&a达米安元素"
    );

    public static final SlimefunItemStack SWEET_INGOT = new SlimefunItemStack(
            "SWEET_INGOT",
            Material.GOLD_INGOT,
            "&e甜蜜锭"
    );

    public static final SlimefunItemStack SWEETENED_SWEET_INGOT = new SlimefunItemStack(
            "SWEETENED_SWEET_INGOT",
            Material.GOLD_INGOT,
            "&e加糖的甜蜜锭"
    );

    public static final SlimefunItemStack SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS = new SlimefunItemStack(
            "SACRIFICIAL_ALTAR_BLACKSTONE_BRICKS",
            Material.POLISHED_BLACKSTONE_BRICKS,
            "&f祭品黑石砖",
            "",
            "&7用作献祭祭坛的一部份."
    );

    public static final SlimefunItemStack SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_WALL = new SlimefunItemStack(
            "SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_WALL",
            Material.POLISHED_BLACKSTONE_BRICK_WALL,
            "&f祭品黑石砖墙",
            "",
            "&7用作献祭祭坛的一部份."
    );

    public static final SlimefunItemStack SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_STAIRS = new SlimefunItemStack(
            "SACRIFICIAL_ALTAR_BLACKSTONE_BRICK_STAIRS",
            Material.POLISHED_BLACKSTONE_BRICK_STAIRS,
            "&f祭品黑石砖阶梯",
            "",
            "&7用作献祭祭坛的一部份."
    );

    public static final SlimefunItemStack SACRIFICIAL_ALTAR_SOUL_TORCH = new SlimefunItemStack(
            "SACRIFICIAL_ALTAR_SOUL_TORCH",
            Material.SOUL_TORCH,
            "&f祭品灵魂火把",
            "",
            "&7用作献祭祭坛的一部份."
    );

    public static final SlimefunItemStack SACRIFICIAL_ALTAR_BLACKSTONE_PRESSURE_PLATE = new SlimefunItemStack(
            "SACRIFICIAL_ALTAR_BLACKSTONE_PRESSURE_PLATE",
            Material.POLISHED_BLACKSTONE_PRESSURE_PLATE,
            "&f祭品垫",
            "",
            "&7用作献祭祭坛的一部份."
    );

    public static final SlimefunItemStack CURSED_RABBIT_PAW = new SlimefunItemStack(
            "CURSED_RABBIT_PAW",
            Material.RABBIT_FOOT,
            "&c诅咒兔爪"
    );

    public static final SlimefunItemStack HUMAN_SKULL = new SlimefunItemStack(
            "HUMAN_SKULL",
            Material.SKELETON_SKULL,
            "&c人类头骨"
    );

    public static final SlimefunItemStack BLOOD_INFUSED_SKULL = new SlimefunItemStack(
            "BLOOD_INFUSED_SKULL",
            "daa4e2294df370b9a50cb924cdda78f740b0fbaf5a687106178505c80a79addc",
            "&c注入鲜血的头骨"
    );

    public static final SlimefunItemStack BLOOD = new SlimefunItemStack(
            "BLOOD",
            Material.REDSTONE,
            "&c鲜血"
    );

    public static final SlimefunItemStack UNHOLY_WITHER_SKELETON_BONE = new SlimefunItemStack(
            "UNHOLY_WITHER_SKELETON_BONE",
            Material.BONE,
            "&c邪恶凋零骷髅骨头"
    );

    public static final SlimefunItemStack PURIFIED_BONE = new SlimefunItemStack(
            "PURIFIED_BONE",
            Material.BONE,
            "&b纯净骨头"
    );

    public static final SlimefunItemStack PURE_BONE_DUST = new SlimefunItemStack(
            "PURE_BONE_DUST",
            Material.BONE_MEAL,
            "&b纯净骨粉"
    );

    public static final SlimefunItemStack BUCKET_OF_BLOOD = new SlimefunItemStack(
            "BUCKET_OF_BLOOD",
            Material.LAVA_BUCKET,
            "&c鲜血之桶"
    );

    public static final SlimefunItemStack POSEIDONS_FISHING_ROD = new SlimefunItemStack(
            "POSEIDONS_FISHING_ROD",
            Material.FISHING_ROD,
            "&b波塞冬的钓鱼竿",
            "&7波塞冬的祝福",
            "",
            "&7可以抓特殊物品."
    );
    static {
        POSEIDONS_FISHING_ROD.addUnsafeEnchantment(Enchantment.LUCK, 5);
        POSEIDONS_FISHING_ROD.addUnsafeEnchantment(Enchantment.LURE, 3);
    }

    public static final SlimefunItemStack POSEIDONS_BLESSING = new SlimefunItemStack(
            "POSEIDONS_BLESSING",
            Material.HEART_OF_THE_SEA,
            "&b波塞冬的祝福"
    );

    public static final SlimefunItemStack CURSED_SWORD = new SlimefunItemStack(
            "CURSED_SWORD",
            Material.NETHERITE_SWORD,
            "&c诅咒之剑",
            "&7生命吸取 I",
            "",
            "&7困惑敌人. 增加伤害.",
            "&7可能对行使者产生负面影响."
    );

    public static final SlimefunItemStack CELESTIAL_SWORD = new SlimefunItemStack(
            "CELESTIAL_SWORD",
            Material.NETHERITE_SWORD,
            "&e天剑",
            "&7神之重击 II",
            "",
            "&7忽略 20% 的抗性."
    );

    public static final SlimefunItemStack ELUCIDATOR = new SlimefunItemStack(
            "ELUCIDATOR",
            Material.NETHERITE_SWORD,
            "&b阐释者",
            "&7伤害 III",
            "&7生命吸取 II",
            "&7过度治疗"
    );
    static {
        ELUCIDATOR.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
        ELUCIDATOR.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 5);
    }

    public static final SlimefunItemStack MAGIC_LUMP_4 = new SlimefunItemStack(
            "MAGIC_LUMP_4",
            Material.GOLD_NUGGET,
            "&6魔法颗粒 &7- &eIV",
            "",
            "&c&o等级: IV");

    public static final SlimefunItemStack MAGIC_LUMP_5 = new SlimefunItemStack(
            "MAGIC_LUMP_5",
            Material.GOLD_NUGGET,
            "&6魔法颗粒 &7- &eV",
            "",
            "&c&o等级: V");

    public static final SlimefunItemStack AQUATIC_HELMET = new SlimefunItemStack(
            "AQUATIC_HELMET",
            Material.NETHERITE_HELMET,
            "&b水灵头盔",
            "&7水下呼吸",
            "&7黑暗视觉"
    );
    static {
        AQUATIC_HELMET.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        AQUATIC_HELMET.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
        AQUATIC_HELMET.addUnsafeEnchantment(Enchantment.OXYGEN, 5);
        AQUATIC_HELMET.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
    }


    public static final SlimefunItemStack RESISTANT_CHESTPLATE = new SlimefunItemStack(
            "RESISTANT_CHESTPLATE",
            Material.NETHERITE_CHESTPLATE,
            "&a抗性胸甲",
            "&7抗性 I",
            "&7回复 II"
    );
    static {
        RESISTANT_CHESTPLATE.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
    }

    public static final SlimefunItemStack FIERY_LEGGINGS = new SlimefunItemStack(
            "FIERY_LEGGINGS",
            Material.NETHERITE_LEGGINGS,
            "&c火焰护腿",
            "&7火之领域 II"
    );
    static {
        FIERY_LEGGINGS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        FIERY_LEGGINGS.addUnsafeEnchantment(Enchantment.THORNS, 6);
    }

    public static final SlimefunItemStack LIGHT_BOOTS = new SlimefunItemStack(
            "LIGHT_BOOTS",
            Material.NETHERITE_BOOTS,
            "&e轻便靴子",
            "&7跳跃提升 I",
            "&7速度 I",
            "&7轻量化"
    );
    static {
        LIGHT_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        LIGHT_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 5);
        LIGHT_BOOTS.addUnsafeEnchantment(Enchantment.SOUL_SPEED, 5);
    }

    public static final SlimefunItemStack AQUATIC_HELMET_FRAME = new SlimefunItemStack(
            "HELMET_FRAME",
            Material.CHAINMAIL_HELMET,
            "&f水灵头盔框架",
            "",
            "&7制作材料"
    );

    public static final SlimefunItemStack RESISTANT_CHESTPLATE_FRAME = new SlimefunItemStack(
            "RESISTANT_CHESTPLATE_FRAME",
            Material.CHAINMAIL_CHESTPLATE,
            "&f抗性胸甲框架",
            "",
            "&7制作材料"
    );

    public static final SlimefunItemStack FIERY_LEGGINGS_FRAME = new SlimefunItemStack(
            "FIERY_LEGGINGS_FRAME",
            Material.CHAINMAIL_LEGGINGS,
            "&f火焰护腿框架",
            "",
            "&7制作材料"
    );

    public static final SlimefunItemStack LIGHT_BOOTS_FRAME = new SlimefunItemStack(
            "LIGHT_BOOTS_FRAME",
            Material.CHAINMAIL_BOOTS,
            "&f轻便靴子框架",
            "",
            "&7制作材料"
    );

    public static final SlimefunItemStack CURSED_SHARD = new SlimefunItemStack(
            "CURSED_SHARD",
            Material.NETHERITE_SCRAP,
            "&c诅咒碎片"
    );

    public static final SlimefunItemStack CELESTIAL_SHARD = new SlimefunItemStack(
            "CELESTIAL_SHARD",
            Material.PRISMARINE_SHARD,
            "&e天界碎片"
    );

    public static final SlimefunItemStack EQUANIMOUS_GEM = new SlimefunItemStack(
            "EQUANIMOUS_GEM",
            Material.EMERALD,
            "&a镇静宝石"
    );

    public static final SlimefunItemStack POLAR_FOX_HIDE = new SlimefunItemStack(
            "POLAR_FOX_HIDE",
            Material.SNOWBALL,
            "&f北极狐狸皮"
    );

    public static final SlimefunItemStack MAGMA_ESSENCE = new SlimefunItemStack(
            "MAGMA_ESSENCE",
            Material.MAGMA_CREAM,
            "&c岩浆精华"
    );

    public static final SlimefunItemStack TROPICAL_FISH_SCALE = new SlimefunItemStack(
            "TROPICAL_FISH_SCALE",
            Material.TROPICAL_FISH_SPAWN_EGG,
            "&b热带鱼鳞"
    );

    public static final SlimefunItemStack PARROT_FEATHER = new SlimefunItemStack(
            "PARROT_FEATHER",
            Material.FEATHER,
            "&a鹦鹉羽毛"
    );

    public static final SlimefunItemStack UNBREAKABLE_RUNE = new SlimefunItemStack(
            "UNBREAKABLE_RUNE",
            new ColoredFireworkStar(
                    Color.fromRGB(0, 188, 0),
                    "&7古代魔法符文 &8&l[&2&l牢不可破&8&l]",
                    "",
                    "&e将此符文放在掉落的物品上",
                    "&e使其&2无法破坏"
            ));

    public static final SlimefunItemStack PIXIE_QUEEN_SPAWN_EGG = new SlimefunItemStack(
            "PIXIE_QUEEN_SPAWN_EGG",
            Material.CREEPER_SPAWN_EGG,
            "&a精灵女王 生怪蛋"
    );

    public static final SlimefunItemStack HEADLESS_HORSEMAN_SPAWN_EGG = new SlimefunItemStack(
            "HEADLESS_HORSEMAN_SPAWN_EGG",
            Material.SPIDER_SPAWN_EGG,
            "&c无头骑士 生怪蛋"
    );

    public static final SlimefunItemStack PIXIE_QUEEN_HEART = new SlimefunItemStack(
            "PIXIE_QUEEN_HEART",
            Material.FERMENTED_SPIDER_EYE,
            "&4精灵女王之心"
    );

    public static final SlimefunItemStack PIXIE_DUST = new SlimefunItemStack(
            "PIXIE_DUST",
            Material.SUGAR,
            "&e精灵之粉",
            "",
            "&7力量 IV"
    );

    public static final SlimefunItemStack VILE_PUMPKIN = new SlimefunItemStack(
            "VILE_PUMPKIN",
            Material.CARVED_PUMPKIN,
            "&c邪恶南瓜"
    );

    public static final SlimefunItemStack VILE_SEEDS = new SlimefunItemStack(
            "VILE_SEEDS",
            Material.MELON_SEEDS,
            "&c邪恶种子",
            "",
            "&7生命值提升 V"
    );

    public static final SlimefunItemStack ACRI_ARCUM = new SlimefunItemStack(
            "ACRI_ARCUM",
            Material.BOW,
            "&e锋利之弓",
            "",
            "&7伤害 III",
            "&7穿甲弹 II"
    );
    static {
        ACRI_ARCUM.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 7);
        ACRI_ARCUM.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
    }

    public static final SlimefunItemStack GHOST_BLOCK_REMOVER = new SlimefunItemStack(
            "GHOST_BLOCK_REMOVER",
            Material.CLOCK,
            "&e幽灵方块移除器",
            "",
            "&7右键点击来移除幽灵方块."
    );
}

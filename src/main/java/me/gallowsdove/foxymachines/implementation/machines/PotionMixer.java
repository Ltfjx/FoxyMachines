package me.gallowsdove.foxymachines.implementation.machines;

import me.gallowsdove.foxymachines.Items;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.protection.ProtectableAction;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.InventoryBlock;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Color;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class PotionMixer extends SlimefunItem implements InventoryBlock, EnergyNetComponent {
  private static final int[] BORDER = {3, 4, 5, 27, 28, 29, 33, 34, 35, 36, 37, 38, 42, 43, 44};
  private static final int[] BORDER_IN = {0, 1, 2, 6, 7, 8, 9, 11, 12, 14, 15, 17, 18, 19, 20, 24, 25, 26};
  private static final int[] BORDER_OUT = {21, 22, 23, 30, 32, 39, 40, 41};
  public static final int ENERGY_CONSUMPTION = 28;
  public static final int CAPACITY = 128;

  public static Map<Block, MachineRecipe> processing = new HashMap<>();
  public static Map<Block, Integer> progress = new HashMap<>();

  protected final List<MachineRecipe> recipes = new ArrayList<>();

  public PotionMixer() {
    super(Items.machines, Items.POTION_MIXER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
      SlimefunItems.CARBONADO, SlimefunItems.GOLD_24K, SlimefunItems.CARBONADO,
      SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.BREWING_STAND), SlimefunItems.ELECTRIC_MOTOR,
      SlimefunItems.GOLD_24K, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.GOLD_24K
    });


    new BlockMenuPreset(getID(), "&6藥水混合器") {

      @Override
      public void init() {
        constructMenu(this);
      }

      public boolean canOpen(Block b, Player p) {
        return p.hasPermission("slimefun.inventory.bypass")
          || SlimefunPlugin.getProtectionManager().hasPermission(p, b.getLocation(),
          ProtectableAction.ACCESS_INVENTORIES
        );
      }

      @Override
      public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
        return new int[0];
      }

      @Override
      public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
        if (flow == ItemTransportFlow.WITHDRAW) {
          return getOutputSlots();
        }

        List<Integer> slots = new ArrayList<>();
        for (int slot : getInputSlots()) {
          if (menu.getItemInSlot(slot) != null) {
            slots.add(slot);
          }
        }

        Collections.sort(slots, compareSlots(menu));

        int[] array = new int[slots.size()];

        for (int i = 0; i < slots.size(); i++) {
          array[i] = slots.get(i);
        }

        return array;
      }
    };
    
    registerBlockHandler(getID(), (p, b, stack, reason) -> {
      BlockMenu inv = BlockStorage.getInventory(b);
      
      if (inv != null) {
        inv.dropItems(b.getLocation(), getOutputSlots());
        inv.dropItems(b.getLocation(), getInputSlots());
      }

      return true;
    });
  }

  private Comparator<Integer> compareSlots(DirtyChestMenu menu) {
    return Comparator.comparingInt(slot -> menu.getItemInSlot(slot).getAmount());
  }

  @Override
  public int[] getInputSlots() {
    return new int[] { 10, 16 };
  }

  @Override
  public int[] getOutputSlots() {
    return new int[] {31};
  }

  @Override
  public EnergyNetComponentType getEnergyComponentType() {
    return EnergyNetComponentType.CONSUMER;
  }

  @Override
  public int getCapacity() {
    return CAPACITY;
  }

  public int getEnergyConsumption() {
    return ENERGY_CONSUMPTION;
  }

  public String getMachineIdentifier() {
    return "POTION_MIXER";
  }

  public List<MachineRecipe> getMachineRecipes() {
    return recipes;
  }

  public List<ItemStack> getDisplayRecipes() {
    List<ItemStack> displayRecipes = new ArrayList<>(recipes.size() * 2);

    for (MachineRecipe recipe : recipes) {
      if (recipe.getInput().length != 1) continue;

      displayRecipes.add(recipe.getInput()[0]);
      displayRecipes.add(recipe.getOutput()[0]);
    }

    return displayRecipes;
  }

  public ItemStack getProgressBar() {
    return new ItemStack(Material.GOLDEN_HOE);
  }

  public MachineRecipe getProcessing(Block b) {
    return processing.get(b);
  }

  public boolean isProcessing(Block b) {
    return getProcessing(b) != null;
  }

  public void registerRecipe(MachineRecipe recipe) {
    recipe.setTicks(recipe.getTicks());
    recipes.add(recipe);
  }

  public void registerRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
    registerRecipe(new MachineRecipe(seconds, input, output));
  }

  public void registerRecipe(int seconds, ItemStack input, ItemStack output) {
    registerRecipe(new MachineRecipe(seconds, new ItemStack[] { input }, new ItemStack[] { output }));
  }

  @Override
  public void preRegister() {
    addItemHandler(new BlockTicker() {

      @Override
      public void tick(Block b, SlimefunItem sf, Config data) {
        PotionMixer.this.tick(b);
      }

      @Override
      public boolean isSynchronized() {
        return false;
      }
    });
  }

  protected void tick(Block b) {
    BlockMenu inv = BlockStorage.getInventory(b);

    if (isProcessing(b)) {
      int timeleft = progress.get(b);

      if (timeleft > 0) {
        ChestMenuUtils.updateProgressbar(inv, 13, timeleft, processing.get(b).getTicks(), getProgressBar());

        if (isChargeable()) {
          if (getCharge(b.getLocation()) < getEnergyConsumption()) {
            return;
          }

          removeCharge(b.getLocation(), getEnergyConsumption());
        }
        progress.put(b, timeleft - 1);
      }
      else {
        inv.replaceExistingItem(13, new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "));

        for (ItemStack output : processing.get(b).getOutput()) {
          inv.pushItem(output.clone(), getOutputSlots());
        }

        progress.remove(b);
        processing.remove(b);
      }
    }
    else {
        MachineRecipe next = findNextRecipe(inv);

        if (next != null) {
            processing.put(b, next);
            progress.put(b, next.getTicks());
        }
    }
  }

  protected PotionEffect[] getCustomEffectsFromBaseData(PotionData potionData, boolean lingering) {
    PotionType type = potionData.getType();
    boolean extended = potionData.isExtended();
    boolean upgraded = potionData.isUpgraded();
    int d = 1;
    if (lingering){
      d = 4;
    }
    switch (type) {
      case FIRE_RESISTANCE:
        if (extended) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 8*60*20/d, 0)};
        } else {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3*60*20/d, 0)};
        }
      case INSTANT_DAMAGE:
        if (upgraded) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.HARM, 0, 1)};
        } else {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.HARM, 0, 0)};
        }
      case INSTANT_HEAL:
        if (upgraded) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.HEAL, 0, 1)};
        } else {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.HEAL, 0, 0)};
        }
      case INVISIBILITY:
        if (extended) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.INVISIBILITY, 8*60*20/d, 0)};
        } else {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.INVISIBILITY, 3*60*20/d, 0)};
        }
      case JUMP:
        if (extended) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.JUMP, 8*60*20/d, 0)};
        } else if (upgraded) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.JUMP, 90*20/d, 1)};
        } else {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.JUMP, 3*60*20/d, 0)};
        }
      case LUCK:
        return new PotionEffect[] {new PotionEffect(PotionEffectType.LUCK, 5*60*20/d, 0)};
      case NIGHT_VISION:
        if (extended) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.NIGHT_VISION, 8*60*20/d, 0)};
        } else {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.NIGHT_VISION, 3*60*20/d, 0)};
        }
      case POISON:
        if (extended) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.POISON, 45*20/d, 0)};
        } else if (upgraded) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.POISON, 21*20/d, 1)};
        } else {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.POISON, 90*20/d, 0)};
        }
      case REGEN:
        if (extended) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.REGENERATION, 45*20/d, 0)};
        } else if (upgraded) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.REGENERATION, 22*20/d, 1)};
        } else {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.REGENERATION, 90*20/d, 0)};
        }
      case SLOW_FALLING:
        if (extended) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.SLOW_FALLING, 4*60*20/d, 0)};
        } else {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.SLOW_FALLING, 90*20/d, 0)};
        }
      case SLOWNESS:
        if (extended) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.SLOW, 4*60*20/d, 0)};
        } else if (upgraded) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.SLOW, 20*20/d, 3)};
        } else {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.SLOW, 90*20/d, 0)};
        }
      case SPEED:
        if (extended) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.SPEED, 8*60*20/d, 0)};
        } else if (upgraded) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.SPEED, 90*20/d, 1)};
        } else {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.SPEED, 3*60*20/d, 0)};
        }
      case STRENGTH:
        if (extended) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 8*60*20/d, 0)};
        } else if (upgraded) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 90*20/d, 1)};
        } else {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3*60*20/d, 0)};
        }
      case TURTLE_MASTER:
        if (extended) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.SLOW, 40*20/d, 3), new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40*20/d, 2)};
        } else if (upgraded) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.SLOW, 20*20/d, 5), new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*20/d, 3)};
        } else {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.SLOW, 20*20/d, 3), new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*20/d, 2)};
      }
      case WATER_BREATHING:
        if (extended) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.WATER_BREATHING, 8*60*20/d, 0)};
        } else {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.WATER_BREATHING, 3*60*20/d, 0)};
      }
      case WEAKNESS:
        if (extended) {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.WEAKNESS, 4*60*20/d, 0)};
        } else {
          return new PotionEffect[] {new PotionEffect(PotionEffectType.WEAKNESS, 90*20/d, 0)};
        }
    }
    return new PotionEffect[] {};
  }

  protected MachineRecipe findNextRecipe(BlockMenu menu) {
    int[] slots = getInputSlots();
    ItemStack potion1 = menu.getItemInSlot(slots[0]);
    ItemStack potion2 = menu.getItemInSlot(slots[1]);

    if (potion1 != null && potion2 != null) {

      if ((potion1.getType() == Material.POTION && potion2.getType() == Material.POTION) ||
       (potion1.getType() == Material.SPLASH_POTION && potion2.getType() == Material.SPLASH_POTION) ||
       (potion1.getType() == Material.LINGERING_POTION && potion2.getType() == Material.LINGERING_POTION)){

        boolean lingering = false;
        if (potion1.getType() == Material.LINGERING_POTION) {
          lingering = true;
        }
        ItemStack potion = potion1.clone();
        potion.setAmount(1);

        PotionMeta potionMeta = (PotionMeta) potion1.getItemMeta();
        PotionMeta potion2Meta = (PotionMeta) potion2.getItemMeta();

        List<PotionEffect> potion1Effects = new ArrayList<PotionEffect>(potionMeta.getCustomEffects());
        List<PotionEffect> potion2Effects = potion2Meta.getCustomEffects();

        for (int i = 0; i < potion2Effects.size(); i++) {
          for (int j = 0; j < potion1Effects.size(); j++) {
            if (potion1Effects.get(j).getType() == potion2Effects.get(i).getType()) {
              if (potion1Effects.get(j).getAmplifier() > potion2Effects.get(i).getAmplifier()) {
                potion2Effects.set(i, potion1Effects.get(j));
              }
              potion1Effects.remove(j);
              break;
            }
          }
          potionMeta.addCustomEffect(potion2Effects.get(i), true);
        }

        for (PotionEffect effect : getCustomEffectsFromBaseData(potionMeta.getBasePotionData(), lingering)) {
          potionMeta.addCustomEffect(effect, false);
        }

        for (PotionEffect effect : getCustomEffectsFromBaseData(potion2Meta.getBasePotionData(), lingering)) {
          for (PotionEffect effect2 : potionMeta.getCustomEffects()) {
            if (effect.getType() == effect2.getType()) {
              if (effect.getAmplifier() > effect2.getAmplifier()) {
                potionMeta.addCustomEffect(effect, true);
                break;
              }
            }
          };
          potionMeta.addCustomEffect(effect, false);
        }

        List<String> lore = new ArrayList<String>() {{add("無法在釀造台上使用");}};
        potionMeta.setBasePotionData​(new PotionData(PotionType.UNCRAFTABLE, false, false));
        switch(potion1.getType()){
          case POTION:
            potionMeta.setDisplayName(ChatColor.AQUA + "混和藥水");
            break;
          case LINGERING_POTION:
            lore.add(ChatColor.RED + "由於Minecraft顯示的時間不正確");
            lore.add(ChatColor.RED + "錯誤,將其乘以4得到真實時間.");
            potionMeta.setDisplayName(ChatColor.AQUA + "混和滯留藥水");
            break;
          case SPLASH_POTION:
            potionMeta.setDisplayName(ChatColor.AQUA + "混和飛濺藥水");
            break;
        }
        potionMeta.setLore(lore);
        potionMeta.setColor(Color.AQUA);

        potion.setItemMeta(potionMeta);


        if (!menu.fits(potion, getOutputSlots())) {
          return null;
        }

        for (int inputSlot : getInputSlots()) {
          menu.consumeItem(inputSlot);
        }

        return new MachineRecipe((1+potionMeta.getCustomEffects().size())*12, new ItemStack[] { potion1, potion2 }, new ItemStack[] { potion });
      }
    }

    return null;
  }

  protected void constructMenu(BlockMenuPreset preset) {
    for (int i : BORDER) {
      preset.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
    }

    for (int i : BORDER_IN) {
      preset.addItem(i, new SlimefunItemStack("_UI_INPUT_SLOT", Material.CYAN_STAINED_GLASS_PANE, " "), ChestMenuUtils.getEmptyClickHandler());
    }

    for (int i : BORDER_OUT) {
      preset.addItem(i, new SlimefunItemStack("_UI_OUTPUT_SLOT", Material.ORANGE_STAINED_GLASS_PANE, " "), ChestMenuUtils.getEmptyClickHandler());
    }

    preset.addItem(13, new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "), ChestMenuUtils.getEmptyClickHandler());

    for (int i : getOutputSlots()) {
      preset.addMenuClickHandler(i, new AdvancedMenuClickHandler() {

        @Override
        public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
          return false;
        }

        @Override
        public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
          return cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR;
        }
      });
  }
}
}

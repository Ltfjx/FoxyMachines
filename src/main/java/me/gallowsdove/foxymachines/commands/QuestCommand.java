package me.gallowsdove.foxymachines.commands;

import io.github.mooy1.infinitylib.commands.AbstractCommand;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.gallowsdove.foxymachines.Items;
import me.gallowsdove.foxymachines.utils.QuestUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.List;

public class QuestCommand extends AbstractCommand {
    public QuestCommand() {
        super("quest", "Prints your current quest.", "foxymachines.info");
    }

    @Override
    public void onExecute(@Nonnull CommandSender commandSender, @Nonnull String[] strings) {
        if (!(commandSender instanceof Player p) || strings.length != 1) {
            return;
        }

        if (SlimefunUtils.isItemSimilar(p.getInventory().getItemInMainHand(), Items.CURSED_SWORD, false, false)) {
            QuestUtils.sendQuestLine(p, Items.CURSED_SWORD);
        } else if (SlimefunUtils.isItemSimilar(p.getInventory().getItemInMainHand(), Items.CELESTIAL_SWORD, false, false)) {
            QuestUtils.sendQuestLine(p, Items.CELESTIAL_SWORD);
        } else {
            p.sendMessage(ChatColor.LIGHT_PURPLE + "你必须拿着 " + ChatColor.RED + "诅咒之剑" +
                    ChatColor.LIGHT_PURPLE + " 或者 " + ChatColor.YELLOW + "天剑" + ChatColor.LIGHT_PURPLE + " 来检查你的任务.");
        }
    }

    @Override
    public void onTab(@Nonnull CommandSender commandSender, @Nonnull String[] strings, @Nonnull List<String> list) { }
}

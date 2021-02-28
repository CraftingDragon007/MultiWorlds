package ch.gamepowerx.multiworlds.commands;

import ch.gamepowerx.multiworlds.MultiWorlds;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MWVer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.BLUE + "[" + ChatColor.RED + "MutliWorlds" + ChatColor.BLUE + "] " + ChatColor.YELLOW + "Version: " + MultiWorlds.getVersion());
        sender.sendMessage(ChatColor.GREEN + "Report Problems, Bugs and Errors to \n" + ChatColor.GRAY + "https://github.com/CraftingDragon007/MultiWorlds/issues");
        return true;
    }
}

package ch.gamepowerx.multiworlds.commands;

import ch.gamepowerx.multiworlds.MultiWorlds;
import ch.gamepowerx.multiworlds.util.MWorld;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MWInfo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==1) {
            MWorld world = MultiWorlds.worldList.getMWorld(args[0]);
            if (world != null) {
                sender.sendMessage("-----------------------------------------------------");
                sender.sendMessage("Name: " + world.getWorld().getName());
                sender.sendMessage("Slots: " + world.getPlayerCount() + " / " + world.getMaxPlayers());
                sender.sendMessage("Type: " + world.getWorld().getWorldType().getName());
                if (world.isGameModeSpecified())
                    sender.sendMessage("GameMode: " + world.getGameMode().name().toLowerCase());
                sender.sendMessage("-----------------------------------------------------");
                return true;
            } else
                sender.sendMessage("§cError die Welt existiert nicht oder wurde nicht geladen!");
        }else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                MWorld world = MultiWorlds.worldList.getMWorld(player.getWorld());
                if (world != null) {
                    sender.sendMessage("-----------------------------------------------------");
                    sender.sendMessage("Name: " + world.getWorld().getName());
                    sender.sendMessage("Slots: " + world.getPlayerCount() + " / " + world.getMaxPlayers());
                    if(world.getWorld().getWorldType().getName()!=null)
                    sender.sendMessage("Type: " + world.getWorld().getWorldType().getName());
                    if (world.isGameModeSpecified())
                        sender.sendMessage("GameMode: " + world.getGameMode().name().toLowerCase());
                    sender.sendMessage("-----------------------------------------------------");
                    return true;
                } else {
                    sender.sendMessage("§cError die Welt existiert nicht oder wurde nicht geladen!");
                    return true;
                }
            }else
            return false;
        }
        return true;
    }
}

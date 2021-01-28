package ch.gamepowerx.multiworlds.commands;

import ch.gamepowerx.multiworlds.MultiWorlds;
import ch.gamepowerx.multiworlds.util.MWorld;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class MWDelete implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MWorld mWorld = MultiWorlds.worldList.getMWorld(args[0]);
        if (mWorld != null) {
            File file = new File(Bukkit.getUpdateFolder()+"/../"+mWorld.getWorld().getName());
            Bukkit.unloadWorld(mWorld.getWorld(),false);
            MultiWorlds.worldList.remove(mWorld);
            if(file.delete()){
                sender.sendMessage("§aDie Welt §6" + mWorld.getWorld().getName() + "§a wurde §cgelöscht§a!");
            }else {
                sender.sendMessage("§cDie Welt konnte nicht gelöscht werden!");
            }
        }
        return true;
    }
}

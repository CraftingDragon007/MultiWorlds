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
import java.io.IOException;

public class MWDelete implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MWorld mWorld = MultiWorlds.worldList.getMWorld(args[0]);
        if (mWorld != null) {
            File file = new File(Bukkit.getWorldContainer() + "/" + mWorld.getWorld().getName());
            Bukkit.unloadWorld(mWorld.getWorld(),false);
            MultiWorlds.worldList.remove(mWorld);
            boolean result = deleteDirectory(file);
            if(result){
                sender.sendMessage("§aDie Welt §6" + mWorld.getWorld().getName() + "§a wurde §cgelöscht§a!");
            }else {
                if(Bukkit.getWorlds().contains(mWorld.getWorld()))
                MultiWorlds.worldList.add(mWorld);
                sender.sendMessage("§cDie Welt konnte nicht gelöscht werden!");
            }
        }
        return true;
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}

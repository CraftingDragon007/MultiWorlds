/*
 *        MultiWorlds basic world management Plugin for Minecraft
 *                  Copyright (C) 2021 CraftingDragon007
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

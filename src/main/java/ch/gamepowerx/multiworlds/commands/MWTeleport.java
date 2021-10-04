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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MWTeleport implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(args.length==1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                MWorld mWorld = MultiWorlds.worldList.getMWorld(args[0]);
                if (mWorld != null) {
                    player.teleport(mWorld.getWorld().getSpawnLocation());
                }
            }
        }else if(args.length==2){
            Player target = Bukkit.getPlayer(args[0]);
            if(target!=null){
                MWorld mWorld = MultiWorlds.worldList.getMWorld(args[1]);
                if (mWorld != null) {
                    target.teleport(mWorld.getWorld().getSpawnLocation());
                }
            }
        }
        return true;
    }
}

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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings("deprecation")
public class MWInfo implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 1) {
            MWorld world = MultiWorlds.worldList.getMWorld(args[0]);
            if (world != null) {
                sender.sendMessage("-----------------------------------------------------");
                sender.sendMessage("Name: " + world.getWorld().getName());
                sender.sendMessage("Slots: " + world.getPlayerCount() + " / " + world.getMaxPlayers());
                if (world.getWorld().getWorldType() != null)
                    sender.sendMessage("Type: " + world.getWorld().getWorldType().getName());
                if (world.isGameModeSpecified())
                    sender.sendMessage("GameMode: " + world.getGameMode().name().toLowerCase());
                sender.sendMessage("-----------------------------------------------------");
                return true;
            } else
                sender.sendMessage("§cError die Welt existiert nicht oder wurde nicht geladen!");
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                MWorld world = MultiWorlds.worldList.getMWorld(player.getWorld());
                if (world != null) {
                    sender.sendMessage("-----------------------------------------------------");
                    sender.sendMessage("Name: " + world.getWorld().getName());
                    sender.sendMessage("Slots: " + world.getPlayerCount() + " / " + world.getMaxPlayers());
                    world.getWorld().getWorldType();
                    sender.sendMessage("Type: " + Objects.requireNonNull(world.getWorld().getWorldType()).getName());
                    if (world.isGameModeSpecified())
                        sender.sendMessage("GameMode: " + world.getGameMode().name().toLowerCase());
                    sender.sendMessage("-----------------------------------------------------");
                } else {
                    sender.sendMessage("§cError die Welt existiert nicht oder wurde nicht geladen!");
                }
                return true;
            } else
                return false;
        }
        return true;
    }
}

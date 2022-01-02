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
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("ALL")
public class MWCreate implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        try {
            MWorld mWorld;
            MultiWorlds.tempHelp.remove(sender);
            if (args.length == 2) {
                mWorld = new MWorld(args[0], World.Environment.valueOf(args[1].toUpperCase()));
            } else if (args.length == 3) {
                // Variable         Name                               Environment                                 Type
                mWorld = new MWorld(args[0], World.Environment.valueOf(args[1].toUpperCase()), WorldType.getByName(args[2].toUpperCase()));
            } else if (args.length == 4) {
                // Variable         Name                               Environment                                 Type         /         Generate Structures
                if (isWorldType(args[2])) {
                    mWorld = new MWorld(args[0], World.Environment.valueOf(args[1].toUpperCase()), WorldType.getByName(args[2].toUpperCase()), Boolean.getBoolean(args[3]));
                } else if (isWorldType(args[3])) {
                    mWorld = new MWorld(args[0], World.Environment.valueOf(args[1].toUpperCase()), WorldType.getByName(args[3].toUpperCase()), Boolean.getBoolean(args[2]));
                } else {
                    return false;
                }
            } else if (args.length == 5) {
                // Variable         Name                               Environment                                 Type                   Generate Structures           Hardcore  / GameMode
                if (!isGameMode(args[4])) {
                    mWorld = new MWorld(args[0], World.Environment.valueOf(args[1].toUpperCase()), WorldType.getByName(args[2].toUpperCase()), Boolean.getBoolean(args[3]), Boolean.getBoolean(args[4]));
                } else {
                    mWorld = new MWorld(args[0], World.Environment.valueOf(args[1].toUpperCase()), WorldType.getByName(args[2].toUpperCase()), Boolean.getBoolean(args[3]), GameMode.valueOf(args[4].toUpperCase()));
                }
            } else if (args.length == 6) {
                // Variable         Name                               Environment                                 Type                         Generate Structures                        Hardcore                     GameMode
                mWorld = new MWorld(args[0], World.Environment.valueOf(args[1].toUpperCase()), WorldType.getByName(args[2].toUpperCase()), Boolean.getBoolean(args[3]), Boolean.getBoolean(args[4]), GameMode.valueOf(args[5].toUpperCase()));
            } else {
                return false;
            }
            MultiWorlds.worldList.add(mWorld);
            return true;
        } catch (Exception e) {

            sender.sendMessage("§cMindestens ein §lFehler §r§cist aufgetreten! Überprüfen sie die Argumente!");
            return false;
        }
    }

    private Boolean isWorldType(String type) {
        try {
            WorldType.valueOf(type.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private Boolean isGameMode(String gameMode) {
        try {
            GameMode.valueOf(gameMode);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

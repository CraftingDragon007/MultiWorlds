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

package ch.gamepowerx.multiworlds.tabcompleter;

import ch.gamepowerx.multiworlds.MultiWorlds;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MWCreate implements TabCompleter {
    private final List<String> completer = new ArrayList<>();

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        completer.clear();
        if (args.length == 1) {
            if (!MultiWorlds.tempHelp.contains(sender)) {
                sender.sendMessage("§6[MWCreate] §bName §a| §bUmgebung §a| §bTyp §a| §bStrukturen §a| §bHardcore §a| §bGameMode");
                MultiWorlds.tempHelp.add(sender);
            }
        } else if (args.length == 2) {
            for (World.Environment environment : World.Environment.values()) {
                addString(environment.toString());
            }
        } else if (args.length == 3) {

            for (WorldType worldType : WorldType.values()) {
                addString(worldType.getName().toLowerCase());
            }
        } else if (args.length == 4) {
            addBoolean();
        } else if (args.length == 5) {
            addBoolean();
        } else if (args.length == 6) {
            for (GameMode gameMode : GameMode.values()) {
                addString(gameMode.name().toLowerCase());
            }
        }
        return completer;
    }

    private void addBoolean() {
        completer.add("true");
        completer.add("false");
    }

    private void addString(String value) {
        completer.add(value);
    }

    @SuppressWarnings("unused")
    private void addObject(Object value) {
        completer.add(String.valueOf(value));
    }
}

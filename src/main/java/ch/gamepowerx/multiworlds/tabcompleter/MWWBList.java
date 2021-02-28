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

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MWWBList implements TabCompleter {
    private final List<String> completer = new ArrayList<>();
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        completer.clear();
        if(args.length==1){
            addString("add");
            addString("remove");
            addString("off");
            addString("on");
        }else if(args.length==2 | args.length==3){
            for (Player p : Bukkit.getOnlinePlayers()) {
                addString(p.getName());
            }
        }
        return completer;
    }

    @NotNull
    private void addBoolean(){ completer.add("true"); completer.add("false"); }
    @NotNull
    private void addString(String value){
        completer.add(value);
    }
    @NotNull
    private void addObject(Object value){
        completer.add(String.valueOf(value));
    }
}

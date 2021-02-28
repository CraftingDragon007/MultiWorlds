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

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import javax.management.monitor.GaugeMonitor;
import java.util.ArrayList;
import java.util.List;

public class MWSet implements TabCompleter {
    private final List<String> completer = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        completer.clear();
        if(args.length==1){
            addString("pvp");
            addString("difficulty");
            addString("spawn");
            addString("weather");
            addString("maxSlots");
            addString("gamemode");
            addString("time");
            addString("whitelist");
            addString("blacklist");
        }
        if(args.length==2){
            if(args[0].equalsIgnoreCase("pvp")) {
                addBoolean();
            }
            if(args[0].equalsIgnoreCase("whitelist")){
                addBoolean();
            }
            if(args[0].equalsIgnoreCase("blacklist")){
                addBoolean();
            }
            if(args[0].equalsIgnoreCase("difficulty")){
                addString("peaceful");
                addString("easy");
                addString("normal");
                addString("hard");
            }
            if(args[0].equalsIgnoreCase("weather")){
                addString("sun");
                addString("storm");
            }
            if(args[0].equalsIgnoreCase("gamemode")){
                for(GameMode gameMode : GameMode.values()){
                    addString(gameMode.name().toLowerCase());
                }
            }
        }
        return completer;
    }

    private void addBoolean(){
        completer.add("true");
        completer.add("false");
    }

    private void addString(String value){
        completer.add(value);
    }

    private void addObject(Object value){
        completer.add(String.valueOf(value));
    }
}

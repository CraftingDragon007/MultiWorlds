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
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MWSet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            MWorld mWorld = MultiWorlds.worldList.getMWorld(player.getWorld());
            if (args.length == 2) {
                switch (args[0]) {
                    case "pvp" :
                        if(getBoolean(args[1]) != null) {
                            mWorld.getWorld().setPVP(getBoolean(args[1]));
                            if(getBoolean(args[1])) {
                                player.sendMessage("§aPVP wurde in der Welt §6"+mWorld.getWorld().getName()+"§a aktiviert!");
                            }else player.sendMessage("§aPVP wurde in der Welt §6"+mWorld.getWorld().getName()+"§a deaktiviert!");
                        }else player.sendMessage("§cUngültiges Argument: "+args[1]);
                        break;
                    case "difficulty" :
                        try {
                            mWorld.getWorld().setDifficulty(Difficulty.valueOf(args[1].toUpperCase()));
                            player.sendMessage("§aIn der Welt §6"+mWorld.getWorld().getName()+"§a wurde die Schwierigkeit auf §6"+args[1]+"§a gesetzt!");
                        }catch(IllegalArgumentException e){
                            player.sendMessage("§cUngültiges Argument: "+args[1]);
                        }
                        break;
                    case "spawn" :
                        mWorld.getWorld().setSpawnLocation(player.getLocation());
                        player.sendMessage("§aDer Spawnpoint dieser Welt wurde auf §6"+player.getLocation().getBlockX()+" "+player.getLocation().getBlockY()+" "+player.getLocation().getBlockZ()+"§a festgelegt!");
                        break;
                    case "weather" :
                        if(args[1].equalsIgnoreCase("sun")) {
                            mWorld.getWorld().setWeatherDuration(36000);
                        }else
                        if(args[1].equalsIgnoreCase("storm")) {
                            mWorld.getWorld().setThunderDuration(36000);
                        }else player.sendMessage("§cUngültiges Argument: "+args[1]);
                        break;
                    case "gamemode" :
                        try {
                            mWorld.setGameMode(GameMode.valueOf(args[1].toUpperCase()));
                            if(mWorld.isGameModeSpecified()){
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if(!p.hasPermission("MW.bypassgamemode"))
                                        if(p.getWorld().equals(mWorld.getWorld()))
                                           p.setGameMode(mWorld.getGameMode());
                                }

                            }
                        }catch(IllegalArgumentException e){
                            player.sendMessage("§cUngültiges Argument: "+args[1]);
                        }
                        break;
                    case "time" :
                        try {
                            mWorld.getWorld().setTime(Long.parseLong(args[1]));
                        }catch(NumberFormatException e){
                            player.sendMessage("§cUngültiges Argument: "+args[1]);
                        }
                        break;
                    case "whitelist" :
                        if(getBoolean(args[1]) != null) {
                            mWorld.setWhitelistEnabled(getBoolean(args[1]));
                            if(getBoolean(args[1])) {
                                player.sendMessage("§aDie Whitelist wurde in der Welt §6" + mWorld.getWorld().getName() + "§a aktiviert!");
                            }else player.sendMessage("§aDie Whitelist wurde in der Welt §6" + mWorld.getWorld().getName() + "§a deaktiviert!");
                        }else player.sendMessage("§cUngültiges Argument: "+args[1]);
                        break;
                    case "blacklist" :
                        if(getBoolean(args[1])!=null) {
                            mWorld.setBlacklistEnabled(getBoolean(args[1]));
                            if(getBoolean(args[1])) {
                                player.sendMessage("§aDie Blacklist wurde in der Welt §6" + mWorld.getWorld().getName() + "§a aktiviert!");
                            }else player.sendMessage("§aDie Blacklist wurde in der Welt §6" + mWorld.getWorld().getName() + "§a deaktiviert!");
                        }else player.sendMessage("§cUngültiges Argument: "+args[1]);
                        break;
                }
            }
        }
        return true;
    }

    @Nullable
    private Boolean getBoolean(String value){
        if(value.equalsIgnoreCase("true")){
            return true;
        }else
        if(value.equalsIgnoreCase("false")){
            return false;
        }else return null;
    }
}

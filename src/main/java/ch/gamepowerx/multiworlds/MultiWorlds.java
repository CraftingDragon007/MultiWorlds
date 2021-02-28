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

package ch.gamepowerx.multiworlds;

import ch.gamepowerx.multiworlds.commands.*;
import ch.gamepowerx.multiworlds.tabcompleter.MWWBList;
import ch.gamepowerx.multiworlds.tabcompleter.Worlds;
import ch.gamepowerx.multiworlds.util.MWorld;
import ch.gamepowerx.multiworlds.util.MWorldList;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class MultiWorlds extends JavaPlugin {
    public static boolean isInRegeneration = false;
    public static MWorldList worldList;
    public static FileConfiguration worldsConfig;
    public static FileConfiguration config;
    public static HashMap<String,UUID> tempNames = new HashMap<>();
    public static List<CommandSender> tempHelp = new ArrayList<>();
    private static String version;

    @Override
    public void onEnable() {
        version = getDescription().getVersion();
        loadConfig();
        loadWorlds();
        registerCommandsAndListener();
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveWorldsToConfig();
        saveConfigs();
    }

    private void loadWorlds(){
        worldList = new MWorldList();
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        File file = new File(getDataFolder(), "worlds.yml");
        if (!file.exists()) {
            try (InputStream in = this.getResource("worlds.yml")) {
                assert in != null;
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        worldsConfig = new YamlConfiguration();
        try {
            worldsConfig.load(file);
        } catch (IOException| InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
        if(config.getBoolean("firstrun")){
            for(World world : Bukkit.getWorlds()){
                String worldName = world.getName();
                worldsConfig.set(worldName+".name",worldName);
                worldsConfig.set(worldName+".specifiedgamemode",false);
                worldsConfig.set(worldName+".gamemode",null);
                worldsConfig.set(worldName+".maxslots",config.get("standardmaxslots"));
                MWorld mWorld = new MWorld(world);
                worldList.add(mWorld);
            }
            config.set("firstrun",false);
        }else {
            for(String worldName : worldsConfig.getKeys(false)){
                //System.out.println(worldName);
                World world = Bukkit.getWorld(worldName);
                if(world!=null){
                    List<UUID> whitelist = new ArrayList<>();
                    List<UUID> blacklist = new ArrayList<>();
                    for(String uuid : worldsConfig.getStringList(worldName+".whitelist")){
                        whitelist.add(UUID.fromString(uuid));
                    }
                    for(String uuid : worldsConfig.getStringList(worldName+".blacklist")){
                        blacklist.add(UUID.fromString(uuid));
                    }
                    if(worldsConfig.getBoolean(worldName+".specifiedgamemode")) {
                        MWorld mWorld = new MWorld(world, worldsConfig.getInt(worldName + ".maxslots"),GameMode.valueOf(worldsConfig.getString(worldName + ".gamemode").toUpperCase()),whitelist,blacklist);
                        worldList.add(mWorld);
                    }else {
                        MWorld mWorld = new MWorld(world,worldsConfig.getInt(worldName + ".maxslots"),whitelist,blacklist);
                        worldList.add(mWorld);
                    }

                }else {
                    MWorld mWorld = new MWorld(worldsConfig.getString(worldName+".name"), WorldType.NORMAL,true,false, worldsConfig.getInt(worldName + ".maxslots"));
                    worldList.add(mWorld);
                }
            }
            for(Player player : Bukkit.getOnlinePlayers()){
                MWorld world = worldList.getMWorld(player.getWorld());
                world.joinWorld(player);
            }
        }
        try {
            worldsConfig.save(file);
            config.save(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveWorldsToConfig(){
        for(MWorld mWorld : worldList){
            String worldName = mWorld.getWorld().getName();
            worldsConfig.set(worldName+".name",worldName);
            worldsConfig.set(worldName+".specifiedgamemode",mWorld.isGameModeSpecified());
            if(mWorld.isGameModeSpecified())
            worldsConfig.set(worldName+".gamemode",mWorld.getGameMode().toString());
            worldsConfig.set(worldName+".maxslots",mWorld.getMaxPlayers());
            List<String> whitelist = new ArrayList<>();
            List<String> blacklist = new ArrayList<>();
            for(UUID uuid : mWorld.getWhitelist()){
                whitelist.add(uuid.toString());
            }
            for(UUID uuid : mWorld.getBlacklist()){
                blacklist.add(uuid.toString());
            }
            worldsConfig.set(worldName + ".whitelist",whitelist);
            worldsConfig.set(worldName + ".blacklist",blacklist);
        }
    }

    public void saveConfigs(){
        try {
            worldsConfig.save(new File(getDataFolder(), "worlds.yml"));
            config.save(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig(){
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try (InputStream in = this.getResource("config.yml")) {
                assert in != null;
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
           getConfig().load(file);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
        config = getConfig();
    }

    private void registerCommandsAndListener(){
        getCommand("mwgamerule").setExecutor(new MWGameRule());
        getCommand("mwgamerule").setTabCompleter(new ch.gamepowerx.multiworlds.tabcompleter.MWGameRule());
        getCommand("mwcreate").setExecutor(new MWCreate());
        getCommand("mwcreate").setTabCompleter(new ch.gamepowerx.multiworlds.tabcompleter.MWCreate());
        getCommand("mwteleport").setExecutor(new MWTeleport());
        getCommand("mwteleport").setTabCompleter(new Worlds());
        getCommand("mwinfo").setExecutor(new MWInfo());
        getCommand("mwinfo").setTabCompleter(new Worlds());
        getCommand("mwset").setExecutor(new MWSet());
        getCommand("mwset").setTabCompleter(new ch.gamepowerx.multiworlds.tabcompleter.MWSet());
        getCommand("mwregenerate").setExecutor(new MWRegenerate());
        getCommand("mwwhitelist").setExecutor(new MWhitelist());
        getCommand("mwwhitelist").setTabCompleter(new MWWBList());
        getCommand("mwblacklist").setExecutor(new MWBlacklist());
        getCommand("mwblacklist").setTabCompleter(new MWWBList());
        getCommand("mwdelete").setExecutor(new MWDelete());
        getCommand("mwdelete").setTabCompleter(new Worlds());
        getCommand("mwver").setExecutor(new MWVer());
        Bukkit.getPluginManager().registerEvents(new Listeners(),this);
    }

    public static String getVersion(){
        return version;
    }
}

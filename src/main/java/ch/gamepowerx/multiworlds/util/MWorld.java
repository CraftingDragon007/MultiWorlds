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

package ch.gamepowerx.multiworlds.util;

import ch.gamepowerx.multiworlds.MultiWorlds;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("ALL")

public class MWorld {
    private final World world;
    private GameMode gameMode;
    private Boolean specificGameMode;
    private Boolean whitelistEnabled;
    private Boolean blacklistEnabled;
    private Integer maxPlayers;
    private final List<Player> playerList;
    private final List<UUID> whitelist;
    private final List<UUID> blacklist;

    public MWorld(World world) {
        this.world = world;
        this.playerList = new ArrayList<>();
        this.whitelist = new ArrayList<>();
        this.blacklist = new ArrayList<>();
        this.specificGameMode = false;
        this.whitelistEnabled = false;
        this.blacklistEnabled = false;
        this.maxPlayers = MultiWorlds.config.getInt("standardmaxslots");
    }

    public MWorld(World world, Integer maxPlayers, List<UUID> whitelist, List<UUID> blacklist) {
        this.world = world;
        this.playerList = new ArrayList<>();
        this.whitelist = new ArrayList<>();
        this.blacklist = new ArrayList<>();
        this.whitelist.addAll(whitelist);
        this.blacklist.addAll(blacklist);
        this.specificGameMode = false;
        this.whitelistEnabled = false;
        this.blacklistEnabled = false;
        this.maxPlayers = maxPlayers;
    }

    public MWorld(World world, GameMode gameMode) {
        this.world = world;
        this.playerList = new ArrayList<>();
        this.whitelist = new ArrayList<>();
        this.blacklist = new ArrayList<>();
        this.gameMode = gameMode;
        this.specificGameMode = true;
        this.whitelistEnabled = false;
        this.blacklistEnabled = false;
        this.maxPlayers = MultiWorlds.config.getInt("standardmaxslots");
    }

    public MWorld(World world, Integer maxPlayers, GameMode gameMode, List<UUID> whitelist, List<UUID> blacklist) {
        this.world = world;
        this.playerList = new ArrayList<>();
        this.whitelist = new ArrayList<>();
        this.blacklist = new ArrayList<>();
        this.whitelist.addAll(whitelist);
        this.blacklist.addAll(blacklist);
        this.gameMode = gameMode;
        this.specificGameMode = true;
        this.whitelistEnabled = false;
        this.blacklistEnabled = false;
        this.maxPlayers = maxPlayers;
    }

    public MWorld(String name, World.Environment environment, WorldType type, Boolean generateStructures, Boolean hardcore, GameMode gameMode) {
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.environment(environment);
        worldCreator.type(type);
        worldCreator.generateStructures(generateStructures);
        worldCreator.hardcore(hardcore);
        if (gameMode != null) {
            this.gameMode = gameMode;
            this.specificGameMode = true;
        } else this.specificGameMode = false;
        this.whitelistEnabled = false;
        this.blacklistEnabled = false;
        this.world = Bukkit.createWorld(worldCreator);
        this.playerList = new ArrayList<>();
        this.whitelist = new ArrayList<>();
        this.blacklist = new ArrayList<>();
        this.maxPlayers = MultiWorlds.config.getInt("standardmaxslots");
    }

    public MWorld(String name, World.Environment environment, WorldType type, Boolean generateStructures, Boolean hardcore) {
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.environment(environment);
        worldCreator.type(type);
        worldCreator.generateStructures(generateStructures);
        worldCreator.hardcore(hardcore);
        this.specificGameMode = false;
        this.whitelistEnabled = false;
        this.blacklistEnabled = false;
        this.world = Bukkit.createWorld(worldCreator);
        this.playerList = new ArrayList<>();
        this.whitelist = new ArrayList<>();
        this.blacklist = new ArrayList<>();
        this.maxPlayers = MultiWorlds.config.getInt("standardmaxslots");
    }

    public MWorld(String name, WorldType type, Boolean generateStructures, Boolean hardcore, Integer maxPlayers) {
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.type(type);
        worldCreator.generateStructures(generateStructures);
        worldCreator.hardcore(hardcore);
        this.specificGameMode = false;
        this.whitelistEnabled = false;
        this.blacklistEnabled = false;
        this.world = Bukkit.createWorld(worldCreator);
        this.playerList = new ArrayList<>();
        this.whitelist = new ArrayList<>();
        this.blacklist = new ArrayList<>();
        this.maxPlayers = maxPlayers;
    }

    public MWorld(String name, World.Environment environment, WorldType type, Boolean generateStructures) {
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.environment(environment);
        worldCreator.type(type);
        worldCreator.generateStructures(generateStructures);
        worldCreator.hardcore(false);
        this.specificGameMode = false;
        this.whitelistEnabled = false;
        this.blacklistEnabled = false;
        this.world = Bukkit.createWorld(worldCreator);
        this.playerList = new ArrayList<>();
        this.whitelist = new ArrayList<>();
        this.blacklist = new ArrayList<>();
        this.maxPlayers = MultiWorlds.config.getInt("standardmaxslots");
    }

    public MWorld(String name, World.Environment environment, WorldType type) {
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.environment(environment);
        worldCreator.type(type);
        worldCreator.generateStructures(true);
        worldCreator.hardcore(false);
        this.specificGameMode = false;
        this.whitelistEnabled = false;
        this.blacklistEnabled = false;
        this.world = Bukkit.createWorld(worldCreator);
        this.playerList = new ArrayList<>();
        this.whitelist = new ArrayList<>();
        this.blacklist = new ArrayList<>();
        this.maxPlayers = MultiWorlds.config.getInt("standardmaxslots");
    }

    public MWorld(String name, World.Environment environment) {
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.environment(environment);
        worldCreator.type(WorldType.NORMAL);
        worldCreator.generateStructures(true);
        worldCreator.hardcore(false);
        this.specificGameMode = false;
        this.whitelistEnabled = false;
        this.blacklistEnabled = false;
        this.world = Bukkit.createWorld(worldCreator);
        this.playerList = new ArrayList<>();
        this.whitelist = new ArrayList<>();
        this.blacklist = new ArrayList<>();
        this.maxPlayers = MultiWorlds.config.getInt("standardmaxslots");
    }

    public MWorld(String name, World.Environment environment, WorldType type, Boolean generateStructures, GameMode gameMode) {
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.environment(environment);
        worldCreator.type(type);
        worldCreator.generateStructures(generateStructures);
        worldCreator.hardcore(false);
        if (gameMode != null) {
            this.gameMode = gameMode;
        }
        this.specificGameMode = false;
        this.whitelistEnabled = false;
        this.blacklistEnabled = false;
        this.world = Bukkit.createWorld(worldCreator);
        this.playerList = new ArrayList<>();
        this.whitelist = new ArrayList<>();
        this.blacklist = new ArrayList<>();
        this.maxPlayers = MultiWorlds.config.getInt("standardmaxslots");

    }

    public boolean isGameModeSpecified() {
        return specificGameMode;
    }

    public boolean isWhitelistEnabled() {
        return whitelistEnabled;
    }

    public boolean isBlacklistEnabled() {
        return blacklistEnabled;
    }

    public void joinWorld(Player player) {
        if (maxPlayers != -1)
            if (playerList.size() < maxPlayers) {
                if (!playerList.contains(player)) {
                    playerList.add(player);
                }
            } else if (player.hasPermission("MW.bypassSlots")) {
                if (!playerList.contains(player)) {
                    playerList.add(player);
                }
            } else {
                player.sendMessage("§cDie Welt ist leider schon voll: §6" + playerList.size() + "/" + maxPlayers + " §c!");
                MWorld world = MultiWorlds.worldList.getMWorld(MultiWorlds.config.getString("spawnworld"));
                if (world != null) {
                    player.teleport(world.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
                }
            }
    }

    public boolean joinWorld(Player player, World fallbackWorld) {
        boolean success = false;
        if (maxPlayers != -1) {
            if (playerList.size() < maxPlayers) {
                if (!playerList.contains(player)) {
                    playerList.add(player);
                    success = true;
                }
            } else if (player.hasPermission("MW.bypassSlots")) {
                if (!playerList.contains(player)) {
                    playerList.add(player);
                    success = true;
                }
            } else if (this.equals(MultiWorlds.worldList.getMWorld(fallbackWorld))) {
                player.kickPlayer("§cDie Fallback Welt ist leider voll: §6" + playerList.size() + "/" + maxPlayers + " §c! \n §6Für weitere Informationen kontaktieren sie bitte den Server Admin!");
            } else {
                player.sendMessage("§cDie Welt ist leider schon voll: §6" + playerList.size() + "/" + maxPlayers + " §c!");
                MWorld world = MultiWorlds.worldList.getMWorld(fallbackWorld);
                this.leaveWorld(player);
                player.teleport(world.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
            }
            if (whitelistEnabled) {
                if (whitelist.contains(player.getUniqueId())) {
                    success = true;
                } else success = player.hasPermission("MW.bypassMWWBList");
            }
            if (blacklistEnabled) {
                if (!blacklist.contains(player.getUniqueId())) {
                    success = true;
                } else success = player.hasPermission("MW.bypassMWWBList");
            }
        }else success = true;
        return success;
    }

    public void leaveWorld(Player player) {
        playerList.remove(player);
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
        this.specificGameMode = true;
    }

    public void setWhitelistEnabled(boolean enabled) {
        this.whitelistEnabled = enabled;
    }

    public void setBlacklistEnabled(boolean enabled) {
        this.blacklistEnabled = enabled;
    }

    public List<UUID> getWhitelist() {
        return whitelist;
    }

    public List<UUID> getBlacklist() {
        return blacklist;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public Integer getPlayerCount() {
        return playerList.size();
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public World getWorld() {
        return world;
    }
}

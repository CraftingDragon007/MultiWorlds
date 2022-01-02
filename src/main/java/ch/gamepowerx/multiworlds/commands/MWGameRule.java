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
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings("ALL")
public class MWGameRule implements CommandExecutor {

    @SuppressWarnings("rawtypes")
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        GameRule<Boolean> booleanGameRule = null;
        GameRule<Integer> integerGameRule = null;
        Class type = null;
        MWorld mWorld = null;
        if (args.length == 3) {
            mWorld = MultiWorlds.worldList.getMWorld(args[0]);
            switch (args[1]) {
                case "announceAdvancements":
                    booleanGameRule = GameRule.ANNOUNCE_ADVANCEMENTS;
                    type = GameRule.ANNOUNCE_ADVANCEMENTS.getType();
                    break;
                case "commandBlockOutput":
                    booleanGameRule = GameRule.COMMAND_BLOCK_OUTPUT;
                    type = GameRule.COMMAND_BLOCK_OUTPUT.getType();
                    break;
                case "disableElytraMovementCheck":
                    booleanGameRule = GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK;
                    type = GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK.getType();
                    break;
                case "disableRaids":
                    booleanGameRule = GameRule.DISABLE_RAIDS;
                    type = GameRule.DISABLE_RAIDS.getType();
                    break;
                case "doDaylightCycle":
                    booleanGameRule = GameRule.DO_DAYLIGHT_CYCLE;
                    type = GameRule.DO_DAYLIGHT_CYCLE.getType();
                    break;
                case "doEntityDrops":
                    booleanGameRule = GameRule.DO_ENTITY_DROPS;
                    type = GameRule.DO_ENTITY_DROPS.getType();
                    break;
                case "doFireTick":
                    booleanGameRule = GameRule.DO_FIRE_TICK;
                    type = GameRule.DO_FIRE_TICK.getType();
                    break;
                case "doInsomnia":
                    booleanGameRule = GameRule.DO_INSOMNIA;
                    type = GameRule.DO_INSOMNIA.getType();
                    break;
                case "doImmediateRespawn":
                    booleanGameRule = GameRule.DO_IMMEDIATE_RESPAWN;
                    type = GameRule.DO_IMMEDIATE_RESPAWN.getType();
                    break;
                case "doLimitedCrafting":
                    booleanGameRule = GameRule.DO_LIMITED_CRAFTING;
                    type = GameRule.DO_LIMITED_CRAFTING.getType();
                    break;
                case "doMobLoot":
                    booleanGameRule = GameRule.DO_MOB_LOOT;
                    type = GameRule.DO_MOB_LOOT.getType();
                    break;
                case "doMobSpawning":
                    booleanGameRule = GameRule.DO_MOB_SPAWNING;
                    type = GameRule.DO_MOB_SPAWNING.getType();
                    break;
                case "doPatrolSpawning":
                    booleanGameRule = GameRule.DO_PATROL_SPAWNING;
                    type = GameRule.DO_PATROL_SPAWNING.getType();
                    break;
                case "doTileDrops":
                    booleanGameRule = GameRule.DO_TILE_DROPS;
                    type = GameRule.DO_TILE_DROPS.getType();
                    break;
                case "doTraderSpawning":
                    booleanGameRule = GameRule.DO_TRADER_SPAWNING;
                    type = GameRule.DO_TRADER_SPAWNING.getType();
                    break;
                case "doWeatherCycle":
                    booleanGameRule = GameRule.DO_WEATHER_CYCLE;
                    type = GameRule.DO_WEATHER_CYCLE.getType();
                    break;
                case "drowningDamage":
                    booleanGameRule = GameRule.DROWNING_DAMAGE;
                    type = GameRule.DROWNING_DAMAGE.getType();
                    break;
                case "fallDamage":
                    booleanGameRule = GameRule.FALL_DAMAGE;
                    type = GameRule.FALL_DAMAGE.getType();
                    break;
                case "fireDamage":
                    booleanGameRule = GameRule.FIRE_DAMAGE;
                    type = GameRule.FIRE_DAMAGE.getType();
                    break;
                case "forgiveDeadPlayers":
                    booleanGameRule = GameRule.FORGIVE_DEAD_PLAYERS;
                    type = GameRule.FORGIVE_DEAD_PLAYERS.getType();
                    break;
                case "keepInventory":
                    booleanGameRule = GameRule.KEEP_INVENTORY;
                    type = GameRule.KEEP_INVENTORY.getType();
                    break;
                case "logAdminCommands":
                    booleanGameRule = GameRule.LOG_ADMIN_COMMANDS;
                    type = GameRule.LOG_ADMIN_COMMANDS.getType();
                    break;
                case "maxCommandChainLength":
                    integerGameRule = GameRule.MAX_COMMAND_CHAIN_LENGTH;
                    type = GameRule.MAX_COMMAND_CHAIN_LENGTH.getType();
                    break;
                case "maxEntityCramming":
                    integerGameRule = GameRule.MAX_ENTITY_CRAMMING;
                    type = GameRule.MAX_ENTITY_CRAMMING.getType();
                    break;
                case "mobGriefing":
                    booleanGameRule = GameRule.MOB_GRIEFING;
                    type = GameRule.MOB_GRIEFING.getType();
                    break;
                case "naturalRegeneration":
                    booleanGameRule = GameRule.NATURAL_REGENERATION;
                    type = GameRule.NATURAL_REGENERATION.getType();
                    break;
                case "randomTickSpeed":
                    integerGameRule = GameRule.RANDOM_TICK_SPEED;
                    type = GameRule.RANDOM_TICK_SPEED.getType();
                    break;
                case "reducedDebugInfo":
                    booleanGameRule = GameRule.REDUCED_DEBUG_INFO;
                    type = GameRule.REDUCED_DEBUG_INFO.getType();
                    break;
                case "sendCommandFeedback":
                    booleanGameRule = GameRule.SEND_COMMAND_FEEDBACK;
                    type = GameRule.SEND_COMMAND_FEEDBACK.getType();
                    break;
                case "showDeathMessages":
                    booleanGameRule = GameRule.SHOW_DEATH_MESSAGES;
                    type = GameRule.SHOW_DEATH_MESSAGES.getType();
                    break;
                case "spawnRadius":
                    integerGameRule = GameRule.SPAWN_RADIUS;
                    type = GameRule.SPAWN_RADIUS.getType();
                    break;
                case "spectatorsGenerateChunks":
                    booleanGameRule = GameRule.SPECTATORS_GENERATE_CHUNKS;
                    type = GameRule.SPECTATORS_GENERATE_CHUNKS.getType();
                    break;
                case "universalAnger":
                    booleanGameRule = GameRule.UNIVERSAL_ANGER;
                    type = GameRule.UNIVERSAL_ANGER.getType();
                    break;
            }
        }
        if (args.length == 2) {
            if (sender instanceof Player) {
                mWorld = MultiWorlds.worldList.getMWorld(((Player) sender).getWorld());
                switch (args[0]) {
                    case "announceAdvancements":
                        booleanGameRule = GameRule.ANNOUNCE_ADVANCEMENTS;
                        type = GameRule.ANNOUNCE_ADVANCEMENTS.getType();
                        break;
                    case "commandBlockOutput":
                        booleanGameRule = GameRule.COMMAND_BLOCK_OUTPUT;
                        type = GameRule.COMMAND_BLOCK_OUTPUT.getType();
                        break;
                    case "disableElytraMovementCheck":
                        booleanGameRule = GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK;
                        type = GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK.getType();
                        break;
                    case "disableRaids":
                        booleanGameRule = GameRule.DISABLE_RAIDS;
                        type = GameRule.DISABLE_RAIDS.getType();
                        break;
                    case "doDaylightCycle":
                        booleanGameRule = GameRule.DO_DAYLIGHT_CYCLE;
                        type = GameRule.DO_DAYLIGHT_CYCLE.getType();
                        break;
                    case "doEntityDrops":
                        booleanGameRule = GameRule.DO_ENTITY_DROPS;
                        type = GameRule.DO_ENTITY_DROPS.getType();
                        break;
                    case "doFireTick":
                        booleanGameRule = GameRule.DO_FIRE_TICK;
                        type = GameRule.DO_FIRE_TICK.getType();
                        break;
                    case "doInsomnia":
                        booleanGameRule = GameRule.DO_INSOMNIA;
                        type = GameRule.DO_INSOMNIA.getType();
                        break;
                    case "doImmediateRespawn":
                        booleanGameRule = GameRule.DO_IMMEDIATE_RESPAWN;
                        type = GameRule.DO_IMMEDIATE_RESPAWN.getType();
                        break;
                    case "doLimitedCrafting":
                        booleanGameRule = GameRule.DO_LIMITED_CRAFTING;
                        type = GameRule.DO_LIMITED_CRAFTING.getType();
                        break;
                    case "doMobLoot":
                        booleanGameRule = GameRule.DO_MOB_LOOT;
                        type = GameRule.DO_MOB_LOOT.getType();
                        break;
                    case "doMobSpawning":
                        booleanGameRule = GameRule.DO_MOB_SPAWNING;
                        type = GameRule.DO_MOB_SPAWNING.getType();
                        break;
                    case "doPatrolSpawning":
                        booleanGameRule = GameRule.DO_PATROL_SPAWNING;
                        type = GameRule.DO_PATROL_SPAWNING.getType();
                        break;
                    case "doTileDrops":
                        booleanGameRule = GameRule.DO_TILE_DROPS;
                        type = GameRule.DO_TILE_DROPS.getType();
                        break;
                    case "doTraderSpawning":
                        booleanGameRule = GameRule.DO_TRADER_SPAWNING;
                        type = GameRule.DO_TRADER_SPAWNING.getType();
                        break;
                    case "doWeatherCycle":
                        booleanGameRule = GameRule.DO_WEATHER_CYCLE;
                        type = GameRule.DO_WEATHER_CYCLE.getType();
                        break;
                    case "drowningDamage":
                        booleanGameRule = GameRule.DROWNING_DAMAGE;
                        type = GameRule.DROWNING_DAMAGE.getType();
                        break;
                    case "fallDamage":
                        booleanGameRule = GameRule.FALL_DAMAGE;
                        type = GameRule.FALL_DAMAGE.getType();
                        break;
                    case "fireDamage":
                        booleanGameRule = GameRule.FIRE_DAMAGE;
                        type = GameRule.FIRE_DAMAGE.getType();
                        break;
                    case "forgiveDeadPlayers":
                        booleanGameRule = GameRule.FORGIVE_DEAD_PLAYERS;
                        type = GameRule.FORGIVE_DEAD_PLAYERS.getType();
                        break;
                    case "keepInventory":
                        booleanGameRule = GameRule.KEEP_INVENTORY;
                        type = GameRule.KEEP_INVENTORY.getType();
                        break;
                    case "logAdminCommands":
                        booleanGameRule = GameRule.LOG_ADMIN_COMMANDS;
                        type = GameRule.LOG_ADMIN_COMMANDS.getType();
                        break;
                    case "maxCommandChainLength":
                        integerGameRule = GameRule.MAX_COMMAND_CHAIN_LENGTH;
                        type = GameRule.MAX_COMMAND_CHAIN_LENGTH.getType();
                        break;
                    case "maxEntityCramming":
                        integerGameRule = GameRule.MAX_ENTITY_CRAMMING;
                        type = GameRule.MAX_ENTITY_CRAMMING.getType();
                        break;
                    case "mobGriefing":
                        booleanGameRule = GameRule.MOB_GRIEFING;
                        type = GameRule.MOB_GRIEFING.getType();
                        break;
                    case "naturalRegeneration":
                        booleanGameRule = GameRule.NATURAL_REGENERATION;
                        type = GameRule.NATURAL_REGENERATION.getType();
                        break;
                    case "randomTickSpeed":
                        integerGameRule = GameRule.RANDOM_TICK_SPEED;
                        type = GameRule.RANDOM_TICK_SPEED.getType();
                        break;
                    case "reducedDebugInfo":
                        booleanGameRule = GameRule.REDUCED_DEBUG_INFO;
                        type = GameRule.REDUCED_DEBUG_INFO.getType();
                        break;
                    case "sendCommandFeedback":
                        booleanGameRule = GameRule.SEND_COMMAND_FEEDBACK;
                        type = GameRule.SEND_COMMAND_FEEDBACK.getType();
                        break;
                    case "showDeathMessages":
                        booleanGameRule = GameRule.SHOW_DEATH_MESSAGES;
                        type = GameRule.SHOW_DEATH_MESSAGES.getType();
                        break;
                    case "spawnRadius":
                        integerGameRule = GameRule.SPAWN_RADIUS;
                        type = GameRule.SPAWN_RADIUS.getType();
                        break;
                    case "spectatorsGenerateChunks":
                        booleanGameRule = GameRule.SPECTATORS_GENERATE_CHUNKS;
                        type = GameRule.SPECTATORS_GENERATE_CHUNKS.getType();
                        break;
                    case "universalAnger":
                        booleanGameRule = GameRule.UNIVERSAL_ANGER;
                        type = GameRule.UNIVERSAL_ANGER.getType();
                        break;
                }
            }
        }

        if (mWorld != null)
            if (booleanGameRule != null | integerGameRule != null)
                if (type != null)
                    if (type.equals(Boolean.class)) {
                        if (args.length == 2) {
                            if (args[1].equalsIgnoreCase("true")) {
                                mWorld.getWorld().setGameRule(Objects.requireNonNull(booleanGameRule), true);
                                sender.sendMessage("§aIn der Welt §6" + mWorld.getWorld().getName() + " §awurde die Spielregel §6" + booleanGameRule.getName() + " §aauf §6" + args[1] + " §agesetzt!");
                            } else if (args[1].equalsIgnoreCase("false")) {
                                mWorld.getWorld().setGameRule(Objects.requireNonNull(booleanGameRule), false);
                                sender.sendMessage("§aIn der Welt §6" + mWorld.getWorld().getName() + " §awurde die Spielregel §6" + booleanGameRule.getName() + " §aauf §6" + args[1] + " §agesetzt!");
                            }
                        } else {
                            if (args[2].equalsIgnoreCase("true")) {
                                mWorld.getWorld().setGameRule(Objects.requireNonNull(booleanGameRule), true);
                                sender.sendMessage("§aIn der Welt §6" + mWorld.getWorld().getName() + " §awurde die Spielregel §6" + booleanGameRule.getName() + " §aauf §6" + args[2] + " §agesetzt!");
                            } else if (args[2].equalsIgnoreCase("false")) {
                                mWorld.getWorld().setGameRule(Objects.requireNonNull(booleanGameRule), false);
                                sender.sendMessage("§aIn der Welt §6" + mWorld.getWorld().getName() + " §awurde die Spielregel §6" + booleanGameRule.getName() + " §aauf §6" + args[2] + " §agesetzt!");
                            }
                        }
                    } else if (type.equals(Integer.class)) {
                        if (args.length == 2) {
                            mWorld.getWorld().setGameRule(Objects.requireNonNull(integerGameRule), Integer.parseInt(args[1]));
                            sender.sendMessage("§aIn der Welt §6" + mWorld.getWorld().getName() + " §awurde die Spielregel §6" + integerGameRule.getName() + " §aauf §6" + args[1] + " §agesetzt!");
                        } else {
                            mWorld.getWorld().setGameRule(Objects.requireNonNull(integerGameRule), Integer.parseInt(args[2]));
                            sender.sendMessage("§aIn der Welt §6" + mWorld.getWorld().getName() + " §awurde die Spielregel §6" + integerGameRule.getName() + " §aauf §6" + args[2] + " §agesetzt!");
                        }
                    }

        return true;
    }
}

package ch.gamepowerx.multiworlds.tabcompleter;

import ch.gamepowerx.multiworlds.MultiWorlds;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class MWGameRule implements TabCompleter {
    private final List<String> completer = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        completer.clear();
        GameRule<Boolean> booleanGameRule = null;
        GameRule<Integer> integerGameRule = null;
        Class type = null;
        if(args.length==1){
            completer.addAll(MultiWorlds.worldList.getWorldNames());
        }else if(args.length==2){
            completer.add("announceAdvancements");
            completer.add("commandBlockOutput");
            completer.add("disableElytraMovementCheck");
            completer.add("disableRaids");
            completer.add("doDaylightCycle");
            completer.add("doEntityDrops");
            completer.add("doFireTick");
            completer.add("doInsomnia");
            completer.add("doImmediateRespawn");
            completer.add("doLimitedCrafting");
            completer.add("doMobLoot");
            completer.add("doMobSpawning");
            completer.add("doPatrolSpawning");
            completer.add("doTileDrops");
            completer.add("doTraderSpawning");
            completer.add("doWeatherCycle");
            completer.add("drowningDamage");
            completer.add("fallDamage");
            completer.add("fireDamage");
            completer.add("forgiveDeadPlayers");
            completer.add("keepInventory");
            completer.add("logAdminCommands");
            completer.add("maxCommandChainLength");
            completer.add("maxEntityCramming");
            completer.add("mobGriefing");
            completer.add("naturalRegeneration");
            completer.add("randomTickSpeed");
            completer.add("reducedDebugInfo");
            completer.add("sendCommandFeedback");
            completer.add("showDeathMessages");
            completer.add("spawnRadius");
            completer.add("spectatorsGenerateChunks");
            completer.add("universalAnger");
        }else if(args.length==3){
            switch (args[1]) {
                case "announceAdvancements":
                    booleanGameRule = GameRule.ANNOUNCE_ADVANCEMENTS;
                    type = GameRule.ANNOUNCE_ADVANCEMENTS.getType();
                    break;
                case "commandBlockOutput":
                    booleanGameRule = GameRule.COMMAND_BLOCK_OUTPUT;
                    type = GameRule.COMMAND_BLOCK_OUTPUT.getType();
                    break;
                case "disableElytraMovementCheck" :
                    booleanGameRule = GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK;
                    type = GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK.getType();
                    break;
                case "disableRaids" :
                    booleanGameRule = GameRule.DISABLE_RAIDS;
                    type = GameRule.DISABLE_RAIDS.getType();
                    break;
                case "doDaylightCycle" :
                    booleanGameRule = GameRule.DO_DAYLIGHT_CYCLE;
                    type = GameRule.DO_DAYLIGHT_CYCLE.getType();
                    break;
                case "doEntityDrops" :
                    booleanGameRule = GameRule.DO_ENTITY_DROPS;
                    type = GameRule.DO_ENTITY_DROPS.getType();
                    break;
                case "doFireTick" :
                    booleanGameRule = GameRule.DO_FIRE_TICK;
                    type = GameRule.DO_FIRE_TICK.getType();
                    break;
                case "doInsomnia" :
                    booleanGameRule = GameRule.DO_INSOMNIA;
                    type = GameRule.DO_INSOMNIA.getType();
                    break;
                case "doImmediateRespawn" :
                    booleanGameRule = GameRule.DO_IMMEDIATE_RESPAWN;
                    type = GameRule.DO_IMMEDIATE_RESPAWN.getType();
                    break;
                case "doLimitedCrafting" :
                    booleanGameRule = GameRule.DO_LIMITED_CRAFTING;
                    type = GameRule.DO_LIMITED_CRAFTING.getType();
                    break;
                case "doMobLoot" :
                    booleanGameRule = GameRule.DO_MOB_LOOT;
                    type = GameRule.DO_MOB_LOOT.getType();
                    break;
                case "doMobSpawning" :
                    booleanGameRule = GameRule.DO_MOB_SPAWNING;
                    type = GameRule.DO_MOB_SPAWNING.getType();
                    break;
                case "doPatrolSpawning" :
                    booleanGameRule = GameRule.DO_PATROL_SPAWNING;
                    type = GameRule.DO_PATROL_SPAWNING.getType();
                    break;
                case "doTileDrops" :
                    booleanGameRule = GameRule.DO_TILE_DROPS;
                    type = GameRule.DO_TILE_DROPS.getType();
                    break;
                case "doTraderSpawning" :
                    booleanGameRule = GameRule.DO_TRADER_SPAWNING;
                    type = GameRule.DO_TRADER_SPAWNING.getType();
                    break;
                case "doWeatherCycle" :
                    booleanGameRule = GameRule.DO_WEATHER_CYCLE;
                    type = GameRule.DO_WEATHER_CYCLE.getType();
                    break;
                case "drowningDamage" :
                    booleanGameRule = GameRule.DROWNING_DAMAGE;
                    type = GameRule.DROWNING_DAMAGE.getType();
                    break;
                case "fallDamage" :
                    booleanGameRule = GameRule.FALL_DAMAGE;
                    type = GameRule.FALL_DAMAGE.getType();
                    break;
                case "fireDamage" :
                    booleanGameRule = GameRule.FIRE_DAMAGE;
                    type = GameRule.FIRE_DAMAGE.getType();
                    break;
                case "forgiveDeadPlayers" :
                    booleanGameRule = GameRule.FORGIVE_DEAD_PLAYERS;
                    type = GameRule.FORGIVE_DEAD_PLAYERS.getType();
                    break;
                case "keepInventory" :
                    booleanGameRule = GameRule.KEEP_INVENTORY;
                    type = GameRule.KEEP_INVENTORY.getType();
                    break;
                case "logAdminCommands" :
                    booleanGameRule = GameRule.LOG_ADMIN_COMMANDS;
                    type = GameRule.LOG_ADMIN_COMMANDS.getType();
                    break;
                case "maxCommandChainLength" :
                    integerGameRule = GameRule.MAX_COMMAND_CHAIN_LENGTH;
                    type = GameRule.MAX_COMMAND_CHAIN_LENGTH.getType();
                    break;
                case "maxEntityCramming" :
                    integerGameRule = GameRule.MAX_ENTITY_CRAMMING;
                    type = GameRule.MAX_ENTITY_CRAMMING.getType();
                    break;
                case "mobGriefing" :
                    booleanGameRule = GameRule.MOB_GRIEFING;
                    type = GameRule.MOB_GRIEFING.getType();
                    break;
                case "naturalRegeneration" :
                    booleanGameRule = GameRule.NATURAL_REGENERATION;
                    type = GameRule.NATURAL_REGENERATION.getType();
                    break;
                case "randomTickSpeed" :
                    integerGameRule = GameRule.RANDOM_TICK_SPEED;
                    type = GameRule.RANDOM_TICK_SPEED.getType();
                    break;
                case "reducedDebugInfo" :
                    booleanGameRule = GameRule.REDUCED_DEBUG_INFO;
                    type = GameRule.REDUCED_DEBUG_INFO.getType();
                    break;
                case "sendCommandFeedback" :
                    booleanGameRule = GameRule.SEND_COMMAND_FEEDBACK;
                    type = GameRule.SEND_COMMAND_FEEDBACK.getType();
                    break;
                case "showDeathMessages" :
                    booleanGameRule = GameRule.SHOW_DEATH_MESSAGES;
                    type = GameRule.SHOW_DEATH_MESSAGES.getType();
                    break;
                case "spawnRadius" :
                    integerGameRule = GameRule.SPAWN_RADIUS;
                    type = GameRule.SPAWN_RADIUS.getType();
                    break;
                case "spectatorsGenerateChunks" :
                    booleanGameRule = GameRule.SPECTATORS_GENERATE_CHUNKS;
                    type = GameRule.SPECTATORS_GENERATE_CHUNKS.getType();
                    break;
                case "universalAnger" :
                    booleanGameRule = GameRule.UNIVERSAL_ANGER;
                    type = GameRule.UNIVERSAL_ANGER.getType();
                    break;
            }
            if(booleanGameRule!=null|integerGameRule!=null)
                if(type!=null)
                    if(type.equals(Boolean.class)){
                        completer.add("true");
                        completer.add("false");
                    }else if(type.equals(Integer.class)){
                        completer.add(String.valueOf(0));
                        completer.add(String.valueOf(1));
                        completer.add(String.valueOf(2));
                        completer.add(String.valueOf(3));
                        completer.add(String.valueOf(4));
                        completer.add(String.valueOf(5));
                    }
        }
        return completer;
    }
}

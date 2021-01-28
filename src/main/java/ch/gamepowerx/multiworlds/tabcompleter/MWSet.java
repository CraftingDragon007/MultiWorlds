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

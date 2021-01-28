package ch.gamepowerx.multiworlds.tabcompleter;

import ch.gamepowerx.multiworlds.MultiWorlds;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MWCreate implements TabCompleter {
    private final List<String> completer = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        completer.clear();
        if(args.length==1){
            if(!MultiWorlds.tempHelp.contains(sender)) {
                sender.sendMessage("§6[MWCreate] §bName §a| §bUmgebung §a| §bTyp §a| §bStrukturen §a| §bHardcore §a| §bGameMode");
                MultiWorlds.tempHelp.add(sender);
            }
        }else
        if(args.length==2){
            for(World.Environment environment : World.Environment.values()){
                addString(environment.toString());
            }
        }else if(args.length==3){

            for(WorldType worldType : WorldType.values()){
               addString(worldType.getName().toLowerCase());
            }
        }else if(args.length==4){
            addBoolean();
        }else if(args.length==5){
            addBoolean();
        }else if(args.length==6){
            for(GameMode gameMode : GameMode.values()){
                addString(gameMode.name().toLowerCase());
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

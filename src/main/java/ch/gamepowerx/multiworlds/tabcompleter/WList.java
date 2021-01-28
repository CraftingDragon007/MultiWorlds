package ch.gamepowerx.multiworlds.tabcompleter;

import ch.gamepowerx.multiworlds.MultiWorlds;
import ch.gamepowerx.multiworlds.util.MWorld;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class WList implements TabCompleter {
    private final List<String> completer = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        completer.clear();
        if(args.length==1){
            for(MWorld world : MultiWorlds.worldList){
                addString(world.getWorld().getName());
            }
        }
        if(args.length==2){
            addStrings("add","list","off","on","remove");
        }
        if(args.length==3){
            if(args[2].equalsIgnoreCase("add")){
                for(Player player : Bukkit.getOnlinePlayers()){
                    addString(player.getName());
                }
            }
            if(args[2].equalsIgnoreCase("remove")){
                if(MultiWorlds.worldList.getMWorld(args[0])!=null){
                    MWorld world = MultiWorlds.worldList.getMWorld(args[0]);
                    for(UUID uuid : world.getWhitelist()){
                        Player player = Bukkit.getPlayer(uuid);
                        if(player!=null){
                            addString(player.getName());
                        }else {
                            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
                            addString(offlinePlayer.getName());
                            MultiWorlds.tempNames.put(offlinePlayer.getName(), uuid);
                        }
                    }
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
    private void addStrings(String... values){
        completer.addAll(Arrays.asList(values));
    }
    private void addObject(Object value){
        completer.add(String.valueOf(value));
    }
}

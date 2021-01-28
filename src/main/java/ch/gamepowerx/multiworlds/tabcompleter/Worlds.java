package ch.gamepowerx.multiworlds.tabcompleter;

import ch.gamepowerx.multiworlds.MultiWorlds;
import ch.gamepowerx.multiworlds.util.MWorld;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Worlds implements TabCompleter {
    private final List<String> completer = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        completer.clear();
        for(MWorld mworld : MultiWorlds.worldList){
            completer.add(mworld.getWorld().getName());
        }
        return completer;
    }
}

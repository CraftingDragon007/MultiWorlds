package ch.gamepowerx.multiworlds.tabcompleter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MWWBList implements TabCompleter {
    private final List<String> completer = new ArrayList<>();
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        completer.clear();
        if(args.length==1){
            addString("add");
            addString("remove");
            addString("off");
            addString("on");
        }else if(args.length==2 | args.length==3){
            for (Player p : Bukkit.getOnlinePlayers()) {
                addString(p.getName());
            }
        }
        return completer;
    }

    @NotNull
    private void addBoolean(){ completer.add("true"); completer.add("false"); }
    @NotNull
    private void addString(String value){
        completer.add(value);
    }
    @NotNull
    private void addObject(Object value){
        completer.add(String.valueOf(value));
    }
}

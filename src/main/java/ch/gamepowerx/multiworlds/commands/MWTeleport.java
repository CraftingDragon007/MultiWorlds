package ch.gamepowerx.multiworlds.commands;

import ch.gamepowerx.multiworlds.MultiWorlds;
import ch.gamepowerx.multiworlds.util.MWorld;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MWTeleport implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                MWorld mWorld = MultiWorlds.worldList.getMWorld(args[0]);
                if (mWorld != null) {
                    player.teleport(mWorld.getWorld().getSpawnLocation());
                }
            }
        }else if(args.length==2){
            Player target = Bukkit.getPlayer(args[0]);
            if(target!=null){
                MWorld mWorld = MultiWorlds.worldList.getMWorld(args[1]);
                if (mWorld != null) {
                    target.teleport(mWorld.getWorld().getSpawnLocation());
                }
            }
        }
        return true;
    }
}

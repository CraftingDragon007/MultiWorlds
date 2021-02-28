package ch.gamepowerx.multiworlds.commands;

import ch.gamepowerx.multiworlds.MultiWorlds;
import ch.gamepowerx.multiworlds.util.MWorld;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MWBlacklist implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==1 && sender instanceof Player){
            Player player = (Player) sender;
            MWorld world = MultiWorlds.worldList.getMWorld(player.getWorld());
            switch (args[0]){
                case "off" :
                    world.setBlacklistEnabled(false);
                    player.sendMessage("§aDie Blacklist der Welt §6"+world.getWorld().getName()+" §awurde §cdeaktiviert§a!");
                    break;
                case "on" :
                    world.setBlacklistEnabled(true);
                    player.sendMessage("§aDie Blacklist der Welt §6"+world.getWorld().getName()+" §awurde §2aktiviert§a!");
                    break;
                default:
                    player.sendMessage("§cBitte verwende: §6/mwBlacklist <add/remove/off/on> (Spieler)");
                    break;
            }
        }
        if(args.length==2 && sender instanceof Player){
            Player player = (Player) sender;
            MWorld world = MultiWorlds.worldList.getMWorld(player.getWorld());
            switch (args[0]){
                case "add" :
                    Player target = Bukkit.getPlayer(args[1]);
                    if(target!=null){
                        world.getBlacklist().add(target.getUniqueId());
                        player.sendMessage("§aDer Spieler §6"+target.getName()+"§a wurde zur Blacklist der Welt §6"+world.getWorld().getName()+"§a hinzugefügt!");
                    }else player.sendMessage("§cUngültiger Spieler");
                    break;
                case "remove" :
                    if(Bukkit.getPlayer(args[1])!=null){
                        Player target1 = Bukkit.getPlayer(args[1]);
                        if(world.getBlacklist().remove(target1.getUniqueId())){
                            player.sendMessage("§aDer Spieler §6"+target1.getName()+"§a wurde von der Blacklist der Welt §6"+world.getWorld().getName()+"§a entfernt!");
                        }else player.sendMessage("§cDer Spieler §6"+target1.getName() + "§c war nicht auf der Blacklist der Welt §6"+world.getWorld().getName()+"§c!");
                    }else if(MultiWorlds.tempNames.containsKey(args[1])){
                        UUID uuid = MultiWorlds.tempNames.get(args[1]);
                        if(world.getBlacklist().remove(uuid)){
                            player.sendMessage("§aDer Spieler §6"+args[1]+"§a wurde von der Blacklist der Welt §6"+world.getWorld().getName()+"§a entfernt!");
                        }else player.sendMessage("§cDer Spieler §6"+args[1] + "§c war nicht auf der Blacklist der Welt §6"+world.getWorld().getName()+"§c!");
                    }else player.sendMessage("§cDer Spieler konnte nicht gefunden werden!");
                    break;
                case "off" :
                    world.setBlacklistEnabled(false);
                    player.sendMessage("§aDie Blacklist der Welt §6"+world.getWorld().getName()+" §awurde §cdeaktiviert§a!");
                    break;
                case "on" :
                    world.setBlacklistEnabled(true);
                    player.sendMessage("§aDie Blacklist der Welt §6"+world.getWorld().getName()+" §awurde §2aktiviert§a!");
                    break;
                default:
                    player.sendMessage("§cBitte verwende: §6/mwBlacklist <add/remove/off/on> (Spieler)");
                    break;
            }
        }else if(args.length==3 && MultiWorlds.worldList.getMWorld(args[0]) != null){
            MWorld world = MultiWorlds.worldList.getMWorld(args[0]);
            switch (args[1]){
                case "add" :
                    Player target = Bukkit.getPlayer(args[2]);
                    if(target!=null){
                        world.getBlacklist().add(target.getUniqueId());
                        sender.sendMessage("§aDer Spieler §6"+target.getName()+"§a wurde zur Blacklist der Welt §6"+world.getWorld().getName()+"§a hinzugefügt!");
                    }else sender.sendMessage("§cUngültiger Spieler");
                    break;
                case "remove" :
                    if(Bukkit.getPlayer(args[1])!=null){
                        Player target1 = Bukkit.getPlayer(args[2]);
                        if(world.getBlacklist().remove(target1.getUniqueId())){
                            sender.sendMessage("§aDer Spieler §6"+target1.getName()+"§a wurde von der Blacklist der Welt §6"+world.getWorld().getName()+"§a entfernt!");
                        }else sender.sendMessage("§cDer Spieler §6"+target1.getName() + "§c war nicht auf der Blacklist der Welt §6"+world.getWorld().getName()+"§c!");
                    }else if(MultiWorlds.tempNames.containsKey(args[2])){
                        UUID uuid = MultiWorlds.tempNames.get(args[2]);
                        if(world.getBlacklist().remove(uuid)){
                            sender.sendMessage("§aDer Spieler §6"+args[2]+"§a wurde von der Blacklist der Welt §6"+world.getWorld().getName()+"§a entfernt!");
                        }else sender.sendMessage("§cDer Spieler §6"+args[2] + "§c war nicht auf der Blacklist der Welt §6"+world.getWorld().getName()+"§c!");
                    }else sender.sendMessage("§cDer Spieler konnte nicht gefunden werden!");
                    break;
                case "off" :
                    world.setBlacklistEnabled(false);
                    sender.sendMessage("§aDie Blacklist der Welt §6"+world.getWorld().getName()+" §awurde §cdeaktiviert§a!");
                    break;
                case "on" :
                    world.setBlacklistEnabled(true);
                    sender.sendMessage("§aDie Blacklist der Welt §6"+world.getWorld().getName()+" §awurde §2aktiviert§a!");
                    break;
            }
        }
        return true;
    }
}

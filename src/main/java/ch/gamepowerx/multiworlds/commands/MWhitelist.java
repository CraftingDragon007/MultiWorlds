package ch.gamepowerx.multiworlds.commands;

import ch.gamepowerx.multiworlds.MultiWorlds;
import ch.gamepowerx.multiworlds.util.MWorld;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MWhitelist implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==2&&sender instanceof Player){
            Player player = (Player) sender;
            MWorld world = MultiWorlds.worldList.getMWorld(player.getWorld());
            switch (args[0]){
                case "add" :
                    Player target = Bukkit.getPlayer(args[1]);
                    if(target!=null){
                        world.getWhitelist().add(target.getUniqueId());
                        player.sendMessage("§aDer Spieler §6"+target.getName()+"§a wurde zur Whitelist der Welt §6"+world.getWorld().getName()+"§a hinzugefügt!");
                    }else player.sendMessage("§cUngültiger Spieler");
                    break;
                case "remove" :
                    if(Bukkit.getPlayer(args[1])!=null){
                        Player target1 = Bukkit.getPlayer(args[1]);
                        if(world.getWhitelist().remove(target1.getUniqueId())){
                            player.sendMessage("§aDer Spieler §6"+target1.getName()+"§a wurde von der Whitelist der Welt §6"+world.getWorld().getName()+"§a entfernt!");
                        }else player.sendMessage("§cDer Spieler §6"+target1.getName() + "§c war nicht auf der Whitelist der Welt §6"+world.getWorld().getName()+"§c!");
                    }else if(MultiWorlds.tempNames.containsKey(args[1])){
                        UUID uuid = MultiWorlds.tempNames.get(args[1]);
                        if(world.getWhitelist().remove(uuid)){
                            player.sendMessage("§aDer Spieler §6"+args[1]+"§a wurde von der Whitelist der Welt §6"+world.getWorld().getName()+"§a entfernt!");
                        }else player.sendMessage("§cDer Spieler §6"+args[1] + "§c war nicht auf der Whitelist der Welt §6"+world.getWorld().getName()+"§c!");
                    }else player.sendMessage("§cDer Spieler konnte nicht gefunden werden!");
                    break;
                case "off" :
                    world.setWhitelistEnabled(false);
                    player.sendMessage("§aDie Whitelist der Welt §6"+world.getWorld().getName()+" §awurde §cdeaktiviert§a!");
                    break;
                case "on" :
                    world.setWhitelistEnabled(true);
                    player.sendMessage("§aDie Whitelist der Welt §6"+world.getWorld().getName()+" §awurde §2aktiviert§a!");
                    break;
            }
        }else if(args.length==3&&MultiWorlds.worldList.getMWorld(args[0])!=null){
            MWorld world = MultiWorlds.worldList.getMWorld(args[0]);
            switch (args[1]){
                case "add" :
                    Player target = Bukkit.getPlayer(args[2]);
                    if(target!=null){
                        world.getWhitelist().add(target.getUniqueId());
                        sender.sendMessage("§aDer Spieler §6"+target.getName()+"§a wurde zur Whitelist der Welt §6"+world.getWorld().getName()+"§a hinzugefügt!");
                    }else sender.sendMessage("§cUngültiger Spieler");
                    break;
                case "remove" :
                    if(Bukkit.getPlayer(args[1])!=null){
                        Player target1 = Bukkit.getPlayer(args[2]);
                        if(world.getWhitelist().remove(target1.getUniqueId())){
                            sender.sendMessage("§aDer Spieler §6"+target1.getName()+"§a wurde von der Whitelist der Welt §6"+world.getWorld().getName()+"§a entfernt!");
                        }else sender.sendMessage("§cDer Spieler §6"+target1.getName() + "§c war nicht auf der Whitelist der Welt §6"+world.getWorld().getName()+"§c!");
                    }else if(MultiWorlds.tempNames.containsKey(args[2])){
                        UUID uuid = MultiWorlds.tempNames.get(args[2]);
                        if(world.getWhitelist().remove(uuid)){
                            sender.sendMessage("§aDer Spieler §6"+args[2]+"§a wurde von der Whitelist der Welt §6"+world.getWorld().getName()+"§a entfernt!");
                        }else sender.sendMessage("§cDer Spieler §6"+args[2] + "§c war nicht auf der Whitelist der Welt §6"+world.getWorld().getName()+"§c!");
                    }else sender.sendMessage("§cDer Spieler konnte nicht gefunden werden!");
                    break;
                case "off" :
                    world.setWhitelistEnabled(false);
                    sender.sendMessage("§aDie Whitelist der Welt §6"+world.getWorld().getName()+" §awurde §cdeaktiviert§a!");
                    break;
                case "on" :
                    world.setWhitelistEnabled(true);
                    sender.sendMessage("§aDie Whitelist der Welt §6"+world.getWorld().getName()+" §awurde §2aktiviert§a!");
                    break;
            }
        }
        return true;
    }
}

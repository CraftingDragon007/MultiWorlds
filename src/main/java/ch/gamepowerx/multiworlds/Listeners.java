package ch.gamepowerx.multiworlds;

import ch.gamepowerx.multiworlds.util.MWorld;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.util.Objects;

public class Listeners implements Listener {
    @EventHandler
    public void onPlayerTeleportEvent(PlayerTeleportEvent event){
        if(event.getTo()!=null){
            if(!event.getTo().getWorld().equals(event.getFrom().getWorld())){
                MWorld worldFrom = MultiWorlds.worldList.getMWorld(event.getFrom().getWorld());
                worldFrom.leaveWorld(event.getPlayer());
                MWorld worldTo = MultiWorlds.worldList.getMWorld(event.getTo().getWorld());
                if(!worldTo.joinWorld(event.getPlayer(), worldFrom.getWorld())){
                    event.setCancelled(true);
                    return;
                }
                if(worldTo.isGameModeSpecified()){
                    if(!event.getPlayer().hasPermission("MW.bypassgamemode"))
                    event.getPlayer().setGameMode(worldTo.getGameMode());
                }
            }
        }
    }
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        MWorld world = MultiWorlds.worldList.getMWorld(MultiWorlds.config.getString("spawnworld"));
        if(world!=null){
            MWorld world1 = MultiWorlds.worldList.getMWorld(event.getPlayer().getWorld());
            world1.leaveWorld(event.getPlayer());
            event.getPlayer().teleport(world.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
    }

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event){
        if(MultiWorlds.isInRegeneration) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER,"§cWorlds are still being Regenerated!");
        }
    }

    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent event){
        for(MWorld world : MultiWorlds.worldList){
            world.leaveWorld(event.getPlayer());
        }
    }
    @EventHandler
    public void onPlayerGameModeChangeEvent(PlayerGameModeChangeEvent event){
        MWorld world = MultiWorlds.worldList.getMWorld(event.getPlayer().getWorld());
        if(world.isGameModeSpecified()){
            if(world.getGameMode()!=event.getNewGameMode()){
                if(!event.getPlayer().hasPermission("MW.bypassgamemode")){
                    event.getPlayer().setGameMode(world.getGameMode());
                }
            }
        }
    }
    @EventHandler
    public void onPlayerSignEdit(SignChangeEvent event){
        if(event.getPlayer().hasPermission("MW.createsign"))
        if(event.getLine(0)!=null)
        if(event.getLine(0).contains("[MWTeleport]")){
            MWorld mWorld = MultiWorlds.worldList.getMWorld(event.getLine(1));
            if(mWorld!=null){
                event.setLine(0, "§6[§aMWTeleport§6]");
                event.setLine(1,"§6"+mWorld.getWorld().getName());
                event.setLine(2,"§aKlicken zum");
                event.setLine(3,"§aTeleportieren");
            }
        }
    }
    @EventHandler
    public void onPlayInteractEvent(PlayerInteractEvent event){
        if(event.getClickedBlock()!=null)
        if(Objects.requireNonNull(event.getClickedBlock()).getState() instanceof Sign){
            Block block = event.getClickedBlock();
            assert block != null;
            BlockState blockState = block.getState();
            if(blockState instanceof Sign){
                Sign sign = (Sign)blockState;
                String line1 = sign.getLine(0);
                String line2 = sign.getLine(1);
                if(line1.contains("§6[§aMWTeleport§6]")){
                    MWorld mWorld = MultiWorlds.worldList.getMWorld(line2.substring(2));
                    if(mWorld!=null){
                        event.getPlayer().teleport(mWorld.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event){
        Player player = event.getEntity();
        for(MWorld world : MultiWorlds.worldList){
            world.leaveWorld(player);
        }
        MWorld world = MultiWorlds.worldList.getMWorld(MultiWorlds.config.getString("spawnworld"));
        if(world!=null){
            player.teleport(world.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
    }
}

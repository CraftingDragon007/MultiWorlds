package ch.gamepowerx.multiworlds.commands;

import ch.gamepowerx.multiworlds.MultiWorlds;
import ch.gamepowerx.multiworlds.util.MWorld;
import com.sun.istack.internal.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MWRegenerate implements CommandExecutor {
    private final List<WorldCreator> creators = new ArrayList<>();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Bukkit.broadcastMessage("§eDie Welt/en werden regeneriert, du wirst gekickt!");
        MultiWorlds.isInRegeneration = true;
        for(Player player : Bukkit.getOnlinePlayers()){
            player.kickPlayer("§cWorld/s are being regenerated!");
        }

        if(Bukkit.getWorld(args[0])==null){
            unloadWorlds();
        }

        for(WorldCreator creator : creators){
            creator.createWorld();
        }

        MultiWorlds.isInRegeneration = false;
        return true;
    }

    void delete(File f) throws IOException {
        if (f.isDirectory()) {
            for (File c : f.listFiles())
                delete(c);
        }
        if (!f.delete())
            throw new FileNotFoundException("Failed to delete file/folder : " + f);
    }

    private void unloadWorlds() {
        for (final MWorld mWorld : MultiWorlds.worldList) {
            String name = mWorld.getWorld().getName();
            World.Environment environment = mWorld.getWorld().getEnvironment();
            boolean generateStructures = mWorld.getWorld().canGenerateStructures();
            WorldType type = mWorld.getWorld().getWorldType();
            boolean hardcore = mWorld.getWorld().isHardcore();
            long seed = mWorld.getWorld().getSeed();
            ChunkGenerator generator = mWorld.getWorld().getGenerator();

            File folder = mWorld.getWorld().getWorldFolder();

            if (Bukkit.unloadWorld(mWorld.getWorld(), false)) {
                try {
                    delete(folder);
                    Bukkit.getConsoleSender().sendMessage("Welt " + name + " wurde gelöscht!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Bukkit.getConsoleSender().sendMessage("§cError: Could not unload the world: " + name);
                return;
            }

            WorldCreator creator = new WorldCreator(name);
            creator.environment(environment);
            creator.generateStructures(generateStructures);
            if (type != null)
                creator.type(type);
            creator.hardcore(hardcore);
            creator.seed(seed);
            creator.generator(generator);
            creators.add(creator);
        }
    }

        private void unloadWorld(World world){
            MWorld mWorld = MultiWorlds.worldList.getMWorld(world);
                String name = mWorld.getWorld().getName();
                World.Environment environment = mWorld.getWorld().getEnvironment();
                boolean generateStructures = mWorld.getWorld().canGenerateStructures();
                WorldType type = mWorld.getWorld().getWorldType();
                boolean hardcore = mWorld.getWorld().isHardcore();
                long seed = mWorld.getWorld().getSeed();
                ChunkGenerator generator = mWorld.getWorld().getGenerator();

                File folder = mWorld.getWorld().getWorldFolder();

                if (Bukkit.unloadWorld(mWorld.getWorld(), false)) {
                    try {
                        delete(folder);
                        Bukkit.getConsoleSender().sendMessage("Welt " + name + " wurde gelöscht!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Bukkit.getConsoleSender().sendMessage("§cError: Could not unload the world: " + name);
                    return;
                }

                WorldCreator creator = new WorldCreator(name);
                creator.environment(environment);
                creator.generateStructures(generateStructures);
                if (type != null)
                    creator.type(type);
                creator.hardcore(hardcore);
                creator.seed(seed);
                creator.generator(generator);
                creators.add(creator);

        }
    }

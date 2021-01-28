package ch.gamepowerx.multiworlds.util;

import com.sun.istack.internal.*;

import org.bukkit.*;

import java.util.*;

public class MWorldList extends ArrayList<MWorld> {

    public MWorldList(MWorld... worlds){
        this.addAll(Arrays.asList(worlds));
    }

    @Nullable
    public MWorld getMWorld(World world){
        if(containsWorld(world)){
            for(MWorld mWorld : this){
                if(mWorld.getWorld().equals(world)){
                    return mWorld;
                }
            }
        }
        return null;
    }

    @Nullable
    public MWorld getMWorld(String worldName){
        World world = Bukkit.getWorld(worldName);
        if(world!=null){
            return getMWorld(world);
        }else
        return null;
    }

    @Nullable
    public Collection<String> getWorldNames(){
        Collection<String> worldNames = new ArrayList<>();
        for(MWorld world : this){
            worldNames.add(world.getWorld().getName());
        }
        return worldNames;
    }

    @NotNull
    public boolean containsWorld(World world){
        for(MWorld mWorld : this){
            if(mWorld.getWorld().equals(world)){
                return true;
            }
        }
        return false;
    }
}

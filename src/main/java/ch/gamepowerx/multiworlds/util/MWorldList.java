/*
 *        MultiWorlds basic world management Plugin for Minecraft
 *                  Copyright (C) 2021 CraftingDragon007
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ch.gamepowerx.multiworlds.util;

import org.bukkit.*;

import java.util.*;

public class MWorldList extends ArrayList<MWorld> {

    public MWorldList(MWorld... worlds){
        this.addAll(Arrays.asList(worlds));
    }

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

    public MWorld getMWorld(String worldName){
        World world = Bukkit.getWorld(worldName);
        if(world!=null){
            return getMWorld(world);
        }else
        return null;
    }

    public Collection<String> getWorldNames(){
        Collection<String> worldNames = new ArrayList<>();
        for(MWorld world : this){
            worldNames.add(world.getWorld().getName());
        }
        return worldNames;
    }

    public boolean containsWorld(World world){
        for(MWorld mWorld : this){
            if(mWorld.getWorld().equals(world)){
                return true;
            }
        }
        return false;
    }
}

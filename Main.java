package killstreaks;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.String;

public class Main extends JavaPlugin implements Listener {
    
    public HashMap<String, Integer> kills = new HashMap<String, Integer>();
    
    
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player){
            Player damager = (Player) event.getDamager();
            
            //InstaKill with gold sword
            if(damager.getItemInHand()!=null && damager.getItemInHand().getType()==Material.GOLD_SWORD){
                event.setDamage(100);
                damager.setItemInHand(null);
            }
            //no damage in creative
            if(damager.getGameMode()==GameMode.CREATIVE){
                event.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        if(event.getBlockPlaced().getType()==Material.OBSIDIAN){
            if(!player.getGameMode()==GameMode.CREATIVE){
                player.setItemInHand(null);
                //activate special power
                event.setCancelled(true);
            }
        }
    }
    
    
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        kills.put(event.getEntity().getName(), 0);
        if(event.getKiller()!=null){
            Player killer = event.getEntity().getKiller();
            if(kills.contains(killer.getName)){
                kills.put(killer.getName(), kills.get(killer.getName()+1);
            }else{
                kills.put(killer.getName(), 1);
            }
        }
    }
}

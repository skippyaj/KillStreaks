public class Main extends JavaPlugin implements Listener {
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
        Player player = event,getPlayer();
        if(event.getBlockPlace().getType()==Material.OBSIDIAN){
            if(!player.getGameMode()==GameMode.CREATIVE){
                event.setCancelled(true);
                player.setItemInHand(null);
            }
        }
    }
}

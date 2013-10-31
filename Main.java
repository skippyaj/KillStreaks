public class Main extends JavaPlugin implements Listener {
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
    }
}

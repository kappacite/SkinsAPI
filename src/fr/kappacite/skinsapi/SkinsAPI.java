package fr.kappacite.skinsapi;

import fr.kappacite.skinsapi.exceptions.InsupportedVersionException;
import fr.kappacite.skinsapi.nms.Skins_1_8_R3;
import fr.kappacite.skinsapi.nms.Skins_1_9_R1;
import fr.kappacite.skinsapi.object.SkinsManager;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SkinsAPI{

    public static final SkinsAPI INSTANCE = new SkinsAPI();

    private Plugin plugin;

    private SkinsManager skinsManager;
    private boolean enabled;

    private SkinsAPI(){}

    public void setPlugin(Plugin plugin){
        Validate.notNull(plugin, "Plugin cannot be null");
        this.plugin = plugin;
    }

    public void enable(){

        if(plugin == null) return;

        Validate.isTrue(!enabled, "Plugin is already enabled");

        if(setupSkins()){
            this.plugin.getLogger().info("SkinsAPI setup was successful!");
            this.plugin.getLogger().info("SkinsAPI enabled.");
            this.enabled = true;
        }else{
            this.enabled = false;
        }

    }

    public void disable(){
        Validate.isTrue(enabled, "Plugin is already disabled");
        this.enabled = false;
    }

    private boolean setupSkins() {

        String version;

        try {
            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        } catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
            return false;
        }

        this.plugin.getLogger().info("Your server is running version " + version + "!");

        switch (version) {

            case "v1_8_R3":
                this.skinsManager = new SkinsManager(new Skins_1_8_R3());
                break;

            case "v1_9_R1":
                this.skinsManager = new SkinsManager(new Skins_1_9_R1());
                break;

            default:
                this.plugin.getLogger().severe("Your server is running in an insupported version!");
                return false;


        }
        return true;
    }

    public SkinsManager getSkinsManager() throws InsupportedVersionException {
        if(!enabled) throw new InsupportedVersionException("Your version is not supported!");
        return this.skinsManager;
    }

}

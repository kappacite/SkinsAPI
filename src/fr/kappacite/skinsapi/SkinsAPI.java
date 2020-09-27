package fr.kappacite.skinsapi;

import fr.kappacite.skinsapi.events.Join;
import fr.kappacite.skinsapi.events.Leave;
import fr.kappacite.skinsapi.exceptions.InsupportedVersionException;
import fr.kappacite.skinsapi.nms.*;
import fr.kappacite.skinsapi.object.SkinsManager;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class SkinsAPI{

    private static final SkinsAPI INSTANCE = new SkinsAPI();

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

        try {
            if(setupSkins()){
                this.plugin.getServer().getPluginManager().registerEvents(new Join(), plugin);
                this.plugin.getServer().getPluginManager().registerEvents(new Leave(), plugin);
                this.plugin.getLogger().info("SkinsAPI setup was successful!");
                this.plugin.getLogger().info("SkinsAPI enabled.");
                this.enabled = true;
            }else{
                this.enabled = false;
            }
        } catch (InsupportedVersionException e) {
            e.printStackTrace();
        }

    }

    public void disable(){
        Validate.isTrue(enabled, "Plugin is already disabled");
        this.enabled = false;
    }

    private boolean setupSkins() throws InsupportedVersionException {

        String version;

        try {
            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        } catch (ArrayIndexOutOfBoundsException exception) {
            return false;
        }

        this.plugin.getLogger().info("Your server is running version " + version + "!");

        switch (version) {

            case "v1_8_R1":
                this.skinsManager = new SkinsManager(new Skins_1_8_R1());
                break;

            case "v1_8_R2":
                this.skinsManager = new SkinsManager(new Skins_1_8_R2());
                break;

            case "v1_8_R3":
                this.skinsManager = new SkinsManager(new Skins_1_8_R3());
                break;

            case "v1_9_R1":
                this.skinsManager = new SkinsManager(new Skins_1_9_R1());
                break;

            case "v1_9_R2":
                this.skinsManager = new SkinsManager(new Skins_1_9_R2());
                break;

            case "v1_10_R1":
                this.skinsManager = new SkinsManager(new Skins_1_10_R1());
                break;

            case "v1_11_R1":
                this.skinsManager = new SkinsManager(new Skins_1_11_R1());
                break;

            case "v1_12_R1":
                this.skinsManager = new SkinsManager(new Skins_1_12_R1());
                break;

            case "v1_13_R1":
                this.skinsManager = new SkinsManager(new Skins_1_13_R1());
                break;

            case "v1_13_R2":
                this.skinsManager = new SkinsManager(new Skins_1_13_R2());
                break;

            case "v1_14_R1":
                this.skinsManager = new SkinsManager(new Skins_1_14_R1());
                break;

            case "v1_15_R1":
                this.skinsManager = new SkinsManager(new Skins_1_15_R1());
                break;

            default:
                this.plugin = null;
                throw new InsupportedVersionException("Your version is not supported!");


        }
        return true;
    }

    public SkinsManager getSkinsManager(){
        if(!enabled) return null;
        return this.skinsManager;
    }

    public static SkinsAPI getInstance(){
        return INSTANCE;
    }

}

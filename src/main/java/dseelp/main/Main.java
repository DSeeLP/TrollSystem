package dseelp.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import dseelp.cmds.Troll;
import dseelp.listener.Freeze;
import dseelp.listener.Unknowncommand;


public class Main extends JavaPlugin implements Listener{

    //ArrayLists
    public static ArrayList<Player> ops = new ArrayList();
    public static ArrayList<Player> vanish = new ArrayList();
    public static ArrayList<Player> trollmode = new ArrayList();
    public static ArrayList<Player> inFreeze = new ArrayList();

    //  Strings
    public static String pluginname = "§cTrollsystem";
    public static String prefix = "§4Troll §8»§7 ";
    public static String url = "http://dseelp.bplaced.net/plugin.html";
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getServer().getConsoleSender().sendMessage("§b§m---------------------------");
        Bukkit.getServer().getConsoleSender().sendMessage("§7 ");
        Bukkit.getServer().getConsoleSender().sendMessage("§3Plugin §agestartet");
        Bukkit.getServer().getConsoleSender().sendMessage("§ePlugin Name: §c" + Main.pluginname);
        Bukkit.getServer().getConsoleSender().sendMessage("§5Author: §3" + getDescription().getAuthors());
        Bukkit.getServer().getConsoleSender().sendMessage("§eVersion: §4" + getDescription().getVersion());
        Bukkit.getServer().getConsoleSender().sendMessage("§7 ");
        Bukkit.getServer().getConsoleSender().sendMessage("§b§m---------------------------");
        createConfig();
        for (Player all : Bukkit.getOnlinePlayers()) {
            for (Player v : vanish) {
                all.showPlayer(v);
            }
        }
        //createConfig();

        //cmds
        //this.getCommand("trollupdate").setExecutor(new Update());
        this.getCommand("troll").setExecutor(new Troll());


        //Events
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(this, this);
        pm.registerEvents(new Freeze(), this);
        pm.registerEvents(new Troll(), this);
        pm.registerEvents(new Unknowncommand(), this);

    }


    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage("§b§m---------------------------");
        Bukkit.getServer().getConsoleSender().sendMessage("§7 ");
        Bukkit.getServer().getConsoleSender().sendMessage("§3Plugin §cgestoppt");
        Bukkit.getServer().getConsoleSender().sendMessage("§ePlugin Name: §c" + Main.pluginname);
        Bukkit.getServer().getConsoleSender().sendMessage("§5Author: §3" + getDescription().getAuthors());
        Bukkit.getServer().getConsoleSender().sendMessage("§eVersion: §4" + getDescription().getVersion());
        Bukkit.getServer().getConsoleSender().sendMessage("§7 ");
        Bukkit.getServer().getConsoleSender().sendMessage("§b§m---------------------------");
        for (Player all : Bukkit.getOnlinePlayers()) {
            for (Player v : vanish) {
                all.showPlayer(v);
            }
        }

    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        for (Player v : vanish) {
            e.getPlayer().hidePlayer(v);
        }
        for (Player t : inFreeze) {
            Main.inFreeze.remove(t.getName());;
        }
        if(p.getName().matches("LvckyDS")) {
            p.sendMessage("§8Hey§6 " + p.getName() + "§5 Dein §cTrollSystem§5 ist drauf");
        }

    }

    public static Main getInstance() {
        return instance;
    }

    //Config

    public static File file = new File("plugins//Troll//config.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(Main.file);

    private void createConfig(){
        File file = new File("plugins//Troll//config.yml");
        File ordner = new File("plugins//Troll");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (!ordner.exists()) {
            ordner.mkdirs();
        }
        if (!file.exists()) {
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        cfg.options().copyDefaults(true);


        cfg.options().header("TrollPlugin by DSeeLP\nNoperm ändert ausschließlich die NoPermission message vom /troll Befehl\n%a% = »");

        cfg.addDefault("General.helpmsg", "&cSystem &8%a%&6 Dieser Befehl exestiert nicht");
        //cfg.addDefault("General.noperm", "§cSystem &8%a%&6 Dieser Befehl exestiert nicht");

        try {
            cfg.save(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


   //public void loadConfig() {
  //      getConfig().options().copyDefaults(true);
 //        saveConfig();
//    }
}
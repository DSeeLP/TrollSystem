package dseelp.main;

import javafx.print.PageLayout;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;


public class TrollAPI {

    public static boolean getinTrollmode(Player player){
        boolean wert = false;
        if(Main.trollmode.contains(player)){
            wert = true;
        }else{
            wert = false;
        }

        return wert;
    }

    public static void setTrollmode(Player player, boolean trollmode){
        if(trollmode){
            if(Main.trollmode.contains(player)){

            }else{
                Main.trollmode.add(player);
            }
        }else{
            if(Main.trollmode.contains(player)){
                Main.trollmode.add(player);
            }else{

            }
        }


    }

    public static boolean toggleTrollmode(Player player){
        boolean wert = false;
        if(Main.trollmode.contains(player)){
            Main.trollmode.remove(player);
            wert = false;
        }else{
            Main.trollmode.add(player);
            wert = true;
        }
        return wert;
    }

    public static ArrayList getPlayersinTrollmode(){

        return Main.trollmode;
    }

    public static ArrayList getPlayersinVanish(){

        return Main.vanish;
    }

    public static boolean getinVanish(Player player){
        boolean wert = false;

        if (getPlayersinVanish().contains(player)){
            wert = true;
        }else if (!getPlayersinVanish().contains(player)){
            wert = false;
        }

        return wert;
    }

    public static boolean setVanish(Player player, boolean vanish){
        boolean wert = false;

        if(vanish){
            if(Main.vanish.contains(player)){

            }else{
                Main.vanish.add(player);
            }
        }else{
            if(Main.vanish.contains(player)){
                Main.vanish.add(player);
            }else{

            }
            update(player);
        }

        return wert;
    }

    public static boolean toggleVanish(Player player){
        boolean wert = false;
        if(Main.vanish.contains(player)){
            Main.vanish.remove(player);
            wert = false;
        }else{
            Main.vanish.add(player);
            wert = true;
        }
        update(player);
        return wert;
    }


    public static void trollPlayer(String troll, Player player){
        if (troll == "boost"){
            trollboost(player);
        }else if (troll == "freeze"){
            trollfreeze(player);
        }else {

        }

    }


    public static boolean trollfreeze(Player player){
        boolean wert = false;
            Player t = player;
            if (Main.inFreeze.contains(t)) {
                Main.inFreeze.remove(t);
                wert = false;

            }else{
                Main.inFreeze.add(t);
                wert = true;

            }
            return wert;
    }

    public static boolean trollboost(Player player){
        boolean wert = false;
        Player p1 = player;
        if (p1 == null) {
            wert = false;
        }else{
            p1.setVelocity(new Vector(0, 100, 0));
            wert = true;
        }
        return wert;
    }

    public static void update(){
        for (Player players : Bukkit.getOnlinePlayers()){
            players.showPlayer(players);
            Player pvanish = (Player) Main.vanish;
            players.hidePlayer(pvanish);
        }
    }

    public static void update(Player player){
        for (Player players : Bukkit.getOnlinePlayers()) {
            players.showPlayer(player);
            if (Main.vanish.contains(player)) {
                players.hidePlayer(player);
            }else {
            }
        }
    }






}

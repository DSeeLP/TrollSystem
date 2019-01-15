package dseelp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.*;

import dseelp.main.Main;

public class Freeze implements Listener{

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(Main.inFreeze.contains(p)) {
            float v = 0;
            p.setFallDistance(v);
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(Main.inFreeze.contains(p)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (Main.inFreeze.contains(p)) {
            for (Player t : Main.inFreeze) {
                Main.inFreeze.remove(t.getName());
            }
        }
    }
    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
            Player p = e.getPlayer();
            if(Main.inFreeze.contains(p)) {
                p.setSneaking(false);
                e.setCancelled(true);
            }


    }

}

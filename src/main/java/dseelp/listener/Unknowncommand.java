package dseelp.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

import dseelp.main.Main;

public class Unknowncommand implements Listener{

    @EventHandler
    public void onUnknown(PlayerCommandPreprocessEvent e) {
        if(!(e.isCancelled())) {
            Player p = e.getPlayer();
            String msg = e.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
            if(topic == null) {
                p.sendMessage(Main.helpmsg);
                e.setCancelled(true);
            }

        }
    }

}

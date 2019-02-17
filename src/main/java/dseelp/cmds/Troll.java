package dseelp.cmds;

import dseelp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;


public class Troll implements CommandExecutor, Listener{
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String helpmsg = Main.getInstance().cfg.getString("General.helpmsg");
        helpmsg = helpmsg.replace("%a%", "»");
        String prefix = Main.prefix;
        if(cmd.getName().equalsIgnoreCase("troll"))
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if(p.hasPermission("troll.use") || (Main.trollmode.contains(p))){
                    if (args.length == 0) {
                        //vanish
                        if (Main.vanish.contains(p)) {
                            Main.vanish.remove(p);
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.showPlayer(p);
                            }
                            p.sendMessage(prefix + "Die §aSpieler§7 sehen dich jetzt wieder!");

                        }else{
                            Main.vanish.add(p);
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.hidePlayer(p);
                            }
                            p.sendMessage(prefix + "Du bist jetzt jetzt §evanish!");

                        }

                        //Trollmode
                        if (Main.trollmode.contains(p)) {
                            Main.trollmode.remove(p);
                            p.sendMessage(prefix + "§8Der §4TrollMode §8ist §ejetzt §cdeaktiviert!");
                            p.setGameMode(GameMode.SURVIVAL);

                        }else{
                            Main.trollmode.add(p);
                            p.sendMessage(prefix + "§8Der §4TrollMode §8ist §ejetzt §aaktiviert!");

                        }
                    }else{
                        if(Main.trollmode.contains(p)) {
                            if (args[0].equalsIgnoreCase("show")) {
                                if (Main.vanish.contains(p)) {
                                    Main.vanish.remove(p);
                                    p.sendMessage(prefix + "Die §aSpieler§7 sehen dich jetzt wieder!");
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        all.showPlayer(p);
                                    }
                                }else{
                                    p.sendMessage(prefix + "Die §aSpieler§7 sehen dich schon!");
                                }
                            }else{
                                if (args[0].equalsIgnoreCase("vanish")) {
                                    if (Main.vanish.contains(p)) {
                                        p.sendMessage(prefix + "Du bist schon §evanish!");
                                    }else{
                                        p.sendMessage(prefix + "Du bist jetzt jetzt §evanish!");
                                        Main.vanish.add(p);
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            all.hidePlayer(p);
                                        }
                                    }
                                }else{
                                    if (args[0].equalsIgnoreCase("gm")) {
                                        if (args[0].equalsIgnoreCase("gm")) {
                                            p.sendMessage(prefix + "Du bist nun im §eKreativ Modus!");
                                            p.setGameMode(GameMode.CREATIVE);
                                        }
                                    }else{
                                        if (args[0].equalsIgnoreCase("help")) {
                                            p.sendMessage("§4TrollSystem §7by DSeeLP");
                                            p.sendMessage("§c/troll §7- Aktiviert/Deaktiviert den §4TrollModus");
                                            p.sendMessage("§c/troll gm §7- Versetzt dich in den §eKreativ Modus");
                                            p.sendMessage("§c/troll show §7- Zeigt dich");
                                            p.sendMessage("§c/troll freeze §7- Friert Spieler ein");
                                            p.sendMessage("§c/troll boost §7- Der Spieler wird nach oben geboosted");
                                            p.sendMessage("§c/troll stick §7- Gibt dir einen §7Knockback 100 §7Stick");
                                            p.sendMessage("§c/troll vanish §7- Versteckt dich");
                                            if (p.hasPermission("troll.other")) {
                                                p.sendMessage("§c/troll toggle <Name> §7- Versteckt dich");
                                            }
                                        }else{
                                            if (args[0].equalsIgnoreCase("freeze")) {
                                                if (args.length == 2) {
                                                    Player t = Bukkit.getPlayer(args[1]);
                                                    if (Main.inFreeze.contains(t)) {
                                                        Main.inFreeze.remove(t);
                                                        p.sendMessage(prefix + "§c" + t.getDisplayName() + "§7 ist nun nicht mehr eingefroren!");

                                                    }else{
                                                        Main.inFreeze.add(t);
                                                        p.sendMessage(prefix + "§a" + t.getDisplayName() + "§7 ist nun eingefroren!");


                                                    }


                                                }else{
                                                    p.sendMessage(prefix + "§cBenutzung: /troll freeze <Name>");
                                                }
                                            }else{
                                                if (args[0].equalsIgnoreCase("boost")) {
                                                    if (args.length == 2) {
                                                        Player p1 = Bukkit.getPlayer(args[1]);
                                                        if (p1 == null) {
                                                            p.sendMessage("§7Der Spieler §6" + args[1] + " §7ist nicht online!");
                                                        }else{
                                                            p1.setVelocity(new Vector(0, 100, 0));
                                                            p.sendMessage("§a" + p1.getName() + " §7wurde in die Luft geschleudert!");
                                                        }
                                                    }else{
                                                        p.sendMessage("§cBenutze /boost <Player>");
                                                    }
                                                }else{
                                                    if (args[0].equalsIgnoreCase("stick")) {
                                                        ItemStack bow = new ItemStack(Material.STICK);
                                                        ItemMeta bowM = bow.getItemMeta();
                                                        bowM.addEnchant(Enchantment.KNOCKBACK, 100, true);
                                                        bowM.setDisplayName("§c§lSuper§b§lKnock");
                                                        bow.setItemMeta(bowM);
                                                        p.getInventory().addItem(bow);
                                                    }else{
                                                        if (p.hasPermission("troll.other")) {
                                                            if (args[0].equalsIgnoreCase("toggle")) {
                                                                Player t = Bukkit.getPlayer(args[1]);
                                                                if(t == null){
                                                                    p.sendMessage(Main.prefix + "Der Spieler§e " + args[1] + " §7ist nicht §aonline!");
                                                                }else{
                                                                    if (args.length == 2) {
                                                                        //vanish
                                                                        if (Main.vanish.contains(t)) {
                                                                            Main.vanish.remove(t);
                                                                            for (Player all : Bukkit.getOnlinePlayers()) {
                                                                                all.showPlayer(t);
                                                                            }
                                                                            t.sendMessage(prefix + "Die §aSpieler§7 sehen dich jetzt wieder!");

                                                                        }else{
                                                                            Main.vanish.add(t);
                                                                            for (Player all : Bukkit.getOnlinePlayers()) {
                                                                                all.hidePlayer(t);
                                                                            }
                                                                            t.sendMessage(prefix + "§7Du bist jetzt jetzt §evanish!");

                                                                        }

                                                                        //Trollmode
                                                                        if (Main.trollmode.contains(t)) {
                                                                            Main.trollmode.remove(t);
                                                                            p.sendMessage(prefix + "§8Du hast den §cTrollMode§8 von§e " + t.getName() + " §cdeaktiviert!");
                                                                            t.sendMessage(prefix + "§8Dein §4TrollMode §8wurde von §e" + p.getDisplayName() + " §cdeaktiviert!");
                                                                            t.setGameMode(GameMode.SURVIVAL);

                                                                        }else{
                                                                            Main.trollmode.add(t);
                                                                            p.sendMessage(prefix + "§8Du hast den §cTrollMode§8 von§e " + t.getName() + " §aaktiviert!");
                                                                            t.sendMessage(prefix + "§8Dein §4TrollMode §8wurde von §e" + p.getDisplayName() + " §aaktiviert!");

                                                                        }

                                                                    }else{
                                                                        p.sendMessage("§cBitte benutze /troll toggle <Name>");
                                                                    }
                                                                }
                                                            }else{
                                                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', helpmsg));
                                                            }
                                                        }else{
                                                            p.sendMessage("§cSystem §7» §6Du hast keine Rechte diesen SubBefehl auszuführen");
                                                        }

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }else{
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', helpmsg));
                        }
                    }
                }else{
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', helpmsg));
                }
            }
        return false;
    }
}
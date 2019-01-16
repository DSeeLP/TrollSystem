import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class test {

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


        cfg.options().header("TrollPlugin by DSeeLP\n§ = &\nNoperm ändert ausschließlich die NoPermission message vom /troll Befehl!\n Ändert die Unknown Command Message Global!");

        cfg.addDefault("General.helpmsg", "§cSystem §8»§6 Dieser Befehl exestiert nicht");
        cfg.addDefault("General.noperm", "§cSystem §8»§6 Dieser Befehl exestiert nicht");

        try {
            cfg.save(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

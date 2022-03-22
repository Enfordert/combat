package ru.enfordert;

import org.bukkit.Bukkit;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.scoreboard.Scoreboard;
import ru.enfordert.commands.setMaxReach;
import ru.enfordert.commands.setRodSpeed;
import ru.enfordert.commands.setVelocity;
import ru.enfordert.listeners.Listeners;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

    public static double reachMultiplier = 3.0; // max reach value
    public static double rodSpeed = 1.4; // Default 1.0
    public static double rodkb = 0.1; // TODO KBROD
    public static double velocity = 0.01; // increased players velocity

    public static boolean weatherChange = false;
    public static boolean entitySpawn = false;
    public static boolean FoodLevelChange = false;

    public static Scoreboard scoreBoard;

    public void onEnable() {

        getServer().getPluginManager().registerEvents(new Listeners(), this);
        scoreBoard = Bukkit.getScoreboardManager().getMainScoreboard();

        getCommand("setreach").setExecutor(new setMaxReach());
        getCommand("setrodspeed").setExecutor(new setRodSpeed());
        getCommand("setVelocity").setExecutor(new setVelocity());

        registerEvents();
    }

    public void onDisable() {

    }

    protected void registerEvents()
    {
        getServer().getPluginManager().registerEvents(new Listeners(), this);
    }


}

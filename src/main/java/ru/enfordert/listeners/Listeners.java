package ru.enfordert.listeners;

import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.enfordert.Main;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.util.Vector;

import java.text.DecimalFormat;

public class Listeners implements Listener {

    double distance;
    Player p, playerCheater, damaged;

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player)
        p = (Player) e.getDamager();

        if(e.getEntity() instanceof Player)
        this.playerCheater = (Player) e.getDamager();
        this.distance = e.getEntity().getLocation().distance(p.getLocation()) - 0.502D;

        if(distance > Main.reachMultiplier
                && !p.isOp()
                && e.getEntity() instanceof Player) {
            Bukkit.getOnlinePlayers().stream().filter(player -> player.isOp()).forEach(this::sendMessageToAdmin);
            e.setCancelled(true);
        }

        if(e.getEntity() instanceof Player)
        this.damaged = (Player)e.getEntity();
        Vector vector = p.getLocation().getDirection();

        if(e.isCancelled() || damaged.isOp() || !(damaged instanceof Player)) return;
        damaged.setVelocity(vector.multiply(Main.velocity));
    }

    private void sendMessageToAdmin(Player player) {
            DecimalFormat format = new DecimalFormat("0.00");
            String output = format.format(distance);
            player.sendMessage("§eNAME: §6" + playerCheater.getName() + "§e DISTANCE: §6" + output);
    }

    @EventHandler
    private void PlayerHunger(FoodLevelChangeEvent event) {
        if(Main.FoodLevelChange) return;
        if (event.getEntity() instanceof Player) {
            event.setFoodLevel(10);
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void EntitySpawn(EntitySpawnEvent event) {
        if(event.getEntity() instanceof EntityItem) return;
        if(Main.entitySpawn) return;

        if (event.getEntityType() != EntityType.PLAYER
                && event.getEntityType() != EntityType.DROPPED_ITEM
                && event.getEntityType() != EntityType.PRIMED_TNT) {
                event.setCancelled(true);
        }
    }

    @EventHandler
    private void WeatherEvent(WeatherChangeEvent event) {
        if(Main.weatherChange) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void fishingThrow(ProjectileLaunchEvent e){
        Projectile hook = e.getEntity();
        if(e.getEntityType().equals(EntityType.FISHING_HOOK)){
            hook.setVelocity(hook.getVelocity().multiply(Main.rodSpeed));
        }
    }


}

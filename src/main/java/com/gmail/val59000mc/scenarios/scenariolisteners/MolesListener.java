package com.gmail.val59000mc.scenarios.scenariolisteners;

import com.gmail.val59000mc.UhcCore;
import com.gmail.val59000mc.configuration.MainConfig;
import com.gmail.val59000mc.events.UhcStartedEvent;
import com.gmail.val59000mc.game.GameManager;
import com.gmail.val59000mc.languages.Lang;
import com.gmail.val59000mc.listeners.PlayerDamageListener;
import com.gmail.val59000mc.players.PlayerManager;
import com.gmail.val59000mc.players.UhcPlayer;
import com.gmail.val59000mc.scenarios.ScenarioListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;


public class MolesListener extends ScenarioListener {
    @Override
    public void onEnable() {
        getGameManager().setFriendlyFire(true);
        getGameManager().setMolesScenarioEnabled(true);
        System.out.println(getGameManager().getFriendlyFire());
    }
    @Override
    public void onDisable() {
        getGameManager().setFriendlyFire(false);
        getGameManager().setMolesScenarioEnabled(false);
        System.out.println(getGameManager().getFriendlyFire());
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onPlayerDeath(PlayerDeathEvent e){
        String playerName = e.getEntity().getName();
        e.setDeathMessage(ChatColor.GREEN + playerName+ ChatColor.RESET + " est mort.");
    }
}


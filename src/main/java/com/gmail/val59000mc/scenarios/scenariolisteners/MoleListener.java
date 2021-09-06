package com.gmail.val59000mc.scenarios.scenariolisteners;

import com.gmail.val59000mc.scenarios.ScenarioListener;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.Set;


public class MoleListener extends ScenarioListener {
    @Override
    public void onEnable() {
        System.out.println("Here");
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Set<Team> TeamsList = scoreboard.getTeams();
        for (Team team : TeamsList) {
            team.setAllowFriendlyFire(true);
            System.out.println(team);
        }
    }
}

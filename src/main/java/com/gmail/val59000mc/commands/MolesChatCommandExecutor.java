package com.gmail.val59000mc.commands;

import com.gmail.val59000mc.game.GameManager;
import com.gmail.val59000mc.game.GameState;
import com.gmail.val59000mc.players.PlayerManager;
import com.gmail.val59000mc.players.UhcPlayer;
import com.gmail.val59000mc.players.UhcTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MolesChatCommandExecutor implements CommandExecutor {

    private final GameManager gameManager;

    public MolesChatCommandExecutor(GameManager gameManager){
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;

        if (!gameManager.getMolesScenarioEnabled()) {
            player.sendMessage("Le scénario Taupe Gun n'est pas activé !");
        }

        if (gameManager.getGameState() != GameState.PLAYING && gameManager.getGameState() != GameState.DEATHMATCH){
            player.sendMessage("La partie n'a pas encore commencé.");
            return true;
        }

        PlayerManager pm = gameManager.getPlayerManager();
        UhcPlayer uhcPlayer = pm.getUhcPlayer(player);

        if (!uhcPlayer.getMoleState()) {
            player.sendMessage("Vous n'êtes pas une taupe.");
            return true;
        }

        String message = String.join(" ", args);

        UhcTeam moleTeam = uhcPlayer.getTeam();
        String moleTeamPrefix = moleTeam.getPrefixAlone();

        String formattedMessage = ChatColor.RED + "[taupes] " + moleTeamPrefix + "<" + ChatColor.MAGIC + "xxxxxx"  + ChatColor.RESET + moleTeamPrefix + "> " + ChatColor.RESET + message;

        PlayerManager playerManager = gameManager.getPlayerManager();
        for (UhcPlayer moleUhcPlayer : playerManager.getMoles()) {
            Player molePlayer = Bukkit.getPlayer(moleUhcPlayer.getUuid());
            molePlayer.sendMessage(formattedMessage);
        }
        return true;
    }
}

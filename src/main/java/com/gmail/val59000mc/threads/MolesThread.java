package com.gmail.val59000mc.threads;

import com.gmail.val59000mc.UhcCore;
import com.gmail.val59000mc.commands.MolesChatCommandExecutor;
import com.gmail.val59000mc.exceptions.UhcPlayerNotOnlineException;
import com.gmail.val59000mc.game.GameManager;
import com.gmail.val59000mc.players.PlayerManager;
import com.gmail.val59000mc.players.TeamManager;
import com.gmail.val59000mc.players.UhcPlayer;
import com.gmail.val59000mc.players.UhcTeam;
import com.gmail.val59000mc.utils.UniversalSound;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class MolesThread implements Runnable {
    private final GameManager gameManager;
    private final PlayerManager playerManager;
    private List<UhcPlayer> moles;

    public MolesThread (GameManager gameManager, PlayerManager playerManager){
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }
    @Override
    public void run() {
        TeamManager teamManager = gameManager.getTeamManager();

        for (UhcTeam team : teamManager.getPlayingUhcTeams()) {
            List<UhcPlayer> teamPlayers = team.getOnlinePlayingMembers();
            Random rand = new Random();
            UhcPlayer selectedPlayer = teamPlayers.get(rand.nextInt(teamPlayers.size()));
            selectedPlayer.setMoleState(true);
            Player player = Bukkit.getPlayer(selectedPlayer.getUuid());

            player.sendMessage(ChatColor.GOLD + "================================" + "\n" + ChatColor.RED + "Vous êtes une taupe." + "\n" + ChatColor.DARK_RED + "GARDEZ CETTE INFORMATION SECRETE" + "\n" + ChatColor.GOLD + "Trahissez votre equipe afin de gagner la partie avec les autres taupes !\nUtilisez la commande /t <message> afin de communiquer annonymement avec les autres taupes.\nLes messages de mort sont aussi annonymisés.\nSeul le pseudo du joueur mort est affiché.\n================================");
            player.sendTitle("", ChatColor.DARK_RED + "VOUS ÊTES UNE TAUPE !");
            playerManager.playSoundToAll(UniversalSound.BLOCK_END_PORTAL_SPAWN);
            for (UhcPlayer teamPlayer : teamPlayers) {
                Player nonMolePlayer = Bukkit.getPlayer(teamPlayer.getUuid());

                if (!teamPlayer.getMoleState()) {
                    nonMolePlayer.sendTitle("", ChatColor.GREEN + "Vous n'êtes pas une taupe.");
                    nonMolePlayer.sendMessage(ChatColor.RED + "Vous n'etes pas une taupe.\n" + ChatColor.GOLD + "Tentez de démasquer la taupe dans votre equipe.");
                }
            }
        }
    }
}
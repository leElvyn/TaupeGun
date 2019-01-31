package com.gmail.val59000mc.playuhc.mc1_13.events;

import com.gmail.val59000mc.playuhc.mc1_13.players.UhcPlayer;

import java.util.Set;

public class UhcWinEvent extends UhcEvent{

	private Set<UhcPlayer> winners;

	public UhcWinEvent(Set<UhcPlayer> winners){
		this.winners = winners;
	}

	public Set<UhcPlayer> getWinners(){
		return winners;
	}

}
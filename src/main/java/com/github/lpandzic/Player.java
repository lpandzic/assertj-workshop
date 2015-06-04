package com.github.lpandzic;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private final List<Player> teammates;

	public Player() {

		this.teammates = new ArrayList<>();
	}

	public void addTeammate(Player player) {
		teammates.add(player);
	}

	public List<Player> getTeammates() {

		return teammates;
	}
}

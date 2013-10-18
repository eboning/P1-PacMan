package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class LairTime implements ICondition {

	GHOST ghost;
	int min, max;
	public LairTime(GHOST ghost, int min, int max) {
		this.ghost = ghost;
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean test(Game game) {
		return (min <= game.getGhostLairTime(ghost) && game.getGhostLairTime(ghost) <= max );
	}

}

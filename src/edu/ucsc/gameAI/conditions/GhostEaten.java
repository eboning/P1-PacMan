package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import pacman.game.Constants.GHOST;
import edu.ucsc.gameAI.ICondition;

public class GhostEaten implements ICondition {

	GHOST gghost;
	public GhostEaten(GHOST ghost) {
		this.gghost = ghost;
	}

	@Override
	public boolean test(Game game) {
		return game.wasGhostEaten(gghost);
	}

}

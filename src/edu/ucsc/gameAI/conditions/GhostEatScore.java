package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostEatScore implements ICondition {
	int min, max;

	public GhostEatScore(int min, int max) {
		this.min = min;
		this.max = max;
	}


	@Override
	public boolean test(Game game) {
		return game.getGhostCurrentEdibleScore() >= this.min && 
			   game.getGhostCurrentEdibleScore() <= this.max;
	}

}

package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class NumberOfLivesRemaining implements ICondition {

	int min, max;
	public NumberOfLivesRemaining(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean test(Game game) {
		return ( min <= game.getPacmanNumberOfLivesRemaining() && game.getPacmanNumberOfLivesRemaining() <= max );
	}

}

package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class Score implements ICondition {
	int min, max;

	public Score(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean test(Game game) {
		return game.getScore() >= this.min && 
			   game.getScore() <= this.max;
	}

}

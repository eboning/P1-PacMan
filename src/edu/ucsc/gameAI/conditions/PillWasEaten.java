package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PillWasEaten implements ICondition {

	@Override
	public boolean test(Game game) {
		return game.wasPillEaten();
	}

}

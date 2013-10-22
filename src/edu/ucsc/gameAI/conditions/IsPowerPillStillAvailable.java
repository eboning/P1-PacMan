package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class IsPowerPillStillAvailable implements ICondition {

	int index;
	public IsPowerPillStillAvailable(int index) {
		this.index = index;
	}

	@Override
	public boolean test(Game game) {
		return game.isPowerPillStillAvailable(index);
	}

}

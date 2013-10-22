package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class IsPillStillAvailable implements ICondition {

	int index;
	public IsPillStillAvailable(int index) {
		this.index = index;
	}

	@Override
	public boolean test(Game game) {
		return game.isPillStillAvailable(index);
	}

}

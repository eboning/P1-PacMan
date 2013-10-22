package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class CurrentPacmanNodeIndex implements ICondition {

	int index;
	public CurrentPacmanNodeIndex(int index) {
		this.index = index;
	}

	@Override
	public boolean test(Game game) {
		return game.getPacmanCurrentNodeIndex() == index;
	}

}

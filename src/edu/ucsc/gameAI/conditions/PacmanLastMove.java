package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PacmanLastMove implements ICondition {

	MOVE move;
	public PacmanLastMove(MOVE move) {
		this.move = move;
	}

	@Override
	public boolean test(Game game) {
		return game.getPacmanLastMoveMade() == move;
	}

}

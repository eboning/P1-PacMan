package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class TotalTime implements ICondition {
	int min, max;

	public TotalTime(int min, int max) {
		this.min = min;
		this.max = max;
	}


	@Override
	public boolean test(Game game) {
		return game.getTotalTime() >= this.min && 
			   game.getTotalTime() <= this.max;
	}

}

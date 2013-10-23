package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class CurrentLevelTime implements ICondition {
	int min, max;

	public CurrentLevelTime(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public boolean test(Game game) {
		return game.getCurrentLevelTime() >= this.min && 
			   game.getCurrentLevelTime() <= this.max;
	}

}

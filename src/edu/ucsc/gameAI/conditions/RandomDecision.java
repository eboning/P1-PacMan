package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;
import java.util.*;

public class RandomDecision implements ICondition {

	@Override
	public boolean test(Game game) {
		return new Random().nextBoolean();
	}

}

package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PacmanInRegion implements ICondition {
	int x1, y1, x2, y2;

	public PacmanInRegion(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public boolean test(Game game) {
		int pacman = game.getPacmanCurrentNodeIndex();
		if(game.getNodeXCood(pacman) >= this.x1 &&
		   game.getNodeXCood(pacman) <= this.x2 &&
		   game.getNodeYCood(pacman) >= this.y1 &&
		   game.getNodeYCood(pacman) <= this.y2) {
			
			return true;
		}
		return false;
	}

}

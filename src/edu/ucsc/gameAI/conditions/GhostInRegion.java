package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import pacman.game.Constants.GHOST;
import edu.ucsc.gameAI.ICondition;

public class GhostInRegion implements ICondition {
	int x1, y1, x2, y2;

	public GhostInRegion(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	@Override
	public boolean test(Game game) {
		boolean tmp = false;
		for(GHOST ghost : GHOST.values()) { //for each ghost
			int ghostIndex = game.getGhostCurrentNodeIndex(ghost);
			if(game.getNodeXCood(ghostIndex) > this.x1 &&
			   game.getNodeXCood(ghostIndex) < this.x2 &&
			   game.getNodeYCood(ghostIndex) > this.y1 &&
			   game.getNodeYCood(ghostIndex) < this.y2) {
				
				tmp = true;
			}
		}
		return tmp;
	}

}

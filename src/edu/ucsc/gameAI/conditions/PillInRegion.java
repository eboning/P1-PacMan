package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PillInRegion implements ICondition {
	int x1, y1, x2, y2;

	public PillInRegion(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	@Override
	public boolean test(Game game) {
		int[] pills = game.getPillIndices();
		boolean tmp = false;
		
		for(int i = 0; i<pills.length; i++) {
			int pill = pills[i];

			if(game.getNodeXCood(pill) > this.x1 &&
			   game.getNodeXCood(pill) < this.x2 &&
			   game.getNodeYCood(pill) > this.y1 &&
			   game.getNodeYCood(pill) < this.y2) {
				
				tmp = true;
			}
		}
		return tmp;
	}

}

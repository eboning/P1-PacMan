package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PowerPillInRegion implements ICondition {
	int x1, y1, x2, y2;

	public PowerPillInRegion(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	@Override
	public boolean test(Game game) {
		int[] powerPills = game.getPowerPillIndices();
		boolean tmp = false;
		for(int i = 0; i<powerPills.length; i++) {
			int powerPill = powerPills[i];

			if(game.getNodeXCood(powerPill) >= this.x1 &&
			   game.getNodeXCood(powerPill) <= this.x2 &&
			   game.getNodeYCood(powerPill) >= this.y1 &&
			   game.getNodeYCood(powerPill) <= this.y2) {
				
				tmp = true;
			}
		}
		return tmp;
	}

}

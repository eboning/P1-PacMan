package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import edu.ucsc.gameAI.ICondition;

public class NoGhosts implements ICondition {
	private static final int MIN_DISTANCE=20;	//if a ghost is this close, run away

	@Override
	public boolean test(Game game) {
		//System.out.println("Testing for no ghosts");
		int current = game.getPacmanCurrentNodeIndex();
		MOVE lastMove = game.getPacmanLastMoveMade();

		for(GHOST ghost : GHOST.values()) {
			int ghostNode = game.getGhostCurrentNodeIndex(ghost);

			if(game.getGhostLairTime(ghost) == 0) { 
				if(game.getShortestPathDistance(current, ghostNode, lastMove) < MIN_DISTANCE) {
					return false;
				}
			}
		}
		return true;
	}

}

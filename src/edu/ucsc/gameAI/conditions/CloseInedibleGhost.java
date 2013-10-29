package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import edu.ucsc.gameAI.ICondition;

public class CloseInedibleGhost implements ICondition {
	private static final int MIN_DISTANCE=20;	//if a ghost is this close, run away

	@Override
	public boolean test(Game game) {
		int current = game.getPacmanCurrentNodeIndex();
		MOVE lastMove = game.getPacmanLastMoveMade();

		for(GHOST ghost : GHOST.values()) {
			int ghostNode = game.getGhostCurrentNodeIndex(ghost);

			if(game.getGhostLairTime(ghost) == 0) { 
				int distance = game.getShortestPathDistance(current, ghostNode, lastMove);
				if(distance < MIN_DISTANCE) {
					System.out.println("Should flee now, distance = " + distance);
					System.out.println("MinDistance = " + MIN_DISTANCE);
					if ( !game.isGhostEdible(ghost) ) {
						return true;
					}
				}
			}
		}
		return false;
	}

}

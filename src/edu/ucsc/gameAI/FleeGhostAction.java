package edu.ucsc.gameAI;

import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class FleeGhostAction implements IAction {
	Game game;

	public FleeGhostAction(Game game) {
		super();
		this.game = game;
	}

	
	@Override
	public void doAction() {
	}

	@Override
	public MOVE getMove() {
	//	System.out.println("Currently Fleeing");
		int current = game.getPacmanCurrentNodeIndex();
		MOVE lastMove = game.getPacmanLastMoveMade();
		int closest = -1;
		for(GHOST ghost : GHOST.values()) {
			if(closest == -1) { 
				if(game.getGhostLairTime(ghost) == 0) { closest = game.getGhostCurrentNodeIndex(ghost); }
			}
			else {
				int ghostNode = game.getGhostCurrentNodeIndex(ghost);
				
				if(game.getGhostLairTime(ghost) == 0) { 
					int ghostDistance = game.getShortestPathDistance(current, ghostNode, lastMove);
					int closestDistance = game.getShortestPathDistance(current, closest, lastMove);
	
					if(ghostDistance < closestDistance) {
						closest = game.getGhostCurrentNodeIndex(ghost);
					}
				}
			}
		}
		if (closest == -1) { 
			return lastMove; 
		}
		return game.getNextMoveAwayFromTarget(current, closest, DM.PATH);
	}
}

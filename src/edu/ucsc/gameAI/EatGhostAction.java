package edu.ucsc.gameAI;

import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class EatGhostAction implements IAction {
	Game game;

	public EatGhostAction(Game game) {
		super();
		this.game = game;
	}

	@Override
	public void doAction() {
	}

	@Override
	public MOVE getMove() {
		int current = game.getPacmanCurrentNodeIndex();
		MOVE lastMove = game.getPacmanLastMoveMade();
		int closest = -1;
		for(GHOST ghost : GHOST.values()) {
			if(closest == -1) { closest = game.getGhostCurrentNodeIndex(ghost); }
			else {
				int ghostNode = game.getGhostCurrentNodeIndex(ghost);
				int ghostDistance = game.getShortestPathDistance(current, ghostNode, lastMove);
				int closestDistance = game.getShortestPathDistance(current, closest, lastMove);

				if(ghostDistance < closestDistance) {
					closest = game.getGhostCurrentNodeIndex(ghost);
				}
			}
		}
		return game.getApproximateNextMoveTowardsTarget(current, closest, lastMove, DM.PATH);

	}

}

package edu.ucsc.gameAI;

import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import static pacman.game.Constants.*;


public class AttackAction implements IAction, IBinaryNode {
	Game game;
	int fromNodeIndex, toNodeIndex;
	MOVE lastMoveMade;

	public AttackAction(Game game, int fromNodeIndex, int toNodeIndex, MOVE lastMoveMade) {
		this.game = game;
		this.fromNodeIndex = fromNodeIndex;
		this.toNodeIndex = toNodeIndex;
		this.lastMoveMade = lastMoveMade;
	}
	
	@Override
	public void doAction() {
	}

	public IAction makeDecision(Game game) {
		return this;
	}
	
	@Override
	public MOVE getMove() {
		return game.getApproximateNextMoveTowardsTarget(fromNodeIndex, toNodeIndex, lastMoveMade, DM.EUCLID);
	}
}

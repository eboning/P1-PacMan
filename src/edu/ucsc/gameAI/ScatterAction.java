package edu.ucsc.gameAI;

import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import static pacman.game.Constants.*;


public class ScatterAction implements IAction, IBinaryNode {
	Game game;
	int fromNodeIndex, toNodeIndex;
	MOVE lastMoveMade;

	public ScatterAction(Game game, int fromNodeIndex, int toNodeIndex,
			MOVE lastMoveMade) {
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
		return game.getApproximateNextMoveAwayFromTarget(fromNodeIndex, toNodeIndex, lastMoveMade, DM.PATH);
	}
}

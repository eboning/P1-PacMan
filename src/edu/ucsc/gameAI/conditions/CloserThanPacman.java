package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.DM;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

//Tests whether our node is closer to the target than pacman is
public class CloserThanPacman implements ICondition {
	int targetNode, myNode;
	public CloserThanPacman(int targetNode, int myNode) {
		this.targetNode = targetNode;
		this.myNode = myNode;
	}
	
	@Override
	public boolean test(Game game) {
		int pacmanNode = game.getPacmanCurrentNodeIndex();
		return game.getDistance(pacmanNode, targetNode, DM.PATH) > game.getDistance(myNode, targetNode, DM.PATH);
	}
}

package edu.ucsc.gameAI;

import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class GoLeftAction implements IAction, IBinaryNode {

	public void doAction() {
		// TODO Auto-generated method stub
		
	}
	
	public IAction makeDecision(Game game) {return this;}
	
	public MOVE getMove(){
		return MOVE.LEFT;		
	}
}

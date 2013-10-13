package edu.ucsc.gameAI.decisionTrees.binary;

import edu.ucsc.gameAI.IAction;

public class TreeAction implements IBinaryNode {
	
	IAction action;
	
	public TreeAction (IAction action){
		this.action = action;
	}

	@Override
	public IAction makeDecision() {
		return action;
	}

}

package edu.ucsc.gameAI.fsm;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.ICondition;

public class Transition implements ITransition {

	public Transition(IState targetState, IAction action, ICondition condition) {
		super();
		this.targetState = targetState;
		this.action = action;
		this.condition = condition;
	}

	IState targetState;
	IAction action;
	ICondition condition;
	
	@Override
	public IState getTargetState() {
		return targetState;
	}

	@Override
	public IAction getAction() {
		return action;
	}

	@Override
	public void setCondition(ICondition condition) {
		this.condition = condition;

	}

	@Override
	public boolean isTriggered() {
		return condition.test();
	}

}

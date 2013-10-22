package edu.ucsc.gameAI.fsm;

import java.util.Collection;

import edu.ucsc.gameAI.IAction;

public class State implements IState {
	
	public State(IAction action, IAction entryAction, IAction exitAction,
			Collection<ITransition> transitions) {
		super();
		this.action = action;
		this.entryAction = entryAction;
		this.exitAction = exitAction;
		this.transitions = transitions;
	}

	IAction action;
	IAction entryAction;
	IAction exitAction;
	Collection<ITransition> transitions;
	
	@Override
	public IAction getAction() {
		return action;
	}

	@Override
	public IAction getEntryAction() {
		return entryAction;
	}

	@Override
	public IAction getExitAction() {
		return exitAction;
	}

	@Override
	public Collection<ITransition> getTransitions() {
		return transitions;
	}

	@Override
	public void setAction(IAction action) {
		this.action = action;		
	}

	@Override
	public void setEntryAction(IAction action) {
		this.entryAction = action;
	}

	@Override
	public void setExitAction(IAction action) {
		this.exitAction = action;
	}

	@Override
	public void setTransitions(Collection<ITransition> trans) {
		this.transitions = trans;
	}

}

package edu.ucsc.gameAI.fsm;

import java.util.LinkedList;
import java.util.Collection;
import pacman.game.Game;

import edu.ucsc.gameAI.IAction;

public class StateMachine implements IStateMachine {
	

	public StateMachine(Collection<IState> states, IState initialState) {
		super();
		this.states = states;
		this.initialState = initialState;
	}
	
	public StateMachine() { }

	Collection<IState> states;
	IState initialState;
	IState currentState;
	
	

	@Override
	public Collection<IAction> update(Game game) {
		ITransition triggeredTransition = null;
		Collection<IAction> actions = new LinkedList<IAction>();
		
		for (ITransition transition : currentState.getTransitions())
			if (transition.isTriggered(game)){
				triggeredTransition = transition;
				break;
			}
		if (triggeredTransition != null){
			IState targetState = triggeredTransition.getTargetState();
			
			actions.add(currentState.getExitAction());
			actions.add(triggeredTransition.getAction());
			actions.add(targetState.getEntryAction());
			
			currentState = targetState;
			return actions;
		}
		else{
			actions.add(currentState.getAction());
			return actions;
		}
			
	}

	@Override
	public IState getCurrentState() {
		return currentState;
	}

	@Override
	public void setCurrentState(IState state) {
		this.currentState = state;
	}

}

package edu.ucsc.gameAI.fsm;

import java.util.ArrayList;
import java.util.Collection;

import edu.ucsc.gameAI.IAction;

public class StateMachine implements IStateMachine {
	

	public StateMachine(Collection<IState> states, IState initialState) {
		super();
		this.states = states;
		this.initialState = initialState;
	}

	Collection<IState> states;
	IState initialState;
	IState currentState;
	
	

	@Override
	public Collection<IAction> update() {
		ITransition triggeredTransition = null;
		Collection<IAction> actions = new ArrayList<IAction>();
		
		for (ITransition transition : currentState.getTransitions())
			if (transition.isTriggered()){
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
		// TODO Auto-generated method stub
		return null;
	}

}

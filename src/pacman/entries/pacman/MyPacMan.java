package pacman.entries.pacman;


import pacman.controllers.Controller;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.fsm.*;
import edu.ucsc.gameAI.conditions.*;

import java.util.*;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getAction() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.pacman.mypackage).
 */
public class MyPacMan extends Controller<MOVE>
{
	
	private StateMachine pacmanStates;
	private State flee, eatGhosts, targetPills;
	private ITransition edibleClose, dangerClose, noGhosts;
	
	public MyPacMan()
	{
		super();
		//Initialize our states and their machine
		
		flee = new State();		
		eatGhosts = new State();	
		targetPills = new State();
		
		//Initialize our transitions
		edibleClose = new Transition();
		edibleClose.setCondition(new CloseEdibleGhost());
		edibleClose.setTargetState(eatGhosts);

		dangerClose = new Transition();
		dangerClose.setCondition(new CloseInedibleGhost());
		dangerClose.setTargetState(flee);
		
		noGhosts = new Transition();
		noGhosts.setCondition(new NoGhosts());
		noGhosts.setTargetState(targetPills);
		
		//Default State: Eat stuff
		List<ITransition> pillsTrans = Arrays.asList(dangerClose, edibleClose);
		targetPills.setTransitions(pillsTrans);		
		
		//Flee if nonedible ghosts are close
		List<ITransition> fleeTrans = Arrays.asList(noGhosts);
		flee.setTransitions(fleeTrans);
		
		//Target edible ghosts if they are close
		List<ITransition> eatGhostTrans = Arrays.asList(dangerClose, noGhosts);
		eatGhosts.setTransitions(eatGhostTrans);
			
		pacmanStates = new StateMachine();
		pacmanStates.setCurrentState(targetPills);

	}
	
	//Adaptation of the StarterPacMan to use FSM
	public MOVE getMove(Game game, long timeDue) 
	{
		IState currState = pacmanStates.getCurrentState();
		if(currState == flee) { System.out.println("In Flee"); }
		if(currState == eatGhosts) { System.out.println("Eating Ghosts"); }
		if(currState == targetPills) { System.out.println("Targeting pills"); }
		
		//if(targetPills.getAction() == null) {
			IAction eatPills = new EatPillsAction(game);
			targetPills.setAction(eatPills);
		//}
		//if(flee.getAction() == null) {
			IAction run = new FleeGhostAction(game);
			flee.setAction(run);
			flee.setEntryAction(run);
		//}
		//if(eatGhosts.getAction() == null) {
			IAction hunt = new EatGhostAction(game);
			eatGhosts.setAction(hunt);
		//}
		Collection<IAction> actions = pacmanStates.update(game);
		if(actions.size() == 1) {
			return actions.iterator().next().getMove();
		} else if (actions.size() == 0) {
			System.out.println("Something is horribly bad");
			return MOVE.LEFT;
		} else {
			return MOVE.RIGHT;
		}
		//Do something with our actions
/*
		IAction action = null;
		if(actions.contains(run)) {
			action = run;
		} else if (actions.contains(hunt)) {
			action = hunt;
		} else if (actions.contains(eatPills)) {
			action = eatPills;
		} else {
			System.out.println("Running by default");
			action = run;
		}
		return action.getMove();
		*/
	}
}
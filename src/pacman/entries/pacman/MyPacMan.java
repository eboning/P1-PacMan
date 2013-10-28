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
	private static final int MIN_DISTANCE=20;	//if a ghost is this close, run away
	
	private StateMachine pacmanStates;
	private State flee, edibleGhostClose, targetPills;
	private ITransition dangerGhost, edibleClose, noGhosts;
	
	public MyPacMan()
	{
		super();
		//Initialize our states and their machine
		Collection<IState> states = new ArrayList();
		flee = new State();	
		states.add(flee);
		
		edibleGhostClose = new State();
		states.add(edibleGhostClose);
		
		targetPills = new State();
		states.add(targetPills);
		pacmanStates = new StateMachine(states, targetPills);
		
		//Initialize our transitions
		dangerGhost = new Transition();
		dangerGhost.setCondition(new CloseInedibleGhost());
		dangerGhost.setTargetState(flee);
		
		edibleClose = new Transition();
		edibleClose.setCondition(new CloseEdibleGhost());
		edibleClose.setTargetState(edibleGhostClose);
		
		noGhosts = new Transition();
		noGhosts.setCondition(new NoGhosts());
		noGhosts.setTargetState(targetPills);
		
		//Default State: Eat stuff
		List<ITransition> pillsTrans = Arrays.asList(dangerGhost, edibleClose);
		targetPills.setTransitions(pillsTrans);
		
		//Flee if nonedible ghosts are close
		List<ITransition> fleeTrans = Arrays.asList(noGhosts);
		flee.setTransitions(fleeTrans);
		
		//Target edible ghosts if they are close
		List<ITransition> edibleGhostTrans = Arrays.asList(dangerGhost, noGhosts);
		edibleGhostClose.setTransitions(edibleGhostTrans);
	
	
	}
	
	//Adaptation of the StarterPacMan to use FSM
	public MOVE getMove(Game game, long timeDue) 
	{
		int current = game.getPacmanCurrentNodeIndex();
		MOVE lastMove = game.getPacmanLastMoveMade();

		//State actions
		IAction eatPills = new EatPillsAction(game);
		targetPills.setAction(eatPills);
		
		IAction run = new FleeGhostAction(game);
		flee.setAction(run);

		IAction hunt = new EatGhostAction(game);
		edibleGhostClose.setAction(hunt);
		
		
		Collection<IAction> actions = pacmanStates.update(game);
		//Do something with our actions
		IAction action = null;
		if(actions.contains(run)) {
			action = run;
		} else if (actions.contains(hunt)) {
			action = hunt;
		} else {
			action = eatPills;
		}
		return action.getMove();
	}
}
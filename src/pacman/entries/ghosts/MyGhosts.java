package pacman.entries.ghosts;

import java.util.EnumMap;

import pacman.controllers.Controller;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.conditions.*;
import edu.ucsc.gameAI.decisionTrees.binary.*;
import edu.ucsc.gameAI.*;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getActions() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.ghosts.mypackage).
 */
public class MyGhosts extends Controller<EnumMap<GHOST,MOVE>>
{
	private EnumMap<GHOST, MOVE> myMoves=new EnumMap<GHOST, MOVE>(GHOST.class);
	
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue)
	{
		myMoves.clear();
		
		for(GHOST ghost : GHOST.values())	//for each ghost
		{
			int pacmanNode = game.getPacmanCurrentNodeIndex();
			int pacmanX = game.getNodeXCood(pacmanNode);
			int pacmanY = game.getNodeYCood(pacmanNode);
			//MOVE lastPacmanMove = game.getPacmanLastMoveMade();
			
			int ghostNode = game.getGhostCurrentNodeIndex(ghost);
			int ghostX = game.getNodeXCood(ghostNode);
			int ghostY = game.getNodeYCood(ghostNode);
			MOVE lastGhostMove = game.getGhostLastMoveMade(ghost);
			
			IBinaryNode scatter = new ScatterAction(game, ghostNode, pacmanNode, lastGhostMove);
			IBinaryNode attack = new AttackAction(game, ghostNode, pacmanNode, lastGhostMove);
					
			BinaryDecision pacmanCloseToGhost = new BinaryDecision();
			pacmanCloseToGhost.setCondition(new PacmanInRegion(ghostX - 25, ghostY - 25, ghostX + 25, ghostY + 25));
			pacmanCloseToGhost.setTrueBranch(scatter);
			pacmanCloseToGhost.setFalseBranch(attack);
			
			BinaryDecision pacmanCloseToPowerPill = new BinaryDecision();
			pacmanCloseToPowerPill.setCondition(new PowerPillInRegion(pacmanX - 25, pacmanY - 25, pacmanX + 25, pacmanY + 25));
			pacmanCloseToPowerPill.setTrueBranch(pacmanCloseToGhost);
			pacmanCloseToPowerPill.setFalseBranch(attack);
			
			BinaryDecision edibleBinaryDecision = new BinaryDecision();
			edibleBinaryDecision.setCondition(new IsEdible(ghost));
			edibleBinaryDecision.setTrueBranch(scatter);
			edibleBinaryDecision.setFalseBranch(pacmanCloseToPowerPill);
			
			if(game.doesGhostRequireAction(ghost))		//if ghost requires an action
			{
				IAction edible = edibleBinaryDecision.makeDecision(game);
				myMoves.put(ghost, edible.getMove());	
			}
		}
		
		
		return myMoves;
	}
	
}
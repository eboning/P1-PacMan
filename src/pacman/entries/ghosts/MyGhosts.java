package pacman.entries.ghosts;

import java.util.EnumMap;

import pacman.controllers.Controller;
import pacman.game.Constants.DM;
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
			int ambushNode = getAmbushNode(game);
			int powerPillNode = getClosestPP(pacmanNode, game) > 0 ? getClosestPP(pacmanNode, game) : pacmanNode;
			
			int ghostNode = game.getGhostCurrentNodeIndex(ghost);
			int ghostX = game.getNodeXCood(ghostNode);
			int ghostY = game.getNodeYCood(ghostNode);
			MOVE lastGhostMove = game.getGhostLastMoveMade(ghost);
			
			IBinaryNode scatter = new ScatterAction(game, ghostNode, pacmanNode, lastGhostMove);
			IBinaryNode attack = new AttackAction(game, ghostNode, pacmanNode, lastGhostMove);
			IBinaryNode ambush = new AttackAction(game, ghostNode, ambushNode, lastGhostMove);
			
			
			BinaryDecision aggressive = new BinaryDecision();
			aggressive.setCondition(new RandomDecision());
			aggressive.setTrueBranch(attack);
			aggressive.setFalseBranch(ambush);
			
			BinaryDecision closerThanPacman = new BinaryDecision();
			IBinaryNode targetPP = new AttackAction(game, ghostNode, powerPillNode, lastGhostMove);			
			closerThanPacman.setCondition(new CloserThanPacman(powerPillNode, ghostNode));
			closerThanPacman.setFalseBranch(scatter);
			closerThanPacman.setTrueBranch(targetPP);
			
			BinaryDecision pacmanCloseToGhost = new BinaryDecision();
			pacmanCloseToGhost.setCondition(new PacmanInRegion(ghostX - 15, ghostY - 15, ghostX + 15, ghostY + 15));			
			pacmanCloseToGhost.setTrueBranch(closerThanPacman);
			pacmanCloseToGhost.setFalseBranch(aggressive);
			
			BinaryDecision pacmanCloseToPowerPill = new BinaryDecision();
			pacmanCloseToPowerPill.setCondition(new PowerPillInRegion(pacmanX - 15, pacmanY - 15, pacmanX + 15, pacmanY + 15));
			pacmanCloseToPowerPill.setTrueBranch(pacmanCloseToGhost);
			pacmanCloseToPowerPill.setFalseBranch(aggressive);
			
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
	
	//Attempts to pick a node in front of pacman
	private int getAmbushNode(Game game) {
		int node = game.getPacmanCurrentNodeIndex();
		MOVE lastMove = game.getPacmanLastMoveMade();
		for(int i=0; i<3; i++) {		
			//If we're at a decision point, pick the first possible move
			//Logic could definitely be improved...
			if(game.isJunction(node)) {
				lastMove = game.getPossibleMoves(node, lastMove)[0];
			}
			int neighbor = game.getNeighbour(node, lastMove);
			//If the move would take us off the map, just return the current node
			node = neighbor > 0 ? neighbor : node;
		}
		return node;
	}
	
	//Returns the closest power pill to this node, or -1 if no more PP
	private int getClosestPP(int node, Game game) {
		int closest = (int)Double.POSITIVE_INFINITY;
		if(game.getActivePowerPillsIndices().length == 0) {
			closest = -1;
		} else {
			for(int pill : game.getActivePowerPillsIndices()) {
				if(game.getDistance(node, pill, DM.PATH) < closest) {
					closest = pill;
				}
			}
		}
		return closest;
	}
}
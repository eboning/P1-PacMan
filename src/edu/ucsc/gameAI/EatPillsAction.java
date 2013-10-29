package edu.ucsc.gameAI;
import java.util.ArrayList;

import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class EatPillsAction implements IAction {
	Game game;

	public EatPillsAction(Game game) {
		super();
		this.game = game;
	}
	
	@Override
	public void doAction() {
	}

	@Override
	public MOVE getMove() {
		System.out.println("Currently Eatin Pills");
		MOVE move;

		//Code ripped straight form StarterPacMan
		int[] pills=game.getPillIndices();
		int[] powerPills=game.getPowerPillIndices();
		int current = game.getPacmanCurrentNodeIndex();
		
		ArrayList<Integer> targets=new ArrayList<Integer>();
		
		for(int i=0;i<pills.length;i++)					//check which pills are available			
			if(game.isPillStillAvailable(i))
				targets.add(pills[i]);
		
		for(int i=0;i<powerPills.length;i++)			//check with power pills are available
			if(game.isPowerPillStillAvailable(i))
				targets.add(powerPills[i]);				
		
		int[] targetsArray=new int[targets.size()];		//convert from ArrayList to array
		
		for(int i=0;i<targetsArray.length;i++)
			targetsArray[i]=targets.get(i);
		
		//return the next direction once the closest target has been identified
		move = game.getNextMoveTowardsTarget(current,game.getClosestNodeIndexFromNodeIndex(current,targetsArray,DM.PATH),DM.PATH);

		return move;
	}

}

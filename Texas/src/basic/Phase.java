package basic;
/**
 * сно╥╫в╤н
 * @author Plonk
 *
 */
public enum Phase {
	PREFLOP, FLOP, TURN, RIVER, NULL;
	
	public static  Phase params(String str) {
		if(str.equals(PREFLOP.name()))
			return PREFLOP;
		else if(str.equals(FLOP.name()))
			return FLOP;
		else if(str.equals(TURN.name())) 
			return TURN;
		else if(str.equals(RIVER.name()))
			return RIVER;
		else
			return NULL; 
}
}
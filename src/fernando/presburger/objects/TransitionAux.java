package fernando.presburger.objects;

import java.util.List;
import java.util.Vector;

/**
 * This is a helper class that helps to create transitions to sets of states used in NFA to DFA algorithm
 * 
 * @author Fernando Hernandez
 *
 */
public class TransitionAux {
	private Vector<Integer> symbol;
	private List<Integer> destState;
	private List<Integer> orgState;
	
	/**
	 * Class constructor
	 * 
	 * @param orgState
	 * @param destState
	 * @param symbol
	 */
	public TransitionAux(List<Integer> orgState, List<Integer> destState, Vector<Integer> symbol){
		this.orgState = orgState;
		this.destState = destState;
		this.symbol = symbol;
	}
	
	/**
	 * Symbol getter
	 * @return
	 */
	public Vector<Integer> getSymbol(){
		return this.symbol;
	}
	
	/**
	 * Destination state getter
	 * @return
	 */
	public List<Integer> getDestState(){
		return this.destState;
	}
	
	/**
	 * Origin state getter
	 * @return
	 */
	public List<Integer> getOrgState(){
		return this.orgState;
	}
	
	/**
	 * To string implementation
	 */
	public String toString(){
		
		return "("+orgState+","+destState+","+symbol.toString()+")";
	}

	/**
	 * Simple string implementation for the current label, returning only 1's an 0's
	 * 
	 * @return
	 */
	public String getSymbolSimpleString(){
		String simpleSymbol = "";
		
		//we return them in reverse order because variables come from a pipe
		for(int i=symbol.size()-1; i >= 0; i--){
			simpleSymbol += symbol.get(i);
		}
		
		//System.out.println(simpleSymbol);
		return simpleSymbol;
	}

}

package fernando.presburger.objects;

import java.util.List;
import java.util.Vector;

import fernando.presburger.utils.Logger;

/**
 * This object represent each transition on an Automaton object specified by origin(current) state, 
 * destination state and the label(symbol) that leads to it.
 * 
 * @author Fernando Hernandez
 *
 */
public class Transition implements Comparable<Transition>{//Comparator<Transition> {
	private Vector<Integer> symbol;
	private int destState;
	private int orgState;
	
	/**
	 * Class constructor
	 * @param orgState Origin state
	 * @param destState destination state
	 * @param symbol label
	 */
	public Transition(int orgState, int destState, Vector<Integer> symbol){
		this.orgState = orgState;
		this.destState = destState;
		this.symbol = symbol;
	}
	
	/**
	 * Symbol getter
	 * @return label for this transition
	 */
	public List<Integer> getSymbol(){
		return this.symbol;
	}
	
	/**
	 * Destination state getter
	 * @return destination state
	 */
	public int getDestState(){
		return this.destState;
	}
	
	/**
	 * Origin state getter
	 * @return Origin state
	 */
	public int getOrgState(){
		return this.orgState;
	}
	
	/**
	 * toString() implementation for this object
	 * 
	 * @return the string describing this object
	 */
	public String toString(){
		
		return "("+orgState+","+destState+","+symbol.toString()+")";
	}

	/**
	 * This method returns a string representing the current symbol just with 1's or 0's
	 * 
	 * @return string containing just 0's or 1's for the current label
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

	/**
	 * This method is used for sorting an array of transitions. Used in the toDotty method in the
	 * Automaton class
	 */
	@Override
	public int compareTo(Transition o) {
		
		//we compare only orgStates first
		if(this.getOrgState() > o.getOrgState())
			return 1;
		else if(this.getOrgState() < o.getOrgState())
			return -1;
		else if(this.getDestState() > o.getDestState())//both orgStates are the same then we compare destStates
			return 1;
		else if(this.getDestState() < o.getDestState())
			return -1;
		else //both org and dest states are the same so we compare the symbol
			return this.getSymbolSimpleString().compareTo(o.getSymbolSimpleString());
		
	}
	
	/**
	 * This method overrides the equals method for this object. It helps on the minimization algorithm.
	 * 
	 */
	@Override
	public boolean equals(Object o){
		Transition t = (Transition) o;
		//Logger.printDEBUG("\tThis Transition: " + this + " the other: " + o);
		if(this.getDestState() == t.getDestState() && this.getOrgState() == t.getOrgState()	&& this.symbol.equals(t.getSymbol()))
			return true;
		else
		return false;
	}
}

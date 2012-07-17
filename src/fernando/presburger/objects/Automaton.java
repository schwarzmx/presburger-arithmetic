package fernando.presburger.objects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.text.html.InlineView;

import org.omg.CORBA.ValueBaseHolder;

import fernando.presburger.utils.Logger;
import fernando.presburger.utils.Util;

/**
 * This class represents an Automaton object, it can be a DFA or an NFA depending on the description given by
 * it's states/transitions/alphabet
 * 
 * @author Fernando Hernandez
 *
 */
public class Automaton {
	private List<Integer> states;
	//private char[] alphabet;
	private List<Transition> transitions;
	private String initialState;
	private List<Integer> finalStates;
	private List<String> variables;
	
	/**
	 * Class constructor
	 * 
	 * @param states
	 * @param variables
	 * @param transitions
	 * @param initialState
	 * @param finalStates
	 */
	public Automaton(List<Integer> states,
					List<String> variables,
					List<Transition> transitions,
					String initialState,
					List<Integer> finalStates){

		this.states = states;
		//this.alphabet = alphabet;
		this.variables = variables;
		this.transitions = transitions;
		this.initialState = initialState;
		this.finalStates = finalStates;
		
	}
	
	/**
	 * Class constructor for default variables (used for testing)
	 * 
	 * @param states
	 * @param transitions
	 * @param initialState
	 * @param finalStates
	 */
	public Automaton(List<Integer> states,
			List<Transition> transitions,
			String initialState,
			List<Integer> finalStates){

		this.states = states;
		String[] vars = {"x","y"}; //default variables
		this.variables = Arrays.asList(vars);
		this.transitions = transitions;
		this.initialState = initialState;
		this.finalStates = finalStates;
		
	}
	
	/**
	 * Initial state getter
	 * @return initial state
	 */
	public String getInitialState(){
		return this.initialState;
	}
	
	/**
	 * List of states getter
	 * @return list of states
	 */
	public List<Integer> getStates(){
		return this.states;
	}
	
	/**
	 * Transitions getter
	 * @return list of transition objects
	 */
	public List<Transition> getTransitions(){
		return this.transitions;
	}
	
	/**
	 * Final states getter
	 * @return list of final states
	 */
	public List<Integer> getFinalStates(){
		return this.finalStates;
	}
	
	/**
	 * Variables getter
	 * @return list of variables for this automaton
	 */
	public List<String> getVariables(){
		return this.variables;
	}
	
	/**
	 * This method is in charge of plotting the automaton in Dotty format
	 * 
	 * @return a String containing the Dotty description of the automaton
	 */
	public String toDotty(){
		String dotty = "digraph G {\n";
		Collections.sort(transitions);
		Collections.sort(states);
		Collections.sort(finalStates);
		
		//System.out.println(transitions);
		//create list of edges
		int currentDestState = transitions.get(0).getDestState();
		for(int state:states){
			dotty += state + " -> " + currentDestState + " [label=\"";
			
			for(Transition transition:transitions){
				if(transition.getOrgState() > state) {
					currentDestState = transition.getDestState(); //update next currentDestState
					dotty +="\"];\n"; //finalize last edge
					break; //optimize run
				}
				
				if(transition.getOrgState() == state)
				{
					if(currentDestState == transition.getDestState()){
						dotty += transition.getSymbolSimpleString() + " "; //just add another symbol
					}
					else{
						currentDestState = transition.getDestState();
						dotty += "\"];\n"+transition.getOrgState() + " -> " + currentDestState + " [label=\"" + transition.getSymbolSimpleString() +" ";
					}
				}
			}
		}
		dotty += "\"];\n"; //close last edge
		
		//create list of final states
		for(int state:finalStates){
			dotty += state + "[peripheries=2];\n";
		}
		
		//set initial state
		dotty += initialState + "[shape=diamond];\n}\n";
		
		// TODO: create list of free variables
		if(this.variables.isEmpty()){
			//no variables
			if(finalStates.isEmpty()){
				//contradiction
				dotty += "false";
			}
			else
			{
				dotty += "true";
			}
		}
		else{
			for(String var: this.variables){
				dotty += var;
			}
		}
		
		
		return dotty;
	}
	
	/**
	 * This method is in charge of expanding the language accepted by this automaton whenever it is necessary in the union algorithm.
	 * 
	 * @param newVariables the new list of variables for this automaton.
	 */
	public void expandLanguage(List<String> newVariables){
		List<String> currentVariables = this.variables;
		List<Transition> newTransitions = new Vector<Transition>();
		
		int symbolLength =newVariables.size();
		int totalSymbols = (int)Math.pow(2, symbolLength);
		for(int i= 0 ; i < totalSymbols; i++){ //iterate over symbols
			//create current symbol
			String binaryString = Integer.toBinaryString(i);
			Vector<Integer> newSymbol = Util.charArrayToVector(binaryString.toCharArray(), symbolLength);
			
			boolean includeSymbol;// = true;
			for(Transition transition:this.transitions){ //iterate over transitions, try to match each transition with the multiple new symbols
				includeSymbol = true;
				List<Integer> currentTransitionSymbol = transition.getSymbol();
				
				for(int j=0; j<newSymbol.size(); j++){ //iterate over letters in the symbol

					try{
						//Logger.printDEBUG("currentVariables: " + currentVariables + " newVariables: " + newVariables + " index: "+ j + " newSymbol: " + newSymbol + " currentTransition: " + transition);
						if(!(newSymbol.get(j) == currentTransitionSymbol.get(currentVariables.indexOf(newVariables.get(j))))) 
						{
							//if the digit for the variable doesn't match then we're not interested in this symbol
							includeSymbol = false;
							break;
						}
					}catch(IndexOutOfBoundsException e){
						//the current variable doesn't exist in the currentSymbol
						//Logger.printDEBUG("\tdidnt find variable: " + newVariables.get(j));
						continue; //continue with next digit
					}
				}
				
				if(includeSymbol){
					//Logger.printDEBUG("\tincluding symbol" + newSymbol);
					newTransitions.add(new Transition(transition.getOrgState(), transition.getDestState(), newSymbol));
				}
			}
			
		}
		
		this.transitions = newTransitions;
		
		
	}
}

package fernando.presburger.engine;

import java.util.List;
import java.util.Vector;

import org.antlr.runtime.CharStream;

import fernando.presburger.objects.AtomicFormula;
import fernando.presburger.objects.Automaton;
import fernando.presburger.objects.BinaryOperatorSubformula;
import fernando.presburger.objects.Formula;
import fernando.presburger.objects.QuantifierSubformula;
import fernando.presburger.objects.Transition;
import fernando.presburger.objects.TransitionAux;
import fernando.presburger.objects.UnaryOperatorSubformula;
import fernando.presburger.utils.Logger;
import fernando.presburger.utils.Util;

/**
 * This class is in charge of performing the several operations on the formula tree.
 * It contains several algorithms that allow this operations.
 * 
 * @author Fernando Hernandez
 *
 */
public class PresburgerEngine {
	//private static final char[] alphabet = {'0','1'};
	
	private int debugCounter;
	private CharStream input;
	private FormulaFactory formulaFactory;
	
	/**
	 * Class constructor
	 * @param input the charstream to parse the formula in FormulaFactory
	 * @throws Exception
	 */
	public PresburgerEngine(CharStream input) throws Exception{
		this.formulaFactory = new FormulaFactory();
		this.input = input;
		this.debugCounter = 0;
	}

	/**
	 * main method that is called from Main class in order to process formula. At the end it return a String
	 * containing the Dotty description of the final automaton.
	 * 
	 * @return a String containing the dotty of the final automaton
	 * @throws Exception
	 */
	public String processFormula() throws Exception {
		Formula mainFormula = formulaFactory.buildFormulaList(input);
		Automaton finalAutomaton = processFormula(mainFormula);
		Logger.printDEBUG("Variables: " + finalAutomaton.getVariables());
		String dotty = finalAutomaton.toDotty();
		Logger.printINFO(dotty);
		return dotty;
	}
	
	/**
	 * This is a recursive method that calls the several algorithms depending on which algorithm is found.
	 * 
	 * @param formula this is an element from the tree
	 * @return an automaton that represents the solution space for the current subtree
	 * @throws Exception
	 */
	public Automaton processFormula(Formula formula) throws Exception{
		Logger.printDEBUG("debugCounter: " + (++debugCounter));
		int formulaType = formula.getType();
		if(formulaType == Formula.ATOMIC_FORMULA){
			//limit case
			Logger.printDEBUG(" found ATOMIC_FORMULA");
			return presburgerToAutomaton((AtomicFormula) formula);
			
		}
		else{
			//do recursive stuff
			if(formulaType == Formula.QUANTIFIER_FORMULA){
				Logger.printDEBUG(" found QUANTIFIER_FORMULA");
				//it's an existence so process subformula and return projection of it
				return projection(
						processFormula(
								((QuantifierSubformula)formula).getSubFormula()
							), 
						((QuantifierSubformula)formula).getQuantifier().getVariable()
						);
			}
			else if(formulaType == Formula.UNARY_OPERATOR_SUBFORMULA){
				Logger.printDEBUG(" found UNARY_OPERATOR_SUBFORMULA");
				//it's a NEG so process subformula and return complement of it
				return complement(processFormula(((UnaryOperatorSubformula)formula).getSubFormula()));
			}
			else{
				//it's an OR (BinaryOperatorSubformula)
				Logger.printDEBUG(" found BINARY_OPERATOR_SUBFORMULA");
				List<Formula> subformulas = ((BinaryOperatorSubformula)formula).getSubformulas();
				return union(
							processFormula(subformulas.get(0)), 
							processFormula(subformulas.get(1))
						);
			}
		}
	}
	
	/**
	 * This method is in charge of performing the algorithm of getting the solution space automaton
	 * for a given AtomicFormula
	 * 
	 * @param formula AtomicFormula object
	 * @return the automaton for the solution space of the current atomic formula
	 * @throws Exception
	 */
	private Automaton presburgerToAutomaton(AtomicFormula formula) throws Exception{
		Logger.printDEBUG("presburgerToAutomaton - processing atomic formula...");
			int currentState;
			Vector<Integer> statesW = new Vector<Integer>();
			Vector<Integer> statesQ = new Vector<Integer>();
			Vector<Integer> statesF = new Vector<Integer>();
			Vector<Transition> transitions = new Vector<Transition>();
			Automaton automaton = null;
			
			//set initial values for algorithm
			currentState = formula.getConstant();
			statesW.add(currentState);
			
			int j;
			while(!statesW.isEmpty())
			{
				statesW.remove(statesW.indexOf(currentState));
				
				if(!statesQ.contains(currentState))
					statesQ.add(currentState);

				
				if(currentState>=0)
					statesF.add(currentState);

				
				//calculate j
				int symbolLength = formula.getVariables().size();
				int totalSymbols = (int) Math.pow(2,symbolLength);
				for(int i=0; i < totalSymbols; i++)
				{
					//create current symbol
					String binaryString = Integer.toBinaryString(i);
					Vector<Integer> symbol = Util.charArrayToVector(binaryString.toCharArray(), symbolLength);
					
					//calculate j
					int dotProd = (int) Util.dotProduct(symbol, (Vector<Integer>)formula.getCoefficients());
					double realJ = (currentState-dotProd)/2.0;
					realJ = Math.floor(realJ);
					j = (int) realJ;
					
					//if state-j is not in statesQ then add it to statesW
					if(!statesQ.contains(j))
					{
						if(!statesW.contains(j)) //only add it if it's not there already
							statesW.add(j);
					}
										
					//add transition (state-k,symbol,state-j) to transitions
					transitions.add(new Transition(currentState, j, symbol));
					
				}
				
				if(!statesW.isEmpty())
					currentState = statesW.get(0);
				
				
				
			}
			
			//add automaton to the list
			automaton = new Automaton(statesQ, formula.getVariables(), transitions, formula.getConstant() + "", statesF);
			
		return automaton;
	}
	
	/**
	 * This method performs the projection of the given automaton on the specified variable.
	 * 
	 * @param automaton the result automaton after projecting on the given variable
	 * @param variable the variable which we want to project on
	 * @return
	 */
	public Automaton projection(Automaton automaton, String variable){
		Logger.printDEBUG("projection on "+variable+"...");
		int var = automaton.getVariables().indexOf(variable); //determine which variable to eliminate
		List<String> newVars = automaton.getVariables();
		newVars.remove(var);
		
		//initialize variables
		List<Integer> statesQ = automaton.getStates();
		List<Integer> statesF = automaton.getFinalStates();
		int initialState = Integer.parseInt(automaton.getInitialState());
		List<Transition> transitions = new Vector<Transition>();
		List<Transition> oldTransitions = automaton.getTransitions();
		
//		Logger.printDEBUG("oldTransitions: " + oldTransitions);
//		Logger.printDEBUG("first transition's symbol: " + oldTransitions.get(3).getSymbol());
		
		//for(Transition currentTransition: oldTransitions){
		for(int i = 0; i < oldTransitions.size(); i++){
			Transition currentTransition = oldTransitions.get(i);
//			Logger.printDEBUG("\tcurrentTransition: " + currentTransition);
			List<Integer> currentSymbol = currentTransition.getSymbol();
//			Logger.printDEBUG("\tcurrentSymbol (before): " + currentSymbol);
			currentSymbol.remove(var);
//			Logger.printDEBUG("\tcurrentSymbol (after): " + currentSymbol);
//			Logger.printDEBUG("oldTransitions: " + oldTransitions);
			transitions.add(
					new Transition(
						currentTransition.getOrgState(), 
						currentTransition.getDestState(), 
						(Vector<Integer>)currentSymbol
					)
				);
		}
		
		statesF = padClosure(transitions, statesF);
		
		//Automaton dfa = new Automaton(statesQ, alphabet, transitions, initialState + "", statesF);//nfaToDfa();
		Automaton dfa = nfaToDfa(new Automaton(statesQ, newVars, transitions, initialState + "", statesF));
		
		dfa = minimization(dfa);
		Logger.printDEBUG("exit projection on "+variable+"...");
		return dfa;
	}
	
	/**
	 * Helper method for performing the pad closure for the projection algorithm.
	 * 
	 * @param transitions the transitions of the current NFA
	 * @param finalStates the final states of the current NFA
	 * @return an NFA after performing the pad closure
	 */
	private List<Integer> padClosure(List<Transition> transitions, List<Integer> finalStates){
		Logger.printINFO("\tpadClosure....");
		//create "closure" symbol, which for lsbf is zeros at the end
		Vector<Integer> symbol = Util.charArrayToVector("0".toCharArray(), transitions.get(0).getSymbol().size());
		
		List<Integer> statesW = finalStates;
		List<Integer> statesF = new Vector<Integer>();
		int currentState = !statesW.isEmpty() ? statesW.get(0) : 0;
		
		while(!statesW.isEmpty())
		{
			//Logger.printDEBUG("statesW : "+statesW);
			
			//pick q from W
			statesW.remove(statesW.indexOf(currentState));
			
			//add q to F'
			if(!statesF.contains(currentState)) statesF.add(currentState);
			
			//for all (q',#,q) in d do
			for(Transition transition:transitions){
				if(transition.getDestState() == currentState 
						&& transition.getSymbol().equals(symbol)){
					
					//if q' not in F' then add to W
					if(!statesF.contains(transition.getOrgState()))
						statesW.add(transition.getOrgState());
				}
			}
			
			if(!statesW.isEmpty())
				currentState = statesW.get(0);
		}
		
		Logger.printINFO("\texit padClosure...");
		
		return statesF;
	}
	
	/**
	 * This method performs the complement of an NFA and returns a DFA
	 * 
	 * @param automaton NFA that we want to complement
	 * @return complemented DFA for the given NFA
	 */
	public Automaton complement(Automaton automaton){
		Logger.printINFO("complement...");
		Automaton dfa = nfaToDfa(automaton);
		
		dfa = minimization(dfa);
		
		dfa = compDFA(dfa);
		Logger.printINFO("exiting complement...");
		return dfa;
	}
	
	/**
	 * This method converts an NFA to its equivalent DFA
	 * 
	 * @param automaton an NFA
	 * @return the equivalent DFA
	 */
	public Automaton nfaToDfa(Automaton automaton){
		Logger.printDEBUG("\tnfaToDfa....");
		Automaton dfa = null;
		
		//initialize sets and variables
		List<List<Integer>> newStates = new Vector<List<Integer>>();
		List<List<Integer>> newFinalStates = new Vector<List<Integer>>();
		List<TransitionAux> newTransitions = new Vector<TransitionAux>();
		String initialState = automaton.getInitialState();
		List<List<Integer>> statesW = new Vector<List<Integer>>();
		List<Integer> currentState = new Vector<Integer>();
		currentState.add(Integer.parseInt(initialState));
		statesW.add(currentState);

		while(!statesW.isEmpty())
		{
			//Logger.printDEBUG("statesW : "+statesW);
			statesW.remove(statesW.indexOf(currentState));
			
			if(!newStates.contains(currentState))
				newStates.add(currentState);
			
			//check Q' ^ F <> 0
			//if(automaton.getFinalStates().contains(currentState)){
			if(Util.checkIntersect(currentState, automaton.getFinalStates())){
				newFinalStates.add(currentState);
			}
			
			//get the length of the first symbol of the first transitions (all of them are the same length)
			int symbolLength = automaton.getTransitions().get(0).getSymbolSimpleString().length();
			int totalSymbols = (int) Math.pow(2,symbolLength);
			for(int i=0; i < totalSymbols; i++)
			{
				List<Integer> statesQQ = new Vector<Integer>();
				//create current symbol
				String binaryString = Integer.toBinaryString(i);
				Vector<Integer> symbol = Util.charArrayToVector(binaryString.toCharArray(), symbolLength);
				
				//get Q'' set: Q'' <- d(Q',a)
				for(Transition transition: automaton.getTransitions()){
					//add all the states that are reachable from the current state(s) with the current symbol
					for(int state:currentState){
						
						if(transition.getSymbol().equals(symbol) && state == transition.getOrgState())
						{
							if(!statesQQ.contains(transition.getDestState())) statesQQ.add(transition.getDestState());
						}
					}
					
				}
				
				// if Q'' not in newStates then add to statesW
				if(!newStates.contains(statesQQ)){
					if(!statesW.contains(statesQQ))//just add it if it's not there already 
						statesW.add(statesQQ);
				}
				
				//add (Q', a, Q'') to transitions
				newTransitions.add(new TransitionAux(currentState, statesQQ, symbol));
			}
			
			//get next state
			if(!statesW.isEmpty())
				currentState = statesW.get(0);
			
		}
		
		/* 
		 * simplify states and transitions, we don't want sets of states for each state/transition
		 * so we replace them with new states that start from 0 and on.
		 * For that we use the indexes in the array of states
		 */
		List<Integer> simplifiedStates = new Vector<Integer>();
		List<Transition> simplifiedTransitions = new Vector<Transition>();
		List<Integer> simplifiedFinalStates = new Vector<Integer>();
		for(List<Integer> state:newStates){
			simplifiedStates.add( newStates.indexOf(state) );
		}
		for(List<Integer> state:newFinalStates){
			simplifiedFinalStates.add( newStates.indexOf(state) );
		}
		for(TransitionAux complexTransition:newTransitions){
			simplifiedTransitions.add(
					new Transition(
							newStates.indexOf(complexTransition.getOrgState()), 
							newStates.indexOf(complexTransition.getDestState()), 
							complexTransition.getSymbol()
							)
					);
		}
		
		//return new DFA
		dfa = new Automaton(
					simplifiedStates, 
					automaton.getVariables(), 
					simplifiedTransitions, 
					"0", 
					simplifiedFinalStates
				);
		
		Logger.printINFO("\texiting nfaToDfa...");
		return dfa;
	}
	
	/**
	 * This method complements a DFA and returns the complemented DFA
	 * 
	 * @param automaton DFA to complement
	 * @return the complemented DFA
	 */
	private Automaton compDFA(Automaton automaton){
		//we assume that we receive a DFA
		
		Logger.printDEBUG("\tcompDFA...");
		Automaton newAutomaton = null;
		
		List<Integer> states = automaton.getStates();
		List<Transition> transitions = automaton.getTransitions();
		List<Integer> oldFinalStates = automaton.getFinalStates();
		List<Integer> newFinalStates = new Vector<Integer>();
		String initialState = automaton.getInitialState();
		
		for(Integer state:states){
			if(!oldFinalStates.contains(state)){
				newFinalStates.add(state);
			}
		}
		
		newAutomaton = new Automaton(states, automaton.getVariables(), transitions, initialState, newFinalStates);
		Logger.printINFO("\texiting compDFA..");
		return newAutomaton;
	}
	
	
	/**
	 * This method performs the union DFA algorithm. It assumes that it receives two DFA's.
	 * 
	 * @param a1 the first DFA
	 * @param a2 the second DFA
	 * @return the union of the two given DFA's
	 */
	public Automaton union(Automaton a1, Automaton a2){
		Logger.printINFO("entering union..");
		Automaton  dfa = null;
		//determine possible language expansion
		List<String> newVars = a1.getVariables();
		if(a1.getVariables().equals(a2.getVariables())){
			//no harm just take one of the var list
		}
		else{
			//expand both languages according to new list of variables
			
			//calculate new list of variables
			for(String var:a2.getVariables()){
				//if var not in a1's list, then add it to the end
				if(!newVars.contains(var)){
					newVars.add(var);
				}
			}
			
			//do expansion
			a1.expandLanguage(newVars);
			a2.expandLanguage(newVars);
		}
		
		//start union
		List<List<Integer>> statesQ = new Vector<List<Integer>>();
		List<List<Integer>> statesW = new Vector<List<Integer>>();
		List<List<Integer>> statesF = new Vector<List<Integer>>();
		List<Integer> currentState = new Vector<Integer>();
		List<TransitionAux> complexTransitions = new Vector<TransitionAux>();
		
		//initialize variables
		currentState.add(Integer.parseInt(a1.getInitialState()));
		currentState.add(Integer.parseInt(a2.getInitialState()));
		statesW.add(currentState); //set initial state
		
		while(!statesW.isEmpty())
		{
			//pick [q1,q2] from W
			statesW.remove(statesW.indexOf(currentState));
			
			//add [q1,q2] to Q
			if(!statesQ.contains(currentState))
				statesQ.add(currentState);
			
			//do binary operation OR and then add currentState to F if it's true
			if(a1.getFinalStates().contains(currentState.get(0))
					|| a2.getFinalStates().contains(currentState.get(1))){
				statesF.add(currentState);
			}
			
			//get the length of the first symbol of the first transitions of a1 (all of them are the same length)
			int symbolLength = a1.getTransitions().get(0).getSymbolSimpleString().length();
			int totalSymbols = (int) Math.pow(2,symbolLength);
			for(int i=0; i < totalSymbols; i++)
			{
				List<Integer> statesQQ = new Vector<Integer>();
				//create current symbol
				String binaryString = Integer.toBinaryString(i);
				Vector<Integer> symbol = Util.charArrayToVector(binaryString.toCharArray(), symbolLength);
				
				//get q1' and q2' from their respective transition formula for d2(q1, a) and d2(q2, a)
				//find the transition for d(currentState(0|1), symbol) in each transition formula
				for(Transition transition:a1.getTransitions()){
					if(transition.getOrgState() == currentState.get(0) && transition.getSymbol().equals(symbol))
					{
						statesQQ.add(transition.getDestState());
						break; //there's only one for DFA so we end looking for transitions for this symbol
					}
				}
				for(Transition transition:a2.getTransitions()){ //do same for seconf transition formula
					if(transition.getOrgState() == currentState.get(1) && transition.getSymbol().equals(symbol))
					{
						statesQQ.add(transition.getDestState());
						break;
					}
				}
				
				
				// if [q1',q2'] not in Q then add to W
				if(!statesQ.contains(statesQQ)){
					if(!statesW.contains(statesQQ))//just add it if it's not there already 
						statesW.add(statesQQ);
				}
				
				//add ([q1,q2], a, [q1',q2']) to transitions
				complexTransitions.add(new TransitionAux(currentState, statesQQ, symbol));
			}
			
			//pick next state
			if(!statesW.isEmpty())
				currentState = statesW.get(0);
			
		}
		
		/* 
		 * simplify states and transitions, we don't want sets of states for each state/transition
		 * so we replace them with new states that start from 0 and on.
		 * For that we use the indexes in the array of states
		 */
		List<Integer> simplifiedStates = new Vector<Integer>();
		List<Transition> simplifiedTransitions = new Vector<Transition>();
		List<Integer> simplifiedFinalStates = new Vector<Integer>();
		for(List<Integer> state:statesQ){
			simplifiedStates.add( statesQ.indexOf(state) );
		}
		for(List<Integer> state:statesF){
			simplifiedFinalStates.add( statesQ.indexOf(state) );
		}
		for(TransitionAux complexTransition:complexTransitions){
			simplifiedTransitions.add(
					new Transition(
							statesQ.indexOf(complexTransition.getOrgState()), 
							statesQ.indexOf(complexTransition.getDestState()), 
							complexTransition.getSymbol()
							)
					);
		}
		
		//return new DFA
		dfa = new Automaton(
					simplifiedStates, 
					newVars, 
					simplifiedTransitions, 
					"0", 
					simplifiedFinalStates
				);
		
		dfa = minimization(dfa);
		
		Logger.printINFO("Exiting unioin...");
		
		return dfa;
	}
	
	/**
	 * This method performs the minimization algorithm for a given DFA. It returns the canonical DFA.
	 * 
	 * @param automaton a DFA
	 * @return the canonical DFA
	 */
	public Automaton minimization(Automaton automaton){
		//minimize DFA
		Logger.printINFO("\tminimizing...");
		//calculate language partition
		List<List<Integer>> partitions = new Vector<List<Integer>>();
		List<Integer> statesF = automaton.getFinalStates();
		List<Integer> statesQ = automaton.getStates();
		int q0 = Integer.parseInt(automaton.getInitialState());
		
		if(statesF.isEmpty() || Util.complement(statesQ, statesF).isEmpty()){
			//this that either there are no final states or that all states are final
			partitions.add(statesQ);
		}
		else{
			partitions.add(statesF);
			partitions.add(Util.complement(statesQ, statesF));
		}
		
		boolean unstable = true;
		int lastDestBlock = -1;
		Vector<Integer> lastSymbol = null;
		while(unstable){
			//pick B,B' from P and a from alphabet such that (a,B') splits B
			
			for(int k = 0; k<partitions.size(); k++){
				//assume that after this run it is stable
				unstable = false;
				
//				Logger.printDEBUG("***partitions: " + partitions + " cycle: "+ k);
				List<Integer> block = partitions.get(k);
				if(block.size() > 1){
					List<Integer> newBlock = new Vector<Integer>();
					int symbolLength = automaton.getVariables().size();
					int totalSymbols = (int) Math.pow(2,symbolLength);
					int currentDestBlock = -2;
					for(int i=0; i < totalSymbols; i++)
					{
						//create current symbol
						String binaryString = Integer.toBinaryString(i);
						Vector<Integer> symbol = Util.charArrayToVector(binaryString.toCharArray(), symbolLength);
						
						for(Transition transition:automaton.getTransitions()){
							
							if(transition.getSymbol().equals(symbol) && block.contains(transition.getOrgState())){
								//only process when symbol is the same and the transitions origin state is in the current block
//								Logger.printDEBUG("\tblock: " + block 
//										+ " transition.getOrgState(): " + transition.getOrgState() 
//										+ " transition.getDestState(): " + transition.getDestState());
								boolean notInBlock = false;
								
									
								//look for destination block
								if(block.contains(transition.getDestState())){
//									Logger.printDEBUG("\t\tblock(1): "+ block);
									currentDestBlock = partitions.indexOf(block);
									notInBlock = false;
								}
								else{
									for(List<Integer> b:partitions){
										
										if(b.contains(transition.getDestState())){
//											Logger.printDEBUG("\t\tblock(1): "+ b);
											currentDestBlock = partitions.indexOf(b);
											notInBlock = false;
											break;
										}
										else
										{
											notInBlock = true;
										}
									}
								}
								if(block.indexOf(transition.getOrgState())==0){
									lastSymbol = symbol;
									lastDestBlock = currentDestBlock;
//									Logger.printDEBUG("set up lastSymbol: " + lastSymbol + " currentDestBlock: "+ currentDestBlock + " state: " + transition.getOrgState());
								}
								
//								Logger.printDEBUG("\tlastDestBlock: "+ lastDestBlock + " currentDestBlock: " + currentDestBlock + " currentSymbol: " + symbol + " lastSymbol: " + lastSymbol + "notInBlock: " + notInBlock);
								
								if(lastDestBlock != -1 
										&& lastDestBlock != currentDestBlock 
										&& symbol.equals(lastSymbol) 
										//&& block.indexOf(transition.getOrgState()) != 0
										&& !notInBlock){
//									Logger.printDEBUG("\t****found different transition!");
									//we detected a transition that has a different dest block than the previous
									//create a new block and add all the states from this block that point to a different 
									//					block than the first state of the block with the current symbol
									newBlock.add(transition.getOrgState());
//									Logger.printDEBUG("\tnewBlock: " + newBlock);

									//if we had to add a block it means that we have to check again
									unstable = true;
								}
							}		
						}
						if(unstable){
//							Logger.printDEBUG("\tpartitions before removeblock(): " + partitions);
//							Logger.printDEBUG("\tblock(2): " + block);
							
							List<Integer> toAdd = (List<Integer>)((Vector)newBlock).clone();
							List<Integer> theRest = Util.complement(block, newBlock);
							partitions.add(toAdd);
							partitions.add(theRest);
							boolean removed = partitions.remove(block);
							
							k--;
							
//							Logger.printDEBUG("\tpartitions.removeblock(): " + removed);
//							Logger.printDEBUG("\tpartitions after removeblock(): " + partitions);
							break;
						}
					}
				}
				
			}
		}
		
		//quotient
		List<Transition> newTransitions = new Vector<Transition>();
		for(Transition transition:automaton.getTransitions()){
			int destState = -1;
			int orgState = -1;
			for(List<Integer> b:partitions){
				if(b.contains(transition.getDestState())){
					destState = partitions.indexOf(b);
				}
				if(b.contains(transition.getOrgState())){
					orgState = partitions.indexOf(b);
				}
			}
			Transition newTransition = new Transition(orgState, destState, (Vector<Integer>)transition.getSymbol());
//			Logger.printDEBUG("newTransitions: " + newTransitions + " newTransition: " + newTransition);
			if(!newTransitions.contains(newTransition))
				newTransitions.add(newTransition);
		}
		
		List<Integer> states = new Vector<Integer>();
		String initialState = "";
		List<Integer> finalStates = new Vector<Integer>();
		for(List<Integer> b:partitions){
			int p = partitions.indexOf(b);
			
			states.add(p);
			
			if(b.contains(q0))
				initialState = "" + p;
			
			for(int f:statesF){
				if(b.contains(f)){
					finalStates.add(p);
				}
			}
		}
		
		
		Automaton a = new Automaton(states, automaton.getVariables(), newTransitions, initialState, finalStates);
		
		Logger.printINFO("\texit minimization...");
		return a;
	}
}

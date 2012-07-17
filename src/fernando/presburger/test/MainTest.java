package fernando.presburger.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.junit.Ignore;
import org.junit.Test;

import fernando.presburger.engine.FormulaFactory;
import fernando.presburger.engine.PresburgerEngine;
import fernando.presburger.main.Main;
import fernando.presburger.objects.Automaton;
import fernando.presburger.objects.Transition;
import fernando.presburger.utils.Logger;
import static org.junit.Assert.*;

public class MainTest {
	
	/******************************************** Tests for several formulas  ********************************************/
	
	@Test
	public void testMain() throws Exception{
		String msg = "/*********************************************/";
		msg += "\nRUNNING MAIN TEST!";
		System.out.println(msg);
		
		String[] args = {"res/formula0.txt", "0"};
		Main.main(args);
		
		assertTrue(true);
	}

	@Test
	@Ignore
	public void testBuildFormulaOnFormulaFactory() throws Exception{
		String msg = "/*********************************************/";
		msg += "\nRUNNING FORMULA FACTORY TEST!";
		System.out.println(msg);
		
		CharStream input = new ANTLRFileStream("res/simplestFormula.txt");
		
		FormulaFactory formulaFactory = new FormulaFactory();
		formulaFactory.buildFormulaList(input);
		
		System.out.println("\n\n\n");
		assertTrue(true);
		
	}
	
	@Test
	@Ignore
	public void testSimplestFormula() throws Exception{
		String msg = "/*********************************************/";
		msg += "\nRUNNING SIMPLEST FORMULA TEST!";
		System.out.println(msg);
		String[] args = {"res/simplestFormula3.txt","0"};
		Main.main(args);
		
		assertTrue(true);
	}
	
	@Test
	@Ignore
	public void testSimplestFormula2() throws Exception{
		String msg = "/*********************************************/";
		msg += "\nRUNNING SIMPLEST FORMULA 2 TEST!";
		System.out.println(msg);
		String[] args = {"res/simplestFormula2.txt"};
		Main.main(args);
		
		assertTrue(true);
	}
	
	
	/******************************************** Tests for algorithms ********************************************/
	
	@Test
	@Ignore
	public void testProjectionAlgorithm() throws Exception{
		//Use automaton from collatz function to project
		
		//initialize Logger
		Logger.initialize(Logger.DEBUG);
		
		//create symbols
		Integer[] a00 = {0,0};
		Vector<Integer> s00 = new Vector<Integer>();
		s00.addAll(Arrays.asList(a00));
		Integer[] a01 = {0,1};
		Vector<Integer> s01 = new Vector<Integer>();
		s01.addAll(Arrays.asList(a01));
		Integer[] a10 = {1,0};
		Vector<Integer> s10 = new Vector<Integer>();
		s10.addAll(Arrays.asList(a10));
		Integer[] a11 = {1,1};
		Vector<Integer> s11 = new Vector<Integer>();
		s11.addAll(Arrays.asList(a11));
		
		//create transitions
		List<Transition> transitions = new Vector<Transition>();
		
		transitions.add(new Transition(1, 2, (Vector<Integer>) s00.clone()));
		transitions.add(new Transition(1, 4, (Vector<Integer>)s10.clone()));
		transitions.add(new Transition(1, 3, (Vector<Integer>)s01.clone()));
		transitions.add(new Transition(2, 2, (Vector<Integer>)s00.clone()));
		transitions.add(new Transition(2, 3, (Vector<Integer>)s01.clone()));
		transitions.add(new Transition(3, 2, (Vector<Integer>)s10.clone()));
		transitions.add(new Transition(3, 3, (Vector<Integer>)s11.clone()));
		transitions.add(new Transition(4, 4, (Vector<Integer>)s11.clone()));
		transitions.add(new Transition(4, 5, (Vector<Integer>)s00.clone()));
		transitions.add(new Transition(5, 4, (Vector<Integer>)s10.clone()));
		transitions.add(new Transition(5, 6, (Vector<Integer>)s01.clone()));
		transitions.add(new Transition(6, 6, (Vector<Integer>)s00.clone()));
		transitions.add(new Transition(6, 5, (Vector<Integer>)s11.clone()));
		
		System.out.println("transitions: " + transitions);
		
		//create states
		Integer[] aStates = {1,2,3,4,5,6};
		List<Integer> states = new Vector<Integer>();
		states.addAll(Arrays.asList(aStates));
				
		//create initial state
		String initialState = "1";
		
		//create final states
		Integer[] aFinalStates = {1,2,6};
		List<Integer> finalStates = new Vector<Integer>();
		finalStates.addAll(Arrays.asList(aFinalStates));
		
		Automaton automaton = new Automaton(states, transitions, initialState, finalStates);
				
		PresburgerEngine pe = new PresburgerEngine(null);
		automaton = pe.projection(automaton, "x");
		
		System.out.println(automaton.toDotty());
		
		assertTrue(true);
	}
	
	@Test
	@Ignore
	public void testNFAtoDFA() throws Exception{
		//create automaton from script on page 16, replacing 'a' with '0' and 'b' with '1' 
		
		//initialize Logger
		Logger.initialize(Logger.DEBUG);
		
		//create symbols
		Integer[] a0 = {0};
		Vector<Integer> s0 = new Vector<Integer>();
		s0.addAll(Arrays.asList(a0));
		Integer[] a1 = {1};
		Vector<Integer> s1 = new Vector<Integer>();
		s1.addAll(Arrays.asList(a1));
		
		//create transitions
		List<Transition> transitions = new Vector<Transition>();
		
		transitions.add(new Transition(1, 1, (Vector<Integer>) s0.clone()));
		transitions.add(new Transition(1, 1, (Vector<Integer>) s1.clone()));
		transitions.add(new Transition(1, 2, (Vector<Integer>) s1.clone()));
		transitions.add(new Transition(2, 3, (Vector<Integer>) s0.clone()));
		transitions.add(new Transition(3, 4, (Vector<Integer>) s0.clone()));
		transitions.add(new Transition(3, 4, (Vector<Integer>) s1.clone()));
		
		System.out.println("transitions: " + transitions);
		
		//create states
		Integer[] aStates = {1,2,3,4};
		List<Integer> states = new Vector<Integer>();
		states.addAll(Arrays.asList(aStates));
		
		//create initial state
		String initialState = "1";
		
		//create final states
		Integer[] aFinalStates = {4};
		List<Integer> finalStates = new Vector<Integer>();
		finalStates.addAll(Arrays.asList(aFinalStates));
		
		List<String> variables = new Vector<String>();
		variables.add("x");
		
		Automaton automaton = new Automaton(states, variables, transitions, initialState, finalStates);
				
		PresburgerEngine pe = new PresburgerEngine(null);
		automaton = pe.nfaToDfa(automaton);
		
		System.out.println(automaton.toDotty());
		
		assertTrue(true);
	}
	
	@Test
	@Ignore
	public void testComplement() throws Exception{
		//create automaton from script on page 16, replacing 'a' with '0' and 'b' with '1' 
		
		//initialize Logger
		Logger.initialize(Logger.DEBUG);
		
		//create symbols
		Integer[] a0 = {0};
		Vector<Integer> s0 = new Vector<Integer>();
		s0.addAll(Arrays.asList(a0));
		Integer[] a1 = {1};
		Vector<Integer> s1 = new Vector<Integer>();
		s1.addAll(Arrays.asList(a1));
		
		//create transitions
		List<Transition> transitions = new Vector<Transition>();
		
		transitions.add(new Transition(1, 1, (Vector<Integer>) s0.clone()));
		transitions.add(new Transition(1, 1, (Vector<Integer>) s1.clone()));
		transitions.add(new Transition(1, 2, (Vector<Integer>) s1.clone()));
		transitions.add(new Transition(2, 3, (Vector<Integer>) s0.clone()));
		transitions.add(new Transition(3, 4, (Vector<Integer>) s0.clone()));
		transitions.add(new Transition(3, 4, (Vector<Integer>) s1.clone()));
		
		System.out.println("transitions: " + transitions);
		
		//create states
		Integer[] aStates = {1,2,3,4};
		List<Integer> states = new Vector<Integer>();
		states.addAll(Arrays.asList(aStates));
				
		//create initial state
		String initialState = "1";
		
		//create final states
		Integer[] aFinalStates = {4};
		List<Integer> finalStates = new Vector<Integer>();
		finalStates.addAll(Arrays.asList(aFinalStates));
		
		//create variable list
		List<String> variables = new Vector<String>();
		variables.add("x");
		
		Automaton automaton = new Automaton(states, variables, transitions, initialState, finalStates);
				
		PresburgerEngine pe = new PresburgerEngine(null);
		automaton = pe.complement(automaton);
		
		System.out.println(automaton.toDotty());
		
		assertTrue(true);
	}
	
	@Test
	@Ignore
	public void testUnion() throws Exception{
		//use automata from page 49
		
		//initialize Logger
		Logger.initialize(Logger.DEBUG);
		//create variables
		List<String> variables = new Vector<String>();
		variables.add("x");
		
		//create symbols
		Integer[] a0 = {0};
		Vector<Integer> s0 = new Vector<Integer>();
		s0.addAll(Arrays.asList(a0));
		Integer[] a1 = {1};
		Vector<Integer> s1 = new Vector<Integer>();
		s1.addAll(Arrays.asList(a1));
		
		//create transitions
		List<Transition> transitions1 = new Vector<Transition>();
		
		transitions1.add(new Transition(1, 1, (Vector<Integer>) s1.clone()));
		transitions1.add(new Transition(1, 2, (Vector<Integer>) s0.clone()));
		transitions1.add(new Transition(2, 1, (Vector<Integer>) s0.clone()));
		transitions1.add(new Transition(2, 2, (Vector<Integer>) s1.clone()));
		
		System.out.println("transitions1: " + transitions1);
		
		//create states
		Integer[] aStates1 = {1,2};
		List<Integer> states1 = new Vector<Integer>();
		states1.addAll(Arrays.asList(aStates1));
		
		//create initial state
		String initialState1 = "1";
		
		//create final states
		Integer[] aFinalStates1 = {1};
		List<Integer> finalStates1 = new Vector<Integer>();
		finalStates1.addAll(Arrays.asList(aFinalStates1));
		
		Automaton automaton1 = new Automaton(states1, variables, transitions1, initialState1, finalStates1);
		
		/**************************************************************************************************/
		//do same for second automaton 
		//create transitions
		List<Transition> transitions2 = new Vector<Transition>();
		
		transitions2.add(new Transition(3, 3, (Vector<Integer>) s0.clone()));
		transitions2.add(new Transition(3, 4, (Vector<Integer>) s1.clone()));
		transitions2.add(new Transition(4, 3, (Vector<Integer>) s1.clone()));
		transitions2.add(new Transition(4, 4, (Vector<Integer>) s0.clone()));
		
		System.out.println("transitions2: " + transitions2);
		
		//create states
		Integer[] aStates2 = {3,4};
		List<Integer> states2 = new Vector<Integer>();
		states2.addAll(Arrays.asList(aStates2));
		
		//create initial state
		String initialState2 = "3";
		
		//create final states
		Integer[] aFinalStates2 = {3};
		List<Integer> finalStates2 = new Vector<Integer>();
		finalStates2.addAll(Arrays.asList(aFinalStates2));
		
		Automaton automaton2 = new Automaton(states2, variables, transitions2, initialState2, finalStates2);
				
		PresburgerEngine pe = new PresburgerEngine(null);
		Automaton unionAutomaton = pe.union(automaton1, automaton2);
		
		System.out.println(unionAutomaton.toDotty());
		
		assertTrue(true);
	}
	
	@Test
	@Ignore
	public void testLanguageExpansion(){
		//creating automaton from page 112
		//initialize Logger
		Logger.initialize(Logger.DEBUG);
		
		//create symbols
		Integer[] a00 = {0,0};
		Vector<Integer> s00 = new Vector<Integer>();
		s00.addAll(Arrays.asList(a00));
		Integer[] a01 = {0,1};
		Vector<Integer> s01 = new Vector<Integer>();
		s01.addAll(Arrays.asList(a01));
		Integer[] a10 = {1,0};
		Vector<Integer> s10 = new Vector<Integer>();
		s10.addAll(Arrays.asList(a10));
		Integer[] a11 = {1,1};
		Vector<Integer> s11 = new Vector<Integer>();
		s11.addAll(Arrays.asList(a11));
		
		//create transitions
		List<Transition> transitions = new Vector<Transition>();
		
		transitions.add(new Transition(1, 1, (Vector<Integer>) s00.clone()));
		transitions.add(new Transition(1, 1, (Vector<Integer>)s10.clone()));
		transitions.add(new Transition(1, 2, (Vector<Integer>)s01.clone()));
		transitions.add(new Transition(2, 2, (Vector<Integer>)s00.clone()));
		transitions.add(new Transition(2, 2, (Vector<Integer>)s10.clone()));
		
		System.out.println("transitions: " + transitions);
		
		//create states
		Integer[] aStates = {1,2};
		List<Integer> states = new Vector<Integer>();
		states.addAll(Arrays.asList(aStates));
				
		//create initial state
		String initialState = "1";
		
		//create final states
		Integer[] aFinalStates = {2};
		List<Integer> finalStates = new Vector<Integer>();
		finalStates.addAll(Arrays.asList(aFinalStates));
				
		Automaton automaton = new Automaton(states, transitions, initialState, finalStates); //creating for default variable set
		Automaton a2 = new Automaton(states, transitions, initialState, finalStates); //creating a 2nd automaton for 2nd test
		
		//create new variables
		List<String> newVars = new Vector<String>();
		String[] aNewVars = {"x","y","z"};
		newVars.addAll(Arrays.asList(aNewVars));
		
		automaton.expandLanguage(newVars); //expand by one variable at the end
		System.out.println(automaton.toDotty());
		System.out.println(automaton.getTransitions());
		
		newVars.clear();
		
		String[] aNewVars2 = {"y","x","z"};
		newVars.addAll(Arrays.asList(aNewVars2));
		
		a2.expandLanguage(newVars); //expand by one reordering variables
		System.out.println(a2.toDotty());	
		System.out.println(a2.getTransitions());
		
		assertTrue(true);
	}
	
	@Test
	@Ignore
	public void testMinimization() throws Exception{
		//do example on page 36
		
		//initialize Logger
		Logger.initialize(Logger.DEBUG);
		//create variables
		List<String> variables = new Vector<String>();
		variables.add("x");
		
		//create symbols
		Integer[] a0 = {0};
		Vector<Integer> s0 = new Vector<Integer>();
		s0.addAll(Arrays.asList(a0));
		Integer[] a1 = {1};
		Vector<Integer> s1 = new Vector<Integer>();
		s1.addAll(Arrays.asList(a1));
		
		//create transitions
		List<Transition> transitions1 = new Vector<Transition>();
		
		transitions1.add(new Transition(1, 2, (Vector<Integer>) s0.clone()));
		transitions1.add(new Transition(1, 4, (Vector<Integer>) s1.clone()));
		transitions1.add(new Transition(2, 3, (Vector<Integer>) s0.clone()));
		transitions1.add(new Transition(2, 7, (Vector<Integer>) s1.clone()));
		transitions1.add(new Transition(3, 2, (Vector<Integer>) s0.clone()));
		transitions1.add(new Transition(3, 4, (Vector<Integer>) s1.clone()));
		transitions1.add(new Transition(4, 7, (Vector<Integer>) s0.clone()));
		transitions1.add(new Transition(4, 3, (Vector<Integer>) s1.clone()));
		transitions1.add(new Transition(5, 3, (Vector<Integer>) s0.clone()));
		transitions1.add(new Transition(5, 7, (Vector<Integer>) s1.clone()));
		transitions1.add(new Transition(6, 7, (Vector<Integer>) s0.clone()));
		transitions1.add(new Transition(6, 3, (Vector<Integer>) s1.clone()));
		transitions1.add(new Transition(7, 6, (Vector<Integer>) s0.clone()));
		transitions1.add(new Transition(7, 5, (Vector<Integer>) s1.clone()));
		
		System.out.println("transitions: " + transitions1);
		
		//create states
		Integer[] aStates = {1,2,3,4,5,6,7};
		List<Integer> states = new Vector<Integer>();
		states.addAll(Arrays.asList(aStates));
				
		//create initial state
		String initialState = "1";
		
		//create final states
		Integer[] aFinalStates = {1,3};
		List<Integer> finalStates = new Vector<Integer>();
		finalStates.addAll(Arrays.asList(aFinalStates));
		
		Automaton automaton = new Automaton(states, variables, transitions1, initialState, finalStates);
		
		PresburgerEngine pe = new PresburgerEngine(null);
		automaton = pe.minimization(automaton);
		 
		System.out.println(automaton.toDotty());
		
		assertTrue(true);
	}
	
}

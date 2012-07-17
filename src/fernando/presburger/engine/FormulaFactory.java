package fernando.presburger.engine;

import jan.presburger.PA2Lexer;
import jan.presburger.PA2Parser;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;

import fernando.presburger.objects.AtomicFormula;
import fernando.presburger.objects.BinaryOperatorSubformula;
import fernando.presburger.objects.Formula;
import fernando.presburger.objects.QuantifierSubformula;
import fernando.presburger.objects.UnaryOperatorSubformula;
import fernando.presburger.utils.Logger;

/**
 * This class is in charge of building a formula tree for later processing by PresburgerEngine class
 * 
 * @author Fernando Hernandez
 *
 */
public class FormulaFactory {
	private static int debugCounter;
	
	/**
	 * This is the method provided by Jan in order to go through the formula tree and build my own tree that then is
	 * processed in order to replace the different formula abvreviations
	 * 
	 * @param ast
	 * @return a formula element from the tree
	 */
	private Object constructFormulaTree( org.antlr.runtime.tree.CommonTree ast ) {
//		debugCounter++;
//		System.out.println("debugCounter" + debugCounter);
        int token = ast.getToken().getType();
        //formulaStack.push(ast);
        //System.out.println("pushed: " + ast.getText());
        
        switch( token ) {
            //quantifiers
            //nodes have two children
            //child 0 is the variable
            //child 1 is the subformula
        	/*
        	 * for the quantifiers I create a new subformula I add it's quantifier and the variable
        	 * related to it, then I expect to get a subformula to pass it on
        	 * 
        	 */
            case PA2Parser.ALL:
                System.out.println("all quantor");
                Formula formulaAll = new QuantifierSubformula();
                String varALL = (String) ast.getChild(0).getText(); //we expect a variable
                System.out.println("variable identifier *: " + varALL);
                ((QuantifierSubformula)formulaAll).setQuantifier(token, varALL);
                
                ((QuantifierSubformula)formulaAll).setSubformula((Formula)constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(1)));
//                debugCounter--;
//                System.out.println("debugCounter" + debugCounter);
                System.out.println("\treturning for all formula quantor..");
                return formulaAll; //pass on the formula
                
             case PA2Parser.EX:
                System.out.println("exists quantor");
                Formula formulaEX = new QuantifierSubformula();
                String varEX = ast.getChild(0).getText(); //(String) constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(0)); //we expect a variable
                System.out.println("variable identifier *: " + varEX);
                ((QuantifierSubformula)formulaEX).setQuantifier(token, varEX);
                
                ((QuantifierSubformula)formulaEX).setSubformula((Formula)constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(1)));
//                debugCounter--;
//                System.out.println("debugCounter" + debugCounter);
                System.out.println("\treturning existence formula quantor..");
                return formulaEX; //pass on the formula

            //binary logical operators
            //nodes have two children
            //child 0 is the left subformula
            //child 1 is the right subformula
                /*
                 * for this operators I create two different subformulas, both subformulas are stored in an inner list
                 * of the current BinaryOperatorSubformula
                 */
            case PA2Parser.AND:
                System.out.println("and");
	        	Formula formulaAND = new BinaryOperatorSubformula();
	        	((BinaryOperatorSubformula)formulaAND).setOperator(token);
	        	List<Formula> subformulasAND = new Vector<Formula>();
	            subformulasAND.add((Formula) constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(0)));
	            subformulasAND.add((Formula) constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(1)));
	            ((BinaryOperatorSubformula) formulaAND).setSubformulas(subformulasAND);
	            
//	            debugCounter--;
//                System.out.println("debugCounter" + debugCounter);
                System.out.println("\treturning binary formula (and)..");
	            return formulaAND;
            case PA2Parser.EQV:
                System.out.println("iff");
                Formula formulaEQV = new BinaryOperatorSubformula();
	        	((BinaryOperatorSubformula)formulaEQV).setOperator(token);
	        	List<Formula> subformulasEQV = new Vector<Formula>();
	        	subformulasEQV.add((Formula) constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(0)));
	        	subformulasEQV.add((Formula) constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(1)));
	            ((BinaryOperatorSubformula) formulaEQV).setSubformulas(subformulasEQV);
	            
//	            debugCounter--;
//                System.out.println("debugCounter" + debugCounter);
                System.out.println("\treturning binary formula (iff)..");
	            return formulaEQV;
            case PA2Parser.IMP:
                System.out.println("implies");
                Formula formulaIMP = new BinaryOperatorSubformula();
	        	((BinaryOperatorSubformula)formulaIMP).setOperator(token);
	        	List<Formula> subformulasIMP = new Vector<Formula>();
	        	subformulasIMP.add((Formula) constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(0)));
	        	subformulasIMP.add((Formula) constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(1)));
	            ((BinaryOperatorSubformula) formulaIMP).setSubformulas(subformulasIMP);
	            
//	            debugCounter--;
//                System.out.println("debugCounter" + debugCounter);
                System.out.println("\treturning binary formula (implies)..");
	            return formulaIMP;
            case PA2Parser.OR:
                System.out.println("or");                
                Formula formulaOR = new BinaryOperatorSubformula();
                ((BinaryOperatorSubformula)formulaOR).setOperator(token);
                List<Formula> subformulasOR = new Vector<Formula>();
                subformulasOR.add((Formula) constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(0)));
                subformulasOR.add((Formula) constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(1)));
	            ((BinaryOperatorSubformula) formulaOR).setSubformulas(subformulasOR);
	            
//	            debugCounter--;
//                System.out.println("debugCounter" + debugCounter);
                System.out.println("\treturning binary formula (or)..");
                return formulaOR;
                
            //unary logical operators
            //have a single child
            //child 0 is the subformula
                /*
                 * forthis case I create an UnaryOperatorSubformula which contains only its operator and a formula
                 */
            case PA2Parser.NEG:
                System.out.println("not");
                Formula formulaNEG = new UnaryOperatorSubformula();
                ((UnaryOperatorSubformula)formulaNEG).setOperator(token);                
                ((UnaryOperatorSubformula)formulaNEG).setSubformula((Formula)constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(0)));
                
//                debugCounter--;
//                System.out.println("debugCounter" + debugCounter);
                System.out.println("\treturning unary formula (or)..");
                return formulaNEG;

            //integer relations: a x + b y + ... R c
            // have at least two children
            // child 0 represents the constant c
            // children 1 .. represent the linear term
            // all children should be of tokentype PLUS/MINUS
                /*
                 * this is the most important case, in which I create an Atomic formula which has
                 * the relation and its coefficients/variables
                 */
            case PA2Parser.EQ:
            case PA2Parser.GEQ:
            case PA2Parser.GT:
            case PA2Parser.LEQ:
            case PA2Parser.LT:
            case PA2Parser.NEQ:
//                System.out.println("equal");
            	System.out.println(ast.getText());
                CoefficientWithVariable constant = (CoefficientWithVariable)constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(0)); //we use only teh coefficient 
                Map<String, Integer> coefVar = new LinkedHashMap<String, Integer>();
                
                for(int i = 1; i < ast.getChildCount(); i++){
                	CoefficientWithVariable entry = (CoefficientWithVariable) constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(i)); 
                	coefVar.put(entry.variable, entry.coefficient);
                }
                
                List<String> variables = new Vector<String>((Collection<String>)coefVar.keySet());
                List<Integer> coefficients = new Vector<Integer>((Collection<Integer>)coefVar.values());
                
//                debugCounter--;
//                System.out.println("debugCounter" + debugCounter);
                System.out.println("\treturning atomic formula..");
                return new AtomicFormula(variables, coefficients, constant.coefficient, token);

            //represent the sign of either a
            //integer (1 children of token type INT)
            //or a
            //linear monomial (2 children of token type INT, resp. VAR)
                /*
                 * for this case I create a tuple with the variable and its coefficient and pass it
                 * on to create the atomic formula
                 * 
                 */
            case PA2Parser.PLUS:
//                System.out.println("sign: +");
                int coefP;
                String varP = "";
                
                coefP = (Integer)constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(0));
                if(ast.getChildCount()==2)
                	varP = (String) constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(1));
                
//                debugCounter--;
//                System.out.println("debugCounter" + debugCounter);
                return new CoefficientWithVariable(coefP, varP);
            case PA2Parser.MINUS:
                System.out.println("sign: -");
                int coefM;
                String varM = "";
                
                coefM = (Integer)constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(0));
                if(ast.getChildCount()==2)
                	varM = (String) constructFormulaTree((org.antlr.runtime.tree.CommonTree) ast.getChild(1));
                
//                debugCounter--;
//                System.out.println("debugCounter" + debugCounter);
                return new CoefficientWithVariable((-1) * coefM, varM);

                /*
                 * for the variable identifiers and integers we just return the string
                 */
            //variable identifiers, no children
            case PA2Parser.VAR:
                System.out.println("variable identifier: "+ast.getText());
                
//                debugCounter--;
//                System.out.println("debugCounter" + debugCounter);
                return ast.getText();

            //natural numbers, no children
            case PA2Parser.INT:
                //System.out.println("natural number: "+ast.getText());
                
//                debugCounter--;
//                System.out.println("debugCounter" + debugCounter);
                return Integer.parseInt(ast.getText());

            default:
                System.out.println("D'OH!");
                return null;
        }
        
    }
	
	/**
	 * This method is in charge of reprocessing the formula tree to replace abreviations with simpler operations
	 * 
	 * @param formula
	 * @return a processed formula element from the tree (it's a subtree of the formula)
	 */
	private Formula reprocessSingleFormula(Formula formula){
		//debugCounter++;
		//if it's not an atomic formula then dig deeper to find one
		if(formula.getType() == Formula.ATOMIC_FORMULA){	
		
			AtomicFormula atomicFormula = (AtomicFormula) formula;
			int relation = atomicFormula.getRelation();
			List<Integer> coefficients = null;
			List<Integer> newCoef = null;
			
			//process each formula according to each case, always adding the recursive case after the current formula
			switch(relation){
				case PA2Parser.EQ:
						//create a binary operator subformula with an <= and a >=, reprocess >=
						Logger.printDEBUG("Found EQ, changing to <= ^ >=");
						BinaryOperatorSubformula subformulaEQ = new BinaryOperatorSubformula();
						subformulaEQ.setOperator(PA2Parser.AND);
						List<Formula> subformulasEQ = new Vector<Formula>();
						subformulasEQ.add((Formula) new AtomicFormula(atomicFormula.getVariables(), atomicFormula.getCoefficients(), atomicFormula.getConstant(), PA2Parser.LEQ));
						subformulasEQ.add(
									new AtomicFormula(atomicFormula.getVariables(), atomicFormula.getCoefficients(), atomicFormula.getConstant(), PA2Parser.GEQ)
								);
						
						subformulaEQ.setSubformulas(subformulasEQ);
						return reprocessSingleFormula(subformulaEQ); //reprocess the AND relation and subformulas
						
				case PA2Parser.GEQ:
						Logger.printDEBUG("Found GEQ, changing to (-1) * <=");
						//just create another atomic formula with other coefficients, constant and other relational operator
						coefficients = atomicFormula.getCoefficients();
						newCoef = new Vector<Integer>();
						
						for(int coef:coefficients){
							newCoef.add(-1 * coef);
						}
						
						return new AtomicFormula(atomicFormula.getVariables(), newCoef, (-1) * atomicFormula.getConstant(), PA2Parser.LEQ);
				case PA2Parser.GT:
					Logger.printDEBUG("Found GT, changing to (-1) * < and reprocessing");
						//change to LT, change coefficients/constant sign and reprocess
						coefficients = atomicFormula.getCoefficients();
						newCoef = new Vector<Integer>();
						
						for(int coef:coefficients){
							newCoef.add(-1 * coef);
						}
						
						return reprocessSingleFormula(
									new AtomicFormula(atomicFormula.getVariables(), newCoef, (-1) * atomicFormula.getConstant(), PA2Parser.LT)
								);
				case PA2Parser.LEQ:
						//limit case in which no change is nedded, just return the same formula;
						Logger.printDEBUG("Found LEQ, returning same formula");
						return formula;
				case PA2Parser.LT:
						Logger.printDEBUG("Found LT, changing to LEQ ^ NEG(EQ)");
						//create binary operator subformula with LEQ for the first and NEG(EQ) on the second, reprocess the second
						BinaryOperatorSubformula subformulaLT = new BinaryOperatorSubformula();
						UnaryOperatorSubformula unarySubformulaLT = new UnaryOperatorSubformula();
						
						subformulaLT.setOperator(PA2Parser.AND);
						
						unarySubformulaLT.setOperator(PA2Parser.NEG);
						unarySubformulaLT.setSubformula(
									new AtomicFormula(atomicFormula.getVariables(), atomicFormula.getCoefficients(), atomicFormula.getConstant(), PA2Parser.EQ)
								);
						
						List<Formula> subformulasLT = new Vector<Formula>();
						subformulasLT.add(
									new AtomicFormula(atomicFormula.getVariables(), atomicFormula.getCoefficients(), atomicFormula.getConstant(), PA2Parser.LEQ)
								);
						
						subformulasLT.add(unarySubformulaLT);
						
						subformulaLT.setSubformulas(subformulasLT);
						return reprocessSingleFormula(subformulaLT); //reprocess AND relation and subformulas
				case PA2Parser.NEQ:
						Logger.printDEBUG("Found NEQ, changing to NEG(EQ)");
						//create an unary operator formula for NEG(EQ), reprocess the subformula
						UnaryOperatorSubformula unarySubformulaNEQ = new UnaryOperatorSubformula();
						unarySubformulaNEQ.setOperator(PA2Parser.NEG);
						unarySubformulaNEQ.setSubformula(
								reprocessSingleFormula(
										new AtomicFormula(atomicFormula.getVariables(), atomicFormula.getCoefficients(), atomicFormula.getConstant(), PA2Parser.EQ)
									)
								);
						return unarySubformulaNEQ;
				
				
				default:
						//not reachable
						Logger.printDEBUG("FATAL ERROR!!!");
						return null;
			}
		}
		else if(formula.getType() == Formula.QUANTIFIER_FORMULA){
			//look for the case of forall operator to replace it with the existence operator equivalent
			if(((QuantifierSubformula)formula).getQuantifier().getOperator() == PA2Parser.ALL){
				Logger.printDEBUG("Found ALL quantor, changing to Â(EX(ÂF))");
				//replace with existence operator: Ax F -> Â(Ex (Â(F)))
				QuantifierSubformula newFormula = new QuantifierSubformula();
				UnaryOperatorSubformula negFormula = new UnaryOperatorSubformula();
				UnaryOperatorSubformula newNegFormula = new UnaryOperatorSubformula();
				
				negFormula.setOperator(PA2Parser.NEG);
				negFormula.setSubformula(((QuantifierSubformula)formula).getSubFormula());
				
				newFormula.setQuantifier(PA2Parser.EX, ((QuantifierSubformula)formula).getQuantifier().getVariable());
				newFormula.setSubformula(negFormula);
				
				newNegFormula.setOperator(PA2Parser.NEG);
				newNegFormula.setSubformula(newFormula);
				
				return newNegFormula;
			}
			else{
				//it's not what we look for, so dig deeper but we keep the quantifier (Existence quantifier)
				Logger.printDEBUG("Found EX quantor, keeping it, but reprocessing the rest");
				QuantifierSubformula newFormula = new QuantifierSubformula();
				newFormula.setSubformula(reprocessSingleFormula(((QuantifierSubformula)formula).getSubFormula()));
				newFormula.setQuantifier(
									((QuantifierSubformula)formula).getQuantifier().getOperator(), 
									((QuantifierSubformula)formula).getQuantifier().getVariable()
								);
				return newFormula;
			}
		}
		else if(formula.getType() == Formula.BINARY_OPERATOR_SUBFORMULA){
			//look for certain binary operators
			BinaryOperatorSubformula subformula = (BinaryOperatorSubformula) formula;
			int operator = subformula.getOperator();
			switch(operator){
				case PA2Parser.EQV:
						Logger.printDEBUG("Found EQV, changing to double implication");
						//replace with F1 -> F2 ^ F2 -> F1 and then reprocess the whole thing
						Formula leftEQVFormula = subformula.getSubformulas().get(0);
						Formula rightEQVFormula = subformula.getSubformulas().get(1);
						BinaryOperatorSubformula doubleIMPFormula = new BinaryOperatorSubformula();
						List<Formula> toPlayWithSubformulas = new Vector<Formula>();
						
						BinaryOperatorSubformula leftDoubleIMPFormula = new BinaryOperatorSubformula();
						BinaryOperatorSubformula rightDoubleIMPFormula = new BinaryOperatorSubformula();
						List<Formula> finalDoubleIMPSubformulas = new Vector<Formula>();
						
						leftDoubleIMPFormula.setOperator(PA2Parser.IMP);
						toPlayWithSubformulas.add(leftEQVFormula);
						toPlayWithSubformulas.add(rightEQVFormula);
						leftDoubleIMPFormula.setSubformulas(toPlayWithSubformulas);
						
						toPlayWithSubformulas.clear();
						//do same for right
						rightDoubleIMPFormula.setOperator(PA2Parser.IMP);
						toPlayWithSubformulas.add(rightEQVFormula);
						toPlayWithSubformulas.add(leftEQVFormula);
						rightDoubleIMPFormula.setSubformulas(toPlayWithSubformulas);
						
						//add left and right implications to main formula, then set AND
						finalDoubleIMPSubformulas.add(leftDoubleIMPFormula);
						finalDoubleIMPSubformulas.add(rightDoubleIMPFormula);
						
						doubleIMPFormula.setOperator(PA2Parser.AND);
						doubleIMPFormula.setSubformulas(finalDoubleIMPSubformulas);
						
						
						return reprocessSingleFormula(doubleIMPFormula); //reprocess AND and its subformulas
				case PA2Parser.IMP:
						Logger.printDEBUG("Found IMP quantor, changing to double implication");
						//replace with ÂF1 v F2 reprocessing its subformulas
						UnaryOperatorSubformula leftFormula = new UnaryOperatorSubformula();
						BinaryOperatorSubformula newImpRemovedFormula = new BinaryOperatorSubformula();
						List<Formula> newNoImpSubformulas = new Vector<Formula>();
						
						leftFormula.setOperator(PA2Parser.NEG);
						leftFormula.setSubformula(
								reprocessSingleFormula(subformula.getSubformulas().get(0))
								);
						
						newNoImpSubformulas.add(leftFormula); //add negated and reprocess left formula 
						newNoImpSubformulas.add(
									reprocessSingleFormula(subformula.getSubformulas().get(1))
								); //add reprocessed right formula
						
						newImpRemovedFormula.setOperator(PA2Parser.OR);
						newImpRemovedFormula.setSubformulas(newNoImpSubformulas);
						
						return newImpRemovedFormula;
				case PA2Parser.AND:
						Logger.printDEBUG("Found AND, changing to Â(ÂF1 V ÂF2)");
						//replace with Â(ÂF1 V ÂF2)
						BinaryOperatorSubformula binaryFormula = new BinaryOperatorSubformula();
						List<Formula> subFormulas = new Vector<Formula>();
						
						UnaryOperatorSubformula newORFormula = new UnaryOperatorSubformula();
						
						for(Formula subformulaAND:subformula.getSubformulas()){
							//reprocess each formula before adding it to the list
							subformulaAND = reprocessSingleFormula(subformulaAND);
							
							UnaryOperatorSubformula formulaNeg = new UnaryOperatorSubformula();
							formulaNeg.setOperator(PA2Parser.NEG);
							formulaNeg.setSubformula(subformulaAND);
							subFormulas.add(formulaNeg);
						}
						
						binaryFormula.setOperator(PA2Parser.OR);
						binaryFormula.setSubformulas(subFormulas);
						
						newORFormula.setOperator(PA2Parser.NEG);
						newORFormula.setSubformula(binaryFormula);
						
						return newORFormula;
				default:
						//it's an OR... not what we look for so dig deeper, for that we create another BinaryOperatorSubformula with the reprocessed subformulas
						Logger.printDEBUG("Found OR processing subformulas");
						List<Formula> oldSubformulas = subformula.getSubformulas();
						List<Formula> newSubformulas = new Vector<Formula>();
						BinaryOperatorSubformula newBinaryOperatorSubformula = new BinaryOperatorSubformula();
						for(Formula f:oldSubformulas){
							newSubformulas.add(reprocessSingleFormula(f));
						}
						newBinaryOperatorSubformula.setSubformulas(newSubformulas);
						newBinaryOperatorSubformula.setOperator(subformula.getOperator());
						return newBinaryOperatorSubformula;
					
			
			}
			
		}
		else{
			//then it's an UnaryOperatorSubformula, we go deeper and keep the operator
			Logger.printDEBUG("Found NEG, processing subformula...");
			UnaryOperatorSubformula newFormula = new UnaryOperatorSubformula();
			newFormula.setOperator(((UnaryOperatorSubformula)formula).getOperator());
			newFormula.setSubformula(reprocessSingleFormula(((UnaryOperatorSubformula)formula).getSubFormula()));
			return newFormula;
		}
		
		
	}
	
	/**
	 * This method is called by the PresburgerEngine, which expects to get a formula tree for performing the operations.
	 *  
	 * @param input
	 * @return a formula tree
	 * @throws Exception
	 */
	public Formula buildFormulaList(CharStream input) throws Exception{
		// Generate a lexer for reading the formula `input'
        PA2Lexer lex = new PA2Lexer(input);
        // Generate from the lexer a token stream to be fed to the parser
        CommonTokenStream tokens = new CommonTokenStream(lex);
        // Generate the parser analyzing the token stream
        PA2Parser parser = new PA2Parser(tokens);
        // Finally parse the input and generate the tree
        PA2Parser.formula_return r = parser.formula();             

        //get the AST encapsuled in r
        org.antlr.runtime.tree.CommonTree ast = (org.antlr.runtime.tree.CommonTree)r.getTree();

        //a simple method for traversing the AST
        //you should start from there to construct the finite automaton
        //for the formula
        //walkAST(ast);
//        debugCounter = 0;
//        System.out.println("debugCounter" + debugCounter);
        Formula finalFormula = (Formula)constructFormulaTree(ast);
//        debugCounter = 0;
        finalFormula = reprocessSingleFormula(finalFormula);
        
        return finalFormula;
	}

	/**
	 * Simple class for storing temporarily the coefficients and variables of an atomic formula
	 * 
	 * @author Fernando Hernandez
	 *
	 */
	private class CoefficientWithVariable{
		public int coefficient;
		public String variable;
		
		public CoefficientWithVariable(int coefficient, String variable){
			this.coefficient = coefficient;
			this.variable = variable;
		}
		
	}

}

package fernando.presburger.objects;

/**
 * Implementation of the Formula interface. Represents a formula that contains a quantor operator.
 * 
 * @author Fernando Hernandez
 *
 */
public class QuantifierSubformula implements Formula{
	private Formula subFormula;
	private Quantifier quantifier;
	
	/**
	 * Class Constructor
	 */
	public QuantifierSubformula(){
		//this.type = QUANTIFIER_FORMULA;
		this.quantifier = new Quantifier();
	}
	
	/**
	 * subformula setter
	 * @param subformula
	 */
	public void setSubformula(Formula subformula){
		this.subFormula = subformula;
	}
	
	/**
	 * Subformula getter
	 * @return this object's subformula
	 */
	public Formula getSubFormula(){
		return this.subFormula;
	}
	
	/**
	 * Quantor operator setter
	 * 
	 * @param quantifier
	 * @param variable
	 */
	public void setQuantifier(int quantifier, String variable){
		this.quantifier.operator = quantifier;
		this.quantifier.variable = variable;
	}
	
	/**
	 * Quantor operator getter
	 * 
	 * @return Quantifier object
	 */
	public Quantifier getQuantifier(){
		return quantifier; //this.quantifiers.get();
	}
	
	/**
	 * Implementation of the getType method, describes what kind of formula this is
	 */
	public int getType(){
		return Formula.QUANTIFIER_FORMULA;
	}
	
	/**
	 * Private class for holding quantifier operator
	 * 
	 * @author Fernando Hernandez
	 *
	 */
	public class Quantifier{
		int operator;
		String variable;
		
		public String getVariable(){
			return this.variable;
		}
		
		public int getOperator(){
			return this.operator;
		}
	}
}

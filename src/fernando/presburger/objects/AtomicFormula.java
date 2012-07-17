package fernando.presburger.objects;

import java.util.List;

/**
 * This object is an implementation of the Formula interface. Describes an Atomic Formula.
 * 
 * @author Fernando Hernandez
 *
 */
public class AtomicFormula implements Formula{
	private List<String> variables;
	private List<Integer> coefficients;
	private int constant;
	private int relation;
	
	/**
	 * Class constructor
	 * 
	 * @param variables
	 * @param coefficients
	 * @param constant
	 * @param relation
	 */
	public AtomicFormula(List<String> variables, 
				List<Integer> coefficients,
				int constant, 
				int relation) {		
		//this.type = Formula.ATOMIC_FORMULA;
		
		this.variables = variables;
		this.coefficients = coefficients;
		this.constant = constant;
		this.relation = relation;
		
	}
	
	/**
	 * Relation operator getter
	 * 
	 * @return
	 */
	public int getRelation(){
		return relation;
	}
	
	/**
	 * Coefficients getter
	 * 
	 * @return
	 */
	public List<Integer> getCoefficients(){
		return coefficients;
	}
	
	/**
	 * Variable's list getter
	 * 
	 * @return
	 */
	public List<String> getVariables(){
		return this.variables;
	}

	/**
	 * Constant getter
	 * 
	 * @return
	 */
	public int getConstant() {
		return constant;
	}

	/**
	 * Implementation of getType method, describes the formula kind
	 */
	@Override
	public int getType() {
		return Formula.ATOMIC_FORMULA;
	}	
	
}

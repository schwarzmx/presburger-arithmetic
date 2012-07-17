package fernando.presburger.objects;

import java.util.List;

/**
 * This class describes a formula that uses a logical binary operator like and, or, equivalence, etc.
 *  
 * @author Fernando Hernandez
 *
 */
public class BinaryOperatorSubformula implements Formula{
	//protected Formula subFormula;
	private int operator;
	private List<Formula> subformulas;
	
	/**
	 * Class constructor
	 */
	public BinaryOperatorSubformula(){
		//this.type = Formula.BINARY_OPERATOR_SUBFORMULA;
	}

	/**
	 * Operator setter
	 * 
	 * @param operator
	 */
	public void setOperator(int operator) {
		this.operator = operator;
	}

	/**
	 * Operator getter
	 * 
	 * @return
	 */
	public int getOperator() {
		return operator;
	}

	/**
	 * Subformulas setter
	 * 
	 * @param subformulas
	 */
	public void setSubformulas(List<Formula> subformulas) {
		this.subformulas = subformulas;
	}

	/**
	 * Subformulas getter
	 * 
	 * @return
	 */
	public List<Formula> getSubformulas() {
		return subformulas;
	}

	/**
	 * Implementation of getType method, describes the formula kind
	 */
	@Override
	public int getType() {
		return Formula.BINARY_OPERATOR_SUBFORMULA;
	}
	
	

}

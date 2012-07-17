package fernando.presburger.objects;

/**
 * Implementation of the Formula interface that describes an unary operator formula, mainly just for the negator operator.
 * 
 * @author Fernando Hernandez
 *
 */
public class UnaryOperatorSubformula implements Formula{
	private int operator;
	private Formula subFormula;
	
	/**
	 * Class constructor
	 * 
	 */
	public UnaryOperatorSubformula(){
		//this.type = Formula.UNARY_OPERATOR_SUBFORMULA;
	}

	/**
	 * Operator setter
	 * @param operator
	 */
	public void setOperator(int operator) {
		this.operator = operator;
	}

	/**
	 * Operator getter
	 * @return
	 */
	public int getOperator() {
		return operator;
	}
	
	/**
	 * Subformula setter
	 * @param subformula
	 */
	public void setSubformula(Formula subformula){
		this.subFormula = subformula;
	}
	
	/**
	 * Subformula getter
	 * 
	 * @return
	 */
	public Formula getSubFormula(){
		return this.subFormula;
	}
	
	/**
	 * Implementation of getType method, describes the formula kind
	 */
	@Override
	public int getType(){
		return Formula.UNARY_OPERATOR_SUBFORMULA;
	}

}

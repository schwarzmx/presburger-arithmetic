package fernando.presburger.objects;


/**
 * Main abstract interface for describing a formula. This interface helps for recursiveness in the main
 * classes operations like traversing the formula trees.
 * 
 * @author Fernando Hernandez
 *
 */
public interface Formula {
	
	public static final int QUANTIFIER_FORMULA = 0;
	public static final int BINARY_OPERATOR_SUBFORMULA = 1;
	public static final int UNARY_OPERATOR_SUBFORMULA = 2;
	public static final int ATOMIC_FORMULA = 3;
	
	/**
	 * Description of getType method, describes the formula kind
	 */
	public int getType();
	
	
}

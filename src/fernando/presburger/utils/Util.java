package fernando.presburger.utils;

import java.util.List;
import java.util.Vector;

/**
 * This class contains several miscellaneous tools for perfoming operations in different algorithms.
 * 
 * @author Fernando Hernandez
 *
 */
public class Util {
	
	/**
	 * Method for performing the dot product used in the Presburger to automaton algorithm
	 * 
	 * @param symbol current symbol vector
	 * @param coefficients current coefficients
	 * @return the dot product of both vectors
	 * @throws Exception
	 */
	public static int dotProduct(Vector<Integer> symbol, Vector<Integer> coefficients) throws Exception {
		//System.out.println("size symbol: " + symbol.size() + " size coeff: " + coefficients.size());
		if (symbol.size() != coefficients.size()) throw new Exception("Vectors of different size!"); 
		
		int i, sum=0;
		
		for (i=0; i<symbol.size(); i++) 
			sum += symbol.get(i) * coefficients.get(i); 
		
		return sum;
	}
	
	/**
	 * This method is used to convert the char array used for a symbol into a Vector of the desired size
	 * 
	 * @param array char array containing the initial binary represented symbol
	 * @param finalLength the final length required for the symbol
	 * @return a Vector representing the current symbol
	 */
	public static Vector<Integer> charArrayToVector(char[] array, int finalLength){
		Vector<Integer> vector = new Vector<Integer>();
		
		//add leading zeros when necessary (lsbf() -> zyx...)
		for(int i=finalLength-array.length; i > 0; i--){
			vector.add(0);
		}
		
		for(char digit:array){
			vector.add(Integer.parseInt(digit + ""));
		}
		
		return vector;
	}
	
	/**
	 * This method is used to check if a set intersect with the other. It is used in several algorithms in PresburgerEngine
	 * 
	 * @param l1 set 1
	 * @param l2 set 2
	 * @return whether set 1 intersects with set 2
	 */
	public static boolean checkIntersect(List<Integer> l1, List<Integer> l2){

		for(int fromL1:l1){
			//if at least one of l1's elements is in l2 then there's an intersection
			if(l2.contains(fromL1))
				return true;
		}
		return false;
	}
	
	/**
	 * This method performs the l1 \ l2 (complement) operation.
	 * 
	 * @param l1 set 1
	 * @param l2 set 2
	 * @return complement of l2 on l1
	 */
	public static List<Integer> complement(List<Integer> l1, List<Integer> l2){
		//take all elements from l1 that are not in l2
		List<Integer> complement = new Vector<Integer>();
		
		for(Integer i:l1){
			if(!l2.contains(i)){
				complement.add(i);
			}
		}
		
		return complement;
	}
}

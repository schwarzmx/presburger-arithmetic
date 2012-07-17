package fernando.presburger.utils;

import java.io.PrintStream;

/**
 * Simple class for performing debug operations over the code
 * 
 * @author schwarzmx
 *
 */
public class Logger {
	public static int loglevel = 2;
	public static PrintStream out;
	
	public static final int DEBUG = 0;
	public static final int INFO = 1;
	public static final int NONE = 2;
	
	/**
	 * Class initializer, this is called from the Main class
	 * 
	 * @param logLevel values from 0 to 2, 0 being the most descriptive level
	 */
	public static void initialize(int logLevel){
		out = System.out;
		loglevel = logLevel;
	}
	
	/**
	 * Method for printing in DEBUG mode
	 * @param msg
	 */
	public static void printDEBUG(String msg){
		if (loglevel == DEBUG)
			out.println(msg);
	}
	
	/**
	 * Method for printing in INFO mode
	 * @param msg
	 */
	public static void printINFO(String msg){
		if (loglevel <= INFO)
			out.println(msg);
	}
}

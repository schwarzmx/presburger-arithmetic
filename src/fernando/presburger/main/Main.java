package fernando.presburger.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CharStream;

import fernando.presburger.engine.PresburgerEngine;
import fernando.presburger.utils.Logger;

/**
 * Main class for this programming assignment
 * 
 * @author Fernando Hernandez
 *
 */
public class Main {

	/**
	 * This is the main method. It requires as first parameter the path to the file that contains the formula.
	 * As optional second parameter it can be set the debug level.
	 * @see Logger
	 * 
	 * @param args execution parameters
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception{
		//create stream for taking the formula into the engine
		CharStream input = null;
		String filename = "";
		if ( args.length > 1 ) {
			//debug level was given
			input = new ANTLRFileStream(args[0]);
			filename = args[0]; //.substring(0,args[0].lastIndexOf('.'));
			
			Logger.initialize(Integer.parseInt(args[1]));
		}
		else if(args.length==1){
			input = new ANTLRFileStream(args[0]);
			filename = args[0]; //.substring(0,args[0].lastIndexOf('.'));
		}
		else{
			input = new ANTLRInputStream(System.in);
		}
		
		Logger.printINFO("Initializing engine...");
		//create engine
		PresburgerEngine engine = new PresburgerEngine(input);
		String dotty = engine.processFormula();
		try {
		    BufferedWriter out = new BufferedWriter(new FileWriter(filename +".dotty"));
		    out.write(dotty);
		    out.close();
		} catch (IOException e) {
		}

	}
}

package jan.presburger;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

public class Main {

    public static void walkAST( org.antlr.runtime.tree.CommonTree ast ) {
        int token = ast.getToken().getType();

        switch( token ) {
            //quantifiers
            //nodes have two children
            //child 0 is the variable
            //child 1 is the subformula
            case PA2Parser.ALL:
                System.out.println("all quantor");
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(1));
                break;
             case PA2Parser.EX:
                System.out.println("exists quantor");
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(1));
                break;

            //binary logical operators
            //nodes have two children
            //child 0 is the left subformula
            //child 1 is the right subformula
            case PA2Parser.AND:
                System.out.println("and");
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(0));
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(1));
                break;
            case PA2Parser.EQV:
                System.out.println("iff");
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(0));
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(1));
                break;
            case PA2Parser.IMP:
                System.out.println("implies");
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(0));
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(1));
                break;
            case PA2Parser.OR:
                System.out.println("or");
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(0));
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(1));
                break;
            //unary logical operators
            //have a single child
            //child 0 is the subformula
            case PA2Parser.NEG:
                System.out.println("not");
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(0));
                break;

            //integer relations: a x + b y + ... R c
            // have at least two children
            // child 0 represents the constant c
            // children 1 .. represent the linear term
            // all children should be of tokentype PLUS/MINUS
            case PA2Parser.EQ:
                System.out.println("equal");
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(0));
                for(int i = 1; i < ast.getChildCount(); i++)
                    walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(i));
                break;
            case PA2Parser.GEQ:
                System.out.println("greater equal");
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(0));
                for(int i = 1; i < ast.getChildCount(); i++)
                    walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(i));
                break;
            case PA2Parser.GT:
                System.out.println("greater than");
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(0));
                for(int i = 1; i < ast.getChildCount(); i++)
                    walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(i));
                break;
            case PA2Parser.LEQ:
                System.out.println("less equal");
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(0));
                for(int i = 1; i < ast.getChildCount(); i++)
                    walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(i));
                break;
            case PA2Parser.LT:
                System.out.println("less than");
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(0));
                for(int i = 1; i < ast.getChildCount(); i++)
                    walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(i));
                break;
            case PA2Parser.NEQ:
                System.out.println("not equal");
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(0));
                for(int i = 1; i < ast.getChildCount(); i++)
                    walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(i));
                break;

            //represent the sign of either a
            //integer (1 children of token type INT)
            //or a
            //linear monomial (2 children of token type INT, resp. VAR)
            case PA2Parser.PLUS:
                System.out.println("sign: +");
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(0));
                if(ast.getChildCount()==2)
                    walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(1));
                break;
            case PA2Parser.MINUS:
                System.out.println("sign: -");
                walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(0));
                if(ast.getChildCount()==2)
                    walkAST((org.antlr.runtime.tree.CommonTree) ast.getChild(1));
                break;

            //variable identifiers, no children
            case PA2Parser.VAR:
                System.out.println("variable identifier: "+ast.getText());
                break;

            //natural numbers, no children
            case PA2Parser.INT:
                System.out.println("natural number: "+ast.getText());
                break;

            default:
                System.out.println("D'OH!");
                break;
        }
    }

	public static void main(String[] args) throws Exception {
		CharStream input = null;
		if ( args.length>0 ) {
			input = new ANTLRFileStream(args[0]);
		}
		else {
			input = new ANTLRInputStream(System.in);
		}
		// Generate a lexer for reading the formula `input'
                PA2Lexer lex = new PA2Lexer(input);
                // Generate from the lexer a token stream to be fed to the parser
		CommonTokenStream tokens = new CommonTokenStream(lex);
                // Generate the parser analyzing the token stream
                PA2Parser parser = new PA2Parser(tokens);
                // Finally parse the input and generate the tree
                PA2Parser.formula_return r = parser.formula();             

                //get the AST encapsuled in r
                org.antlr.runtime.tree.CommonTree ast = (org.antlr.runtime.tree.CommonTree)r.tree;

                //a simple method for traversing the AST
                //you should start from there to construct the finite automaton
                //for the formula
                walkAST(ast);
	}
}
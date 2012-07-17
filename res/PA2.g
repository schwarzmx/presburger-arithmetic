grammar PA2;
options { output=AST; }
tokens {
  PLUS;
  MINUS;
  NEG;
  AND;
  OR;
  IMP;
  EQV;
  ALL;
  EX;
  EQ;
  NEQ;
  GEQ;
  LEQ;
  GT;
  LT;
}

formula 
  : linearpred
  | '!' formula                 -> ^(NEG["not"] formula)
  | '(' formula con formula ')' -> ^(con formula formula)
  | quan VAR formula            -> ^(quan VAR formula)
  ;
	
linearpred 
  : linearterm comp cons -> ^( comp cons linearterm )
  ;

linearterm 
  : linmon (sgnlinmon)*
  ;

linmon 	
  : '+'? INT VAR -> ^(PLUS["+"]  INT VAR)
  | '-'  INT VAR -> ^(MINUS["-"] INT VAR)
  | '+'? VAR     -> ^(PLUS["+"] INT["1"] VAR)
  | '-'  VAR     -> ^(MINUS["-"] INT["1"] VAR)
  ;
	
sgnlinmon 
  : '+' INT VAR -> ^(PLUS["+"]  INT VAR)
  | '-' INT VAR -> ^(MINUS["-"] INT VAR)
  | '+' VAR     -> ^(PLUS["+"] INT["1"] VAR)
  | '-' VAR     -> ^(MINUS["-"] INT["1"] VAR)
  ;
  
cons 
  : '+'? INT -> ^(PLUS["+"]  INT)
  | '-' INT  -> ^(MINUS["-"] INT)
  ;

quan : 'A' -> ^(ALL["forall"])
     | 'E' -> ^(EX["exists"])
     ;

con : '&&'  -> ^(AND["and"])
    | '||'  -> ^(OR["or"])
    | '->'  -> ^(IMP["implies"])
    | '<->' -> ^(EQV["iff"])
    ;

comp : '==' -> ^(EQ["eq"])
     | '!=' -> ^(NEQ["neq"])
     | '>=' -> ^(GEQ["geq"])
     | '<=' -> ^(LEQ["leq"])
     | '>'  -> ^(GT["gt"])
     | '<'  -> ^(LT["lt"])
     ;
	
VAR  :  ('a'..'z')('a'..'z'|'0'..'9'|'_')*;

INT : ('0'..'9')+;

WS : (' '|'\n'|'\t'|'\r')+ { skip(); };

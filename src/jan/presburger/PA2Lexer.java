package jan.presburger;
// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/kretinsk/Desktop/antlr/PA2.g 2010-11-25 14:02:21

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PA2Lexer extends Lexer {
    public static final int LT=18;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int INT=20;
    public static final int MINUS=5;
    public static final int AND=7;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int NEQ=14;
    public static final int WS=21;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int EX=12;
    public static final int ALL=11;
    public static final int NEG=6;
    public static final int OR=8;
    public static final int IMP=9;
    public static final int GT=17;
    public static final int PLUS=4;
    public static final int EQV=10;
    public static final int VAR=19;
    public static final int GEQ=15;
    public static final int EQ=13;
    public static final int LEQ=16;

    // delegates
    // delegators

    public PA2Lexer() {;} 
    public PA2Lexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public PA2Lexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/kretinsk/Desktop/antlr/PA2.g"; }

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:3:7: ( '!' )
            // /home/kretinsk/Desktop/antlr/PA2.g:3:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:4:7: ( '(' )
            // /home/kretinsk/Desktop/antlr/PA2.g:4:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:5:7: ( ')' )
            // /home/kretinsk/Desktop/antlr/PA2.g:5:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:6:7: ( '+' )
            // /home/kretinsk/Desktop/antlr/PA2.g:6:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:7:7: ( '-' )
            // /home/kretinsk/Desktop/antlr/PA2.g:7:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:8:7: ( 'A' )
            // /home/kretinsk/Desktop/antlr/PA2.g:8:9: 'A'
            {
            match('A'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:9:7: ( 'E' )
            // /home/kretinsk/Desktop/antlr/PA2.g:9:9: 'E'
            {
            match('E'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:10:7: ( '&&' )
            // /home/kretinsk/Desktop/antlr/PA2.g:10:9: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:11:7: ( '||' )
            // /home/kretinsk/Desktop/antlr/PA2.g:11:9: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:12:7: ( '->' )
            // /home/kretinsk/Desktop/antlr/PA2.g:12:9: '->'
            {
            match("->"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:13:7: ( '<->' )
            // /home/kretinsk/Desktop/antlr/PA2.g:13:9: '<->'
            {
            match("<->"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:14:7: ( '==' )
            // /home/kretinsk/Desktop/antlr/PA2.g:14:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:15:7: ( '!=' )
            // /home/kretinsk/Desktop/antlr/PA2.g:15:9: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:16:7: ( '>=' )
            // /home/kretinsk/Desktop/antlr/PA2.g:16:9: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:17:7: ( '<=' )
            // /home/kretinsk/Desktop/antlr/PA2.g:17:9: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:18:7: ( '>' )
            // /home/kretinsk/Desktop/antlr/PA2.g:18:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:19:7: ( '<' )
            // /home/kretinsk/Desktop/antlr/PA2.g:19:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "VAR"
    public final void mVAR() throws RecognitionException {
        try {
            int _type = VAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:73:6: ( ( 'a' .. 'z' ) ( 'a' .. 'z' | '0' .. '9' | '_' )* )
            // /home/kretinsk/Desktop/antlr/PA2.g:73:9: ( 'a' .. 'z' ) ( 'a' .. 'z' | '0' .. '9' | '_' )*
            {
            // /home/kretinsk/Desktop/antlr/PA2.g:73:9: ( 'a' .. 'z' )
            // /home/kretinsk/Desktop/antlr/PA2.g:73:10: 'a' .. 'z'
            {
            matchRange('a','z'); 

            }

            // /home/kretinsk/Desktop/antlr/PA2.g:73:19: ( 'a' .. 'z' | '0' .. '9' | '_' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/kretinsk/Desktop/antlr/PA2.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VAR"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:75:5: ( ( '0' .. '9' )+ )
            // /home/kretinsk/Desktop/antlr/PA2.g:75:7: ( '0' .. '9' )+
            {
            // /home/kretinsk/Desktop/antlr/PA2.g:75:7: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/kretinsk/Desktop/antlr/PA2.g:75:8: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kretinsk/Desktop/antlr/PA2.g:77:4: ( ( ' ' | '\\n' | '\\t' | '\\r' )+ )
            // /home/kretinsk/Desktop/antlr/PA2.g:77:6: ( ' ' | '\\n' | '\\t' | '\\r' )+
            {
            // /home/kretinsk/Desktop/antlr/PA2.g:77:6: ( ' ' | '\\n' | '\\t' | '\\r' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\t' && LA3_0<='\n')||LA3_0=='\r'||LA3_0==' ') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/kretinsk/Desktop/antlr/PA2.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // /home/kretinsk/Desktop/antlr/PA2.g:1:8: ( T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | VAR | INT | WS )
        int alt4=20;
        alt4 = dfa4.predict(input);
        switch (alt4) {
            case 1 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:10: T__22
                {
                mT__22(); 

                }
                break;
            case 2 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:16: T__23
                {
                mT__23(); 

                }
                break;
            case 3 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:22: T__24
                {
                mT__24(); 

                }
                break;
            case 4 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:28: T__25
                {
                mT__25(); 

                }
                break;
            case 5 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:34: T__26
                {
                mT__26(); 

                }
                break;
            case 6 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:40: T__27
                {
                mT__27(); 

                }
                break;
            case 7 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:46: T__28
                {
                mT__28(); 

                }
                break;
            case 8 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:52: T__29
                {
                mT__29(); 

                }
                break;
            case 9 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:58: T__30
                {
                mT__30(); 

                }
                break;
            case 10 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:64: T__31
                {
                mT__31(); 

                }
                break;
            case 11 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:70: T__32
                {
                mT__32(); 

                }
                break;
            case 12 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:76: T__33
                {
                mT__33(); 

                }
                break;
            case 13 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:82: T__34
                {
                mT__34(); 

                }
                break;
            case 14 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:88: T__35
                {
                mT__35(); 

                }
                break;
            case 15 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:94: T__36
                {
                mT__36(); 

                }
                break;
            case 16 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:100: T__37
                {
                mT__37(); 

                }
                break;
            case 17 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:106: T__38
                {
                mT__38(); 

                }
                break;
            case 18 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:112: VAR
                {
                mVAR(); 

                }
                break;
            case 19 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:116: INT
                {
                mINT(); 

                }
                break;
            case 20 :
                // /home/kretinsk/Desktop/antlr/PA2.g:1:120: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA4 dfa4 = new DFA4(this);
    static final String DFA4_eotS =
        "\1\uffff\1\21\3\uffff\1\23\4\uffff\1\26\1\uffff\1\30\14\uffff";
    static final String DFA4_eofS =
        "\31\uffff";
    static final String DFA4_minS =
        "\1\11\1\75\3\uffff\1\76\4\uffff\1\55\1\uffff\1\75\14\uffff";
    static final String DFA4_maxS =
        "\1\174\1\75\3\uffff\1\76\4\uffff\1\75\1\uffff\1\75\14\uffff";
    static final String DFA4_acceptS =
        "\2\uffff\1\2\1\3\1\4\1\uffff\1\6\1\7\1\10\1\11\1\uffff\1\14\1\uffff"+
        "\1\22\1\23\1\24\1\15\1\1\1\12\1\5\1\13\1\17\1\21\1\16\1\20";
    static final String DFA4_specialS =
        "\31\uffff}>";
    static final String[] DFA4_transitionS = {
            "\2\17\2\uffff\1\17\22\uffff\1\17\1\1\4\uffff\1\10\1\uffff\1"+
            "\2\1\3\1\uffff\1\4\1\uffff\1\5\2\uffff\12\16\2\uffff\1\12\1"+
            "\13\1\14\2\uffff\1\6\3\uffff\1\7\33\uffff\32\15\1\uffff\1\11",
            "\1\20",
            "",
            "",
            "",
            "\1\22",
            "",
            "",
            "",
            "",
            "\1\24\17\uffff\1\25",
            "",
            "\1\27",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | VAR | INT | WS );";
        }
    }
 

}
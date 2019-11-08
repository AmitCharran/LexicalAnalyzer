/**

 This class parses VM code, loads instructions into VM's instruction store, and displays the instruction store.

 The EBNF syntax for VM code is:

 <instruction list> --> { <instruction unit> }+
 <instruction unit> --> [ <label> ] <instruction>
 <label> --> <unsigned int> ":"
 <instruction> -->
 "iconst" <unsigned int> | "iconst" <signed int> | "iload" <unsigned int> | "istore" <unsigned int> |
 "fconst" <float> | "fconst" <floatE> | "fload" <unsigned int> | "fstore" <unsigned int> |
 "iadd" | "isub" | "imul" | "idiv" | "fadd" | "fsub" | "fmul" | "fdiv" |
 "intToFloat" |
 <cmp inst name> <unsigned int> |
 "goto" <unsigned int> |
 "invoke" <unsigned int> "," <unsigned int> "," <unsigned int> |
 "return" | "ireturn" | "freturn" |
 "print" <unsigned int>
 <cmp inst name> -->
 "icmpeq" | "icmpne" | "icmplt" | "icmple" | "icmpgt" | "icmpge" |
 "fcmpeq" | "fcmpne" | "fcmplt" | "fcmple" | "fcmpgt" | "fcmpge"

 The following functions from LexVM.java are used:

 static String t // holds an extracted token
 static State state // the current state of the finite automaton
 static int getToken() // extracts the next token
 static void display(String s)
 static void displayln(String s)
 static void setLex(String inFile, String outFile)
 static void closeIO()

 The instructions are represented by objects of leaf classes of the "Instruction" class.
 As the parser processes each instruction in the input file, it creates a corresponding instruction object
 and stores it in the "instStore[]" array.
 The updateLabels() function then updates the target labels of comparison-jump instructions, "goto", "invoke"
 to indexes of "instStore[]".

 **/


import java.util.*;

public abstract class VM extends LexVM
{
    private static int instStoreSize = 10001;
    private static Instruction instStore[] = new Instruction[instStoreSize]; // the instruction store
    private static int i = 0; // index used to load instructions in "instStore[]"

    public static HashMap<Integer,Integer> labelMap = new HashMap<Integer,Integer>();
    // used to map labels to indexes of "instStore[]"

    // The following classes have no field so a single object can be shared.

    private static Iadd iadd = new Iadd();
    private static Isub isub = new Isub();
    private static Imul imul = new Imul();
    private static Idiv idiv = new Idiv();
    private static Fadd fadd = new Fadd();
    private static Fsub fsub = new Fsub();
    private static Fmul fmul = new Fmul();
    private static Fdiv fdiv = new Fdiv();
    private static IntToFloat intToFloat = new IntToFloat();
    private static Return return_ = new Return();
    private static IReturn ireturn = new IReturn();
    private static FReturn freturn = new FReturn();

    private static boolean syntaxErrorFound = false;
    private static boolean errorFound = false;


    public static void instructionList()
    {
        do
        {
            instructionUnit();
            i++;
        } while ( i <= instStoreSize-2 && beginsInstructionUnit() );
        if ( i == instStoreSize-1 )
            errorMsg(3, 0, null);
    }

    public static boolean beginsInstructionUnit()
    {
        return state.isInstName() || state == State.UnsignedInt;
    }

    public static void instructionUnit()
    {
        if ( state == State.UnsignedInt ) // a label is present
        {
            int label = Integer.parseInt(t);
            getToken();
            if ( state == State.Colon )
            {
                if ( labelMap.containsKey(label) )
                    errorMsg(1, label, null);
                else
                    labelMap.put(label, i);
                getToken();
            }
            else
                syntaxErrorMsg(1);
        }
        switch( state )
        {
            case Iconst:
            {
                getToken();
                if ( state == State.UnsignedInt || state == State.SignedInt )
                {
                    instStore[i] = new Iconst(Integer.parseInt(t));
                    getToken();
                }
                else
                    syntaxErrorMsg(2);
                return;
            }
            case Iload: case Istore: case Fload: case Fstore:
            case Icmpeq: case Icmpne: case Icmplt: case Icmple: case Icmpgt: case Icmpge:
            case Fcmpeq: case Fcmpne: case Fcmplt: case Fcmple: case Fcmpgt: case Fcmpge:
            case Goto: case Print:
        {
            State savedState = state;
            getToken();
            if ( state == State.UnsignedInt )
            {
                int t_int = Integer.parseInt(t);
                switch( savedState )
                {
                    case Iload:  instStore[i] = new Iload(t_int);  break;
                    case Istore: instStore[i] = new Istore(t_int); break;
                    case Fload:  instStore[i] = new Fload(t_int);  break;
                    case Fstore: instStore[i] = new Fstore(t_int); break;
                    case Icmpeq: instStore[i] = new Icmpeq(t_int); break;
                    case Icmpne: instStore[i] = new Icmpne(t_int); break;
                    case Icmplt: instStore[i] = new Icmplt(t_int); break;
                    case Icmple: instStore[i] = new Icmple(t_int); break;
                    case Icmpgt: instStore[i] = new Icmpgt(t_int); break;
                    case Icmpge: instStore[i] = new Icmpge(t_int); break;
                    case Fcmpeq: instStore[i] = new Fcmpeq(t_int); break;
                    case Fcmpne: instStore[i] = new Fcmpne(t_int); break;
                    case Fcmplt: instStore[i] = new Fcmplt(t_int); break;
                    case Fcmple: instStore[i] = new Fcmple(t_int); break;
                    case Fcmpgt: instStore[i] = new Fcmpgt(t_int); break;
                    case Fcmpge: instStore[i] = new Fcmpge(t_int); break;
                    case Goto:   instStore[i] = new Goto(t_int);   break;
                    case Print:  instStore[i] = new Print(t_int);
                }
                getToken();
            }
            else
                syntaxErrorMsg(3);
            return;
        }
            case Fconst:
            {
                getToken();
                if ( state == State.Float || state == State.FloatE )
                {
                    instStore[i] = new Fconst(Double.parseDouble(t));
                    getToken();
                }
                else
                    syntaxErrorMsg(4);
                return;
            }
            case Iadd: case Isub: case Imul: case Idiv:
            case Fadd: case Fsub: case Fmul: case Fdiv:
            case IntToFloat: case Return: case Ireturn: case Freturn:
        {
            switch( state )
            {
                case Iadd:       instStore[i] = iadd;       break;
                case Isub:       instStore[i] = isub;       break;
                case Imul:       instStore[i] = imul;       break;
                case Idiv:       instStore[i] = idiv;       break;
                case Fadd:       instStore[i] = fadd;       break;
                case Fsub:       instStore[i] = fsub;       break;
                case Fmul:       instStore[i] = fmul;       break;
                case Fdiv:       instStore[i] = fdiv;       break;
                case IntToFloat: instStore[i] = intToFloat; break;
                case Return:     instStore[i] = return_;    break;
                case Ireturn:    instStore[i] = ireturn;    break;
                case Freturn:    instStore[i] = freturn;
            }
            getToken();
            return;
        }
            case Invoke:
            {
                getToken();
                if ( state == State.UnsignedInt )
                {
                    int label = Integer.parseInt(t);
                    getToken();
                    if ( state == State.Comma )
                    {
                        getToken();
                        if ( state == State.UnsignedInt )
                        {
                            int numOfParams = Integer.parseInt(t);
                            getToken();
                            if ( state == State.Comma )
                            {
                                getToken();
                                if ( state == State.UnsignedInt )
                                {
                                    instStore[i] = new Invoke(label, numOfParams, Integer.parseInt(t));
                                    getToken();
                                }
                                else
                                    syntaxErrorMsg(3);
                            }
                            else
                                syntaxErrorMsg(5);
                        }
                        else
                            syntaxErrorMsg(3);
                    }
                    else
                        syntaxErrorMsg(5);
                }
                else
                    syntaxErrorMsg(3);
                return;
            }
            default:
                syntaxErrorMsg(6);
                return;
        }
    }

    public static void syntaxErrorMsg(int i)
    {
        syntaxErrorFound = true;

        display(t + " : Syntax Error, unexpected symbol where ");

        switch( i )
        {
            case 1: displayln(": expected"); return;
            case 2: displayln("integer expected"); return;
            case 3: displayln("unsigned integer expected"); return;
            case 4: displayln("floating-point number expected"); return;
            case 5: displayln(", expected"); return;
            case 6: displayln("instruction name or label expected"); return;
        }
    }

    public static void errorMsg(int i, int label, String instName) // miscellaneous error messages
    {
        errorFound = true;

        switch( i )
        {
            case 1: displayln("Label "+label+" occurs more than once."); return;
            case 2: displayln("Target label "+label+" of "+instName+" instruction is missing."); return;
            case 3: displayln("Limit on # of instructions "+(instStoreSize-1)+" exceeded."); return;
        }
    }

    public static void updateLabels()

    // Update target labels of comparison-jump instructions, "goto", "invoke" to indexes of "instStore[]".

    {
        int j = 0;

        while ( instStore[j] != null )
        {
            if ( instStore[j] instanceof CmpInst ||
                    instStore[j] instanceof Goto ||
                    instStore[j] instanceof Invoke )
            {
                instStore[j].updateLabel();
            }
            j++;
        }
    }

    public static void displayInstStore()
    {
        int j = 0;

        while ( instStore[j] != null )
        {
            displayln(j + ": " + instStore[j]);
            j++;
        }
    }

    public static void main(String argv[])
    {
        setIO( argv[0], argv[1] );
        setLex();

        getToken();
        instructionList();
        if ( ! t.isEmpty() )
        {
            displayln(t + "  -- unexpected symbol");
            syntaxErrorFound = true;
        }
        if ( syntaxErrorFound || errorFound )
        {
            System.out.println("Errors found -- see the file "+argv[1]);
            closeIO();
        }
        else
        {
            updateLabels();
            if ( errorFound )
            {
                System.out.println("Errors found -- see the file "+argv[1]);
                closeIO();
            }
            else
            {
                displayInstStore();
                closeIO();
            }
        }
    }
}
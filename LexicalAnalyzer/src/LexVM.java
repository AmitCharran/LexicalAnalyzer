import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class LexVM extends IO {
    private String infilePath;
    private String outfilePath;
    private String state;

    //Constructor
    public LexVM(){
        infilePath = "";
        outfilePath = "";
    }
    public LexVM(String i, String o){
        infilePath = i;
        outfilePath = o;
    }

    //Accessors and Mutators
    public String getInfilePath() {
        return infilePath;
    }

    public void setInfilePath(String infilePath) {
        this.infilePath = infilePath;
    }

    public String getOutfilePath() {
        return outfilePath;
    }

    public void setOutfilePath(String outfilePath) {
        this.outfilePath = outfilePath;
    }
    //Class functions
    public void openFiles(){
        setIO(infilePath,outfilePath);
    }
    public void closeFiles(){
        closeIO();
    }
    //****
    //This is a good example of how to iterate through the whole file
    public void printAll(){
        while(a != -1){
            System.out.print((char)a);
            a = getNextChar();
        }
    }
    public void output(String s){
        displayln(s);
    }
    public boolean isLetter(){
        return Character.isLetter(a);
    }
    public void instructionName(StringBuilder ans){
        switch(ans.toString()){
            case "iconst":
                displayln(ans.toString() + "     : iconst");
                break;
            case "iload":
                displayln(ans.toString() + "     : iload");
                break;
            case "istore":
                displayln(ans.toString() + "     : istore");
                break;
            case "fconst":
                displayln(ans.toString() +"     : fconst");
                break;
            case "fload":
                displayln(ans.toString() +"     : fload");
                break;
            case "fstore":
                displayln(ans.toString() + "     : fstore");
                break;
            case "iadd":
                displayln(ans.toString() + "     : iadd");
                break;
            case "isub":
                displayln(ans.toString() + "     : isub");
                break;
            case "icmpge":
                displayln(ans.toString() + "      : icmpge");
                break;
            case "imul":
                displayln(ans.toString() + "     : imul");
                break;
            case "idiv":
                displayln(ans.toString() + "     : idiv");
                break;
            case "fadd":
                displayln(ans.toString() + "     : fadd");
                break;
            case "fsub":
                displayln(ans.toString() +"     : fsub");
                break;
            case "fmul":
                displayln(ans.toString()+"     : fmul");
                break;
            case "fdiv":
                displayln(ans.toString()+"     : fdiv");
                break;
            case "intToFloat":
                displayln(ans.toString() +"     : intToFloat");
                break;
            case "icmpeq":
                displayln(ans.toString() + "     : icmpeq");
                break;
            case "icmpne":
                displayln(ans.toString() + "     : icmpne");
                break;
            case "icmplt":
                displayln(ans.toString() + "     : icmplt");
                break;
            case "icmple":
                displayln(ans.toString() + "     : icmple");
                break;
            case "icmpgt":
                displayln(ans.toString() + "     : icmpgt");
                break;
            case "icempge":
                displayln(ans.toString() + "     : icempge");
                break;
            case "fcmpeq":
                displayln(ans.toString() + "     : fcmpeq");
                break;
            case "fcmpne":
                displayln(ans.toString() + "     : fcmpne");
                break;
            case "fcmplt":
                displayln(ans.toString() + "     : fcmplt");
                break;
            case "fcmple":
                displayln(ans.toString() + "     : fcmple");
                break;
            case "fcmpgt":
                displayln(ans.toString()+ "     : fcmpgt");
                break;
            case "fcmpge":
                displayln(ans.toString() + "     : fcmpge");
                break;
            case "goto":
                displayln(ans.toString() + "     : goto");
                break;
            case "invoke":
                displayln(ans.toString() + "     : invoke");
                break;
            case "return":
                displayln(ans.toString() + "     : return");
                break;
            case "ireturn":
                displayln(ans.toString() + "     : ireturn");
                break;
            case "freturn":
                displayln(ans.toString() + "     : freturn");
                break;
            case "print":
                displayln(ans.toString() + "     : print");
                break;

                default:
                    displayln(ans.toString() + "     : Lexical Error, invalid Error");


        }
    }




//Non-Final States
    public void start(){
        //Non-final State
        while(a != -1) {
            StringBuilder ans = new StringBuilder("");
            if (a == ':') {
                ans.append((char) a);
                colon(ans);
            }
           else  if (a == ',') {
                ans.append((char) a);
                comma(ans);
            }
            else if (Character.isDigit((char) a)) {
                unsignedInt(ans);
            }
            else if (a == '+') {
                ans.append((char) a);
                a = getNextChar();
                add(ans);
            }
            else if (a == '.') {
                ans.append((char) a);
                a = getNextChar();
                period(ans);
            }
            else if (a == '-') {
                ans.append((char) a);
                a = getNextChar();
                sub(ans);
            }else if(Character.isLetter(a)) {
                id(ans);
            }
            else {
                a = getNextChar();
            }
        }
    }

    public void sub(StringBuilder ans){
        //Non-final state
        if(Character.isDigit((char)a)){
            signedInt(ans);
        }
        else if(a == '.'){
            ans.append((char)a);
            a = getNextChar();
            period(ans);
        }else{
            displayln(ans.toString() + "    : Lexical Error, invalid Token");
        }
    }

    public void e(StringBuilder ans){
        //Non-Final State
        if(a == '+' || a == '-'){
            ans.append((char)a);
            a = getNextChar();
            floatE(ans);
        }
        else if(Character.isDigit(a)){
            floatE(ans);
        }else{
            displayln(ans.toString() + "     : Lexical Error, invalid Token");
        }

    }
    public void add(StringBuilder ans){
        //Non-Final State
        if(Character.isDigit((char)a)){
            signedInt(ans);
        }
        else if(a == '.'){
            ans.append((char)a);
            a = getNextChar();
            period(ans);
        } else{
            displayln(ans.toString() + "    : Lexical Error, invalid Token");
        }
    }

    public void period(StringBuilder ans){
        //Non-Final State
        if(Character.isDigit(a)){
            Float(ans);
        }else{
            displayln(ans.toString() + "    : Lexical Error, invalid Token");
        }

    }







    //Final States
    public void colon(StringBuilder ans) {
        //final state
        displayln(ans.toString() + "      : colon");
        a = getNextChar();
    }
    public void comma(StringBuilder ans){
        //final state
            displayln(ans.toString() + "       : comma");
            a = getNextChar();

    }
    public void id(StringBuilder ans){
        //final state
        while(Character.isLetter(a)){
            ans.append((char)a);
            a = getNextChar();
        }
        instructionName(ans);

    }
    void unsignedInt(StringBuilder ans){
        //final state
            digit(ans);

            if(a == '.'){
                ans.append((char)a);
                a = getNextChar();
                Float(ans);
                return;
            }
            displayln(ans.toString() + "     : unsignedInt");

    }
    void signedInt(StringBuilder ans){
        //final state
            digit(ans);
            if(a == '.'){
                ans.append((char)a);
                a = getNextChar();
                Float(ans);
                return;
            }

            displayln(ans.toString() +"     : signedInt");
    }
    void Float(StringBuilder ans){
        //final state
      digit(ans);
      if(a == 'e' || a ==  'E'){
          ans.append((char)a);
          a = getNextChar();
          e(ans);
          return;
      }
      displayln(ans.toString() + "      : float");
    }
    public void floatE(StringBuilder ans){
        //final State
        if(Character.isDigit(a)){
        digit(ans);
        displayln(ans.toString() + "      : floatE");
        }else{
            displayln(ans.toString() + "     : Lexical Error, invalid Token");
        }
    }

    public void digit(StringBuilder ans){
        while(Character.isDigit(a)){
            ans.append((char)a);
            a = getNextChar();
        }
    }
















    ////
    //Don't need this code
    void E(){
        term();
        if(a == '+' || a == '-'){
           a = getNextChar();
            E();
        }
    }
    void term(){
        primary();
        if(a == '*' || a == '/'){
           a = getNextChar();
           E();
        }
    }
    void primary(){
        //Need to add character is float
        if(Character.isLetter(a) || Character.isDigit(a)){
            a = getNextChar();
        }else if(a == '-'){
            a = getNextChar();
            primary();
        }else if(a == '('){
            a = getNextChar();
            E();
            if(a == ')')
                a = getNextChar();
            else
                System.out.println("Error: ) expected");
        }
        else
            System.out.println("Error: <id>, <int> , <float> , - , or ( expected");
    }

}

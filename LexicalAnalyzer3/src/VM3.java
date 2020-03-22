public abstract class VM3 extends VM {
    private static Instruction instStore[] = getInstStoreArray();
    private static int pc = 0;
    private static String stack[] = new String[instStoreSize];
    private static int top = -1;


    private static String[] arrayOfInstructions = arrayOfInstr();
    private static String[] arrayOfValues = arrayOfValues();
    private static ActivationRecord allActivationRecord[] = createArrayOfActivationRecord(arrayOfInstructions, arrayOfValues);
    private static String vars[][] = new String[instStoreSize][]; // holds all the vars, each element will hold an array

    private static int raStack[] = new int[instStoreSize]; //probably doesnt need to be this long
    private static int raTop = -1;

    public static void main(String argv[])
    {
        setIO( argv[0], argv[1] );
        setLex();
        initialzeInstStoreArray(argv[0],argv[1]);

        arrayOfInstructions = arrayOfInstr();
        arrayOfValues = arrayOfValues();
        allActivationRecord = createArrayOfActivationRecord(arrayOfInstructions, arrayOfValues);

        int x = 0;
        int iteration = 0;


        displayln("I mentioned in the email. This is how far I reached." +
                "\nI was able to get the order of the instructions." +
                "\nHowever I realized I do not have the correct understanding of how to handle" +
                "\nall the variables. For examples, which ones I can save on the stack" +
                "\nand how to handle computation if they are in the vars array" +
                "\nAlso I don't think the order is correct, since I can't compute the values correct I" +
                " don't get the correct values for icmpge,fcmpge...\nI do not get the right jumps" +
                "\nThat's why in the second test case, there is an infinite loop and program crashes because of" +
                "\nArray out of bounds Exception");

        displayln("\n\nHere is the order of the instructions" +
                "\nThey are in order and I numbered them");


        while(instStore[pc] != null){
           displayln(iteration + ": " + instStore[pc]);
            chooseInsr();
            iteration++;


            //System.out.println(pc);
//            x++;
//            if(x == 100){
//                break;
//            }
        }
//        for(int i = 0; i < stack.length; i++){
//            if(stack[i] != null)
//            System.out.println(stack[i]);
//        }

        closeIO();

    }












    public static void intToFloat(){
        //here I would convert from the top of the stack to float
        pc++;
    }
    public static void goTo(){
        int x = Integer.parseInt(arrayOfValues[pc].trim());
        pc = x;
    }

    public static void invoke(){
        pushAR(pc);

        //getting k2 and k3
        String[] split = allActivationRecord[pc].getValue().split(" ");
        for(int i = 0; i < split.length; i++){
            split[i] = split[i].replaceAll(",$", "");
        }
        String x =  split[1];
        int xInt = Integer.parseInt(x);

        String y =  split[2];
        int yInt = Integer.parseInt(y);

        vars[pc] = new String[yInt+xInt];
        for(int i = 0; i < yInt+xInt; i++){
            vars[pc][i] = "";
        }

        // Right here I might put a goto next instruction if needed

            for(int i = 0; i < yInt+xInt; i++){
                if(!isEmpty()) {
                    vars[pc][i] = pop();
                }
            }




        pc = Integer.parseInt(split[0]);
    }

    public static void Return(){
        pc = popAR() + 1;

        // maybe also pop stack
    }
    public static void ReturnItem(){
        int current = pc;
        pc = popAR();
        for(int i = 0; i < vars[pc].length; i++){
            if(vars[pc][i] == ""){
                vars[pc][i] = arrayOfValues[current];
                push(arrayOfValues[pc]);
            }
        }

        pc = pc+1;
    }
    public static void load(){
       // System.out.println(arrayOfValues[pc] + "load");

        push(arrayOfValues[pc]);

//        int prev = popAR();
//        pushAR(prev);
//
//        for(int i = 0; i < vars[prev].length; i++){
//            if(vars[prev][i] == ""){
//                vars[prev][i] = arrayOfValues[pc];
//            }
//        }
        pc++;

    }

    public static void Const(){
//        int prev = popAR();
//        pushAR(prev);
//
//        for(int i = 0; i < vars[prev].length; i++){
//            if(vars[prev][i] == ""){
//                vars[prev][i] = arrayOfValues[pc];
//            }
//        }
        push(arrayOfValues[pc]);
        pc++;
        //then increment pc from main
    }

    public static void print(){
        if(isEmpty())
        System.out.println(pop() + "PRINT");
        else {
           // System.out.println("stack is empty");
        }
        pc++;
    }
    public static void store(){
        push(arrayOfValues[pc]);
        pc++;
    }
    public static void mul(){
        if(isEmpty()){
            //System.out.println("mul : stack is empty");
        }else{

        String top = "1";
        String topM1 ="1";

        if(isEmpty()){
            top = pop();
        }
            if(isEmpty()){
                topM1 = pop();
            }

        String toPush = topM1 + " * " + top;
       // System.out.println(topM1 + " * " + top);
        push(toPush);
        }
        pc++;
    }
    public static void div(){
        if(isEmpty()){
           // System.out.println("div : stack is empty");
        }else {
            String top = "1";
            String topM1 ="1";

            if(isEmpty()){
                top = pop();
            }
            if(isEmpty()){
                topM1 = pop();
            }
            String toPush = topM1 + " / " + top;
            //System.out.println(topM1 + " / " + top);
            push(toPush);
        }
        pc++;
    }
    public static void sub(){
        if(isEmpty()){
           // System.out.println("sub : stack is empty");
        }else {
            String top = "0";
            String topM1 ="0";

            if(isEmpty()){
                top = pop();
            }
            if(isEmpty()){
                topM1 = pop();
            }
            String toPush = topM1 + " - " + top;
           // System.out.println(topM1 + " - " + top);
            push(toPush);
        }
        pc++;
    }
    public static void add(){
        if(isEmpty()){
            //System.out.println("add : stack is empty");
        }else {
            String top = "0";
            String topM1 ="0";

            if(isEmpty()){
                top = pop();
            }
            if(isEmpty()){
                topM1 = pop();
            }
            String toPush = topM1 + " + " + top;
          //  System.out.println(toPush);
            push(toPush);
        }
        pc++;
    }

    public static void chooseInsr(){

        switch(arrayOfInstructions[pc].trim()){
            case "invoke": invoke(); break;
            case "fstore": store(); break;
            case "istore": store(); break;
            case "return": Return(); break;
            case "freturn": ReturnItem(); break;
            case "ireturn": ReturnItem();break;
            case "fmul": mul(); break;
            case "imul": mul(); break;
            case "iadd": add(); break;
            case "fadd": add();break;
            case "isub": sub();break;
            case "fsub": sub();break;
            case "idiv": div();break;
            case "fdiv": div();break;
            case "fload": load();break;
            case "iload": load();break;
            case "goto": goTo();break;
            case "fconst": Const();break;
            case "iconst": Const();break;
            case "print": print();break;
            case "intToFloat": intToFloat();break; // this does nothing
            case "icmpne":icmpne();break;
            case "icmpeq":icmpeq();break;
            case "icmpge":icmpge();break;
            case "icmplt":icmplt();break;
            case "icmple":icmple();break;
            case "icmpgt":icmpgt();break;
            case "fcmpeq":fcmpeq();break;
            case "fcmpne":fcmpne();break;
            case "fcmplt":fcmplt();break;
            case "fcmple":fcmple();break;
            case "fcmpgt":fcmpgt();break;
            case "fcmpge":fcmpge();break;
            default:
                System.out.println("can't find instruction");
        }


    }

    private static void fcmpge() {
        //System.out.println("fcmpge is called");
        pc++;
    }

    private static void fcmpgt() {
        //System.out.println("fcmpgt is called");
        pc++;
    }

    private static void fcmple() {
        //System.out.println("fcmple is called");
        pc++;
    }

    private static void fcmplt() {
        //System.out.println("fcmplt is called");
        pc++;
    }

    private static void fcmpne() {
        //System.out.println("fcmpne is called");
        pc++;
    }

    private static void fcmpeq() {
        //System.out.println("fcmpeq is called");
        pc++;
    }

    private static void icmpgt() {
        //System.out.println("icmpgt is called");
        pc++;
    }

    private static void icmple() {
        //System.out.println("icmplt is called");
        pc++;
    }

    private static void icmplt() {
        //System.out.println("icmplt is called");
        pc++;
    }

    private static void icmpge() {
        //System.out.println("icmpge is called");
        pc++;
    }

    private static void icmpeq() {
        //System.out.println("icmpeq is called");
        pc++;
    }

    private static void icmpne() {
        //System.out.println("icmpne is called");
        pc++;
    }


    //this is pretty much a stack
    public static void push(String k){
        stack[++top] = k;
    }

    public static String pop(){
        String ans = stack[top--];
        return ans;
    }

    public static boolean isEmpty(){
        return (top == -1);
    }
    public static boolean isFull(){
        return top >= stack.length - 1;
    }



    public static ActivationRecord[] createArrayOfActivationRecord(String[] instruction, String[] value){
        ActivationRecord ans[] = new ActivationRecord[instStoreSize];

        for(int i = 0;i < instStoreSize; i++){
            ans[i] = null;
        }
        int j = 0;
        while(!instruction[j].equals("")){
                ans[j] = new ActivationRecord(instruction[j], value[j]);
                j++;
        }


        return ans;

    }

    public static void printStringArray(String[] ans){

        for(int i = 0; i < ans.length;i++){
            if(!ans[i].equals(""))
            System.out.println(ans[i]);
        }
    }
    public static void printInstStore(){
        int j = 0;
        while(instStore[j] != null){
            System.out.println(instStore[j]);
            j++;
        }
    }

    public static String[] arrayOfInstr(){
        String ans[] = new String[instStoreSize];
        for(int i = 0; i < ans.length; i++){
            ans[i] = "";
        }
        int i = 0;
        while(instStore[i] != null) {
                String str = instStore[i].toString();
                String[] splited = str.split(" ");
                ans[i] = splited[0];
                i++;
        }
        return ans;
    }
    public static String[] arrayOfValues(){
        String ans[] = new String[instStoreSize];
        for(int i = 0; i < ans.length; i++){
            ans[i] = "";
        }
        int i = 0;
        while(instStore[i] != null) {
            String str = instStore[i].toString();

            String[] splited = str.split(" ");

            if(ans.length != 1){
                String s = "";
                for(int j = 1; j < splited.length; j++){
                    s += (splited[j] + " ");
                }
                ans[i] = s;
            }
            i++;
        }
        return ans;
    }




    public static class ActivationRecord{
        private int returnAddress;
        private String instr;
        private String value;

        public ActivationRecord(int ra, String s, String v){
            returnAddress = ra;
            instr = s;
            value = v;

        }
        public ActivationRecord(String s, String v){
            returnAddress = -1;
            instr = s;
            value = v;
        }

        public int getReturnAddress() {
            return returnAddress;
        }

        public void setReturnAddress(int returnAddress) {
            this.returnAddress = returnAddress;
        }

        public String getInstr() {
            return instr;
        }

        public void setInstr(String instr) {
            this.instr = instr;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String toString(){
            return instr;
        }
    }
    public static void pushAR(int k){

        raStack[++raTop] = k;
    }

    public static int popAR(){
        int ans = raStack[raTop--];
        return ans;
    }

    public static boolean isEmptyAR(){
        return raTop == -1;
    }
    public static boolean isFullAR(){
        return raTop >= stack.length - 1;
    }

}

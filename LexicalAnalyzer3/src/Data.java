import java.util.Stack;

public abstract class Data{
    // This is the "single universal operand stack for all function calls"
    private Data stack[] = new Data[10001];


    // the value of pc ranges over the index instStore array so size is between 0 - 10000
    public int pc; // it has the instruction currently being executed



    private int top =  -1;

//
//    // Maybe might not need this code
//    public Data(){
//        // give this function calls that equivalent to stack and variable private
//        stack = new Data[10000];
//        top = -1;
//    }

    String instructionName; // this will be instruction name
    String instructionValue; // this will be instruction value

    //this is pretty much a stack
    public void push(Data k){
        stack[++top] = k;
    }

    public Data pop(){
        Data ans = stack[top--];
        return ans;
    }

    public boolean isEmpty(){
        return top <= -1;
    }
    public boolean isFull(){
        return top >= stack.length - 1;
    }


}

import sun.rmi.server.Activation;

final class Invoke extends Instruction
{
    int startLabel;
    int numOfParams;
    int numOfLocalVars;

    // Project 3 part
    ActivationRecord ar; // constructing a new activation record "ar"
    Data vars[]; // size k2+ k3;

    static String invoke = "invoke";

    Invoke(int i, int j, int k)
    {
        startLabel = i; //k1
        numOfParams = j; //k2
        numOfLocalVars = k; //k3

        ActivationRecord ar = new ActivationRecord();
        ar.pc = i;
        vars = new Data[j + k];

        // Pop top k2 objects from the operand stack; assign stack[top−k2+1], …, stack[top], respectively, to vars[0], …, vars[k2−1]. This performs parameter passing.






    }

    public void invokeP3(){
        ActivationRecord ar = new ActivationRecord();
        vars = new Data[numOfParams + numOfLocalVars];

        // Pop top k2 objects from the operand stack; assign stack[top−k2+1], …, stack[top], respectively, to vars[0], …, vars[k2−1]. This performs parameter passing.

        for(int i = 0; i < numOfParams;i++){
         vars[i] = pop();
        }

        ar.returnAddress = ar.pc + 1;

        push(ar);
        pc = startLabel; //pc = k1

    }

    public String toString()
    {
        return invoke + " " + startLabel + ", " + numOfParams + ", " + numOfLocalVars;
    }

    String instName()
    {
        return invoke;
    }

    void updateLabel()
    {
        if ( VM.labelMap.containsKey(startLabel) )
            startLabel = VM.labelMap.get(startLabel);
        else
            VM.errorMsg(2, startLabel, invoke);
    }

    @Override
    void execute() {


    }


}
final class Invoke extends Instruction
{
    int startLabel;
    int numOfParams;
    int numOfLocalVars;

    static String invoke = "invoke";

    Invoke(int i, int j, int k)
    {
        startLabel = i;
        numOfParams = j;
        numOfLocalVars = k;
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
}
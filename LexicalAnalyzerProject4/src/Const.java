abstract class Const extends Instruction
{
    abstract Data getOperand();

    void execute()
    {
        VM.top++;
        if ( VM.top == VM.opStackSize )  // operand stack overflow
            VM.runtimeError(1, VM.pc, toString(), 0);
        VM.opStack[VM.top] = getOperand();
        VM.pc++;
        VM.updateOpStackPeakSize();
    }


    static final class Iconst extends Const
    {
        int val; // val to be pushed onto operand stack

        Iconst(int i)
        {
            val = i;
        }

        public String toString()
        {
            return "iconst "+val;
        }

        String instName()
        {
            return "iconst";
        }

        Data getOperand()
        {
            return new Int(val);
        }
    }

    static final class Fconst extends Const
    {
        double val; // val to be pushed onto operand stack

        Fconst(double d)
        {
            val = d;
        }

        public String toString()
        {
            return "fconst "+val;
        }

        String instName()
        {
            return "fconst";
        }

        Data getOperand()
        {
            return new Floatp(val);
        }
    }
}
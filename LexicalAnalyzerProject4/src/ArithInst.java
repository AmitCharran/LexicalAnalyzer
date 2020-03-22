abstract class ArithInst extends Instruction
{
    static final class Iadd extends ArithInst
    {
        String instName()
        {
            return "iadd";
        }

        void execute()
        {
            ((Int)(VM.opStack[VM.top-1])).val = ((Int)(VM.opStack[VM.top-1])).val + ((Int)(VM.opStack[VM.top])).val;
            VM.opStack[VM.top] = null;
            VM.top--;
            VM.pc++;
        }
    }

    static final class Isub extends ArithInst
    {
        String instName()
        {
            return "isub";
        }

        void execute()
        {
            ((Int)(VM.opStack[VM.top-1])).val = ((Int)(VM.opStack[VM.top-1])).val - ((Int)(VM.opStack[VM.top])).val;
            VM.opStack[VM.top] = null;
            VM.top--;
            VM.pc++;
        }
    }

    static final class Imul extends ArithInst
    {
        String instName()
        {
            return "imul";
        }

        void execute()
        {
            ((Int)(VM.opStack[VM.top-1])).val = ((Int)(VM.opStack[VM.top-1])).val * ((Int)(VM.opStack[VM.top])).val;
            VM.opStack[VM.top] = null;
            VM.top--;
            VM.pc++;
        }
    }

    static final class Idiv extends ArithInst
    {
        String instName()
        {
            return "idiv";
        }

        void execute()
        {
            int divisor = ((Int)(VM.opStack[VM.top])).val;
            if ( divisor == 0 )
                VM.runtimeError(4, VM.pc, "idiv", 0);
            ((Int)(VM.opStack[VM.top-1])).val = ((Int)(VM.opStack[VM.top-1])).val / divisor;
            VM.opStack[VM.top] = null;
            VM.top--;
            VM.pc++;
        }
    }

    static final class Fadd extends ArithInst
    {
        String instName()
        {
            return "fadd";
        }

        void execute()
        {
            ((Floatp)(VM.opStack[VM.top-1])).val = ((Floatp)(VM.opStack[VM.top-1])).val + ((Floatp)(VM.opStack[VM.top])).val;
            VM.opStack[VM.top] = null;
            VM.top--;
            VM.pc++;
        }
    }

    static final class Fsub extends ArithInst
    {
        String instName()
        {
            return "fsub";
        }

        void execute()
        {
            ((Floatp)(VM.opStack[VM.top-1])).val = ((Floatp)(VM.opStack[VM.top-1])).val - ((Floatp)(VM.opStack[VM.top])).val;
            VM.opStack[VM.top] = null;
            VM.top--;
            VM.pc++;
        }
    }

    static final class Fmul extends ArithInst
    {
        String instName()
        {
            return "fmul";
        }

        void execute()
        {
            ((Floatp)(VM.opStack[VM.top-1])).val = ((Floatp)(VM.opStack[VM.top-1])).val * ((Floatp)(VM.opStack[VM.top])).val;
            VM.opStack[VM.top] = null;
            VM.top--;
            VM.pc++;
        }
    }

    static final class Fdiv extends ArithInst
    {
        String instName()
        {
            return "fdiv";
        }

        void execute()
        {
            ((Floatp)(VM.opStack[VM.top-1])).val = ((Floatp)(VM.opStack[VM.top-1])).val / ((Floatp)(VM.opStack[VM.top])).val;
            VM.opStack[VM.top] = null;
            VM.top--;
            VM.pc++;
        }
    }
}
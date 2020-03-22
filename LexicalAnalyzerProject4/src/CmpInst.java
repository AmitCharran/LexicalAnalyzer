abstract class CmpInst extends Instruction
{
    int label; // go-to target label

    CmpInst(int i)
    {
        label = i;
    }

    public String toString()
    {
        return instName() + " " + label;
    }

    void updateLabel()
    {
        if ( VM.labelMap.containsKey(label) )
            label = VM.labelMap.get(label);
        else
            VM.errorMsg(2, label, instName());
    }

    abstract boolean compare();

    void execute()
    {
        if ( compare() )
            VM.pc = label;
        else
            VM.pc++;
        VM.opStack[VM.top] = null;
        VM.opStack[VM.top-1] = null;
        VM.top = VM.top-2;
    }

    static final class Icmpeq extends CmpInst
    {
        Icmpeq(int label)
        {
            super(label);
        }

        String instName()
        {
            return "icmpeq";
        }

        boolean compare()
        {
            return ((Int)(VM.opStack[VM.top-1])).val == ((Int)(VM.opStack[VM.top])).val;
        }
    }

    static final class Icmpne extends CmpInst
    {
        Icmpne(int label)
        {
            super(label);
        }

        String instName()
        {
            return "icmpne";
        }

        boolean compare()
        {
            return ((Int)(VM.opStack[VM.top-1])).val != ((Int)(VM.opStack[VM.top])).val;
        }
    }

    static final class Icmplt extends CmpInst
    {
        Icmplt(int label)
        {
            super(label);
        }

        String instName()
        {
            return "icmplt";
        }

        boolean compare()
        {
            return ((Int)(VM.opStack[VM.top-1])).val < ((Int)(VM.opStack[VM.top])).val;
        }
    }

    static final class Icmple extends CmpInst
    {
        Icmple(int label)
        {
            super(label);
        }

        String instName()
        {
            return "icmple";
        }

        boolean compare()
        {
            return ((Int)(VM.opStack[VM.top-1])).val <= ((Int)(VM.opStack[VM.top])).val;
        }
    }

    static final class Icmpgt extends CmpInst
    {
        Icmpgt(int label)
        {
            super(label);
        }

        String instName()
        {
            return "icmpgt";
        }

        boolean compare()
        {
            return ((Int)(VM.opStack[VM.top-1])).val > ((Int)(VM.opStack[VM.top])).val;
        }
    }

    static final class Icmpge extends CmpInst
    {
        Icmpge(int label)
        {
            super(label);
        }

        String instName()
        {
            return "icmpge";
        }

        boolean compare()
        {
            return ((Int)(VM.opStack[VM.top-1])).val >= ((Int)(VM.opStack[VM.top])).val;
        }
    }

    static final class Fcmpeq extends CmpInst
    {
        Fcmpeq(int label)
        {
            super(label);
        }

        String instName()
        {
            return "fcmpeq";
        }

        boolean compare()
        {
            return ((Floatp)(VM.opStack[VM.top-1])).val == ((Floatp)(VM.opStack[VM.top])).val;
        }
    }

    static final class Fcmpne extends CmpInst
    {
        Fcmpne(int label)
        {
            super(label);
        }

        String instName()
        {
            return "fcmpne";
        }

        boolean compare()
        {
            return ((Floatp)(VM.opStack[VM.top-1])).val != ((Floatp)(VM.opStack[VM.top])).val;
        }
    }

    static final class Fcmplt extends CmpInst
    {
        Fcmplt(int label)
        {
            super(label);
        }

        String instName()
        {
            return "fcmplt";
        }

        boolean compare()
        {
            return ((Floatp)(VM.opStack[VM.top-1])).val < ((Floatp)(VM.opStack[VM.top])).val;
        }
    }

    static final class Fcmple extends CmpInst
    {
        Fcmple(int label)
        {
            super(label);
        }

        String instName()
        {
            return "fcmple";
        }

        boolean compare()
        {
            return ((Floatp)(VM.opStack[VM.top-1])).val <= ((Floatp)(VM.opStack[VM.top])).val;
        }
    }

    static final class Fcmpgt extends CmpInst
    {
        Fcmpgt(int label)
        {
            super(label);
        }

        String instName()
        {
            return "fcmpgt";
        }

        boolean compare()
        {
            return ((Floatp)(VM.opStack[VM.top-1])).val > ((Floatp)(VM.opStack[VM.top])).val;
        }
    }

    static final class Fcmpge extends CmpInst
    {
        Fcmpge(int label)
        {
            super(label);
        }

        String instName()
        {
            return "fcmpge";
        }

        boolean compare()
        {
            return ((Floatp)(VM.opStack[VM.top-1])).val >= ((Floatp)(VM.opStack[VM.top])).val;
        }
    }
}
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
    }
}
abstract class ArithInst extends Instruction
{
    static final class Iadd extends ArithInst
    {
        String instName()
        {
            return "iadd";
        }
    }

    static final class Isub extends ArithInst
    {
        String instName()
        {
            return "isub";
        }
    }

    static final class Imul extends ArithInst
    {
        String instName()
        {
            return "imul";
        }
    }

    static final class Idiv extends ArithInst
    {
        String instName()
        {
            return "idiv";
        }
    }

    static final class Fadd extends ArithInst
    {
        String instName()
        {
            return "fadd";
        }
    }

    static final class Fsub extends ArithInst
    {
        String instName()
        {
            return "fsub";
        }
    }

    static final class Fmul extends ArithInst
    {
        String instName()
        {
            return "fmul";
        }
    }

    static final class Fdiv extends ArithInst
    {
        String instName()
        {
            return "fdiv";
        }
    }
}
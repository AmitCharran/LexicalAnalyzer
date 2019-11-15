abstract class ReturnInst extends Instruction
{
    static final class Return extends ReturnInst
    {
        String instName()
        {
            return "return";
        }
    }

    static final class IReturn extends ReturnInst
    {
        String instName()
        {
            return "ireturn";
        }
    }

    static final class FReturn extends ReturnInst
    {
        String instName()
        {
            return "freturn";
        }
    }
}
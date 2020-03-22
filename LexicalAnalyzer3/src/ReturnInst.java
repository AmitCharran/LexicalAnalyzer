abstract class ReturnInst extends Instruction
{
    public void returnAr(){
        ActivationRecord x = (ActivationRecord) pop();
        pc = x.returnAddress;
    }


    static final class Return extends ReturnInst
    {
        String instName()
        {
            return "return";
        }

        @Override
        void execute() {


        }
    }

    static final class IReturn extends ReturnInst
    {
        String instName()
        {
            return "ireturn";
        }

        @Override
        void execute() {


        }
    }

    static final class FReturn extends ReturnInst
    {
        String instName()
        {
            return "freturn";
        }

        @Override
        void execute() {

        }
    }
}
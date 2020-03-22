abstract class ArithInst extends Instruction
{
    static final class Iadd extends ArithInst
    {
        String instName()
        {
            return "iadd";
        }

        @Override
        void execute() {
            integer top = (integer) pop();
            integer topMinus1 = (integer) pop();

            integer ans = new integer(topMinus1.x + top.x);
            push(ans);
        }
    }

    static final class Isub extends ArithInst
    {
        String instName()
        {
            return "isub";
        }
        @Override
        void execute() {

        }
    }

    static final class Imul extends ArithInst
    {
        String instName()
        {
            return "imul";
        }
        @Override
        void execute() {

        }
    }

    static final class Idiv extends ArithInst
    {
        String instName()
        {
            return "idiv";
        }

        @Override
        void execute() {

        }
    }

    static final class Fadd extends ArithInst
    {
        String instName()
        {
            return "fadd";
        }

        @Override
        void execute() {

        }
    }

    static final class Fsub extends ArithInst
    {
        String instName()
        {
            return "fsub";
        }

        @Override
        void execute() {

        }
    }

    static final class Fmul extends ArithInst
    {
        String instName()
        {
            return "fmul";
        }

        @Override
        void execute() {

        }
    }

    static final class Fdiv extends ArithInst
    {
        String instName()
        {
            return "fdiv";
        }

        @Override
        void execute() {

        }
    }
}
abstract class Const extends Instruction
{
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

        @Override
        void execute() {


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

        @Override
        void execute() {

        }
    }
}
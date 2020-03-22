abstract class Load extends Instruction
{
    int index; // vars[index] to be pushed onto operand stack

    Load(int i)
    {
        index = i;
    }

    public String toString()
    {
        return instName() + " " + index;
    }

    static final class Fload extends Load
    {
        Fload(int i)
        {
            super(i);
        }

        String instName()
        {
            return "fload";
        }

        @Override
        void execute() {


        }
    }

    static final class Iload extends Load
    {
        Iload(int i)
        {
            super(i);
        }

        String instName()
        {
            return "iload";
        }

        @Override
        void execute() {


        }
    }
}
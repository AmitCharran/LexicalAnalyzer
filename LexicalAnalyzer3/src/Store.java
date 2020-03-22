abstract class Store extends Instruction
{
    int index; // top of stack popped and stored in vars[index]

    Store(int i)
    {
        index = i;
    }

    public String toString()
    {
        return instName() + " " + index;
    }

    static final class Istore extends Store
    {
        Istore(int i)
        {
            super(i);
        }

        String instName()
        {
            return "istore";
        }

        @Override
        void execute() {

        }
    }

    static final class Fstore extends Store
    {
        Fstore(int i)
        {
            super(i);
        }

        String instName()
        {
            return "fstore";
        }

        @Override
        void execute() {

        }
    }
}
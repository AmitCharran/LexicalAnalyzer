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
}
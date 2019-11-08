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
}
abstract class Instruction
{
    public String toString()
    {
        return instName();
    }

    abstract String instName();

    void updateLabel()
    {
    }
}
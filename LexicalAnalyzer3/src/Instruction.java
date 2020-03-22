abstract class Instruction extends Data
{
    public String toString()
    {
        return instName();
    }

    abstract String instName();

    void updateLabel()
    {
    }


    abstract void execute();
}
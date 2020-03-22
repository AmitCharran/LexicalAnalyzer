public class FloatingPointNumber extends Data{
    float x;
    public FloatingPointNumber(){
        x = Float.parseFloat(instructionValue);
    }
    public FloatingPointNumber(float x){
        this.x= x;
    }
}

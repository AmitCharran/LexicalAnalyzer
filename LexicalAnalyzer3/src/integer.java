public class integer extends Data {
        int x;
        public integer(){
            x = Integer.parseInt(instructionValue);
        }
        public integer(int x){
            this.x= x;
        }
}

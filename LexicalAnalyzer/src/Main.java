public class Main {

    public static void main(String[] args) {
        LexVM object = new LexVM("a.txt","b.txt");
        object.openFiles();
        object.start();
        object.closeFiles();

    }

}

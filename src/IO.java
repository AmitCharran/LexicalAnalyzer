import java.io.*;

public abstract class IO {
    public static BufferedReader inStream;
    public static PrintWriter outStream;

    public static int a;
    public static char c;

    // Returns the next character on the input stream
    public static int getNextChar(){
        try{
            return inStream.read();
        }catch(IOException e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int getChar(){
        // Returns the next non-whitespace character on the input stream
        // Returns -1, end-of-stream, if the end of the input stream is reached

        int i = getNextChar();
        //this code here, removes whitespaces basically
        while(Character.isWhitespace((char) i))
            i = getNextChar();

        return i;
    }
    public static void display(String s){
        outStream.print(s);
    }
    public static void displayln(String s){
        outStream.println(s);
    }

    public static void setIO(String infile, String outfile){
        try{
            inStream = new BufferedReader(new FileReader(infile));
            outStream = new PrintWriter(new FileOutputStream(outfile));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void closeIO(){
        try{
            inStream.close();
            outStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}

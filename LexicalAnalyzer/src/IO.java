import java.io.*;


//Note****
// This class defines I/O variables and functions used by lexical analyzers and parsers
// Since this is an abstract class you cannot create objects with it directly
// Other classes will use "extends IO" and then you can create objects but indirectly
// An Object which extends IO class can use IO's functions in their class


public abstract class IO{
    public static BufferedReader inStream;
    public static PrintWriter outStream;

    public static int a; // current input character on inStream
    public static char c; // used to convert "a" to char type

    public static int getNextChar(){
        //returns next character
        try{

            return inStream.read();
        }catch(IOException e){
            e.printStackTrace();
            return -1;
        }
    }
    public static int getChar(){
        //Returns next non-Whitespace character
        int i = getNextChar(); //using the function above so you don't have to keep writing exceptions
        while(Character.isWhitespace((char)i)){
                i = getNextChar();
        }
        return i;
    }
    public static void display(String s){
        outStream.print(s); //prints string to the outfile
    }
    public static void displayln(String s){
        outStream.println(s); //prints string to outfile and then next line
    }
    public static void setIO(String infile, String outfile){
        //Setting input and output files
        //Also sets the current character "a"
        try{
            inStream = new BufferedReader(new FileReader(infile));
            outStream = new PrintWriter(new FileOutputStream(outfile));
            a = inStream.read(); //when files are initialized, then a is set to first character
        }catch (FileNotFoundException e){
            //Need to put this exception first because FNFEx is in IOEx
            //Can also just only use IOEx if you want, but this might give more clarification if there's errors
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void closeIO(){
        try{
            inStream.close();
            outStream.checkError();
        }catch(IOException e){
            e.printStackTrace();
        }
    }



}

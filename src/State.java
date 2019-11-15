public enum State{

    // These States are the columns in the stateArray[13][128]
    // The Ordinal number is the state number basically
    // final state      ordinal number      token accepted
        Id,             // 0                Identifiers (only temporarily accepted)
        SignedInt,      // 1                signed Integers
        UnsignedInt,    // 2                unsigned integers
        Float,          // 3                floats without exponentiation part
        FloatE,         // 4                floats with exponentiation part
        Colon,          // 5                :
        Comma,          // 6                ;


    // non-final state                      string categorized
        Start,          // 7                the empty string
        Add,            // 8                +
        Sub,            // 9                -
        Period,         // 10               "." , "+." , "-."
        E,              // 11               float parts ending with E or e
        EPlusMinus,     // 12               float parts ending with + or - in exponentiation part


    // special instruction name states
        Iconst,
        Iload,
        Istore,
        Fconst,
        Fload,
        Fstore,
        Iadd,
        Isub,
        Imul,
        Idiv,
        Fadd,
        Fsub,
        Fmul,
        Fdiv,
        IntToFloat,
        Icmpeq,
        Icmpne,
        Icmplt,
        Icmple,
        Icmpgt,
        Icmpge,
        Fcmpeq,
        Fcmpne,
        Fcmplt,
        Fcmple,
        Fcmpgt,
        Fcmpge,
        Goto,
        Invoke,
        Return,
        Ireturn,
        Freturn,
        Print,

        UNDEF;

        // The number they use to compare is the "ordinal" number
        // the ordinal number is basically the state number in the array
        boolean isFinal(){
            // checks if the state less than or equal to "comma" or semi-colon
            // Meaning it is in the states
            // Id
            // SignedInt
            // UnsignedInt
            // Float
            // FloatE
            // Colon or
            // Comma
            // These are all final States
            return this.compareTo(State.Comma) <= 0;
        }
        boolean isInstName(){
            // This checks to see if they are in the "special instruction name" list from above
            // Anything in between Const.Iconst and Print (inclusive)
            // So in that instructionName list it does not include UNDEF
            return this.compareTo(State.Iconst) >= 0 && this.compareTo(State.Print) <= 0;
        }
        boolean isArithInstruction(){
            // in the "special instruction name" list
            // instructions between ArithInst.Iadd and ArithInst.Fdiv (inclusive)
            return this.compareTo(State.Iadd) >= 0 && this.compareTo(State.Fdiv) <= 0;
        }
        boolean isCmpInstruction(){
            // All the compare instructions in the "special instruction name" list
            // Instructions in between CmpInst.Icmpeq and CmpInst.Fcmpge
            return this.compareTo(State.Icmpeq) >= 0 && this.compareTo(State.Fcmpge) <= 0;
        }

}
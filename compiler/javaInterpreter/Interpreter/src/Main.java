import Lexer.LexicalAnalyzer;
import Lexer.LexicalAnalyzerImpl;
import Core.LexicalType;
import Core.LexicalUnit;

import java.io.File;


public class Main {
    public static final String SOURCE_PATH = "test1.bas";
    public static void main(String[] args) throws Exception{
        LexicalAnalyzer lex;
        LexicalUnit first;

        if(new File(SOURCE_PATH).exists() == false) {
            System.out.println("No file exists.");
            return;
        }

        lex = new LexicalAnalyzerImpl(SOURCE_PATH);
    }
}

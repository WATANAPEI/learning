import Core.Environment;
import Lexer.LexicalAnalyzer;
import Lexer.LexicalAnalyzerImpl;
import Core.LexicalUnit;
import Syntax.Node;
import Syntax.ProgramNode;

import java.io.File;


public class Main {
    public static final String SOURCE_PATH = "./Interpreter/test1.bas";
    public static void main(String[] args) throws Exception{
        String path = new File(".").getAbsoluteFile().getParent();
        System.out.println(path);
        try {
            LexicalAnalyzer lex;
            LexicalUnit first;
            Environment env;
            Node program;

            //First Exist check
            if (new File(SOURCE_PATH).exists() == false) {
                System.out.println("No file exists.");
                return;
            }

            lex = new LexicalAnalyzerImpl(SOURCE_PATH);
            env = new Environment(lex);
            first = lex.get();
            lex.unget(first);

            program = ProgramNode.isMatch(env, first);
            boolean res = program.parse();
            if (!res) {
                System.out.println("Syntax error");
                return;
            }
            // show AST
            System.out.println("--Syntax parsed--");
            System.out.println(program);
            System.out.println("-----------------");
            System.out.println("-------run!------");
            // execute program
            program.eval();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}

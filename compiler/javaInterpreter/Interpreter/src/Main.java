import Lexer.LexicalAnalyzer;
import Lexer.LexicalAnalyzerImpl;
import Core.LexicalType;
import Core.LexicalUnit;

public class Main {
    public static void main(String[] args) {
        LexicalAnalyzer analyzer = new LexicalAnalyzerImpl("test1.bas");

        LexicalUnit unit;
        for (unit = analyzer.get(); unit.type != LexicalType.EOF; unit = analyzer.get()) {
            System.out.println(unit);
        }
        System.out.println(unit);
    }
}

package Lexer;

import Core.LexicalType;
import Core.LexicalUnit;

public interface LexicalAnalyzer {
    public LexicalUnit get();
    public boolean expect(LexicalType type);
    public void unget(LexicalUnit token);
}

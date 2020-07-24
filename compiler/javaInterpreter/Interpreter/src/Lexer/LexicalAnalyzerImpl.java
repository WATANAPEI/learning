package Lexer;

import Core.LexicalType;
import Core.LexicalUnit;

import java.io.FileReader;
import java.util.Deque;


public class LexicalAnalyzerImpl implements LexicalAnalyzer {
    private TokenParser tokenParser;
    Deque<LexicalUnit> tokenBuffer;


    public LexicalAnalyzerImpl(String filePath) throws Exception {
        tokenParser = new TokenParser(new FileReader(filePath));
    }


    @Override
    public LexicalUnit get() {
        Token nextToken = tokenParser.get();

        if(nextToken == null) {
            return new LexicalUnit(LexicalType.EOF);
        }
        return nextToken.parseLexicalUnit();
    }

    @Override
    public boolean expect(LexicalType type) {
       return false;
    }

    @Override
    public void unget(LexicalUnit token) {
        this.tokenBuffer.add(token);

    }
}

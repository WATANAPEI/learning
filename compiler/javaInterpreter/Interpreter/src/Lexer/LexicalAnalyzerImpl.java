package Lexer;

import Core.LexicalType;
import Core.LexicalUnit;

import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.Deque;


public class LexicalAnalyzerImpl implements LexicalAnalyzer {
    private TokenParser tokenParser;
    Deque<LexicalUnit> tokenBuffer;


    public LexicalAnalyzerImpl(String filePath) throws Exception {
        tokenParser = new TokenParser(new FileReader(filePath));
        this.tokenBuffer = new ArrayDeque<LexicalUnit>();
    }


    @Override
    public LexicalUnit get() {
        if(tokenBuffer.size() == 0) {
            Token nextToken = tokenParser.get();

            LexicalUnit nextUnit;
            if(nextToken == null) {
                nextUnit = new LexicalUnit(LexicalType.EOF);
            } else {
                nextUnit = nextToken.parseLexicalUnit();
            }
            tokenBuffer.add(nextUnit);
        }
        return tokenBuffer.poll();
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

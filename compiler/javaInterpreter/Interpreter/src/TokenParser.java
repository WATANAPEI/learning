import java.io.IOException;
import java.io.Reader;
import java.util.EnumSet;
import java.util.Optional;

/**
 * Read stream and separate into Token
 *
 */
public class TokenParser {

    private Reader reader;
    private boolean readerIsEOF = false;

    public TokenParser(Reader reader) {
        super();
        this.reader = reader;
    }

    private String nextTokenBuff = "";

    public Token get() {
        if (readerIsEOF) {
            return null;
        }
        // get next character which is not EOF or space
        String tokenFirstStr = normalizeStr(nextTokenBuff);
        if(!readerIsEOF && tokenFirstStr.isEmpty()) {
            tokenFirstStr += readNextStr();
        }

        //get the first character equal to the first character of token
        TokenType tokenType = getMatchTokenType(tokenFirstStr);

        // keep reading until different character match
        String tokenVal = tokenFirstStr;
        while(!readerIsEOF && tokenType.isMatch(tokenVal)) {
            tokenVal += readNext();
        }

        // the last character is needed to keep to match next token
        this.nextTokenBuff = String.valueOf(tokenVal.charAt(tokenVal.length() - 1 ));

        //erase the last character from output string
        tokenVal = tokenVal.substring(0, tokenVal.length() - 1);

        return new Token(tokenType, tokenVal);
    }

    /**
     * find token type which matches to tokenstring
     * @param tokenFirstStr
     * @return
     */
    private TokenType getMatchTokenType(String tokenFirstStr) {
        Optional<TokenType> tokenTypeOpt = EnumSet.allOf(TokenType.class)
                .stream()
                .filter(t -> t.isMatch(tokenFirstStr))
                .findFirst();

        return tokenTypeOpt.get();
    }

    private String normalizeStr(String str) {
        return str
                .replaceAll("\t", " ")
                .replaceAll("\r", " ")
                .replaceAll(" ", "");
    }

    private String readNextStr() {
        String tmp = "";
        while(tmp.isEmpty() && !readerIsEOF) {
            char c = readNext();
            tmp += c;
            tmp = normalizeStr(tmp);
        }
        return tmp;
    }

    private char readNext() {

        if(this.readerIsEOF) {
            return 0;
        }

        int ci = 0;
        try {
            ci = reader.read();
        } catch(IOException e) {
            e.printStackTrace();
        }

        if(ci == -1) {
            this.readerIsEOF = true;
            try {
                reader.close();
            } catch(IOException e) {
                e.printStackTrace();
            }

            return 0;
        }

        return (char)ci;
    }

}

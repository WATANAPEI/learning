package lexer;

import org.xml.sax.ext.LexicalHandler;

public abstract class Token {
    String image;
    TokenType type;
    LexicalType lexicalType;
    public String getImage() {
        return this.image;
    }
    public TokenType tokenType() {
        return this.type;
    }

    public LexicalType lexicalType() {
        return this.lexicalType;
    }

}

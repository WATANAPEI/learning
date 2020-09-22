package lexer;

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

    public String toString() {
        return image + ": " + type.toString();
    }

}

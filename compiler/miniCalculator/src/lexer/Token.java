package lexer;

public abstract class Token {
    String image;
    TokenType type;
    public String getImage() {
        return this.image;
    }
    public TokenType tokenType() {
        return this.type;
    }

}

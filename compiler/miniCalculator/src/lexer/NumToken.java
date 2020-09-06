package lexer;

class NumToken extends Token {
    public NumToken(String image) {
        this.image = image;
        this.type = TokenType.NUMBER;
    }


}

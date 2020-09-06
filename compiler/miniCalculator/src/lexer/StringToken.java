package lexer;

class StringToken extends Token {
    public StringToken(String image) {
        this.image = image;
        this.type = TokenType.STRING;
    }


}

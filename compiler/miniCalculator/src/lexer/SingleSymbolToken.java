package lexer;

class SingleSymbolToken extends Token {
    public SingleSymbolToken(String image) {
        this.image = image;
        this.type = TokenType.SINGLE_SYMBOL;
    }


}

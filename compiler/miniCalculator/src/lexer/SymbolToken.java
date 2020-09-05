package lexer;

class SymbolToken extends Token {
    public SymbolToken(String image) {
        this.image = image;
        this.type = TokenType.Number;
    }


}

package lexer;

class WordToken extends Token {
    public WordToken(String image) {
        this.image = image;
        this.type = TokenType.WORD;
    }


}

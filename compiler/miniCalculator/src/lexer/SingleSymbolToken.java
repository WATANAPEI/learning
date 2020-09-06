package lexer;

import java.util.EnumSet;

class SingleSymbolToken extends Token {
    public SingleSymbolToken(String image){
        this.image = image;
        this.type = TokenType.SINGLE_SYMBOL;
        this.lexicalType = EnumSet.allOf(LexicalType.class).stream()
                .findAny().orElseThrow();

    }


}

package lexer;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

class SingleSymbolToken extends Token {
    public SingleSymbolToken(String image){
        this.image = image;
        this.type = TokenType.SINGLE_SYMBOL;
        //this.lexicalType = EnumSet.allOf(LexicalType.class).stream().findAny().orElseThrow();
        List<LexicalType> tmp = EnumSet.allOf(LexicalType.class).stream()
                .filter(e -> e.getImage().equals(image)).collect(Collectors.toList());
        if(tmp.size() > 1) {
            throw new IllegalStateException("2 or more LexicalType match with symbol");
        } else {
            this.lexicalType = tmp.get(0);
        }

    }


}

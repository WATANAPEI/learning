package parser;

import lexer.LexicalType;
import lexer.NullToken;
import lexer.Token;
import lexer.TokenType;

import java.util.Map;
import java.util.Optional;

/**
 * <Root> := {<Stmt>}
 * <Stmt> := <Expr> | <String> | <Assign>
 * <Assign> := <Word> <=> <Expr>
 * <Expr> := <Term> { <+|-> <Term>}
 * <Term> := <Factor> { <*|/> <Factor>}
 * <Factor> := <(> <Number> | <Word> | <Expression> <)>
 * @return
 */
class FactorNode extends Node {
    Node node;

    private FactorNode() {
    }

    public void addChildNode(Node node) {
        this.node = node;
    }

    public static Optional<Node> checkNode(Parser parser) {
        FactorNode factorNode = new FactorNode();
        Token token = parser.getCurrent().orElse(new NullToken());
        if(token.tokenType() == TokenType.SINGLE_SYMBOL
                && parser.checkCurrentLexicalType(LexicalType.OPEN_BRA)) {
            parser.consume(LexicalType.OPEN_BRA);
            Node exprNode = ExprNode.checkNode(parser).orElse(null);
            if(token.tokenType() == TokenType.SINGLE_SYMBOL
                    && parser.checkCurrentLexicalType(LexicalType.CLOSE_BRA)) {
                parser.consume(LexicalType.CLOSE_BRA);
                return Optional.ofNullable(exprNode);
            } else {
                throw new IllegalStateException("No closing bracket.");
            }
        }
        if(token.tokenType() == TokenType.NUMBER) {
            //token = parser.getNext().orElseThrow();
            factorNode.addChildNode(new NumberLiteralNode(token));
            parser.getNext(); // proceed a token
            return Optional.of(factorNode);
        }else if(token.tokenType() == TokenType.WORD){
            //token = parser.getNext().orElseThrow();
            factorNode.addChildNode(new WordNode(token));
            parser.getNext(); // proceed a token
            return Optional.of(factorNode);
        }else {
            throw new IllegalStateException("Parsing error: FactorNode");
        }
    }

    @Override
    public Optional<Value> value() {
        return Optional.ofNullable(node.value().orElse(null));
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable) {
        return node.eval(symbolTable);
    }
}

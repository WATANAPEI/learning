package node;

import lexer.LexicalType;
import lexer.Token;
import lexer.TokenType;
import parser.Parser;
import parser.Value;

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

    public static Node checkNode(Parser parser) {
        FactorNode factorNode = new FactorNode();
        Token token = parser.getCurrent()
                .orElseThrow(() -> new IllegalStateException("No Token."));
        if(parser.checkCurrentLexicalType(LexicalType.OPEN_BRA)) {
            Node bracketNode = BracketNode.checkNode(parser);
            factorNode.addChildNode(bracketNode);
            return factorNode;
        }
        if(token.checkTokenType(TokenType.NUMBER)) {
            factorNode.addChildNode(new NumberLiteralNode(token));
            parser.getNext(); // proceed a token
            return factorNode;
        }else if(token.checkTokenType(TokenType.WORD)){
            if(parser.checkNextLexicalType(LexicalType.OPEN_BRA)) {
                factorNode.addChildNode(FuncCallNode.checkNode(parser));
                return factorNode;
            }
            factorNode.addChildNode(new WordNode(token));
            parser.getNext(); // proceed a token
            return factorNode;
        }else if(token.checkTokenType(TokenType.STRING)) {
            factorNode.addChildNode(new StringLiteralNode(token));
            parser.getNext(); //proceed a token
            return factorNode;
        }else {
            throw new IllegalStateException("Parsing error: FactorNode");
        }
    }

    @Override
    public Optional<Value> value() {
        return Optional.ofNullable(node.value().orElse(null));
    }

    @Override
    public Optional<Value> eval(Map<String, Value> symbolTable, Map<String, Node> functionTable) {
        return node.eval(symbolTable, functionTable);
    }
}

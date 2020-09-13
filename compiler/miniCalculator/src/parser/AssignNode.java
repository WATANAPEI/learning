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
 * <Factor> := <Number>
 * @return
 */
class AssignNode extends Node {
    Node lhs;
    Node rhs;

    private AssignNode() {
    }

    private AssignNode(Node lhs, Node rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void assignVarNode(Node node) {
        this.lhs = node;
    }

    public void assignValueNode(Node node) {
        this.rhs = node;
    }

    public static Optional<Node> checkNode(Parser parser) {
        AssignNode assignNode = new AssignNode();
        Token token = parser.peekNext().orElse(new NullToken());
        if(token.tokenType() == TokenType.WORD) {
            Node varNode = WordNode.checkNode(parser).orElseThrow();
            if(parser.checkLexicalType(LexicalType.ASSIGN)) {
                assignNode.assignVarNode(varNode);
                parser.getNext();
                assignNode.assignValueNode(ExprNode.checkNode(parser).orElseThrow());
                return Optional.ofNullable(assignNode);
            } else {
                WordNode wordNode = new WordNode(token);
                return Optional.ofNullable(wordNode);
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Value> value() {
        return Optional.empty();
    }

    @Override
    public void eval(Map<String, Value> symbolTable) {
        symbolTable.put(lhs.value().orElseThrow().getSValue().orElseThrow(),
                rhs.value().orElse(null));
    }
}

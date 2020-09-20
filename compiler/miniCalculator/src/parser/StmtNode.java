package parser;

import lexer.LexicalType;
import lexer.NullToken;
import lexer.Token;
import lexer.TokenType;

import java.util.Map;
import java.util.Optional;

/**
 * <Root> := {<Stmt>}
 * <Stmt> := <Expr> | <String> | <Assign> | <If>
 * <If> := <If> <(> <Condition> <)> <Stmt> { <Else> <Stmt> }
 * <Assign> := <Word> <=> <Expr>
 * <Expr> := <Term> { <+|-> <Term>} | <Term> <Compare> <Term>
 * <Term> := <Factor> { <*|/> <Factor>}
 * <Factor> := <(> <Expr> <)> | <Word> | <Number>
 * <Compare> := <<>==>
 * @return
 */

class StmtNode extends Node {
    Node node;

    private StmtNode() {
    }

    public void addChildNode(Node node) {
        this.node = node;
    }

    public static Optional<Node> checkNode(Parser parser) {
        StmtNode stmtNode = new StmtNode();
        Token token = parser.getCurrent().orElse(new NullToken());
        if(token.tokenType() == TokenType.STRING) {
            //token = parser.getNext().orElseThrow();
            stmtNode.addChildNode(new StringLiteralNode(token));
            parser.getNext(); //proceed a token
            return Optional.of(stmtNode);
        }
        if(token.tokenType() == TokenType.NUMBER || token.tokenType() == TokenType.WORD) {
            //token = parser.getNext().orElseThrow();
            Token nextToken = parser.peekNext().orElse(new NullToken());
            if(nextToken.lexicalType() == LexicalType.ASSIGN) {
                Node assignNode = AssignNode.checkNode(parser).orElseThrow();
                stmtNode.addChildNode(assignNode);
                return Optional.of(stmtNode);

            } else {
                Node exprNode = ExprNode.checkNode(parser).orElseThrow();
                stmtNode.addChildNode(exprNode);
                return Optional.of(stmtNode);
            }
        } else {
            throw new IllegalStateException("Parsing error: StmtNode");
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

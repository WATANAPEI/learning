package node;

import lexer.LexicalType;
import lexer.NullToken;
import lexer.Token;
import lexer.TokenType;
import parser.Parser;
import parser.Value;

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

    public static Node checkNode(Parser parser) {
        StmtNode stmtNode = new StmtNode();
        Token token = parser.getCurrent()
                .orElseThrow(() -> new IllegalStateException("No Token."));
        if(token.tokenType() == TokenType.STRING) {
            //token = parser.getNext().orElseThrow();
            stmtNode.addChildNode(new StringLiteralNode(token));
            parser.getNext(); //proceed a token
            return stmtNode;
        } else if(token.tokenType() == TokenType.NUMBER || token.tokenType() == TokenType.WORD) {
            //token = parser.getNext().orElseThrow();
            Token nextToken = parser.peekNext().orElse(new NullToken());
            if(nextToken.lexicalType() == LexicalType.ASSIGN) {
                Node assignNode = AssignNode.checkNode(parser);
                stmtNode.addChildNode(assignNode);
                return stmtNode;
            } else {
                Node exprNode = ExprNode.checkNode(parser);
                stmtNode.addChildNode(exprNode);
                return stmtNode;
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

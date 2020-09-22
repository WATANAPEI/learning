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
 * <Stmt> := <Expr> | <String> | <Assign> | <If> | <Condition>
 * <If> := <If> <(> <Condition> <)> <Stmt> { <Else> <Stmt> }
 * <Assign> := <Word> <=> <Expr>
 * <Condition> := <Expr> <Compare> <Expr>
 * <Expr> := <Term> { <+|-> <Term>}
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
        if(token.checkTokenType(TokenType.STRING)) {
            stmtNode.addChildNode(new StringLiteralNode(token));
            parser.getNext(); //proceed a token
            return stmtNode;
        } else {
            if(parser.checkCurrentLexicalType(LexicalType.ID)
                    && parser.checkNextLexicalType(LexicalType.ASSIGN)) {
                Node assignNode = AssignNode.checkNode(parser);
                stmtNode.addChildNode(assignNode);
                return stmtNode;
            } else {
                Node lhs = ExprNode.checkNode(parser);
                if(parser.checkCurrentLexicalType(
                        LexicalType.GT,
                        LexicalType.LT,
                        LexicalType.GE,
                        LexicalType.LE,
                        LexicalType.NE,
                        LexicalType.EQ)) {
                    Token opToken = parser.getCurrent()
                            .orElseThrow(() -> new IllegalStateException("No Token."));
                    parser.getNext(); // consume operator token
                    stmtNode.addChildNode(new ConditionNode(opToken, lhs, ExprNode.checkNode(parser)));
                    return stmtNode;
                }
                stmtNode.addChildNode(lhs);
                return stmtNode;
            }
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

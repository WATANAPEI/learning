package node;

import lexer.LexicalType;
import lexer.Token;
import lexer.TokenType;
import parser.Parser;
import parser.Value;

import java.util.Map;
import java.util.Optional;

/**
 * <root> := {<stmt>}
 * <stmt> := <expr> | <assign> | <if> | <condition> | <for>
 * <for> := <FOR> ( <stmt> ; <condition> ; <stmt> ) <stmt>
 * <if> := <IF> <(> <condition> <)> <stmt> { <ELSE> <stmt> }
 * <assign> := <word> <=> <expr>
 * <condition> := <expr> <Compare> <expr>
 * <expr> := <term> { <+|-> <term>}
 * <term> := <factor> { <*|/> <factor>}
 * <factor> := <(> <expr> <)> | <word> | <number> | <string>
 * <compare> := <<>==>
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
        if (parser.checkCurrentLexicalType(LexicalType.IF)) {
            return IfNode.checkNode(parser);
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

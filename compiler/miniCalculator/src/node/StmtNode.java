package node;

import lexer.LexicalType;
import lexer.Token;
import parser.Parser;
import parser.Value;

import java.util.Map;
import java.util.Optional;

/**
 * <root> := { <funcdef> } { <stmtlist> }
 * <stmtlist> := { <stmt> }
 * <funcdef> :=  <FUNCTION> <word> <(> <word> <)> <stmtlist>
 * <stmt> := <expr> <;>
 *          | <assign> <;>
 *          | <if>
 *          | <condition> <;>
 *          | <for>
 *          | <return>
 * <for> := <FOR> <(> <assign> <;> <condition> <;> <assign> <)> <stmt>
 * <if> := <IF> <(> <condition> <)> <stmt> { <ELSE> <stmt> }
 * <assign> := <word> <=> <expr>
 * <condition> := <expr> <Compare> <expr>
 * <return> := <RETURN> <expr> <;>
 * <expr> := <term> { <+|-> <term>}
 * <term> := <factor> { <*|/> <factor>}
 * <factor> := <(> <expr> <)>
 *     | <word>
 *     | <number>
 *     | <string>
 *     | <invoke>
 * <invoke> := <word> <(> {<word>} <)>
 * <compare> := < <> | == | >= | <= | != >
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
            stmtNode.addChildNode(IfNode.checkNode(parser));
            return stmtNode;
        } else if (parser.checkCurrentLexicalType(LexicalType.FOR)) {
            stmtNode.addChildNode(ForNode.checkNode(parser));
            return stmtNode;
        } else if (parser.checkCurrentLexicalType(LexicalType.FUNC)) {
            stmtNode.addChildNode(FuncDefNode.checkNode(parser));
            return stmtNode;
        } else if (parser.checkCurrentLexicalType(LexicalType.RETURN)) {
            stmtNode.addChildNode(ReturnNode.checkNode(parser));
            return stmtNode;
        } else {
            if(parser.checkCurrentLexicalType(LexicalType.ID)
                    && parser.checkNextLexicalType(LexicalType.ASSIGN)) {
                Node assignNode = AssignNode.checkNode(parser);
                stmtNode.addChildNode(assignNode);
                parser.consume(LexicalType.SEMI_COLON);
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
                    parser.consume(LexicalType.SEMI_COLON);
                    return stmtNode;
                }
                stmtNode.addChildNode(lhs);
                parser.consume(LexicalType.SEMI_COLON);
                return stmtNode;
            }
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

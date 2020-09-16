package parser;

import java.util.Map;
import java.util.Optional;

class StmtNode extends Node {
    Node node;

    private StmtNode() {
    }

    public void addChildNode(Node node) {
        this.node = node;
    }

    public static Optional<Node> checkNode(Parser parser) {
        StmtNode stmtNode = new StmtNode();
        Node exprNode = ExprNode.checkNode(parser).orElse(null);
        if(exprNode != null) {
            stmtNode.addChildNode(exprNode);
            return Optional.of(stmtNode);
        } else {
            Node assignNode = AssignNode.checkNode(parser).orElse(null);
            if(assignNode != null) {
                stmtNode.addChildNode(assignNode);
                return Optional.of(stmtNode);
            } else {
                Node strNode = StringLiteralNode.checkNode(parser).orElse(null);
                if(strNode != null) {
                    stmtNode.addChildNode(strNode);
                    return Optional.of(stmtNode);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    @Override
    public Optional<Value> value() {
        return Optional.ofNullable(node.value().orElse(null));
    }

    @Override
    public Optional<String> eval(Map<String, Value> symbolTable) {
        return node.eval(symbolTable);
    }
}

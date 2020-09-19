package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <Root> := {<Stmt>}
 * <Stmt> := <Expr> | <String> | <Assign>
 * <Assign> := <Word> <=> <Expr>
 * <Expr> := <Term> { <+|-> <Term>}
 * <Term> := <Factor> { <*|/> <Factor>}
 * <Factor> := <(> <Number> | <Word> | <Expr> <)>
 * @return
 */

class RootNode extends Node {
    private List<Node> nodes;

    private RootNode() {
        nodes = new ArrayList();
    }

    public void addChildNode(Node node) {
        this.nodes.add(node);
    }

    public static Optional<Node> checkNode(Parser parser) {
        RootNode rootNode = new RootNode();
        Optional<Node> node = StmtNode.checkNode(parser);
        rootNode.addChildNode(node.orElseThrow());
        while(parser.peekNext().isPresent()) {
            node = StmtNode.checkNode(parser);
            rootNode.addChildNode(node.orElseThrow());
        }
        return Optional.ofNullable(rootNode);
    }


    /**
     * rootNode doesn't return value.
     * @return
     */
    @Override
    public Optional<Value> value() {
        return Optional.empty();
    }

    @Override
    public Optional<String> eval(Map<String, String> symbolTable) {
        // call eval() in each node
        StringBuilder sb = new StringBuilder();
        for(Node node: nodes) {
            if(sb.length() != 0) {
                sb.append("\n");
            }
            sb.append(node.eval(symbolTable)
                    .orElse(""));
        }
        return Optional.ofNullable(sb.toString());
    }
}

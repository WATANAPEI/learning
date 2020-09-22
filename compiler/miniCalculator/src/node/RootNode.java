package node;

import parser.Parser;
import parser.StringValue;
import parser.Value;

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

public class RootNode extends Node {
    private List<Node> nodes;

    private RootNode() {
        nodes = new ArrayList();
    }

    public void addChildNode(Node node) {
        this.nodes.add(node);
    }

    public static Node checkNode(Parser parser) {
        RootNode rootNode = new RootNode();
        Node node = StmtNode.checkNode(parser);
        rootNode.addChildNode(node);
        while(parser.getCurrent().isPresent()) {
            node = StmtNode.checkNode(parser);
            rootNode.addChildNode(node);
        }
        return rootNode;
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
    public Optional<Value> eval(Map<String, Value> symbolTable) {
        // call eval() in each node
        StringBuilder sb = new StringBuilder();
        for(Node node: nodes) {
            if(sb.length() != 0) {
                sb.append("\n");
            }
            node.eval(symbolTable).ifPresent(e -> {
                sb.append(e.getSValue().orElse(""));
            });
        }
        return Optional.ofNullable(new StringValue(sb.toString()));
    }
}

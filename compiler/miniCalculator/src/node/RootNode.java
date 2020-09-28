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
        rootNode.addChildNode(StmtListNode.checkNode(parser));
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
    public Optional<Value> eval(Map<String, Value> symbolTable, Map<String, Node> functionTable) {
        // call eval() in each node
        StringBuilder sb = new StringBuilder();
        for(Node node: nodes) {
            node.eval(symbolTable, functionTable).ifPresent(e -> {
                e.getSValue().ifPresent(f -> sb.append(f));
            });
        }
        return Optional.ofNullable(new StringValue(sb.toString()));
    }
}

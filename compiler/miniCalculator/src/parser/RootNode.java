package parser;

import lexer.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class RootNode extends Node {
    List<Node> nodes;

    public RootNode() {
        nodes = new ArrayList();
    }

    public void addChildNode(Node node) {
        this.nodes.add(node);
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
    public void eval() {
        // call eval() in each node
        nodes.stream()
                .forEach(e -> e.eval());
    }
}

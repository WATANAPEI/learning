package parser;

import lexer.LexicalType;
import lexer.NullToken;
import lexer.Token;
import lexer.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class RootNode extends Node {
    private List<Node> nodes;

    public RootNode() {
        nodes = new ArrayList();
    }

    public void addChildNode(Node node) {
        this.nodes.add(node);
    }

    public Optional<Node> checkNode(Parser parser) {
        Optional<Node> node = new StmtNode().checkNode(parser);
        addChildNode(node.orElseThrow());
        while(parser.peekNext().isPresent()) {
            node = new StmtNode().checkNode(parser);
            addChildNode(node.orElseThrow());
        }
        return Optional.ofNullable(this);
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
    public void eval(Map symbolTable) {
        // call eval() in each node
        nodes.stream()
                .forEach(e -> e.eval(symbolTable));
    }
}

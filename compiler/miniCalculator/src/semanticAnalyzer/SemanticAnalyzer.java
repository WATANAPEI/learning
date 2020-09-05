package semanticAnalyzer;

import parser.Node;

public class SemanticAnalyzer {
    Node node;
    public SemanticAnalyzer(Node node) {
        this.node = node;
    }

    public Node check() {
        //modify ast here(not implemented yet)
        return this.node;

    }
}

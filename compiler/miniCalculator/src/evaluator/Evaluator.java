package evaluator;

import parser.Node;

public class Evaluator {
    Node ast;

    public Evaluator(Node ast) {
        this.ast = ast;
    }

    public void eval() {
        // evaluate each node here
        ast.eval();
    }
}

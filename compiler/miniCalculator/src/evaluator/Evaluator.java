package evaluator;

import parser.Node;
import parser.Value;

import java.util.HashMap;
import java.util.Map;

public class Evaluator {
    Node ast;
    Map<String, String> symbolTable;

    public Evaluator(Node ast) {
        this.ast = ast;
        this.symbolTable = new HashMap<>();
    }

    public String eval() {
        // evaluate each node here
        return ast.eval(symbolTable)
                .orElse("This node has no value.");
    }
}

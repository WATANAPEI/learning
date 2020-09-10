package evaluator;

import parser.Node;
import parser.Value;

import java.util.HashMap;
import java.util.Map;

public class Evaluator {
    Node ast;
    Map<String, Value> symbolTable;

    public Evaluator(Node ast) {
        this.ast = ast;
        this.symbolTable = new HashMap<>();
    }

    public void eval() {
        // evaluate each node here
        ast.eval(symbolTable);
    }
}

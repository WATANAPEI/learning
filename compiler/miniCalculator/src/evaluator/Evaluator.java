package evaluator;

import node.Node;
import parser.StringValue;
import parser.Value;

import java.util.HashMap;
import java.util.Map;

public class Evaluator {
    Node ast;
    Map<String, Value> symbolTable;
    Map<String, Node> functionTable;

    public Evaluator(Node ast) {
        this.ast = ast;
        this.symbolTable = new HashMap<>();
        this.functionTable = new HashMap<>();
    }

    public String eval() {
        // evaluate each node here
        return ast.eval(symbolTable, functionTable)
                .orElse(new StringValue("This node has no value."))
                .getSValue()
                .orElse("This node has no String value.");
    }
}

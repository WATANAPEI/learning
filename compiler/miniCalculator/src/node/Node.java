package node;

import parser.Value;

import java.util.Map;
import java.util.Optional;

public abstract class Node {
    public abstract Optional<Value> value();
    public abstract Optional<Value> eval(Map<String, Value> symbolTable, Map<String, Node> functionTable);

}

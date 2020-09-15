package parser;

import java.util.Map;
import java.util.Optional;

public abstract class Node {
    public abstract Optional<Value> value();
    public abstract Optional<String> eval(Map<String, Value> symbolTable);

}

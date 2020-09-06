package parser;

import java.util.Optional;

public abstract class Node {
    public abstract Optional<Value> value();
    public abstract void eval();

}

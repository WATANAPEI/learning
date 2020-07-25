package LibFunc;

import Core.Value;
import Core.ValueImpl;
import Syntax.ExprListNode;

public class SqrtFunction extends Function {
    @Override
    public Value eval(ExprListNode arg) {
        Value val = arg.getValue(0);
        return new ValueImpl(Math.sqrt(val.getDValue()));
    }
}

package LibFunc;

import Core.Value;
import Syntax.ExprListNode;

public class PrintFunction extends Function{
    @Override
    public Value eval(ExprListNode arg) {
        Value value = arg.getValue(0);
        System.out.println(value.getSValue());
        return null;

    }
}

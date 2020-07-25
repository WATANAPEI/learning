package LibFunc;

import Core.Value;
import Syntax.ExprListNode;

public abstract class Function {
    public abstract Value eval(ExprListNode arg);

}

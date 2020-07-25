package Syntax;

import Core.LexicalUnit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class NextNodeList extends ArrayList<Class<? extends Node>> {

    public NextNodeList(Class<? extends Node>... nextNodes) {
        addAll(Arrays.asList(nextNodes));
    }

    //find next node
    public Node nextNode(Environment env, LexicalUnit unit) {
        Iterator<Class<? extends Node>> i = iterator();
        while(i.hasNext()) {
            Method isMatch;
            try {
                isMatch = i.next().getMethod("isMatch", Environment.clas, LexicalUnit.class);
                Object res = isMatch.invoke(null, env, unit);
                if(res != null) {
                    return (Node) res;
                }
            } catch(NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}

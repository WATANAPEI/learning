package Core;

import Lexer.LexicalAnalyzer;
import LibFunc.PrintFunction;
import LibFunc.SqrtFunction;
import LibFunc.Function;
import java.util.Hashtable;
import java.util.Map;

public class Environment {
    private LexicalAnalyzer input;
    private Hashtable<String, Value> varTable;
    private Hashtable<String, Function> funcTable;

    public Environment(LexicalAnalyzer my_input) {
        this.input = my_input;
        varTable = new Hashtable();
        funcTable = new Hashtable();
        funcInit();
    }

    private void funcInit() {
        funcTable.put("PRINT", new PrintFunction());
        funcTable.put("SQRT", new SqrtFunction());
    }

    public LexicalAnalyzer getInput() {
        return input;
    }

    public void setVarValue(LexicalUnit varName, Value value) {
        varTable.put(varName.getValue().getSValue(), value);
    }

    public Value getVarValue(LexicalUnit varName) {
        Value res = varTable.get(varName.getValue().getSValue());
        if(res == null) {
            throw new IllegalStateException("No variable found");
        }
        return res;
    }

    public void setFunc(LexicalUnit funcName, Function function) {
        funcTable.put(funcName.getValue().getSValue(), function);
    }

    public Function getFuncion(LexicalUnit funcName) {
        Function res = funcTable.get(funcName.getValue().getSValue());
        if(res == null) {
            throw new IllegalStateException("No function name found.");
        }
        return res;
    }

}

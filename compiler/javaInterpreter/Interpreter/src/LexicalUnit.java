//TODO 残っているswitch文を埋める
public class LexicalUnit {
    LexicalType type;
    Value value;
    LexicalUnit link;

    public LexicalUnit(LexicalType type) {
        this.type = type;
    }

    public  LexicalUnit(LexicalType type, Value value) {
        this.type = type;
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    public LexicalType getType() {
        return type;
    }

    public String toString() {
        switch (type) {
            case LITERAL:
                return "LITERAL:\t" + value.getSValue();
            case NAME:
                return "NAME:\t" + value.getSValue();
            case DOUBLEVAL:
                return "DOUBLEVAL:\t" + value.getSValue();
            case INTVAL:
                return "INTVAL:\t" + value.getSValue();
            case IF:
                return "IF";
            case THEN:
                return "THEN";
            case ELSE:
                return "ELSE";
            case ELSEIF:
                return "ELSEIF";
            case ENDIF:
                return "ENDIF";
            case FOR:
                return "FOR";
            case FORALL:
            case NEXT:
            case EQ:
            case LT:
            case GT:
            case LE:
            case GE:
            case NE:
            case FUNC:
            case DIM:
            case AS:
            case END:
            case NL:
            case DOT:
            case WHILE:
            case DO:
            case UNTIL:
            case ADD:
            case SUB:
            case MUL:
            case DIV:
            case LP:
            case RP:
            case COMMA:
            case LOOP:
            case TO:
            case WEND:
            case EOF:

        }
    }
}

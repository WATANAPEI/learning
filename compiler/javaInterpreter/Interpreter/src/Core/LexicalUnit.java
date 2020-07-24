package Core;

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
                return "FORALL";
            case NEXT:
                return "NEXT";
            case EQ:
                return "EQ";
            case LT:
                return "LT";
            case GT:
                return "GT";
            case LE:
                return "LE";
            case GE:
                return "GE";
            case NE:
                return "NE";
            case FUNC:
                return "FUNC";
            case DIM:
                return "DIM";
            case AS:
                return "AS";
            case END:
                return "END";
            case NL:
                return "NL";
            case DOT:
                return "DOT";
            case WHILE:
                return "WHILE";
            case DO:
                return "DO";
            case UNTIL:
                return "UNTIL";
            case ADD:
                return "ADD";
            case SUB:
                return "SUB";
            case MUL:
                return "MUL";
            case DIV:
                return "DIV";
            case LP:
                return "LP";
            case RP:
                return "RP";
            case COMMA:
                return "COMMA";
            case LOOP:
                return "LOOP";
            case TO:
                return "TO";
            case WEND:
                return "WEND";
            case EOF:
                return "EOF";
            default:
                return "Unknown type";

        }
    }
}

package Core;

public class ValueImpl implements Value{
    private ValueType type;
    private String val;

    public ValueImpl(String s) {
        this.type = ValueType.STRING;
        this.val = s;
    }

    public ValueImpl(int i) {
        this.type = ValueType.INTEGER;
        this.val = i + "";
    }

    public ValueImpl(double d) {
        this.type = ValueType.DOUBLE;
        this.val = d + "";
    }
    public ValueImpl(boolean b) {
        this.type = ValueType.BOOL;
        this.val = b + "";
    }

    @Override
    public String getSValue() {
        return val;
    }

    @Override
    public int getIValue() {
        return Integer.parseInt(val);
    }

    @Override
    public double getDValue() {
        return Double.parseDouble(val);
    }

    @Override
    public boolean getBValue() {
        return Boolean.parseBoolean(val);
    }

    @Override
    public ValueType getType() {
        return this.type;
    }
}

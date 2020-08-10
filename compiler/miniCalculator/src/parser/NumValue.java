package parser;

public class NumValue implements Value{
    int val;

    public NumValue(String str) {
        this.val = Integer.parseInt(str);
    }

    @Override
    public Integer getIValue() {
        return val;
    }

    @Override
    public String getSValue() {
        return String.valueOf(val);
    }

    @Override
    public Double getDValue() {
        return (double)val;
    }

    @Override
    public Boolean getBValue() {
        return null;
    }
}

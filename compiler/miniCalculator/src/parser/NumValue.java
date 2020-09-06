package parser;

import java.util.Optional;

/**
 * for now value limits integer
 */
public class NumValue implements Value{
    Integer val;

    public NumValue(String str) {
        this.val = Integer.parseInt(str);
    }
    public NumValue(Integer value) {
        this.val = value;
    }

    @Override
    public Optional<Integer> getIValue() {
        return Optional.of(val);
    }

    @Override
    public Optional<String> getSValue() {
        return Optional.of(String.valueOf(val));
    }

    /*
    @Override
    public Optional<Double> getDValue() {
        return Optional.of(Double.valueOf(val));
    }
     */

    /**
     * return true if number value exists
     * @return
     */
    @Override
    public Optional<Boolean> getBValue() {
        return Optional.ofNullable(val != null);
    }
}

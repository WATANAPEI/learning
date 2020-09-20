package parser;

import java.util.Optional;

/**
 * for now value limits integer
 */
public class BooleanValue implements Value{
    Boolean val;

    public BooleanValue(Boolean value) {
        this.val = value;
    }

    @Override
    public Optional<Integer> getIValue() {
        return Optional.empty();
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

    @Override
    public Optional<Boolean> getBValue() {
        return Optional.of(this.val);
    }
}

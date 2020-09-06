package parser;

import java.util.Optional;

public class StringValue implements Value{
    String val;

    public StringValue(String str) {
        this.val = str;
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
        return Optional.empty();
    }
     */

    /**
     * return true if value string exists
     * @return
     */
    @Override
    public Optional<Boolean> getBValue() {
        return Optional.ofNullable(!val.isEmpty());
    }
}

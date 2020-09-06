package parser;

import java.util.Optional;

public interface Value {
    public Optional<Integer> getIValue();
    public Optional<String> getSValue();
    //public Optional<Double> getDValue();
    public Optional<Boolean> getBValue();
}




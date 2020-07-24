package Syntax;

import Core.LexicalType;
import Core.LexicalUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstCollection {

    List<LexicalType> firstListUnit;

    public FirstCollection(LexicalType... firstTypes) {
        this.firstListUnit = Arrays.asList(firstTypes);
    }

    public FirstCollection(FirstCollection... firstCollections) {
        this.firstListUnit = new ArrayList<>();
        for (FirstCollection firstCollection: firstCollections) {
            firstListUnit.addAll(firstCollection.firstListUnit);
        }
    }

    public boolean contains(LexicalUnit unit) {
        LexicalType type = unit.getType();
        return firstListUnit.contains(type);
    }
}

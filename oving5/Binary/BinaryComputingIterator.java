package oving5.Binary;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BinaryOperator;

public class BinaryComputingIterator implements Iterator<Double> {
    private final Iterator<Double> iterator1;
    private final Iterator<Double> iterator2;
    private final BinaryOperator<Double> operator;
    private final Double default1;
    private final Double default2;


    public BinaryComputingIterator(Iterator<Double> iterator1, Iterator<Double> iterator2, BinaryOperator<Double> operator) {
        this(iterator1, iterator2, null, null, operator);
    }

   
    public BinaryComputingIterator(Iterator<Double> iterator1, Iterator<Double> iterator2, Double default1, Double default2, BinaryOperator<Double> operator) {
        if (iterator1 == null || iterator2 == null || operator == null) {
            throw new IllegalArgumentException("Iterators and operator cannot be null");
        }
        this.iterator1 = iterator1;
        this.iterator2 = iterator2;
        this.operator = operator;
        this.default1 = default1;
        this.default2 = default2;
    }

    @Override
    public boolean hasNext() {
        return iterator1.hasNext() || iterator2.hasNext() || (default1 != null && iterator2.hasNext()) || (default2 != null && iterator1.hasNext());
    }

    @Override
    public Double next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements to compute");
        }
        
        Double value1 = iterator1.hasNext() ? iterator1.next() : default1;
        Double value2 = iterator2.hasNext() ? iterator2.next() : default2;

        if (value1 == null || value2 == null) {
            throw new NoSuchElementException("No more elements available and no default value provided");
        }

        return operator.apply(value1, value2);
    }
}
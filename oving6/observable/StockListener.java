package oving6.observable;

public interface StockListener {
    /**
        @param stock
        @param oldValue
        @param newValue
     */
    void stockPriceChanged(Stock stock, double oldValue, double newValue);
}

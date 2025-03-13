package oving6.observable;

import java.util.HashSet;
import java.util.Set;

public class StockIndex implements StockListener {
    private String name;
    private Set<Stock> stocks;
    private double index;

    public StockIndex(String name, Stock... stocks) {
        if (name == null) {
            throw new IllegalArgumentException("Navn kan ikke være null");
        }
        this.name = name;
        this.stocks = new HashSet<>();
        this.index = 0.0;
        if (stocks != null) {
            for (Stock s : stocks) {
                if (s == null) {
                    throw new IllegalArgumentException("Aksje kan ikke være null");
                }
                addStock(s);
            }
        }
    }


    public void addStock(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("Aksje kan ikke være null");
        }
        if (stocks.contains(stock)) {
            return;
        }
        stocks.add(stock);
        index += stock.getPrice();
        stock.addStockListener(this);
    }


    public void removeStock(Stock stock) {
        if (stock != null && stocks.contains(stock)) {
            stocks.remove(stock);
            index -= stock.getPrice();
            stock.removeStockListener(this);
        }
    }

    public double getIndex() {
        return index;
    }


    @Override
    public void stockPriceChanged(Stock stock, double oldValue, double newValue) {
        if (stocks.contains(stock)) {
            index = index - oldValue + newValue;
        }
    }
}

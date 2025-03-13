package oving6.observable;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private String ticker;
    private double price;
    private List<StockListener> listeners;

    public Stock(String ticker, double price) {
        if (ticker == null) {
            throw new IllegalArgumentException("Ticker kan ikke være null");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Pris må være større enn 0");
        }
        this.ticker = ticker;
        this.price = price;
        this.listeners = new ArrayList<>();
    }

    public String getTicker() {
        return ticker;
    }

    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Pris må være større enn 0");
        }
        if (price == this.price) {
            return;
        }
        double oldPrice = this.price;
        this.price = price;
        notifyListeners(oldPrice, price);
    }

    public void addStockListener(StockListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener kan ikke være null");
        }
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeStockListener(StockListener listener) {
        listeners.remove(listener);
    }


    protected void notifyListeners(double oldPrice, double newPrice) {
        for (StockListener listener : new ArrayList<>(listeners)) {
            listener.stockPriceChanged(this, oldPrice, newPrice);
        }
    }
}

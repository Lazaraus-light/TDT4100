package oving6.observable;

import java.util.HashMap;
import java.util.Map;

public class SmartStock extends Stock {


    private Map<StockListener, double[]> intervalListeners;

    private Map<StockListener, double[]> differenceListeners;

    public SmartStock(String ticker, double price) {
        super(ticker, price);
        intervalListeners = new HashMap<>();
        differenceListeners = new HashMap<>();
    }

    /**
     * @param listener
     * @param min
     * @param max
     */
    public void addStockListener(StockListener listener, double min, double max) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener kan ikke være null");
        }
        intervalListeners.put(listener, new double[] { min, max });
    }

    /**
     * @param listener
     * @param difference
     */
    public void addStockListener(StockListener listener, double difference) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener kan ikke være null");
        }

        differenceListeners.put(listener, new double[] { difference, getPrice() });
    }


    @Override
    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Pris må være større enn 0");
        }
        double oldPrice = getPrice();
        if (price == oldPrice) {
            return;
        }

        super.setPrice(price);


        for (Map.Entry<StockListener, double[]> entry : intervalListeners.entrySet()) {
            double min = entry.getValue()[0];
            double max = entry.getValue()[1];
            if (price < min || price > max) {
                entry.getKey().stockPriceChanged(this, oldPrice, price);
            }
        }

        for (Map.Entry<StockListener, double[]> entry : differenceListeners.entrySet()) {
            double threshold = entry.getValue()[0];
            double lastNotified = entry.getValue()[1];
            if (Math.abs(price - lastNotified) >= threshold) {
                entry.getKey().stockPriceChanged(this, lastNotified, price);
                // Oppdater den siste varslede prisen for lytteren
                entry.getValue()[1] = price;
            }
        }
    }
}

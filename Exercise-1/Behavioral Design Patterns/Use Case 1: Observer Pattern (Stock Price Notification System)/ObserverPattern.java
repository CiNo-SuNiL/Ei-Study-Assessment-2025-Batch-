import java.util.ArrayList;
import java.util.List;

interface Stock {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Observer interface
interface Observer {
    void update(float price);
}

class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private float stockPrice;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockPrice);
        }
    }

    public void setStockPrice(float stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers();
    }
}


class MobileApp implements Observer {
    @Override
    public void update(float price) {
        System.out.println("MobileApp: Stock price updated to " + price);
    }
}

class WebApp implements Observer {
    @Override
    public void update(float price) {
        System.out.println("WebApp: Stock price updated to " + price);
    }
}


public class ObserverPattern {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();
        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        stockMarket.addObserver(mobileApp);
        stockMarket.addObserver(webApp);

        stockMarket.setStockPrice(120.5f);
        stockMarket.setStockPrice(122.5f);
    }
}

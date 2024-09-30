package structural;

interface PaymentGateway {
    void processPayment(String amount);
}

class PayPal {
    public void sendPayment(String amount) {
        System.out.println("Processing PayPal payment of " + amount);
    }
}

class PayPalAdapter implements PaymentGateway {
    private PayPal payPal;

    public PayPalAdapter(PayPal payPal) {
        this.payPal = payPal;
    }

    @Override
    public void processPayment(String amount) {
        payPal.sendPayment(amount);
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        PayPal payPal = new PayPal();
        PaymentGateway paymentGateway = new PayPalAdapter(payPal);
        paymentGateway.processPayment("100 USD");
    }
}

package com.business.order.Services;

import com.business.order.Entity.Orders;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.Order;
import com.paypal.orders.OrdersGetRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    @Value("${paypal.access.token}")
    private String accessToken;

    @Autowired
    private APIContext apiContext;

    private final PayPalHttpClient paypalHttpClient;

    public PaymentService(PayPalHttpClient paypalHttpClient){
        this.paypalHttpClient = paypalHttpClient;
    }

    public Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description
    ) throws Exception {

        Amount amount = new Amount();
        amount.setCurrency(currency);
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue(); //Important: Rounding
        amount.setTotal(String.valueOf(total));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setReturnUrl("http://localhost:8080/payment/execute");
        redirectUrls.setCancelUrl("http://localhost:8080/payment/cancel");
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    //.. other methods (execute payment.., cancel payment) ...

    //Execute payment
    public Payment executePayment(String paymentId, String payerId) throws Exception {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecution);
    }

    private APIContext getApiContext() {
        return new APIContext(clientId, clientSecret, mode);
    }


    //Cancel payment
    public void cancelPendingOrder(String orderId) throws Exception {
        try {
            Order order = getOrder(orderId);

            if (order.status().equals("CREATED")) { // Only cancel if order is in CREATED state
                Orders request = new Orders();
                List<Patch> patches = new ArrayList<>();

                Patch patch = new Patch()
                        .setOp("replace")
                        .setPath("/status")
                        .setValue("CANCELLED"); // Or VOIDED depending on PayPal API version
                patches.add(patch);

                request.requestBody(patches);


                paypalHttpClient.execute(request);

                // Optionally: Log success, update your database, etc.
                System.out.println("Order " + orderId + " cancelled successfully.");

            } else {
                throw new IllegalStateException("Order " + orderId + " cannot be cancelled. Status: " + order.status());
            }
        } catch (Exception e) {
           //Handle exceptions appropriately (log, throw custom exception etc.)
            System.err.println("Error cancelling order: " + e.getMessage());
            throw e;
        }
    }

    //Refund Payment
    public Refund createRefund(String transactionId, String amount, String currency, String reason) throws PayPalRESTException {
        APIContext apiContext = new APIContext(accessToken);

        Refund refund = new Refund();

        Amount refundAmount = new Amount();
        refundAmount.setCurrency(currency);
        refundAmount.setTotal(amount);
        refund.setAmount(refundAmount);

        if (reason != null && !reason.isEmpty()) {
            refund.setDescription(reason);
        }

        Sale sale = new Sale();
        sale.setId(transactionId);

        return sale.refund(apiContext, refund);
    }
//    public void refundCapturedPayment(String paymentId, String reason) throws Exception {
//        try {
//            // ... (Code to create a Refund request using PayPal Java SDK) ...
//            // You'll need to look up the specific Refund API call and construct
//            // the request object with the paymentId and refund amount/reason.
//            // Example (adapt to your needs):
//            RefundRequest refundRequest = new RefundRequest();
//            // ... set amount, reason, etc.
//            // Execute the refund request
//            // paypalClient.execute(refundRequest);
//
//            System.out.println("Refund for payment" + paymentId + "initiated.");
//        } catch (Exception e) {
//            System.err.println("Error initiating refund: " + e.getMessage());
//            throw e;
//        }
//    }

    private Order getOrder(String orderId) throws Exception {
        OrdersGetRequest request = new OrdersGetRequest(orderId);
        return paypalHttpClient.execute(request).result();
    }
}

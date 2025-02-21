package com.business.order.Controller;

import com.business.order.Services.PaymentService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public String createPayment(
            @RequestParam("total") Double total,
            @RequestParam("currency") String currency,
            @RequestParam("description") String description,
            HttpServletRequest request
    ) throws Exception {

        Payment payment = paymentService.createPayment(total, currency, "paypal", "sale", description);

        for (Links links : payment.getLinks()){
            if (links.getRel().equals("approval_url")) {
                return "redirect:" + links.getHref();
            }
        }
        return "error";
    }

    @GetMapping("/execute")
    public String executePayment(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId
    ) throws Exception {

        Payment payment = paymentService.executePayment(paymentId, payerId);
        payment.setId(paymentId);
        payment.getPayer();
        payment.getUpdateTime();


        return "success";
    }


//    @GetMapping("/cancel")
//    public String cancelPayment() {
//        return "cancel";
//    }

    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable String orderId) {
        try {
            paymentService.cancelPendingOrder(orderId);
            return new ResponseEntity<>("Order cancelled successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Or appropriate error code
        }
    }

    @PostMapping("/refund/{paymentId}")
    public ResponseEntity<String> refundPayment(@PathVariable String paymentId, @RequestParam(required = false) String reason) { // Reason is optional
        try {
            paymentService.refundCapturedPayment(paymentId, reason);
            return new ResponseEntity<>("Refund initiated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

package br.api.notebook.controller;

import br.api.notebook.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/payment/pix")
    public ResponseEntity<String> savePayment(){
        return ResponseEntity.ok().body(paymentService.savePaymentPix());
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/payment/bankslip")
    public ResponseEntity<String> savePaymentBank(){
        return ResponseEntity.ok().body(paymentService.savePaymentBankSlip());
    }
}

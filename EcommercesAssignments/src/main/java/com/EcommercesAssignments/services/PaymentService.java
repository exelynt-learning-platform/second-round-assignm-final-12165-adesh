package com.EcommercesAssignments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EcommercesAssignments.entity.Payment;
import com.EcommercesAssignments.reposi.PaymentRepo;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    // Payment Logic
    public String pay(double amount) {

        if (amount > 0) {
            return "SUCCESS";
        }
        return "FAILED";
    }

    // Create Payment
    public Payment create(Payment payment) {

        if (payment.getAmount() > 0) {
            payment.setStatus("SUCCESS");
        } else {
            payment.setStatus("FAILED");
        }

        return paymentRepo.save(payment);
    }

    // Get All Payments
    public List<Payment> getAll() {
        return paymentRepo.findAll();
    }

    // Get Payment By Id
    public Payment getById(Long id) {
        return paymentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    // Update Payment
    public Payment update(Long id, Payment payment) {
        Payment p = paymentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        p.setAmount(payment.getAmount());
        p.setPaymentMethod(payment.getPaymentMethod());

        if (payment.getAmount() > 0) {
            p.setStatus("SUCCESS");
        } else {
            p.setStatus("FAILED");
        }

        return paymentRepo.save(p);
    }

    // Delete Payment
    public void delete(Long id) {
        paymentRepo.deleteById(id);
    }
}
package com.EcommercesAssignments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.EcommercesAssignments.entity.Payment;
import com.EcommercesAssignments.reposi.PaymentRepo;
import com.EcommercesAssignments.services.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment save(@RequestBody Payment payment) {
        Payment p = paymentService.create(payment);

        return paymentRepo.save(p);
    }

    @GetMapping
    public List<Payment> getAll() {
        return paymentService.getAll();
    }

    @GetMapping("/{id}")
    public Payment getById(@PathVariable Long id) {
        return paymentService.getById(id);
    }

    @PutMapping("/{id}")
    public Payment update(@PathVariable Long id, @RequestBody Payment payment) {
        return paymentService.update(id, payment);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        paymentService.delete(id);
        return "Payment Deleted";
    }
}
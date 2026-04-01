package com.EcommercesAssignments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EcommercesAssignments.entity.Order;
import com.EcommercesAssignments.reposi.Orderrepo;

@Service
public class OrderService {

    @Autowired
    private Orderrepo orderrepo;

    @Autowired
    private PaymentService paymentService;

    // Create Order
    public Order place(Order order) {

        String status = paymentService.pay(order.getAmount());
        order.setPaymentStatus(status);
        order.setPaymentStatus("PLACED");
        return orderrepo.save(order);
    }

    // Get All Orders
    public List<Order> get() {
        return orderrepo.findAll();
    }

    // Update Order
    public Order update(Long id, Order order) {

        Order ord = orderrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        ord.setAmount(order.getAmount());
        ord.setDelivaryAddres(order.getDelivaryAddres());
        ord.setProduct(order.getProduct());

        String status = paymentService.pay(order.getAmount());
        ord.setPaymentStatus(status);

        return orderrepo.save(ord);
    }

    // Delete Order
    public void delete(Long id) {
        orderrepo.deleteById(id);
    }
}
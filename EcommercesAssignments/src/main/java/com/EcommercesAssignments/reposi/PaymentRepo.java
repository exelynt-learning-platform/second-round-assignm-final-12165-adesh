package com.EcommercesAssignments.reposi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EcommercesAssignments.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Long> {

}
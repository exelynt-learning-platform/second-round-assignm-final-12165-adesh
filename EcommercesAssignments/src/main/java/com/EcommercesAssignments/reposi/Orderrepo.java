package com.EcommercesAssignments.reposi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EcommercesAssignments.entity.Order;
import com.EcommercesAssignments.entity.User;

public interface Orderrepo extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

}

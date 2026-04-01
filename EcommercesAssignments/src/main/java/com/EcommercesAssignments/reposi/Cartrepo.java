package com.EcommercesAssignments.reposi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EcommercesAssignments.entity.Cart;
import com.EcommercesAssignments.entity.User;

public interface Cartrepo extends JpaRepository<Cart, Long> {

    List<Cart> findByUser(User user);

}

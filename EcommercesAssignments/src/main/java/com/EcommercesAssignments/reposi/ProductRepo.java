package com.EcommercesAssignments.reposi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EcommercesAssignments.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}

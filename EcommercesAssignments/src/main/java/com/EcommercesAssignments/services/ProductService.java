package com.EcommercesAssignments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EcommercesAssignments.entity.Product;
import com.EcommercesAssignments.reposi.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product addproduct(Product product) {
        return productRepo.save(product);
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public List<Product> getAll() {
        return productRepo.findAll();

    }

    public Product update(Long id, Product product) {
        Product p = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (p != null) {
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setDescription(product.getDescription());
            p.setStockQuantity(product.getStockQuantity());
            p.setImgUrl(product.getImgUrl());

            return productRepo.save(p);
        }
        return null;
    }

    public void delete(Long id) {
        productRepo.deleteById(id);
    }

}

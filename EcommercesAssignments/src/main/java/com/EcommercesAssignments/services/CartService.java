package com.EcommercesAssignments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EcommercesAssignments.entity.Cart;
import com.EcommercesAssignments.entity.CartItem;
import com.EcommercesAssignments.entity.Product;
import com.EcommercesAssignments.entity.User;
import com.EcommercesAssignments.reposi.Cartrepo;
import com.EcommercesAssignments.reposi.ProductRepo;
import com.EcommercesAssignments.reposi.UserRepo;

@Service
public class CartService {

    @Autowired
    private Cartrepo cartRepository;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private ProductRepo productRepository;

    // Add to Cart
    public Cart addToCart(Cart cart) {

        // Fetch User from DB
        Long userId = cart.getUser().getId();
        User user = userRepository.findById(userId).orElseThrow();
        cart.setUser(user);

        // Fetch Products for Cart Items
        List<CartItem> items = cart.getItems();

        for (CartItem item : items) {
            Long productId = item.getProduct().getId();
            Product product = productRepository.findById(productId).orElseThrow();
            item.setProduct(product);
        }

        return cartRepository.save(cart);
    }

    // Get Cart By Id
    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    // Update Cart
    public Cart updateCart(Long id, Cart cart) {

        Cart existingCart = cartRepository.findById(id).orElseThrow();

        existingCart.setUser(cart.getUser());
        existingCart.setItems(cart.getItems());

        return cartRepository.save(existingCart);
    }

    // Delete Cart
    public String deleteCart(Long id) {
        cartRepository.deleteById(id);
        return "Cart Deleted Successfully";
    }
}
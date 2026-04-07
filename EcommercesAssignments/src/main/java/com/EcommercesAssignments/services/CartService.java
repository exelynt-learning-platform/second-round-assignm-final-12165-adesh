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
        if (cart.getUser() == null) {
            throw new RuntimeException("User is required");
        }

        Long userId = cart.getUser().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found With id" + userId));
        cart.setUser(user);

        // Fetch Products for Cart Items
        List<CartItem> items = cart.getItems();

        for (CartItem item : items) {

            Long productId = item.getProduct().getId();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStockQuantity() <= 0) {
                throw new RuntimeException("Product out of stock");
            }

            item.setProduct(product);
        }

        return cartRepository.save(cart);
    }

    // Get Cart By Id
    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));
    }

    // Update Cart
    public Cart updateCart(Long id, Cart cart) {

        Cart existingCart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));

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
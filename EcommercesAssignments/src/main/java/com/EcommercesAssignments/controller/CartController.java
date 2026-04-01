package com.EcommercesAssignments.controller;

import com.EcommercesAssignments.entity.Cart;
import com.EcommercesAssignments.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public Cart add(@RequestBody Cart cart) {
        return cartService.addToCart(cart);
    }

    @GetMapping("/{id}")
    public Cart get(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

    @PutMapping("/{id}")
    public Cart update(@PathVariable Long id, @RequestBody Cart cart) {
        return cartService.updateCart(id, cart);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cartService.deleteCart(id);
    }
}
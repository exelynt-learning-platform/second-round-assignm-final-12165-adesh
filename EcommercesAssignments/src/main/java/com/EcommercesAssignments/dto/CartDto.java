package com.EcommercesAssignments.dto;

import lombok.Data;

@Data
public class CartDto {
    private Long userid;
    private Long productid;
    private int quantity;
}

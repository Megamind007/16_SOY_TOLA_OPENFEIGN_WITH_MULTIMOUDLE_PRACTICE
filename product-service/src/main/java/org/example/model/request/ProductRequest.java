package org.example.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.example.model.Product;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private Double price;
    public Product toEntity(){
        return new Product(null, name, price);
    }
}

package com.example.demo.util;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.ProductRepository;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapperHelper {

    @Autowired
    private ProductRepository productRepository;


    public List<Long> mapProductsToIds(Set<Product> products) {
        if (products == null) return null;
        return products.stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }

    public Set<Product> mapIdsToProducts(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(id ->
                        productRepository.findById(id).orElseThrow( () -> new RuntimeException("Product not found " + id))
                ).collect(Collectors.toSet());
    }
}


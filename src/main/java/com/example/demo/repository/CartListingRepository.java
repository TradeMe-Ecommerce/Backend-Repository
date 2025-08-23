package com.example.demo.repository;

import com.example.demo.entity.CartListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartListingRepository extends JpaRepository<CartListing, Long> {
}

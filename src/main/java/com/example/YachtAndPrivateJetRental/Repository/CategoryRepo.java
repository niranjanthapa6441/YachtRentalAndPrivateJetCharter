package com.example.YachtAndPrivateJetRental.Repository;

import com.example.YachtAndPrivateJetRental.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
    Optional<Category> findByCategory(String category);
}

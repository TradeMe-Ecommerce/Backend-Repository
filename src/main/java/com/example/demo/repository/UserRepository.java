package com.example.demo.repository;


import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

    void removeById(long id);

    List<User> findAllByRole(Role role);

    List<User> findByNameContainingIgnoreCase(String name);
}

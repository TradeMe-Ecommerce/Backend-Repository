package com.example.demo.repository;

import com.example.demo.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // ahora ordena por el atributo 'date' que ya existe en la entidad
    List<Notification> findByUser_IdOrderByDateDesc(Long userId);
}

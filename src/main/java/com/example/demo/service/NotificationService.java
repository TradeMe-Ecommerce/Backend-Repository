package com.example.demo.service;

import com.example.demo.DTO.NotificationDTO;
import com.example.demo.entity.Notification;

import java.util.List;

public interface NotificationService {
    NotificationDTO createNotification(NotificationDTO notification);
    List<NotificationDTO> getNotificationsByUserId(Long userId);
    void deleteNotification(Long id);
}

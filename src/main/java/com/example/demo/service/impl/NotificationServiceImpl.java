package com.example.demo.service.impl;

import com.example.demo.DTO.NotificationDTO;
import com.example.demo.entity.Notification;
import com.example.demo.mapper.NotificationMapper;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private  NotificationMapper notificationMapper;


    @Override
    public List<NotificationDTO> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUser_IdOrderByDateDesc(userId)
                .stream()
                .map(notificationMapper::toDTO)
                .toList();
    }

    @Override
    public NotificationDTO createNotification(NotificationDTO dto) {
        Notification entity = notificationMapper.toEntity(dto);
        entity = notificationRepository.save(entity);
        return notificationMapper.toDTO(entity);
    }

    @Override
    public void deleteNotification(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notificationRepository.delete(notification);
    }
}


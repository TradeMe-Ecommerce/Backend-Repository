package com.example.demo.mapper;

import com.example.demo.DTO.NotificationDTO;
import com.example.demo.entity.Notification;
import com.example.demo.entity.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-25T06:54:22-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationDTO toDTO(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationDTO notificationDTO = new NotificationDTO();

        notificationDTO.setUserId( notificationUserId( notification ) );
        notificationDTO.setId( notification.getId() );
        if ( notification.getDate() != null ) {
            notificationDTO.setDate( new SimpleDateFormat().format( notification.getDate() ) );
        }
        notificationDTO.setDescription( notification.getDescription() );

        return notificationDTO;
    }

    @Override
    public Notification toEntity(NotificationDTO notificationDTO) {
        if ( notificationDTO == null ) {
            return null;
        }

        Notification notification = new Notification();

        notification.setUser( notificationDTOToUser( notificationDTO ) );
        notification.setId( notificationDTO.getId() );
        try {
            if ( notificationDTO.getDate() != null ) {
                notification.setDate( new SimpleDateFormat().parse( notificationDTO.getDate() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        notification.setDescription( notificationDTO.getDescription() );

        return notification;
    }

    private long notificationUserId(Notification notification) {
        if ( notification == null ) {
            return 0L;
        }
        User user = notification.getUser();
        if ( user == null ) {
            return 0L;
        }
        long id = user.getId();
        return id;
    }

    protected User notificationDTOToUser(NotificationDTO notificationDTO) {
        if ( notificationDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( notificationDTO.getUserId() );

        return user;
    }
}

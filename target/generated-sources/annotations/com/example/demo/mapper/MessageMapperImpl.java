package com.example.demo.mapper;

import com.example.demo.DTO.MessageDTO;
import com.example.demo.entity.Message;
import com.example.demo.entity.Room;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-23T18:02:35-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDTO toDTO(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setRoomId( messageRoomId( message ) );
        messageDTO.setDate( convertDateToString( message.getDate() ) );
        messageDTO.setId( message.getId() );
        messageDTO.setMessage( message.getMessage() );

        return messageDTO;
    }

    @Override
    public Message toEntity(MessageDTO messageDTO) {
        if ( messageDTO == null ) {
            return null;
        }

        Message message = new Message();

        message.setRoom( messageDTOToRoom( messageDTO ) );
        message.setDate( convertStringToDate( messageDTO.getDate() ) );
        message.setId( messageDTO.getId() );
        message.setMessage( messageDTO.getMessage() );

        return message;
    }

    private long messageRoomId(Message message) {
        if ( message == null ) {
            return 0L;
        }
        Room room = message.getRoom();
        if ( room == null ) {
            return 0L;
        }
        long id = room.getId();
        return id;
    }

    protected Room messageDTOToRoom(MessageDTO messageDTO) {
        if ( messageDTO == null ) {
            return null;
        }

        Room room = new Room();

        room.setId( messageDTO.getRoomId() );

        return room;
    }
}

package com.example.demo.mapper;

import com.example.demo.DTO.MessageDTO;
import com.example.demo.DTO.RoomDTO;
import com.example.demo.entity.Message;
import com.example.demo.entity.Room;
import com.example.demo.entity.User;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-24T20:14:07-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public RoomDTO toDTO(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setUserId( roomUserId( room ) );
        roomDTO.setUserName( roomUserName( room ) );
        roomDTO.setPeerUserId( roomPeerUserId( room ) );
        roomDTO.setPeerUserName( roomPeerUserName( room ) );
        roomDTO.setId( room.getId() );
        roomDTO.setMessages( messageSetToMessageDTOList( room.getMessages() ) );

        return roomDTO;
    }

    @Override
    public Room toEntity(RoomDTO roomDTO) {
        if ( roomDTO == null ) {
            return null;
        }

        Room room = new Room();

        room.setUser( roomDTOToUser( roomDTO ) );
        room.setPeerUser( roomDTOToUser1( roomDTO ) );
        room.setId( roomDTO.getId() );
        room.setMessages( messageDTOListToMessageSet( roomDTO.getMessages() ) );

        return room;
    }

    private long roomUserId(Room room) {
        if ( room == null ) {
            return 0L;
        }
        User user = room.getUser();
        if ( user == null ) {
            return 0L;
        }
        long id = user.getId();
        return id;
    }

    private String roomUserName(Room room) {
        if ( room == null ) {
            return null;
        }
        User user = room.getUser();
        if ( user == null ) {
            return null;
        }
        String name = user.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private long roomPeerUserId(Room room) {
        if ( room == null ) {
            return 0L;
        }
        User peerUser = room.getPeerUser();
        if ( peerUser == null ) {
            return 0L;
        }
        long id = peerUser.getId();
        return id;
    }

    private String roomPeerUserName(Room room) {
        if ( room == null ) {
            return null;
        }
        User peerUser = room.getPeerUser();
        if ( peerUser == null ) {
            return null;
        }
        String name = peerUser.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected List<MessageDTO> messageSetToMessageDTOList(Set<Message> set) {
        if ( set == null ) {
            return null;
        }

        List<MessageDTO> list = new ArrayList<MessageDTO>( set.size() );
        for ( Message message : set ) {
            list.add( messageMapper.toDTO( message ) );
        }

        return list;
    }

    protected User roomDTOToUser(RoomDTO roomDTO) {
        if ( roomDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( roomDTO.getUserId() );
        user.setName( roomDTO.getUserName() );

        return user;
    }

    protected User roomDTOToUser1(RoomDTO roomDTO) {
        if ( roomDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( roomDTO.getPeerUserId() );
        user.setName( roomDTO.getPeerUserName() );

        return user;
    }

    protected Set<Message> messageDTOListToMessageSet(List<MessageDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<Message> set = new LinkedHashSet<Message>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( MessageDTO messageDTO : list ) {
            set.add( messageMapper.toEntity( messageDTO ) );
        }

        return set;
    }
}

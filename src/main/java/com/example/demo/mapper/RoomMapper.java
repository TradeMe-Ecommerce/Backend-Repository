package com.example.demo.mapper;

import com.example.demo.DTO.RoomDTO;
import com.example.demo.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MessageMapper.class})
public interface RoomMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "peerUser.id", target = "peerUserId")
    @Mapping(source = "peerUser.name", target = "peerUserName")
    RoomDTO toDTO(Room room);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "userName", target = "user.name")
    @Mapping(source = "peerUserId", target = "peerUser.id")
    @Mapping(source = "peerUserName", target = "peerUser.name")
    Room toEntity(RoomDTO roomDTO);
}

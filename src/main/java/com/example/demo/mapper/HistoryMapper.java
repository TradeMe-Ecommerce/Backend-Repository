package com.example.demo.mapper;

import com.example.demo.DTO.HistoryDTO;
import com.example.demo.entity.History;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface HistoryMapper {

    @Mapping(source="user.id", target="userId")
    HistoryDTO toDTO(History history);

    @Mapping(source="userId", target="user.id")
    History toEntity(HistoryDTO historyDTO);

}

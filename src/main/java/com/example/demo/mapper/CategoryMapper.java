package com.example.demo.mapper;
import com.example.demo.DTO.CategoryDTO;
import com.example.demo.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO dto);

}
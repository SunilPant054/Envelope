package com.example.envelope.mapper;

import com.example.envelope.domain.Category;
import com.example.envelope.dto.CategoryRequestDto;
import com.example.envelope.dto.CategoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDto toResponseDto(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenant", ignore = true)
    Category toEntity(CategoryRequestDto requestDto);
}

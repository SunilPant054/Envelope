package com.example.envelope.service;

import com.example.envelope.domain.Category;
import com.example.envelope.dto.CategoryRequestDto;
import com.example.envelope.dto.CategoryResponseDto;
import com.example.envelope.mapper.CategoryMapper;
import com.example.envelope.repository.CategoryRepository;
import com.example.envelope.repository.TenantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final TenantRepository tenantRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public CategoryResponseDto createCategory(UUID tenantId, CategoryRequestDto categoryRequestDto){

        Category category = categoryMapper.toEntity(categoryRequestDto);
        category.setTenant(tenantRepository.findById(tenantId)
                .orElseThrow( () -> new EntityNotFoundException("Tenant not found!" + tenantId)));

        return categoryMapper.toResponseDto(categoryRepository.save(category));
    }

    /*
    Transactional is optional as only one db call without any data change.
     */
    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getCategories(UUID tenantId){
        return categoryRepository.findByTenantId(tenantId).stream()
                    .map(categoryMapper::toResponseDto)
                    .toList();
    }

    @Transactional
    public CategoryResponseDto updateCategory(UUID tenantId, UUID categoryId, CategoryRequestDto categoryRequestDto){

        Category category = categoryRepository.findByTenantIdAndId(tenantId, categoryId).orElseThrow(
                () -> new EntityNotFoundException("Category not found" + categoryId));

        categoryMapper.updateEntityFromDto(categoryRequestDto, category);

        Category updatedCategory = categoryRepository.save(category);

        return categoryMapper.toResponseDto(updatedCategory);
    }

    @Transactional
    public void deleteCategory(UUID tenantId, UUID categoryId){
        Category category = categoryRepository.findByTenantIdAndId(tenantId, categoryId)
                .orElseThrow( () -> new EntityNotFoundException("Category not found!" + categoryId));

        categoryRepository.delete(category);
    }

}

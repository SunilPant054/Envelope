package com.example.envelope.controller;

import com.example.envelope.domain.Category;
import com.example.envelope.dto.CategoryRequestDto;
import com.example.envelope.dto.CategoryResponseDto;
import com.example.envelope.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tenants")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/{tenantId}/categories")
    public ResponseEntity<CategoryResponseDto> createCategory(
            @PathVariable UUID tenantId,
            @Valid @RequestBody CategoryRequestDto categoryRequestDto) {

        CategoryResponseDto response = categoryService.createCategory(tenantId, categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{tenantId}/categories")
    public ResponseEntity<List<CategoryResponseDto>> getCategories(@PathVariable UUID tenantId){
        List<CategoryResponseDto> categories = categoryService.getCategories(tenantId);
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @PutMapping("/{tenantId}/categories/{categoryId}")
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @PathVariable UUID tenantId,
            @PathVariable UUID categoryId,
            @Valid @RequestBody CategoryRequestDto categoryRequestDto){

        CategoryResponseDto categoryResponse = categoryService.updateCategory(tenantId, categoryId, categoryRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }

    @DeleteMapping("/{tenantId}/categories/{categoryId}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable UUID tenantId,
            @PathVariable UUID categoryId
    ) {
        categoryService.deleteCategory(tenantId, categoryId);
        return ResponseEntity.noContent().build();
    }
}

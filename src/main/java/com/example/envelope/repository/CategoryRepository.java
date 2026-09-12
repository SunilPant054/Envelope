package com.example.envelope.repository;

import com.example.envelope.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findByTenantId(UUID tenantId);
    Optional<Category> findByTenantIdAndId(UUID tenantId, UUID categoryId);
}

package com.example.envelope.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @JoinColumn(name = "tenant_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Tenant tenant;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "is_essential", nullable = false)
    private boolean isEssential;
}

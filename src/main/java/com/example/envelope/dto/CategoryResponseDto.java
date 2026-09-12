package com.example.envelope.dto;

import java.util.UUID;

public record CategoryResponseDto(UUID id, String name, int weight, boolean essential) {
}

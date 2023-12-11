package com.food.order;

import lombok.Builder;

@Builder
public record ErrorDTO(String code, String message) {
}

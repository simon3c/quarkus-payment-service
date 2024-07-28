package com.simon.payment.service.dto;

import java.util.List;

public record PaginatedResponse<T>(
        List<T> items,
        int page,
        int size,
        long totalItems
) { }

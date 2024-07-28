package com.simon.payment.service.dto;

import com.simon.payment.domain.enumeration.PaymentStatus;

import java.math.BigDecimal;

public record PaymentDto(
        Long id,
        BigDecimal amount,
        String currency,
        PaymentStatus status
) { }

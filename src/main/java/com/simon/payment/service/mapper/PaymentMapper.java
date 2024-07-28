package com.simon.payment.service.mapper;

import com.simon.payment.domain.Payment;
import com.simon.payment.service.dto.PaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentDto toDto(Payment payment);
    Payment toEntity(PaymentDto paymentDto);
}

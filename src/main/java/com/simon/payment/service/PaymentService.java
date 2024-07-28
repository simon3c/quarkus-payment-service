package com.simon.payment.service;

import com.simon.payment.domain.Payment;
import com.simon.payment.repository.PaymentRepository;
import com.simon.payment.service.dto.PaginatedResponse;
import com.simon.payment.service.dto.PaymentDto;
import com.simon.payment.service.mapper.PaymentMapper;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public PaymentDto savePayment(PaymentDto paymentDto) {
        var payment = Payment.builder()
                .amount(paymentDto.amount())
                .currency(paymentDto.currency())
                .build();
        this.paymentRepository.persist(payment);
        return PaymentMapper.INSTANCE.toDto(payment);
    }

    public PaginatedResponse<PaymentDto> getAllPayments(
            int page,
            int size,
            String sortBy,
            String sortDirection
    ) {
        var sort = getSort(sortBy, sortDirection);
        var paginatedList = this.paymentRepository.findAll(sort)
                .page(Page.of(page, size)).list().stream()
                .map(PaymentMapper.INSTANCE::toDto)
                .toList();
        var totalItems = this.paymentRepository.count();
        return new PaginatedResponse<>(paginatedList, page, size, totalItems);
    }

    private Sort getSort(String sortBy, String sortDirection) {
        if ("desc".equalsIgnoreCase(sortDirection)) {
            return Sort.descending(sortBy);
        } else {
            return Sort.ascending(sortBy);
        }
    }
}

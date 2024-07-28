package com.simon.payment.web.rest;

import com.simon.payment.PaymentConstants;
import com.simon.payment.service.dto.PaginatedResponse;
import com.simon.payment.service.PaymentService;
import com.simon.payment.service.dto.PaymentDto;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(PaymentConstants.PAYMENTS_API)
public class PaymentResource {

    private final PaymentService paymentService;

    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPayment(PaymentDto paymentDto) {
        var saved = this.paymentService.savePayment(paymentDto);
        return Response.status(Response.Status.CREATED).entity(saved).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedResponse<PaymentDto> getAllPaymentRequests(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("sortBy") @DefaultValue("id") String sortBy,
            @QueryParam("sortDirection") @DefaultValue("asc") String sortDirection
    ) {
        return this.paymentService.getAllPayments(page, size, sortBy, sortDirection);
    }
}

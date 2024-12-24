package com.gadashov.pdf.controller;

import com.gadashov.pdf.service.DocumentService;
import com.gadashov.pdf.dto.request.InvoiceRequest;
import com.gadashov.pdf.dto.request.component.BuyerCC;
import com.gadashov.pdf.dto.request.component.InvoiceItemCC;
import com.gadashov.pdf.dto.request.component.SellerCC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/pdf")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }


    @PostMapping("/generate")
    public ResponseEntity<byte[]> getInvoice(@RequestBody InvoiceRequest request) {
        var response = documentService.generateInvoiceDocument(request);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=invoice.pdf");

        return ResponseEntity.ok()
//                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(response.toByteArray());
    }

    @GetMapping("/")
    public ResponseEntity<byte[]> getInvoice2() {
        SellerCC seller = SellerCC.builder()
                .name("Acme Corporation")
                .address("123 Business St, Metropolis, USA")
                .email("contact@acme.com")
                .build();

        // Buyer information
        BuyerCC buyer = BuyerCC.builder()
                .name("John Doe")
                .address("456 Residential Rd, Gotham, USA")
                .email("john.doe@example.com")
                .build();

        // Invoice items
        List<InvoiceItemCC> items = Arrays.asList(
                InvoiceItemCC.builder()
                        .name("Product A")
                        .quantity(2)
                        .price(50.00)
                        .total(100.00)
                        .build(),
                InvoiceItemCC.builder()
                        .name("Product B")
                        .quantity(1)
                        .price(75.00)
                        .total(75.00)
                        .build()
        );

        // Invoice request
        InvoiceRequest request = InvoiceRequest.builder()
                .orderNumber("INV-00123")
                .date("2024-12-13")
                .dueDate("2025-01-13")
                .subtotal("175.00")
                .taxRate("10")
                .taxAmount("17.50")
                .totalAmount("192.50")
                .seller(seller)
                .buyer(buyer)
                .items(items)
                .build();

        var response = documentService.generateInvoiceDocument(request);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=invoice.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(response.toByteArray());
    }
}
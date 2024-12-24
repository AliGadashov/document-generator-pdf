package com.gadashov.pdf.dto.request.component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceItemCC {
    private String name;
    private int quantity;
    private double price;
    private double total;
}

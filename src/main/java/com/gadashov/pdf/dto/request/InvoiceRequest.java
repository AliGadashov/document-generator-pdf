package com.gadashov.pdf.dto.request;

import com.gadashov.pdf.dto.request.component.BuyerCC;
import com.gadashov.pdf.dto.request.component.InvoiceItemCC;
import com.gadashov.pdf.dto.request.component.SellerCC;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceRequest {

    private String orderNumber;
    private String date;
    private String dueDate;
    private String subtotal;
    private String taxRate;
    private String taxAmount;
    private String totalAmount;

    private SellerCC seller;
    private BuyerCC buyer;
    private List<InvoiceItemCC> items;

}

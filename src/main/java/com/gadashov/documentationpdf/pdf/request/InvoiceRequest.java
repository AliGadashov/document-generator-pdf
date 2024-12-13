package com.gadashov.documentationpdf.pdf.request;

import com.gadashov.documentationpdf.pdf.root.Item;
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

    private String invoiceNumber;
    private String date;
    private String dueDate;
    private String sellerName;
    private String sellerAddress;
    private String sellerEmail;
    private String buyerName;
    private String buyerAddress;
    private String buyerEmail;
    private List<Item> items;
    private String subtotal;
    private String taxRate;
    private String taxAmount;
    private String totalAmount;

}

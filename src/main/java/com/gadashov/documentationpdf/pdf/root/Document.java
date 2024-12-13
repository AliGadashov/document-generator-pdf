package com.gadashov.documentationpdf.pdf.root;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

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
    private double subtotal;
    private double taxRate;
    private double taxAmount;
    private double totalAmount;

}

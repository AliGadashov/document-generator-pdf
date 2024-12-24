package com.gadashov.pdf.service;

import com.gadashov.pdf.dto.request.InvoiceRequest;
import com.gadashov.pdf.util.PdfGenerator;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;

@Service
public class DocumentService {
    private final SpringTemplateEngine templateEngine;

    public DocumentService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public ByteArrayOutputStream generateInvoiceDocument(InvoiceRequest request) {
        Context context = new Context();

        context.setVariable("invoiceNumber", request.getOrderNumber());
        context.setVariable("date", request.getDate());
        context.setVariable("dueDate", request.getDueDate());

        context.setVariable("sellerName", request.getSeller().getName());
        context.setVariable("sellerAddress", request.getSeller().getAddress());
        context.setVariable("sellerEmail", request.getSeller().getEmail());

        context.setVariable("buyerName", request.getBuyer().getName());
        context.setVariable("buyerAddress", request.getBuyer().getAddress());
        context.setVariable("buyerEmail", request.getBuyer().getEmail());

        context.setVariable("items", request.getItems());
        context.setVariable("subtotal", request.getSubtotal());
        context.setVariable("taxRate", request.getTaxRate());
        context.setVariable("taxAmount", request.getTaxAmount());
        context.setVariable("totalAmount", request.getTotalAmount());
        var content = templateEngine.process("invoice_template", context);
        return PdfGenerator.generate(content);
    }

}

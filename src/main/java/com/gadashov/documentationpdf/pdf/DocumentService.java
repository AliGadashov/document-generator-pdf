package com.gadashov.documentationpdf.pdf;

import com.gadashov.documentationpdf.pdf.request.InvoiceRequest;
import com.gadashov.documentationpdf.pdf.root.component.DocumentType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class DocumentService {

    public String generateDocument(DocumentType documentType, InvoiceRequest data) throws IOException {

        String htmlTemplatePath = getHtmlTemplatePath(documentType);

        String outputFilePath = "src/main/resources/pdfOutput/output_" + System.currentTimeMillis() + ".pdf";

        PdfGenerator.generatePdf(htmlTemplatePath, data, outputFilePath);

        return outputFilePath;
    }

    private String getHtmlTemplatePath(DocumentType documentType) {
        switch (documentType) {
            case INVOICE:
                return "src/main/resources/html/invoice_template.html";
            case RECEIPT:
                return "src/main/resources/html/report_template.html";
            case CONTRACT:
                return "src/main/resources/html/receipt_template.html";
            default:
                throw new IllegalArgumentException("Unknown document type: " + documentType);
        }
    }
}

package com.gadashov.documentationpdf.pdf;

import com.gadashov.documentationpdf.pdf.request.InvoiceRequest;
import com.gadashov.documentationpdf.pdf.root.component.DocumentType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/pdf")
public class DocumentController {

    private final DocumentService pdfService;

    public DocumentController(DocumentService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/generate")
    public String generatePdf(@RequestParam DocumentType documentType, @RequestBody InvoiceRequest request) {

        try {
            String outputFilePath = pdfService.generateDocument(documentType, request);
            return "PDF generated successfully: " + outputFilePath;
        } catch (IOException e) {
            return "Error generating PDF: " + e.getMessage();
        }
    }
}
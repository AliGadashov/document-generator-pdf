package com.gadashov.documentationpdf.pdf;

import com.gadashov.documentationpdf.pdf.request.InvoiceRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.util.Map;

public class PdfGenerator {

    public static void generatePdf(String htmlTemplatePath, InvoiceRequest data, String outputFilePath) throws IOException {
        // Load the HTML template
        String htmlContent = loadHtmlTemplate(htmlTemplatePath);

        // Fill the HTML template with data
        String filledHtmlContent = fillHtmlTemplate(htmlContent, data);



        // Parse the filled HTML content
        Document document = Jsoup.parse(filledHtmlContent, "UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

        // Generate PDF from the filled HTML content
        try (OutputStream outputStream = new FileOutputStream(outputFilePath)) {
            ITextRenderer renderer = new ITextRenderer();
            SharedContext context = renderer.getSharedContext();
            context.setPrint(true);
            context.setInteractive(false);

            // Set the base URL for resources (images, CSS, etc.)
            String baseUrl = FileSystems.getDefault()
                    .getPath("src/main/resources/html")
                    .toUri()
                    .toURL()
                    .toString();

            // Set the document for the renderer and create the PDF
            renderer.setDocumentFromString(document.html(), baseUrl);
            renderer.layout();
            renderer.createPDF(outputStream);

            System.out.println("PDF generation completed: " + outputFilePath);
        } catch (Exception e) {
            throw new IOException("Error generating PDF: " + e.getMessage(), e);
        }
    }

    private static String loadHtmlTemplate(String htmlTemplatePath) throws IOException {
        File htmlFile = new File(htmlTemplatePath);
        return new String(java.nio.file.Files.readAllBytes(htmlFile.toPath()));
    }

    private static String fillHtmlTemplate(String htmlContent, InvoiceRequest data) {
        // Iterate over the map and replace placeholders in the HTML
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getValue() instanceof Map) {
                // Process nested maps recursively
                Map<String, Object> innerMap = (Map<String, Object>) entry.getValue();
                htmlContent = processInnerMap(htmlContent, innerMap);
            } else {
                // Replace placeholder with value for non-map entries
                htmlContent = replacePlaceholder(htmlContent, entry.getKey(), entry.getValue());
            }
        }
        return htmlContent;
    }

    private static String processInnerMap(String htmlContent, Map<String, Object> innerMap) {
        // Iterate over the inner map and process each entry
        for (Map.Entry<String, Object> innerEntry : innerMap.entrySet()) {
            htmlContent = processValue(htmlContent, innerEntry);
        }
        return htmlContent;
    }

    private static String processValue(String htmlContent, Map.Entry<String, Object> entry) {
        // If the value is a map, process it recursively
        if (entry.getValue() instanceof Map) {
            htmlContent = processInnerMap(htmlContent, (Map<String, Object>) entry.getValue());
        } else {
            // Otherwise, replace the placeholder with the value
            htmlContent = replacePlaceholder(htmlContent, entry.getKey(), entry.getValue());
        }
        return htmlContent;
    }

    private static String replacePlaceholder(String htmlContent, String key, Object value) {
        // Create the placeholder and the replacement value
        String placeholder = "{{" + key + "}}";
        String replacement = (value != null) ? value.toString() : "";

        System.out.println("Replacing: " + placeholder + " with: " + replacement);

        // Replace the placeholder in the HTML content
        return htmlContent.replace(placeholder, replacement);
    }
}

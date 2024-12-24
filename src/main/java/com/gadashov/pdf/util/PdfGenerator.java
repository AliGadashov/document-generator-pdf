package com.gadashov.pdf.util;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;

public class PdfGenerator {
    @SneakyThrows
    public static ByteArrayOutputStream generate(String content) {

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(content, null);
            builder.toStream(outputStream);
            builder.run();

            return outputStream;
        }

    }

}

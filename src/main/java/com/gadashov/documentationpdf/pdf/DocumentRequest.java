package com.gadashov.documentationpdf.pdf;

import com.gadashov.documentationpdf.pdf.root.component.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentRequest {
    private DocumentType documentType;
    private Map<String, Object> data;
}

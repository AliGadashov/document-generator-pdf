package com.gadashov.documentationpdf.pdf.root;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    private String name;
    private int quantity;
    private double price;
    private double total;

}

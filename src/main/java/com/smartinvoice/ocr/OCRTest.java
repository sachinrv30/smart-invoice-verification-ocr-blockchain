package com.smartinvoice.ocr;

import java.io.File;

public class OCRTest {

    public static void main(String[] args) {

        File file = new File("uploads/sample_invoice.png");

        System.out.println("Exists: " + file.exists());
        System.out.println("Absolute Path: " + file.getAbsolutePath());

        OCRService service = new OCRService();

        String text = service.extractText(file);

        System.out.println("\n========== OCR OUTPUT ==========");
        System.out.println(text);
    }
}
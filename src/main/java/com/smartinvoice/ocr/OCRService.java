package com.smartinvoice.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;

public class OCRService {

    private final ITesseract tesseract;

    public OCRService() {
        tesseract = new Tesseract();
        tesseract.setDatapath("/opt/homebrew/share/tessdata");
        tesseract.setLanguage("eng");
    }

    public String extractText(File imageFile) {
        try {
            return tesseract.doOCR(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
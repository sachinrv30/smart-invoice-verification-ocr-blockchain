package com.smartinvoice.model;

public class OCRResult {

    private String rawText;
    private boolean success;

    public OCRResult() {
    }

    public OCRResult(String rawText, boolean success) {
        this.rawText = rawText;
        this.success = success;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
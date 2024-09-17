package com.example.userregistrationportal.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class OcrService {

    // Initialize Tesseract OCR instance
    private final ITesseract tesseract;

    public OcrService() {
        tesseract = new Tesseract();
        // Optionally set the language and the data path (this is where the trained language data is located)
        tesseract.setDatapath("C:\\Tess4J\\tessdata");  // Make sure the path points to the correct location of tessdata
        tesseract.setLanguage("eng");  // English language for OCR
    }

    /**
     * Method to extract Aadhar number from the uploaded Aadhar card image.
     *
     * @param aadharImage Aadhar card image file
     * @return Extracted Aadhar number as a string
     * @throws TesseractException if OCR processing fails
     */
    public String extractAadhaarNumberFromImage(File aadharImage) throws TesseractException {
        // Perform OCR on the image file to extract text
        String result = tesseract.doOCR(aadharImage);
        // Extract Aadhar number (12-digit number) from the recognized text
        String aadharNumber = extractAadharNumber(result);

        return aadharNumber;
    }

    /**
     * This method validates whether the uploaded image contains the Aadhaar card number.
     * It performs basic validation by checking for a 12-digit number in the OCR-extracted text.
     *
     * @param extractedText Extracted text from the image file
     * @return true if Aadhaar card image, otherwise false
     */
    public boolean isAadhaarImage(File aadharImage) {
        try {
            // Perform OCR on the image file
            String result = tesseract.doOCR(aadharImage);
            // Check for the presence of a valid Aadhar number
            return extractAadharNumber(result) != null;
        } catch (TesseractException e) {
            return false;
        }
    }

    /**
     * Helper method to extract a 12-digit Aadhaar number from the recognized text using regex.
     *
     * @param ocrText OCR extracted text
     * @return Extracted Aadhar number or null if not found
     */
    private String extractAadharNumber(String ocrText) {
        // Regular expression to match 12-digit Aadhaar numbers
        String regex = "\\b\\d{4}\\s?\\d{4}\\s?\\d{4}\\b";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(ocrText);

        // If a match is found, return the Aadhar number
        if (matcher.find()) {
            return matcher.group().replaceAll("\\s", "");  // Remove spaces if present
        }

        // Return null if no valid Aadhar number is found
        return null;
    }
}
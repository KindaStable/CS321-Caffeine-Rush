package com.example.LinguaLens;

import java.io.IOException;
import java.util.Locale;
import org.apache.commons.text.StringEscapeUtils;

/**
 * Main class for LinguaLens.
 * This class handles the main flow of the application, including image selection, text extraction, translation, and display.
 * 
 * @author Garrett Thrower
 */
public class Main {
    public static void main(String[] args) throws IOException {

        // Show initial dialog to the user asking for an image to translate.
        UI.showDialogue("Please select an image with text that you would like to be translated.", "Select Image", 0);

        // Let the user choose an image and read the text from it.
        String filePath = UI.chooseImage();
        Reader.read(filePath);

        // Escape special characters in the extracted text to prepare it for translation.
        String escapedText = StringEscapeUtils.escapeJson(StorageManager.getImageText());

        // Construct the prompt for the translation request.
        String prompt = "Translate the all of the below text into " + Locale.getDefault().getDisplayLanguage() + ". Ensure that all of the text is displayed: " + escapedText;

        // If there is text to translate, translate it, store the translation, and display it to the user.
        if (!escapedText.isEmpty()) {
            Translator.translate(prompt);
            UI.showDialogue(StorageManager.getLastTranslation(), "Thanks", 0);
        }

        // Exit the application.
        System.exit(0);
    }
}
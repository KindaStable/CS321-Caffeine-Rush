package com.example.LinguaLens;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import com.example.LinguaLens.Reader.Vertex;

import java.util.stream.Stream;
import java.nio.file.Files;

/**
 * This class stores the inputted image, text from the image,  the boundaries 
 * of the text from the image, and the translated text
 * 
 * @author Braden Doty
 */
public class StorageManager {
    private static Map<String, List<Vertex>> storedWordMap = new HashMap<>();
    private static String storedImageText = "";
    private static String storedTranslation = "";
    private static String translationLanguage = "";

    // Specify the folder names
    private static final String HISTORY_FOLDER = "History";
    private static final String IMAGES_FOLDER = "InputImages";

    /**
     * stores the text from the image and the word boundaries
     * of the words
     * 
     * @param imageText A string with the image text from the Reader class
     * @param wordBoundaries A hash map from the reader class
     */
    public static void storeReaderValues(String imageText, Map<String, List<Reader.Vertex>> wordBoundaries){
        storedWordMap = wordBoundaries;
        storedImageText = imageText;
    }

    /**
     * stores the translated text in a string variable called translation.
     * The text is also stored in a text file in the history folder 
     * titled "yyyyMMddHHmmss"
     * 
     * @param translation The translated text from the API in a string
     */
  public static void storeTranslation(String translation) {
        try {
            String[] tempStore = translation.split("\\[");
            String translationLanguageLine = tempStore[0];
            translationLanguage = translationLanguageLine.substring(translationLanguageLine.indexOf(":") + 2).trim();
    
            // Check if there is a closing bracket and extract the text inside brackets.
            if (tempStore.length > 1) {
                storedTranslation = tempStore[1].split("\\]")[0];
            } else {
                storedTranslation = ""; // No translation text found within brackets
            }
    
            String language = translationLanguageLine.substring(translationLanguageLine.indexOf(":") + 2); // +2 to skip the colon and the space
            translationLanguage = language;

            createFolderIfNotExists(HISTORY_FOLDER);
            
            //Get the current date and time
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String formattedDate = dateFormat.format(currentDate);
            
            //Specify the file name with the current date and time
            String fileName = formattedDate + ".txt";
            
            //Create a FileWriter and specify the file path
            File newFile = new File(HISTORY_FOLDER, fileName);

            deleteOldestFileIfLimitReached(HISTORY_FOLDER, 10);

            writeTextToFile(storedTranslation, newFile);

            System.out.println("File '" + fileName + "' has been created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * stores a copy of the inputted image in a folder called
     *  "inputImages". The images are renamed "yyyyMMddHHmmss"
     * @param inputtedImageLocation The file path of the users image
     */
    public static void storeImage(String inputtedImageLocation) {
        try {
            createFolderIfNotExists(IMAGES_FOLDER);
            
            //Get the current date and time
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String formattedDate = dateFormat.format(currentDate);

            //Specify the image name with the current date and time
            String imageName = formattedDate + ".png";
            
            File outputImageFile = new File(IMAGES_FOLDER, imageName);
            
            //Create a ImageWriter and specify the file path
            Image image = ImageIO.read(new File(inputtedImageLocation));
            ImageIO.write((BufferedImage) image, "png", outputImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns a hash map containing the text from the image and the coordinates
     * of each word
     * 
     * @return 
     */
    public static Map<String, List<Vertex>> getWordDimensionsMap() {
        return storedWordMap;
    }

    /**
     * Returns the text from the image in a string variable
     * @return
     */
    public static String getImageText() {
        return storedImageText;
    }

    /**
     * Returns the translated text in a string variable
     * @return
     */
    public static String getLastTranslation() {
        return storedTranslation;
    }

    /**
     * Returns the language of the inputted text
     * @return
     */
    public static String getTranslationLanguage()
    {
        return translationLanguage;
    }
    
    //Check if a folder exist in the current working directory. If not create one
    private static void createFolderIfNotExists(String folderName) {
        File newFolder = new File(System.getProperty("user.dir"), folderName);
        if (!newFolder.exists()) {
            if (newFolder.mkdir()) {
                System.out.println("Folder created successfully.");
            } else {
                System.out.println("Failed to create the folder.");
            }
        }
    }
    
    //Write data to created text files
    private static void writeTextToFile(String text, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            writer.write(text);
        }
    }

    /**
     * Delets the oldest file in the History Folder afer the limit of text files
     * is past
     */
    private static void deleteOldestFileIfLimitReached(String folderName, int fileLimit) {
        try {
            Path historyFolderPath = Paths.get(System.getProperty("user.dir"), folderName);

            // List all files in the History folder
            try (Stream<Path> paths = Files.list(historyFolderPath)) {
                long fileCount = paths.count();

                // If the file count exceeds the limit, delete the oldest file
                if (fileCount >= fileLimit) {
                    Files.walk(historyFolderPath)
                            .filter(Files::isRegularFile)
                            .min(Comparator.comparingLong(path -> path.toFile().lastModified()))
                            .ifPresent(oldestFile -> {
                                try {
                                    Files.delete(oldestFile);
                                    System.out.println("Deleted the oldest file: " + oldestFile.getFileName());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
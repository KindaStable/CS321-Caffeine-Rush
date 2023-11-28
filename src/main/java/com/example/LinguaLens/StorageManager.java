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
    private static String storedImageLocation = "";
      private static String storedImageName = "";
    private static String translationLanguage = "";

    // Specify the folder and file names
    private static final String HISTORY_FOLDER = "History";
    private static final String IMAGE_FILE = "image" + ".png";;
    private static final String OGTEXT_FILE = "ogText" + ".txt";;
    private static final String TRANSLATEDTEXT_FILE = "translatedText" + ".txt";;


    /**
     * stores a copy of the inputted image in a folder called
     *  "inputImages". The images are renamed "yyyyMMddHHmmss"
     * @param inputtedImageLocation The file path of the users image
     */
    public static void storeImageLocation(String inputtedImageLocation, String imageName) {
        storedImageLocation = inputtedImageLocation;
        storedImageName = imageName;
    }

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

            translationHistory();
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

        /**
     * Creates the History folder and its sub folders
     * 
     */
    private static void translationHistory()
    {
            createFolderIfNotExists(HISTORY_FOLDER);
            
            //Get the current date and time
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String formattedDate = dateFormat.format(currentDate);

            createFolderIfNotExists(formattedDate);

            createFile(formattedDate, TRANSLATEDTEXT_FILE);
            createFile(formattedDate, OGTEXT_FILE);
            createFile(formattedDate, IMAGE_FILE);

            deleteOldestFolderIfLimitReached(HISTORY_FOLDER, 10);
    }

    /**
     * Wrte strings to folders
     * 
     * 
     * @param text contains the string that is put in the file
     * @param file contains the file that is to be written too
     */
    private static void writeTextToFile(String text, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            writer.write(text);
        }
    }
    
    /**
     * Check if a folder exist in the current working directory. If not create one
     * 
     * 
     * @param folderName Either HISTORY_FOlDER or The Date
     */
    private static void createFolderIfNotExists(String folderName) {
        if (folderName == HISTORY_FOLDER){
            File newFolder = new File(System.getProperty("user.dir"), folderName);
            if (!newFolder.exists()) {
                if (newFolder.mkdir()) {
                    System.out.println("Folder created successfully.");
                } else {
                    System.out.println("Failed to create the folder.");
                }
            }
        }
        else{
            Path historyFolderPath = Paths.get(System.getProperty("user.dir"), HISTORY_FOLDER);
            File newFolder = new File(historyFolderPath.toString(), folderName);
            if (!newFolder.exists()) {
                if (newFolder.mkdir()) {
                    System.out.println("Folder created successfully.");
                } else {
                    System.out.println("Failed to create the folder.");
                }
            }
        }

    }
            

    /**
     * Create the image, ogText, and translatedText file for the History folder
     * 
     * @param formattedDate stores the name of the folders
     * @param fileName contains the name of the file being created
     */
    private static void createFile(String formattedDate, String fileName)
    {
        try{
        //Create a FileWriter and specify the file path
        Path historyFolderPath = Paths.get(System.getProperty("user.dir"), HISTORY_FOLDER);
        if (fileName == IMAGE_FILE){
            File outputImageFile = new File(historyFolderPath.toString(), formattedDate + File.separator + fileName);

            //Create a ImageWriter and specify the file path
            Image image = ImageIO.read(new File(storedImageLocation+storedImageName));
            ImageIO.write((BufferedImage) image, "png", outputImageFile);
        }
        else{
            File newFile = new File(historyFolderPath.toString(), formattedDate + File.separator + fileName);

            if(fileName == TRANSLATEDTEXT_FILE)
            {
                writeTextToFile(storedTranslation, newFile);
            }
            else
            {
                writeTextToFile(storedImageText, newFile);
            }
        }
            
        System.out.println("File '" + fileName + "' has been created.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the oldest folder in the History Folder afer the limit of text files
     * is past based on the folder names
     * 
     * @param folderName name of folder being searched
     * @param folderLimit Max folders in History folders
     */
    private static void deleteOldestFolderIfLimitReached(String folderName, int folderLimit) {
        try {
            Path historyFolderPath = Paths.get(System.getProperty("user.dir"), folderName);
            List<Path> folders = Files.list(historyFolderPath)
                    .filter(Files::isDirectory)
                    .sorted(Comparator.comparingLong(path -> Long.parseLong(path.getFileName().toString())))
                    .toList();
            if (folders.size() > folderLimit) {
                List<Path> foldersToDelete = folders.subList(0, folders.size() - folderLimit);
                for (Path folder : foldersToDelete) {
                    Files.walk(folder)
                            .sorted(Comparator.reverseOrder())
                            .map(Path::toFile)
                            .forEach(File::delete);

                    System.out.println("Deleted folder: " + folder);
                }
            }
        

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

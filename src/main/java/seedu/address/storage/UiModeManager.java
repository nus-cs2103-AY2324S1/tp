package seedu.address.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Manages the storage and validation of a UIMode.
 */
public class UiModeManager {

    private String uiMode;
    private final String uiModeFilePath; // The path to the file where the UIMode is stored

    /**
     * Constructs a UiModeManager and fetch the UiMode from a file called uiMode.txt,
     * file will be created if it does not exist.
     */
    public UiModeManager() {
        this.uiModeFilePath = "./data/uiMode.txt";
        this.uiMode = readUiModeFromFile();
    }

    /**
     * Reads the UIMode from a file if it exists, otherwise, returns an empty string.
     *
     * @return The stored UIMode or an empty string if no UIMode is set.
     */
    private String readUiModeFromFile() {
        try {
            Path folder = Paths.get("./data/");
            if (!Files.exists(folder)) {
                Files.createDirectory(folder);
            }
        } catch (Exception e) {
            return "there was an error creating folder";
        }
        Path path = Paths.get("data", "uiMode.txt");
        File uiModeFile = path.toFile();
        if (!uiModeFile.exists()) {
            createUiModeFile(uiModeFile);
            return "MainWindow.fxml";
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(uiModeFilePath))) {
            String string = reader.readLine();
            if (string == null) {
                return "MainWindow.fxml";
            }
            if (!string.equals("LightWindow.fxml") && !string.equals("MainWindow.fxml")) {
                return "MainWindow.fxml";
            }
            return string;
        } catch (IOException e) {
            // Handle file reading error
            return "MainWindow.fxml";
        }
    }

    /**
     * Creates a UiMode file if it doesn't exist.
     *
     * @param file The file to be created.
     */
    private void createUiModeFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Checks if the input String matches the stored UIMode.
     *
     * @param input The input to be checked against the stored UIMode.
     * @return true if the input matches the stored UIMode, false otherwise.
     */
    public boolean check(String input) {
        return input.equals(uiMode);
    }

    /**
     * Retrieves the stored UIMode.
     *
     * @return The stored UIMode.
     */
    public String getUiMode() {
        return uiMode;
    }

    /**
     * Sets a new UIMode, updating it in memory and writing it to the UIMode file.
     *
     * @param newUiMode The new UIMode to be set.
     */
    public void setUiMode(String newUiMode) {
        if (newUiMode == null) {
            newUiMode = "";
        }
        uiMode = newUiMode;
        File uiModeFile = new File(uiModeFilePath);

        if (!uiModeFile.exists()) {
            createUiModeFile(uiModeFile);
        }
        writeUiModeToFile(newUiMode);
    }


    /**
     * Writes the UIMode to the UIMode file.
     *
     * @param newUiMode The UIMode to be written to the file.
     */
    private void writeUiModeToFile(String newUiMode) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(uiModeFilePath, false))) {
            writer.write(newUiMode);
        } catch (IOException e) {
            System.out.println("there was an error writing UIMode to file");
        }
    }
}

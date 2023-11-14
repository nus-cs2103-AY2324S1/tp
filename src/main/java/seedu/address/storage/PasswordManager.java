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
import java.util.Objects;

/**
 * Manages the storage and validation of a password.
 */
public class PasswordManager {

    private String password;
    private final String passwordFilePath; // The path to the file where the password is stored

    /**
     * Constructs a PasswordManager and fetch the password from a file called encoded.txt,
     * file will be created if it does not exist.
     */
    public PasswordManager() {
        this.passwordFilePath = "./data/encoded.txt";
        this.password = readPasswordFromFile();
    }

    /**
     * Reads the password from a file if it exists, otherwise, returns an empty string.
     *
     * @return The stored password or an empty string if no password is set.
     */
    private String readPasswordFromFile() {
        try {
            Path folder = Paths.get("./data/");
            if (!Files.exists(folder)) {
                Files.createDirectory(folder);
            }
        } catch (Exception e) {
            return "there was an error creating folder";
        }
        Path path = Paths.get("data", "encoded.txt");
        File passwordFile = path.toFile();

        if (!Files.exists(path)) {
            createPasswordFile(passwordFile);
            return "";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(passwordFile))) {
            String string = reader.readLine();
            return Objects.requireNonNullElse(string, "");
        } catch (IOException e) {
            // Handle file reading error
            return "";
        }
    }

    /**
     * Creates a password file if it doesn't exist.
     *
     * @param file The file to be created.
     */
    private void createPasswordFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Checks if the input String matches the stored password.
     *
     * @param input The input to be checked against the stored password.
     * @return true if the input matches the stored password, false otherwise.
     */
    public boolean check(String input) {
        return input.equals(password);
    }

    /**
     * Retrieves the stored password.
     *
     * @return The stored password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets a new password, updating it in memory and writing it to the password file.
     *
     * @param newPassword The new password to be set.
     */
    public void setPassword(String newPassword) {
        if (newPassword == null) {
            newPassword = "";
        }
        password = newPassword;
        File passwordFile = new File(passwordFilePath);

        if (!passwordFile.exists()) {
            createPasswordFile(passwordFile);
        }
        writePasswordToFile(newPassword);
    }


    /**
     * Writes the password to the password file.
     *
     * @param newPassword The password to be written to the file.
     */
    private void writePasswordToFile(String newPassword) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(passwordFilePath, false))) {
            writer.write(newPassword);
        } catch (IOException e) {
            System.out.println("there was an error writing password to file");
        }
    }
}

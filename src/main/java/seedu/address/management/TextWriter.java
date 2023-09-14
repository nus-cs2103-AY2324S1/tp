package seedu.address.management;

import seedu.address.cardsList.CardList;
import seedu.address.pojo.Flashcard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextWriter {
    private String filePath;

    private CardList ls;

    public void Write() {
        try {
            // Clean the file by overwriting it with an empty content
            BufferedWriter cleanWriter = new BufferedWriter(new FileWriter(filePath, false));
            cleanWriter.write("");
            cleanWriter.close();

            // Append tasks from the array list to the file
            BufferedWriter appendWriter = new BufferedWriter(new FileWriter(filePath, true));
            for (int i = 0; i < ls.size(); i++) {
                Flashcard task = ls.get(i);
                appendWriter.write(task.toString() + System.lineSeparator());
            }
            appendWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

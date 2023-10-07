package seedu.address.management;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import seedu.address.cardslist.CardList;
import seedu.address.pojo.FlashCard;
/**
 * Writes a .txt file based on the cardList
 *
 * @author Wang Cheng
 * @version 1.0
 * @since 1.0
 */
public class TextWriter {
    private String filePath;

    private CardList ls;

    /**
     * Takes flashcards from CardList and formats it into a string to write to .txt file
     */
    public void write() {
        try {
            // Clean the file by overwriting it with an empty content
            BufferedWriter cleanWriter = new BufferedWriter(new FileWriter(filePath, false));
            cleanWriter.write("");
            cleanWriter.close();

            // Append tasks from the array list to the file
            BufferedWriter appendWriter = new BufferedWriter(new FileWriter(filePath, true));
            for (int i = 0; i < ls.size(); i++) {
                FlashCard task = ls.get(i);
                appendWriter.write(task.toString() + System.lineSeparator());
            }
            appendWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

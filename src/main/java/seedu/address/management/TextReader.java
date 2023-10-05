package seedu.address.management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import seedu.address.cardslist.CardList;
import seedu.address.pojo.FlashCard;


/**
 * Reads from .txt file and returns CardList
 *
 * @author Wang Cheng
 * @version 1.0
 * @since 1.0
 */
public class TextReader {
    private final String filePath;

    private CardList ls;

    public TextReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads .txt file and processes the data into flashcards to be placed in a CardList
     */
    public void readAndProcessFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(""); // Assuming tab-separated values

                if (columns.length == 4) {
                    String word = columns[0];
                    String translation = columns[1];
                    Date reviewDate = dateFormat.parse(columns[2]);
                    int level = Integer.parseInt(columns[3]);

                    FlashCard flashcard = new FlashCard(word, translation, reviewDate, level);
                    ls.add(flashcard);

                } else {
                    System.err.println("Invalid data format: " + line);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}



package seedu.flashlingo.model.util;

import java.util.Date;

import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.ReadOnlyFlashlingo;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.ProficiencyLevel;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

/**
 * Contains utility methods for populating {@code Flashlingo} with sample data.
 */
public class SampleDataUtil {
    public static FlashCard[] getSampleFlashCards() {
        return new FlashCard[]{
            new FlashCard(new OriginalWord("Welcome", "English"),
                new TranslatedWord("欢迎", "Chinese"), new Date(), new ProficiencyLevel(1)),
            new FlashCard(new OriginalWord("to", "English"), new TranslatedWord("来", "Chinese"),
                new Date(), new ProficiencyLevel(1)),
            new FlashCard(new OriginalWord("Flashlingo", "English"),
                new TranslatedWord("闪邻国", "Chinese"), new Date(), new ProficiencyLevel(1))
        };
    }

    public static ReadOnlyFlashlingo getSampleFlashlingo() {
        Flashlingo sampleFl = new Flashlingo();
        for (FlashCard sampleFlashCard : getSampleFlashCards()) {
            sampleFl.addFlashCard(sampleFlashCard);
        }
        return sampleFl;
    }
}

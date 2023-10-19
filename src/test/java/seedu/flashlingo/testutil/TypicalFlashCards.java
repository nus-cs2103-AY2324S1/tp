package seedu.flashlingo.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * A utility class containing a list of {@code FlashCard} objects to be used in tests.
 */
public class TypicalFlashCards {
    public static final FlashCard WORD = new FlashCardBuilder().withOriginalWord("你好", "Chinese")
            .withTranslatedWord("hi", "English").withLevel(2)
            .withWhenToReview("2023-12-12T23:59:59Z").build();

    private TypicalFlashCards() {} // prevents instantiation

    /**
     * Returns an {@code Flashlingo} with all the typical flash cards.
     */
    public static Flashlingo getTypicalFlashlingo() {
        Flashlingo fl = new Flashlingo();
        for (FlashCard flashCard : getTypicalFlashCards()) {
            fl.addFlashCard(flashCard);
        }
        return fl;
    }

    public static List<FlashCard> getTypicalFlashCards() {
        return new ArrayList<>(Arrays.asList(WORD));
    }

}

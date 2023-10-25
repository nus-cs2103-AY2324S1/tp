package seedu.flashlingo.testutil;

import static seedu.flashlingo.logic.commands.CommandTestUtil.VALID_ORIGINAL_WORD_AMY;
import static seedu.flashlingo.logic.commands.CommandTestUtil.VALID_ORIGINAL_WORD_BOB;
import static seedu.flashlingo.logic.commands.CommandTestUtil.VALID_TRANSLATION_AMY;
import static seedu.flashlingo.logic.commands.CommandTestUtil.VALID_TRANSLATION_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * A utility class containing a list of {@code FlashCard} objects to be used in tests.
 */
public class TypicalFlashCards {

    public static final FlashCard DANIEL = new FlashCardBuilder().withOriginalWord("Daniel Meier",
                    "English")
            .withTranslatedWord("丹尼尔·梅尔", "Mandarin")
            .build();

    public static final FlashCard BENSON = new FlashCardBuilder().withOriginalWord("Benson Meier",
                    "English")
            .withTranslatedWord("本森·梅尔", "Mandarin")
            .build();


    // Manually added
    public static final FlashCard HOON = new FlashCardBuilder().withOriginalWord("Hoon Meier",
                    "English")
            .withTranslatedWord("胡恩·梅尔", "Mandarin")
            .build();
    public static final FlashCard IDA = new FlashCardBuilder().withOriginalWord("Ida Mueller",
                    "English")
            .withTranslatedWord("艾达·米勒", "Mandarin")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final FlashCard AMY = new FlashCardBuilder().withOriginalWord(VALID_ORIGINAL_WORD_AMY,
                    "")
            .withTranslatedWord(VALID_TRANSLATION_AMY, "")
            .build();
    public static final FlashCard BOB = new FlashCardBuilder().withOriginalWord(VALID_ORIGINAL_WORD_BOB,
                    "")
            .withTranslatedWord(VALID_TRANSLATION_BOB, "")
            .build();

    public static final FlashCard WORD = new FlashCardBuilder().withOriginalWord("你好", "Chinese")
            .withTranslatedWord("hi", "English").withLevel(2)
            .withWhenToReview("2023-12-12T23:59:59Z").build();

    private TypicalFlashCards() {} // prevents instantiation

    /**
     * Returns an {@code Flashlingo} with all the typical flashcards.
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

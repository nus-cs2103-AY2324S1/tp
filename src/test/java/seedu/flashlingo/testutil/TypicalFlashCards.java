package seedu.flashlingo.testutil;

import java.util.List;

import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.flashcard.FlashCard;

import static seedu.flashlingo.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalFlashCards {

    public static final FlashCard ALICE = new FlashcardBuilder().withOriginalWord("Alice Pauline",
                    "English")
            .withTranslation("爱丽丝·宝琳","Mandarin").build();
    public static final FlashCard BENSON = new FlashcardBuilder().withOriginalWord("Benson Meier",
                    "English")
            .withTranslation("本森·梅尔", "Mandarin")
            .build();
    public static final FlashCard CARL = new FlashcardBuilder().withOriginalWord("Carl Kurz", "English")
            .withTranslation("卡尔·库尔兹", "Mandarin")
            .build();
    public static final FlashCard DANIEL = new FlashcardBuilder().withOriginalWord("Daniel Meier",
                    "English")
            .withTranslation("丹尼尔·梅尔", "Mandarin")
            .build();
    public static final FlashCard ELLE = new FlashcardBuilder().withOriginalWord("Elle Meyer",
                    "English")
            .withTranslation("艾尔·迈耶", "Mandarin")
            .build();
    public static final FlashCard FIONA = new FlashcardBuilder().withOriginalWord("Fiona Kunz",
                    "English")
            .withTranslation("菲奥娜昆兹", "Mandarin")
            .build();
    public static final FlashCard GEORGE = new FlashcardBuilder().withOriginalWord("George Best",
                    "English")
            .withTranslation("乔治·贝斯特", "Mandarin")
            .build();

    // Manually added
    public static final FlashCard HOON = new FlashcardBuilder().withOriginalWord("Hoon Meier",
                    "English")
            .withTranslation("胡恩·梅尔", "Mandarin")
            .build();
    public static final FlashCard IDA = new FlashcardBuilder().withOriginalWord("Ida Mueller",
                    "English")
            .withTranslation("艾达·米勒","Mandarin")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final FlashCard AMY = new FlashcardBuilder().withOriginalWord(VALID_ORIGINAL_WORD_AMY,
                    VALID_ORIGINAL_WORD_LANGUAGE)
            .withTranslation(VALID_TRANSLATION_AMY, VALID_TRANSLATION_LANGUAGE)
            .build();
    public static final FlashCard BOB = new FlashcardBuilder().withOriginalWord(VALID_ORIGINAL_WORD_BOB,
                    VALID_ORIGINAL_WORD_LANGUAGE)
            .withTranslation(VALID_TRANSLATION_BOB, VALID_TRANSLATION_LANGUAGE)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

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
        //TODO:
        return null;
    }
}

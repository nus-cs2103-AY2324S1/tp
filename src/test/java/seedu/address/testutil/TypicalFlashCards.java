package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ORIGINAL_WORD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORIGINAL_WORD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSLATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSLATION_BOB;

import java.util.List;

import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalFlashCards {

    public static final FlashCard ALICE = new FlashcardBuilder().withOriginalWord("Alice Pauline")
            .withTranslation("爱丽丝·宝琳").build();
    public static final FlashCard BENSON = new FlashcardBuilder().withOriginalWord("Benson Meier")
            .withTranslation("本森·梅尔")
            .build();
    public static final FlashCard CARL = new FlashcardBuilder().withOriginalWord("Carl Kurz")
            .withTranslation("卡尔·库尔兹")
            .build();
    public static final FlashCard DANIEL = new FlashcardBuilder().withOriginalWord("Daniel Meier")
            .withTranslation("丹尼尔·梅尔")
            .build();
    public static final FlashCard ELLE = new FlashcardBuilder().withOriginalWord("Elle Meyer")
            .withTranslation("艾尔·迈耶")
            .build();
    public static final FlashCard FIONA = new FlashcardBuilder().withOriginalWord("Fiona Kunz")
            .withTranslation("菲奥娜昆兹")
            .build();
    public static final FlashCard GEORGE = new FlashcardBuilder().withOriginalWord("George Best")
            .withTranslation("乔治·贝斯特")
            .build();

    // Manually added
    public static final FlashCard HOON = new FlashcardBuilder().withOriginalWord("Hoon Meier")
            .withTranslation("胡恩·梅尔")
            .build();
    public static final FlashCard IDA = new FlashcardBuilder().withOriginalWord("Ida Mueller")
            .withTranslation("艾达·米勒")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final FlashCard AMY = new FlashcardBuilder().withOriginalWord(VALID_ORIGINAL_WORD_AMY)
            .withTranslation(VALID_TRANSLATION_AMY)
            .build();
    public static final FlashCard BOB = new FlashcardBuilder().withOriginalWord(VALID_ORIGINAL_WORD_BOB)
            .withTranslation(VALID_TRANSLATION_BOB)
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

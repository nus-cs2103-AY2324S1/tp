package seedu.flashlingo.model.util;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.ReadOnlyFlashlingo;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.ProficiencyLevel;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;
import seedu.flashlingo.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Flashlingo} with sample data.
 */
public class SampleDataUtil {
    public static FlashCard[] getSampleFlashCards() {
        return new FlashCard[]{new FlashCard(new OriginalWord("sorry", "eng"),
            new TranslatedWord("gomen", "jap"), new Date(), new ProficiencyLevel(1)),
            new FlashCard(new OriginalWord("hi", "eng"),
                    new TranslatedWord("konnichiwa", "jap"), new Date(), new ProficiencyLevel(2))
        };
    }

    public static ReadOnlyFlashlingo getSampleFlashlingo() {
        Flashlingo sampleFl = new Flashlingo();
        for (FlashCard sampleFlashCard : getSampleFlashCards()) {
            sampleFl.addFlashCard(sampleFlashCard);
        }
        return sampleFl;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
          .map(Tag::new)
          .collect(Collectors.toSet());
    }

}

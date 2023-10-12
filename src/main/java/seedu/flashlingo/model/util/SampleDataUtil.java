package seedu.flashlingo.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.ReadOnlyFlashlingo;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Flashlingo} with sample data.
 */
public class SampleDataUtil {
    public static FlashCard[] getSampleFlashCards() {
        //TODO: Add sample data/import from file
        return new FlashCard[]{};
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

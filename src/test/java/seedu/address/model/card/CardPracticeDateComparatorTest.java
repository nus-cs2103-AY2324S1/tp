package seedu.address.model.card;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.CardBuilder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardPracticeDateComparatorTest {
    @Test
    public void compareTo() {
        Card cardWithLowPriority = new CardBuilder()
                .withNextPracticeDate(LocalDateTime.of(2023, 4, 10, 23, 59))
                .build();
        Card cardWithHighPriority = new CardBuilder()
                .withNextPracticeDate(LocalDateTime.of(2018, 4, 10, 23, 59))
                .build();
        // card has higher priority
        assertTrue(cardWithHighPriority.compareTo(cardWithLowPriority) < 0);
        // equal priority
        assertTrue(cardWithLowPriority.compareTo(cardWithLowPriority) == 0);
        // card has lower priority
        assertTrue(cardWithLowPriority.compareTo(cardWithHighPriority) > 0);
    }
}

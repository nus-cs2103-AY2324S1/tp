package seedu.address.model.card;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Card's answer in lesSON.
 */
public class PracticeDate implements Comparable<PracticeDate> {
    public static final String MESSAGE_CONSTRAINTS =
            "Practice Date should be a valid date, such as in the form YYYY-MM-DDThh-mm-ss";
    private static final double EASY_MULTIPLIER = 3;
    private static final double MEDIUM_MULTIPLIER = 1.5;
    private static final double HARD_MULTIPLIER = 0.5;
    private static final long BASE_DURATION_HOURS = 4; // base duration for when duration cannot be calculated
    public final LocalDateTime practiceDate;

    /**
     * Constructs a {@code PracticeDate}.
     * @param practiceDate a valid LocalDateTime containing the next practice date
     */
    public PracticeDate(LocalDateTime practiceDate) {
        requireNonNull(practiceDate);
        checkArgument(isValidNextPracticeDate(practiceDate.toString()), MESSAGE_CONSTRAINTS);
        this.practiceDate = practiceDate;
    }

    /**
     * Constructs a new next practice date based on a previous practice date.
     * Next practice date = duration between last two practice dates * multiplier + most recent practice date.
     * @param cardLastPracticeDate the card's current last practice date (i.e. the last last practice date)
     * @param cardNextPracticeDate the card's current next practice date (i.e. the most recent practice date)
     * @param mutliplier multiplier to multiply the duration between the practice dates
     */
    private PracticeDate(PracticeDate cardLastPracticeDate, PracticeDate cardNextPracticeDate, double multiplier) {
        if (cardLastPracticeDate.equals(cardNextPracticeDate)) {
            this.practiceDate = cardNextPracticeDate.practiceDate.plusHours((long) (BASE_DURATION_HOURS * multiplier));
        } else {
            Duration duration = Duration.between(cardLastPracticeDate.practiceDate, cardNextPracticeDate.practiceDate);
            long durationInSeconds = duration.getSeconds();
            long newDurationInSeconds = (long) (durationInSeconds * multiplier);
            this.practiceDate = cardNextPracticeDate.practiceDate.plusSeconds(newDurationInSeconds);
        }
    }

    /**
     * Constructs a new Practice Date based on the last practice date, next practice date, and difficulty.
     * @param cardLastPracticeDate last practice date
     * @param cardNextPracticeDate next practice date
     * @param difficulty difficulty
     * @return
     */
    public static PracticeDate calculateNewPracticeDate(
            PracticeDate cardLastPracticeDate, PracticeDate cardNextPracticeDate, String difficulty) {
        switch (difficulty) {
        case "easy":
            return new PracticeDate(cardLastPracticeDate, cardNextPracticeDate, EASY_MULTIPLIER);
        case "medium":
            return new PracticeDate(cardLastPracticeDate, cardNextPracticeDate, MEDIUM_MULTIPLIER);
        case "hard":
        default:
            return new PracticeDate(cardLastPracticeDate, cardNextPracticeDate, HARD_MULTIPLIER);
        }
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidNextPracticeDate(String test) {
        try {
            LocalDateTime.parse(test);
        } catch (DateTimeParseException d) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(PracticeDate o) {
        return this.practiceDate.compareTo(o.practiceDate);
    }

    @Override
    public String toString() {
        return this.practiceDate.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PracticeDate)) {
            return false;
        }

        PracticeDate otherDate = (PracticeDate) other;
        return practiceDate.equals(otherDate.practiceDate);
    }

    @Override
    public int hashCode() {
        return practiceDate.hashCode();
    }

}

package seedu.address.model.card;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Represents a Card's PractiseDate in lesSON.
 */
public class PracticeDate implements Comparable<PracticeDate> {
    public static final String MESSAGE_CONSTRAINTS =
            "Practice Date should be a valid date in the form: YYYY-MM-DDThh-mm-ss";

    // Multiplier for spaced repetition
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
     *
     * @param cardLastPracticeDate the card's current last practice date (i.e. the last practice date)
     * @param cardNextPracticeDate the card's current next practice date (i.e. the most recent practice date)
     * @param multiplier multiplier to multiply the duration between the practice dates
     */
    private PracticeDate(PracticeDate cardLastPracticeDate, PracticeDate cardNextPracticeDate, double multiplier) {
        if (cardLastPracticeDate.equals(cardNextPracticeDate)) {
            long hours = (long) (BASE_DURATION_HOURS * multiplier);
            this.practiceDate = cardNextPracticeDate.practiceDate.plusHours(hours);
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
     * @return a new PractiseDate after calculation
     */
    public static PracticeDate calculateNewPracticeDate(
            PracticeDate cardLastPracticeDate, PracticeDate cardNextPracticeDate, Difficulty difficulty) {
        switch (difficulty) {
        case EASY:
            return new PracticeDate(cardLastPracticeDate, cardNextPracticeDate, EASY_MULTIPLIER);
        case MEDIUM:
            return new PracticeDate(cardLastPracticeDate, cardNextPracticeDate, MEDIUM_MULTIPLIER);
        case HARD:
        default:
            return new PracticeDate(cardLastPracticeDate, cardNextPracticeDate, HARD_MULTIPLIER);
        }
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidNextPracticeDate(String practiseDateString) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(practiseDateString);
            return true;
        } catch (DateTimeParseException d) {
            return false;
        }
    }

    public String getDisplayName() {
        int year = this.practiceDate.getYear();

        Month month = this.practiceDate.getMonth();
        String monthString = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        int day = this.practiceDate.getDayOfMonth();

        return String.format("%d %s %d", day, monthString, year);
    }

    @Override
    public int compareTo(PracticeDate otherPractiseDate) {
        return this.practiceDate.compareTo(otherPractiseDate.practiceDate);
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

        // compare LocalDateTime equality
        PracticeDate otherDate = (PracticeDate) other;
        return practiceDate.equals(otherDate.practiceDate);
    }

    @Override
    public int hashCode() {
        return practiceDate.hashCode();
    }
}

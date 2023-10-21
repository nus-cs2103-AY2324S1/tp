package seedu.address.testutil;

import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A utility class to help with building Person objects.
 */
public class CardBuilder {

    public static final String DEFAULT_QUESTION = "What is the opcode for R-format instructions?";
    public static final String DEFAULT_ANSWER = "0";
    public static final LocalDateTime DEFAULT_NEXT_PRACTICE_DATE = LocalDateTime.MIN; // highest priority

    private Question question;
    private Answer answer;
    private LocalDateTime nextPracticeDate;

    /**
     * Creates a {@code CardBuilder} with the default details.
     */
    public CardBuilder() {
        question = new Question(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
        nextPracticeDate = DEFAULT_NEXT_PRACTICE_DATE;
    }

    /**
     * Initializes the CardBuilder with the data of {@code cardToCopy}.
     */
    public CardBuilder(Card cardToCopy) {
        question = cardToCopy.getQuestion();
        answer = cardToCopy.getAnswer();
        nextPracticeDate = cardToCopy.getNextPracticeDate();
    }

    /**
     * Sets the {@code Question} of the {@code Card} that we are building.
     */
    public CardBuilder withQuestion(String question) {
        this.question = new Question(question);
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code Card} that we are building.
     */
    public CardBuilder withAnswer(String answer) {
        this.answer = new Answer(answer);
        return this;
    }

    /**
     * Sets the next practice date of the {@code Card} that we are building
     */
    public CardBuilder withNextPracticeDate(LocalDateTime nextPracticeDate) {
        this.nextPracticeDate = nextPracticeDate;
        return this;
    }

    public Card build() {
        return new Card(question, answer, "new", nextPracticeDate);
    }

}

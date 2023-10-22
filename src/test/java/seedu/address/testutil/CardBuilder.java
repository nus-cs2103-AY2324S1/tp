package seedu.address.testutil;

import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;
import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class to help with building Card objects.
 */
public class CardBuilder {

    public static final String DEFAULT_QUESTION = "What is the opcode for R-format instructions?";
    public static final String DEFAULT_ANSWER = "0";
    public static final Set<Tag> TAGS = new HashSet<>();

    private Question question;
    private Answer answer;
    private Set<Tag> tags;

    /**
     * Creates a {@code CardBuilder} with the default details.
     */
    public CardBuilder() {
        question = new Question(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
        tags = TAGS;
    }

    /**
     * Initializes the CardBuilder with the data of {@code cardToCopy}.
     */
    public CardBuilder(Card cardToCopy) {
        question = cardToCopy.getQuestion();
        answer = cardToCopy.getAnswer();
        tags =cardToCopy.getTags();
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
     * Sets the {@code Tags} of the {@code Card} that we are building.
     */
    public CardBuilder withTags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Card build() {
        return new Card(question, answer, "new", tags);
    }

}

package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Difficulty;
import seedu.address.model.card.Hint;
import seedu.address.model.card.PracticeDate;
import seedu.address.model.card.Question;
import seedu.address.model.card.SolveCount;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Card objects.
 */
public class CardBuilder {

    public static final String DEFAULT_QUESTION = "What is the opcode for R-format instructions?";
    public static final String DEFAULT_ANSWER = "0";
    public static final PracticeDate DEFAULT_NEXT_PRACTICE_DATE =
            new PracticeDate(LocalDateTime.MIN); // highest priority
    public static final List<Tag> DEFAULT_TAGS = new ArrayList<>();
    public static final Hint DEFAULT_HINT = new Hint.EmptyHint();

    private Question question;
    private Answer answer;
    private PracticeDate nextPracticeDate;
    private List<Tag> tags;
    private Hint hint;

    /**
     * Creates a {@code CardBuilder} with the default details.
     */
    public CardBuilder() {
        question = new Question(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
        nextPracticeDate = DEFAULT_NEXT_PRACTICE_DATE;
        tags = DEFAULT_TAGS;
        hint = DEFAULT_HINT;
    }

    /**
     * Initializes the CardBuilder with the data of {@code cardToCopy}.
     */
    public CardBuilder(Card cardToCopy) {
        question = cardToCopy.getQuestion();
        answer = cardToCopy.getAnswer();
        nextPracticeDate = cardToCopy.getNextPracticeDate();
        tags = cardToCopy.getTags();
        hint = cardToCopy.getHint();
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
    public CardBuilder withNextPracticeDate(PracticeDate practiceDate) {
        this.nextPracticeDate = practiceDate;
        return this;
    }

    /**
     * Sets the {@code Tags} of the {@code Card} that we are building.
     */
    public CardBuilder withTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Empty constructor for no tags
     * @returns card builder with no tags
     */
    public CardBuilder withTags() {
        this.tags = new ArrayList<>();
        return this;
    }

    /**
     * Sets the {@code Hint} of the {@code Card} that we are building.
     */
    public CardBuilder withHint(String hint) {
        this.hint = new Hint(hint);
        return this;
    }

    /**
     * Sets the {@code Hint} of the {@code Card} that we are building.
     */
    public CardBuilder withHint(Hint hint) {
        this.hint = hint;
        return this;
    }

    /**
     * @return a Card with specific details
     */
    public Card build() {
        Difficulty difficulty = Difficulty.NEW;
        PracticeDate lastPracticeDate = null;
        SolveCount solveCount = new SolveCount();
        return new Card(question, answer, difficulty,
                tags, nextPracticeDate, lastPracticeDate,
                solveCount, hint);
    }

}

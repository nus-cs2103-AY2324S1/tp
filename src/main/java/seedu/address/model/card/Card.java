package seedu.address.model.card;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Card in lesSON.
 * Guarantees: details are present and not null, field values are validated
 */
public class Card implements Comparable<Card> {
    // Identity fields
    private final Question question;
    private final Answer answer;

    // Data fields for card
    private Difficulty difficulty;
    private PracticeDate lastPracticeDate; // last date card was practiced, can be null.
    private PracticeDate nextPracticeDate; // next date card should be practiced.
    private final List<Tag> tags = new ArrayList<>();
    private final SolveCount solveCount;
    private final Hint hint;

    /**
     * Every field must be present, and taking in if solveCount is provided
     */
    public Card(Question question, Answer answer, Difficulty difficulty,
                List<Tag> tags, PracticeDate nextPracticeDate, PracticeDate lastPracticeDate,
                SolveCount solveCount, Hint hint) {
        requireAllNonNull(question, answer, difficulty, tags, nextPracticeDate);
        assert(question != null);
        assert(answer != null);

        this.question = question;
        this.answer = answer;
        this.difficulty = difficulty;
        this.tags.addAll(tags);

        if (lastPracticeDate == null) {
            this.lastPracticeDate = nextPracticeDate;
        } else {
            this.lastPracticeDate = lastPracticeDate;
        }

        this.nextPracticeDate = nextPracticeDate;
        this.solveCount = solveCount;
        this.hint = hint;
    }

    // Difficulty
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return this.difficulty.toString();
    }

    // Question
    public Question getQuestion() {
        return question;
    }

    // Answer
    public Answer getAnswer() {
        return answer;
    }

    // PractiseDate
    public PracticeDate getNextPracticeDate() {
        return this.nextPracticeDate;
    }

    public PracticeDate getLastPracticeDate() {
        return this.lastPracticeDate;
    }

    /**
     * Sets a next practice date, replacing the previous practice dates with new
     * values.
     * @param practiceDate the next practice date.
     */
    private void setNextPracticeDate(PracticeDate practiceDate) {
        this.lastPracticeDate = this.nextPracticeDate;
        this.nextPracticeDate = practiceDate;
    }

    /**
     * Sets a new practice date based on {@code difficulty}.
     * @param difficulty the difficulty of the Card to adjust the new practise date
     */
    public void setNewPracticeDateWith(Difficulty difficulty) {
        PracticeDate newPracticeDate = PracticeDate.calculateNewPracticeDate(this.lastPracticeDate,
                this.nextPracticeDate, difficulty);

        setNextPracticeDate(newPracticeDate);
    }

    // Tags
    public List<Tag> getTags() {
        return Collections.unmodifiableList(tags);
    }

    // SolveCount
    public SolveCount getSolveCount() {
        return solveCount;
    }
    public void incrementSolveCount() {
        this.solveCount.incrementSolveCount();
    }

    // Hint
    public Hint getHint() {
        return this.hint;
    }

    /**
     * Returns true if both cards have the same question.
     * This defines a weaker notion of equality between two cards.
     */
    public boolean isSameCard(Card otherCard) {
        if (otherCard == this) {
            return true;
        }

        return otherCard != null && otherCard.getQuestion().equals(getQuestion());
    }

    /**
     * Returns true if both cards have the same identity and data fields.
     * This defines a stronger notion of equality between two cards.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Card)) {
            return false;
        }

        // compare Question, Answer and Tag equality
        Card otherCard = (Card) other;
        return question.equals(otherCard.question)
                && answer.equals(otherCard.answer)
                && tags.equals(otherCard.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("question", question)
                .add("answer", answer)
                .add("tags", tags)
                .toString();
    }

    @Override
    public int compareTo(Card other) {
        return this.nextPracticeDate.compareTo(other.nextPracticeDate);
    }
}

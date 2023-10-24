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
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Card implements Comparable<Card> {
    // Identity fields
    private final Question question;
    private final Answer answer;

    // Data fields
    private String difficulty;
    private PracticeDate lastPracticeDate; // last date card was practiced, can be null.
    private PracticeDate nextPracticeDate; // next date card should be practiced.
    private Integer priority;
    private List<Tag> tags = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Card(Question question, Answer answer, String difficulty, List<Tag> tags,
            PracticeDate nextPracticeDate, PracticeDate lastPracticeDate) {
        requireAllNonNull(question, answer, difficulty, tags, nextPracticeDate);
        this.question = question;
        this.answer = answer;
        assert(this.question != null);
        assert(this.answer != null);
        this.difficulty = difficulty;
        this.tags.addAll(tags);
        if (lastPracticeDate == null) {
            this.lastPracticeDate = nextPracticeDate;
        } else {
            this.lastPracticeDate = lastPracticeDate;
        }
        this.nextPracticeDate = nextPracticeDate;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }

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
    public void setNextPracticeDate(PracticeDate practiceDate) {
        this.lastPracticeDate = this.nextPracticeDate;
        this.nextPracticeDate = practiceDate;
    }

    /**
     * Sets a new practice date based on difficulty.
     * @param difficulty
     */
    public void setNewPracticeDateWith(String difficulty) {
        PracticeDate newPracticeDate = PracticeDate.calculateNewPracticeDate(
                this.lastPracticeDate, this.nextPracticeDate, difficulty);
        this.setNextPracticeDate(newPracticeDate);
    }

    public List<Tag> getTags() {
        return Collections.unmodifiableList(tags);
    }

    /**
     * Returns true if both cards have the same question.
     * This defines a weaker notion of equality between two cards.
     */
    public boolean isSameCard(Card otherCard) {
        if (otherCard == this) {
            return true;
        }

        return otherCard != null
                && otherCard.getQuestion().equals(getQuestion());
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

        Card otherCard = (Card) other;
        return question.equals(otherCard.question)
                && answer.equals(otherCard.answer)
                && tags.equals(otherCard.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
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

    public String questiontoString() {
        return "Question: " + this.getQuestion().toString();
    }

    public String answertoString() {
        return "Answer: " + this.getAnswer().toString();
    }

}

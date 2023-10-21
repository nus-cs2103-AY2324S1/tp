package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;

import java.time.LocalDateTime;

/**
 * Jackson-friendly version of {@link Card}.
 */
class JsonAdaptedCard {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "card's %s field is missing!";
    public static final String NEXT_PRACTICE_DATE_FIELD_NAME = "next practice date";

    private final String question;
    private final String answer;
    private final String difficulty;
    private final String nextPracticeDate;

    /**
     * Constructs a {@code JsonAdaptedCard} with the given card details.
     */
    @JsonCreator
    public JsonAdaptedCard(@JsonProperty("question") String question, @JsonProperty("answer") String answer,
                           @JsonProperty("difficulty") String difficulty, @JsonProperty("next-practice-date") String nextPracticeDate) {
        this.question = question;
        this.answer = answer;
        this.difficulty = difficulty;
        this.nextPracticeDate = nextPracticeDate;
    }

    /**
     * Converts a given {@code card} into this class for Jackson use.
     */
    public JsonAdaptedCard(Card source) {
        question = source.getQuestion().question;
        answer = source.getAnswer().answer;
        difficulty = source.getDifficulty();
        nextPracticeDate = source.getNextPracticeDate().toString();
    }

    /**
     * Converts this Jackson-friendly adapted card object into the model's {@code card} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted card.
     */
    public Card toModelType() throws IllegalValueException {

        if (question == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Question.class.getSimpleName()));
        }

        if (!Question.isValidQuestion(question)) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }

        final Question modelQuestion = new Question(question);

        if (answer == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName()));
        }

        if (!Answer.isValidAnswer(answer)) {
            throw new IllegalValueException(Answer.MESSAGE_CONSTRAINTS);
        }

        final Answer modelAnswer = new Answer(answer);

        if (nextPracticeDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, NEXT_PRACTICE_DATE_FIELD_NAME));
        }

        final LocalDateTime modelNextPracticeDate = LocalDateTime.parse(nextPracticeDate);

        return new Card(modelQuestion, modelAnswer, difficulty, modelNextPracticeDate);
    }
}

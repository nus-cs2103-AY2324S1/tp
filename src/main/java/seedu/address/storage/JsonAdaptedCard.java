package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.PracticeDate;
import seedu.address.model.card.Question;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Card}.
 */
class JsonAdaptedCard {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Card's %s field is missing!";
    public static final String LAST_PRACTICE_DATE_FIELD_NAME = "last practice date";
    public static final String NEXT_PRACTICE_DATE_FIELD_NAME = "next practice date";

    private final String question;
    private final String answer;
    private final String difficulty;
    private final String nextPracticeDate;
    private final String lastPracticeDate;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedCard} with the given card details.
     */
    @JsonCreator
    public JsonAdaptedCard(@JsonProperty("question") String question,
            @JsonProperty("answer") String answer,
            @JsonProperty("difficulty") String difficulty,
            @JsonProperty("tags") List<JsonAdaptedTag> tags,
            @JsonProperty("next-practice-date") String nextPracticeDate,
            @JsonProperty("last-practice-date") String lastPracticeDate) {
        this.question = question;
        this.answer = answer;
        this.difficulty = difficulty;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.nextPracticeDate = nextPracticeDate;
        this.lastPracticeDate = lastPracticeDate;
    }

    /**
     * Converts a given {@code Card} into this class for Jackson use.
     */
    public JsonAdaptedCard(Card source) {
        question = source.getQuestion().question;
        answer = source.getAnswer().answer;
        difficulty = source.getDifficulty();
        nextPracticeDate = source.getNextPracticeDate().practiceDate.toString();
        lastPracticeDate = source.getLastPracticeDate().practiceDate.toString();
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted Card object into the model's
     * {@code Card} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted card.
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

        final List<Tag> cardTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            cardTags.add(tag.toModelType());
        }

        if (nextPracticeDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, NEXT_PRACTICE_DATE_FIELD_NAME));
        }

        if (!PracticeDate.isValidNextPracticeDate(nextPracticeDate)) {
            throw new IllegalValueException(PracticeDate.MESSAGE_CONSTRAINTS);
        }

        final PracticeDate modelNextPracticeDate = new PracticeDate(LocalDateTime.parse(nextPracticeDate));

        // if there is a last practice date, and it is invalid, throw an error.
        // it's okay for last practice date to be null, but not okay for it to be
        // invalid.
        if (lastPracticeDate != null && !PracticeDate.isValidNextPracticeDate(lastPracticeDate)) {
            throw new IllegalValueException(PracticeDate.MESSAGE_CONSTRAINTS);
        }

        final PracticeDate modelLastPracticeDate = lastPracticeDate != null
                ? new PracticeDate(LocalDateTime.parse(lastPracticeDate))
                : null;

        return new Card(modelQuestion, modelAnswer, difficulty, modelNextPracticeDate, modelLastPracticeDate);
    }

}

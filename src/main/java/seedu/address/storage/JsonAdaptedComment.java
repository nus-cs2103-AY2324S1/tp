package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.fields.Comment;

/**
 * Jackson-friendly version of {@link Comment}.
 */
class JsonAdaptedComment {

    private final String commentName;

    /**
     * Constructs a {@code JsonAdaptedComment} with the given {@code commentName}.
     */
    @JsonCreator
    public JsonAdaptedComment(String commentName) {
        this.commentName = commentName;
    }

    /**
     * Converts a given {@code Comment} into this class for Jackson use.
     */
    public JsonAdaptedComment(Comment source) {
        commentName = source.commentName;
    }

    @JsonValue
    public String getCommentName() {
        return commentName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Comment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Comment toModelType() throws IllegalValueException {
        if (!Comment.isValidCommentName(commentName)) {
            throw new IllegalValueException(Comment.MESSAGE_CONSTRAINTS);
        }
        return new Comment(commentName);
    }

}

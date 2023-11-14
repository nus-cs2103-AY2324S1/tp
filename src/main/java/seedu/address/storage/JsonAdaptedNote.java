package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.messages.ConstraintMessage;
import seedu.address.model.note.Content;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Note}.
 */
public class JsonAdaptedNote {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Note's %s field is missing!";
    public static final String INVALID_INDEX_MESSAGE = "Invalid index given!";

    private final String content;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final Integer index;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("content") String content,
                           @JsonProperty("tags") List<JsonAdaptedTag> tags,
                           @JsonProperty("index") Integer index) {
        this.content = content;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.index = index;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        content = source.getContent().content;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        index = source.getIndex();
    }

    /**
     * Converts this Jackson-friendly adapted note object into the model's {@code Note} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted note.
     */
    public Note toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (content == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Content.class.getSimpleName()));
        }
        if (!Content.isValidContent(content)) {
            throw new IllegalValueException(ConstraintMessage.NOTE_CONTENT.toString());
        }
        if (index < 0) {
            throw new IllegalValueException(INVALID_INDEX_MESSAGE);
        }
        final Content modelContent = new Content(content);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Note(modelContent, modelTags, index);
    }
}

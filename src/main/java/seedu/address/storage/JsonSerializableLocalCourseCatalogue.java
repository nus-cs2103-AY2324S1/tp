package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.LocalCourseCatalogue;
import seedu.address.model.ReadOnlyLocalCourseCatalogue;
import seedu.address.model.localcourse.LocalCourse;

/**
 * An Immutable LocalCourseCatalogue that is serializable to JSON format.
 */
@JsonRootName(value = "localcoursecatalogue")
class JsonSerializableLocalCourseCatalogue {

    public static final String MESSAGE_DUPLICATE_LOCAL_COURSE = "LocalCourse catalogue contains duplicate local "
            + "course(s).";

    private final List<JsonAdaptedLocalCourse> localCourses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableLocalCourseCatalogue} with the given localCourses.
     */
    @JsonCreator
    public JsonSerializableLocalCourseCatalogue(@JsonProperty("localCourses") List<JsonAdaptedLocalCourse>
                                                        localCourseList) {
        this.localCourses.addAll(localCourseList);
    }

    /**
     * Converts a given {@code ReadOnlyLocalCourseCatalogue} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableLocalCourseCatalogue}.
     */
    public JsonSerializableLocalCourseCatalogue(ReadOnlyLocalCourseCatalogue source) {
        localCourses.addAll(source.getLocalCourseList().stream().map(
                JsonAdaptedLocalCourse::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code LocalCourseCatalogue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public LocalCourseCatalogue toModelType() throws IllegalValueException {
        LocalCourseCatalogue localCourseCatalogue = new LocalCourseCatalogue();
        for (JsonAdaptedLocalCourse jsonAdaptedLocalCourse : localCourses) {
            LocalCourse localCourse = jsonAdaptedLocalCourse.toModelType();
            if (localCourseCatalogue.hasLocalCourse(localCourse)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LOCAL_COURSE);
            }
            localCourseCatalogue.addLocalCourse(localCourse);
        }
        return localCourseCatalogue;
    }

}

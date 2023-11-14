package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.PartnerCourseCatalogue;
import seedu.address.model.ReadOnlyPartnerCourseCatalogue;
import seedu.address.model.partnercourse.PartnerCourse;

@JsonRootName(value = "partnercoursecatalogue")
class JsonSerializablePartnerCourseCatalogue {

    public static final String MESSAGE_DUPLICATE_PARTNER_COURSE = "PartnerCourse catalogue contains duplicate partner "
            + "course(s).";

    private final List<JsonAdaptedPartnerCourse> partnerCourses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePartnerCourseCatalogue} with the given partnerCourses.
     */
    @JsonCreator
    public JsonSerializablePartnerCourseCatalogue(@JsonProperty("partnerCourses") List<JsonAdaptedPartnerCourse>
                                                              partnerCourseList) {
        this.partnerCourses.addAll(partnerCourseList);
    }

    /**
     * Converts a given {@code ReadOnlyPartnerCourseCatalogue} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePartnerCourseCatalogue}.
     */
    public JsonSerializablePartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue source) {
        partnerCourses.addAll(source.getPartnerCourseList().stream().map(
                JsonAdaptedPartnerCourse::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code PartnerCourseCatalogue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PartnerCourseCatalogue toModelType() throws IllegalValueException {
        PartnerCourseCatalogue partnerCourseCatalogue = new PartnerCourseCatalogue();
        for (JsonAdaptedPartnerCourse jsonAdaptedPartnerCourse : partnerCourses) {
            PartnerCourse partnerCourse = jsonAdaptedPartnerCourse.toModelType();
            if (partnerCourseCatalogue.hasPartnerCourse(partnerCourse)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PARTNER_COURSE);
            }
            partnerCourseCatalogue.addPartnerCourse(partnerCourse);
        }
        return partnerCourseCatalogue;
    }
}

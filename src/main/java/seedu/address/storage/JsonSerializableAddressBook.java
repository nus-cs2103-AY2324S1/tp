package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_APPLICANT = "Applicants list contains duplicate applicant(s).";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "Interviews list contains duplicate interview(s).";
    public static final String MESSAGE_INVALID_INTERVIEW = "Interviews list contains interview(s) with invalid time.";
    public static final String MESSAGE_INTERVIEW_CLASH = "Interviews list contains clashing interviews";
    public static final String MESSAGE_APPLICANT_MISMATCH =
            "Interviews list contains interview(s) where the applicant does not exist in the applicants list.";

    private final List<JsonAdaptedInterview> interviews = new ArrayList<>();
    private final List<JsonAdaptedApplicant> applicants = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given applicants and interviews
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("applicants") List<JsonAdaptedApplicant> applicants,
                                       @JsonProperty("interviews") List<JsonAdaptedInterview> interviews) {
        this.applicants.addAll(applicants);
        this.interviews.addAll(interviews);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        applicants.addAll(source.getApplicantList().stream()
                .map(JsonAdaptedApplicant::new).collect(Collectors.toList()));
        interviews.addAll(source.getInterviewList().stream().map(JsonAdaptedInterview::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedApplicant jsonAdaptedApplicant : applicants) {
            Applicant applicant = jsonAdaptedApplicant.toModelType();
            if (addressBook.hasApplicant(applicant)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPLICANT);
            }
            addressBook.addApplicant(applicant);
        }
        for (JsonAdaptedInterview jsonAdaptedInterview : interviews) {
            Interview interview = jsonAdaptedInterview.toModelType();
            if (!interview.isValid()) {
                throw new IllegalValueException(MESSAGE_INVALID_INTERVIEW);
            }
            if (addressBook.hasInterview(interview)) {
                throw new IllegalValueException((MESSAGE_DUPLICATE_INTERVIEW));
            }
            if (addressBook.hasInterviewClash(interview)) {
                throw new IllegalValueException((MESSAGE_INTERVIEW_CLASH));
            }
            if (!addressBook.hasExactApplicant(interview.getInterviewApplicant())) {
                throw new IllegalValueException(MESSAGE_APPLICANT_MISMATCH);
            }
            addressBook.addInterview(interview);
        }
        return addressBook;
    }

}

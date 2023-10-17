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
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Member;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {
    public static final String MESSAGE_DUPLICATE_MEMBER = "Members list contains duplicate member(s).";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "Applicants list contains duplicate applicant(s).";

    private final List<JsonAdaptedMember> members = new ArrayList<>();
    private final List<JsonAdaptedApplicant> applicants = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("members") List<JsonAdaptedMember> members,
                                       @JsonProperty("applicants") List<JsonAdaptedApplicant> applicants) {
        this.members.addAll(members);
        this.applicants.addAll(applicants);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        members.addAll(source.getMemberList().stream().map(JsonAdaptedMember::new).collect(Collectors.toList()));
        applicants.addAll(source.getApplicantList().stream().map(JsonAdaptedApplicant::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {

        AddressBook addressBook = new AddressBook();

        for (JsonAdaptedMember jsonAdaptedMember : members) {
            Member member = jsonAdaptedMember.toModelType();
            if (addressBook.hasMember(member)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MEMBER);
            }
            addressBook.addMember(member);
        }

        for (JsonAdaptedApplicant jsonAdaptedApplicant : applicants) {
            Applicant applicant = jsonAdaptedApplicant.toModelType();
            if (addressBook.hasApplicant(applicant)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPLICANT);
            }
            addressBook.addApplicant(applicant);
        }

        return addressBook;
    }

}

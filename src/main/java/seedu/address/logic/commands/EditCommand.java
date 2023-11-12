package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Condition;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_CONDITION + "CONDITION] "
            + "[" + PREFIX_BLOODTYPE + "BLOOD TYPE] "
            + "[" + PREFIX_EMERGENCY_CONTACT + "EMAIL] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " T0123456H "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com"
            + PREFIX_REMARK + "remarks";
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_DOESNT_EXIST = "This person hasn't been saved";
    public static final String MESSAGE_IC_CHANGED = "You can't change a person's IC";
    private static final Logger logger = LogsCenter.getLogger(EditCommand.class.getName());
    private final Ic nric;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param nric                 of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Ic nric, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(nric);
        requireNonNull(editPersonDescriptor);

        this.nric = nric;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (editPersonDescriptor.getIc().isPresent() && !(editPersonDescriptor.getIc().get().equals(nric))) {
            throw new CommandException(MESSAGE_IC_CHANGED);
        }

        // combine doctor list and patient list
        Person personToEdit = getPersonToEdit(model);
        Person editedPerson = getEditedPerson(model, personToEdit);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        logger.info("Successfully edited person");
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    private Person getPersonToEdit(Model model) throws CommandException {
        List<Person> lastShownList = new ArrayList<>();
        lastShownList.addAll(model.getFilteredDoctorList());
        lastShownList.addAll(model.getFilteredPatientList());
        List<Person> personToEditList = lastShownList.stream()
                .filter(x -> x.getIc().equals(nric))
                .collect(Collectors.toList());
        if (personToEditList.size() == 0) {
            logger.warning("Could not edit - person isn't in adressbook");
            throw new CommandException(MESSAGE_DOESNT_EXIST);
        }
        //developer assumption - can't have 2 people with same IC
        assert personToEditList.size() < 2;
        Person personToEdit = personToEditList.get(0);
        return personToEdit;
    }

    private Person getEditedPerson(Model model, Person personToEdit) throws CommandException {
        Person editedPerson;
        if (personToEdit instanceof Patient) {
            Patient patientToEdit = (Patient) personToEdit;
            validatePatient();
            editedPerson = createEditedPatient(patientToEdit, editPersonDescriptor);
        } else {
            assert personToEdit.isDoctor();
            Doctor doctorToEdit = (Doctor) personToEdit;
            validateDoctor();
            editedPerson = createEditedDoctor(doctorToEdit, editPersonDescriptor);
        }
        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            logger.warning("Edited Person and orignal person are the same");
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        return editedPerson;
    }
    private void validatePatient() throws CommandException {
        if (editPersonDescriptor.getTags().isPresent()
                && !editPersonDescriptor.isValidPatientTagList(editPersonDescriptor.getTags().get())) {
            logger.warning("Invalid tag for patient");
            throw new CommandException("Please enter a valid patient tag.");
        }
    }
    private void validateDoctor() throws CommandException {
        if (editPersonDescriptor.getCondition().isPresent() || editPersonDescriptor.getBloodType().isPresent()
                || editPersonDescriptor.getEmergencyContact().isPresent()) {
            logger.warning("Error thrown - tried to edit condition/blood type/emergency contact of doctor");
            throw new CommandException("Doctors cannot have Condition, BloodType or Emergency Contact fields.");
        }
        if (editPersonDescriptor.getTags().isPresent()
                && !editPersonDescriptor.isValidDoctorTagList(editPersonDescriptor.getTags().get())) {
            logger.warning(editPersonDescriptor.getTags().toString());
            throw new CommandException("Please enter valid Doctor tags.");
        }
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Doctor createEditedDoctor(Doctor personToEdit, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(personToEdit);
        requireNonNull(personToEdit);

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Remark updatedRemarks = editPersonDescriptor.getRemark().orElse(personToEdit.getRemark());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(personToEdit.getGender());
        Ic updatedIc = personToEdit.getIc(); // since you can't modify ic
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<Appointment> updatedAppointments =
                editPersonDescriptor.getAppointments().orElse(personToEdit.getAppointments());

        logger.fine("Successfully created Edited Doctor");
        return new Doctor(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedRemarks,
                updatedGender, updatedIc, updatedAppointments, updatedTags);
    }


    private static Patient createEditedPatient(Patient personToEdit, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(personToEdit);
        requireNonNull(personToEdit);

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Phone updatedEmergencyContact =
                editPersonDescriptor.getEmergencyContact().orElse(personToEdit.getEmergencyContact());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Remark updatedRemarks = editPersonDescriptor.getRemark().orElse(personToEdit.getRemark());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(personToEdit.getGender());
        Ic updatedIc = personToEdit.getIc(); // since you can't modify ic
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<Appointment> updatedAppointments =
                editPersonDescriptor.getAppointments().orElse(personToEdit.getAppointments());
        BloodType updatedBloodType = editPersonDescriptor.getBloodType().orElse(personToEdit.getBloodType());
        Condition updatedCondition = editPersonDescriptor.getCondition().orElse(personToEdit.getCondition());
        logger.fine("Successfully created Edited Patient");
        return new Patient(updatedName, updatedPhone, updatedEmergencyContact, updatedEmail, updatedAddress,
                updatedRemarks, updatedGender, updatedIc, updatedCondition, updatedBloodType, updatedAppointments,
                updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return nric.equals(otherEditCommand.nric)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("nric", nric)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }


    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Phone emergencyContact;
        private Email email;
        private Address address;
        private Remark remark;
        private Gender gender;
        private Ic ic;
        private Set<Tag> tags;
        private Condition condition;
        private BloodType bloodType;
        private Set<Appointment> appointments;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmergencyContact(toCopy.emergencyContact);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setRemark(toCopy.remark);
            setGender(toCopy.gender);
            setIc(toCopy.ic);
            setTags(toCopy.tags);
            setBloodType(toCopy.bloodType);
            setCondition(toCopy.condition);
            setAppointments(toCopy.appointments);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, emergencyContact, address,
                    gender, ic, tags, bloodType, condition, remark, appointments);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmergencyContact(Phone phone) {
            this.emergencyContact = phone;
        }

        public Optional<Phone> getEmergencyContact() {
            return Optional.ofNullable(emergencyContact);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }

        public void setIc(Ic ic) {
            this.ic = ic;
        }

        public Optional<Ic> getIc() {
            return Optional.ofNullable(ic);
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }

        public Optional<Condition> getCondition() {
            return Optional.ofNullable(condition);
        }

        public void setBloodType(BloodType bloodType) {
            this.bloodType = bloodType;
        }

        public Optional<BloodType> getBloodType() {
            return Optional.ofNullable(bloodType);
        }

        /**
         * Ensures the set of tags contains only valid patient tags.
         *
         * @param tagSet The set of tags to be validated.
         * @return true if the tag set is valid (contains zero or one valid patient tag), false otherwise.
         */
        public boolean isValidPatientTagList(Set<Tag> tagSet) {
            if (tagSet.size() > 1) {
                return false;
            }
            for (Tag tag : tagSet) {
                if (!tag.isValidPatientTag()) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Ensures the set of tags contains only valid doctor tags.
         *
         * @param tagSet The set of tags to be validated.
         * @return true if the tag set is valid (contains no duplicate doctor tags), false otherwise.
         */
        public boolean isValidDoctorTagList(Set<Tag> tagSet) {
            for (Tag tag : tagSet) {
                if (!tag.isValidDoctorTag()) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setAppointments(Set<Appointment> appointments) {
            this.appointments = (appointments != null) ? new HashSet<>(appointments) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Appointment>> getAppointments() {
            return (appointments != null) ? Optional.of(Collections.unmodifiableSet(appointments)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(address, otherEditPersonDescriptor.address)
                    && Objects.equals(gender, otherEditPersonDescriptor.gender)
                    && Objects.equals(ic, otherEditPersonDescriptor.ic)
                    && Objects.equals(tags, otherEditPersonDescriptor.tags)
                    && Objects.equals(condition, otherEditPersonDescriptor.condition)
                    && Objects.equals(bloodType, otherEditPersonDescriptor.bloodType)
                    && Objects.equals(remark, otherEditPersonDescriptor.remark)
                    && Objects.equals(appointments, otherEditPersonDescriptor.appointments);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("gender", gender)
                    .add("nric", ic)
                    .add("tags", tags)
                    .add("condition", condition)
                    .add("blood type", bloodType)
                    .add("appointments", appointments)
                    .toString();
        }
    }
}

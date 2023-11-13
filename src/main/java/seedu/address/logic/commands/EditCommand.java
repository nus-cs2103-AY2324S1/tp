package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICALHISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Location;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Specialist;
import seedu.address.model.person.Specialty;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    private static final String MESSAGE_USAGE_GENERAL = "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TAG + "TAG]... ";

    private static final String PERSON_EXAMPLE =
            PREFIX_NAME + "John Doe "
                    + PREFIX_PHONE + "98765432 "
                    + PREFIX_EMAIL + "johnd@example.com "
                    + PREFIX_TAG + "friends "
                    + PREFIX_TAG + "owesMoney ";

    public static final String MESSAGE_USAGE_PATIENT = COMMAND_WORD + " "
            + ": Edit a patient in the address book.\n"
            + MESSAGE_USAGE_GENERAL
            + "[" + PREFIX_AGE + "AGE] "
            + "[" + PREFIX_MEDICALHISTORY + "MEDICAL HISTORY]... \n"
            + "Example: " + COMMAND_WORD + " "
            + PERSON_EXAMPLE
            + PREFIX_AGE + "30 "
            + PREFIX_MEDICALHISTORY + "Osteoporosis";

    public static final String MESSAGE_USAGE_SPECIALIST = COMMAND_WORD + " "
            + ": Edit a specialist in the address book. \n"
            + MESSAGE_USAGE_GENERAL
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_SPECIALTY + "SPECIALTY] \n"
            + "Example: " + COMMAND_WORD + " "
            + PERSON_EXAMPLE
            + PREFIX_LOCATION + "311, Clementi Ave 2, #02-25 "
            + PREFIX_SPECIALTY + "Physiotherapist ";

    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);
        if (editPersonDescriptor instanceof EditPatientDescriptor) {
            this.editPersonDescriptor = new EditPatientDescriptor((EditPatientDescriptor) editPersonDescriptor);
        } else {
            this.editPersonDescriptor = new EditSpecialistDescriptor((EditSpecialistDescriptor) editPersonDescriptor);
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToEdit = model.getSelectedPerson();
        Person editedPerson;

        if (personToEdit instanceof Patient) {
            if (!(editPersonDescriptor instanceof EditPatientDescriptor)) {
                throw new CommandException(Messages.MESSAGE_PERSON_TYPE_MISMATCH_INDEX);
            }
            editedPerson = createEditedPatient((Patient) personToEdit,
                    (EditPatientDescriptor) editPersonDescriptor);
        } else {
            if (!(editPersonDescriptor instanceof EditSpecialistDescriptor)) {
                throw new CommandException(Messages.MESSAGE_PERSON_TYPE_MISMATCH_INDEX);
            }
            editedPerson = createEditedSpecialist((Specialist) personToEdit,
                    (EditSpecialistDescriptor) editPersonDescriptor);
        }

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateSelectedPerson(editedPerson);
        model.commit();

        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code patientToEdit}
     * edited with {@code editPatientDescriptor}.
     */
    private static Patient createEditedPatient(Patient patientToEdit, EditPatientDescriptor editPatientDescriptor) {
        assert patientToEdit != null;

        Name updatedName = editPatientDescriptor.getName().orElse(patientToEdit.getName());
        Phone updatedPhone = editPatientDescriptor.getPhone().orElse(patientToEdit.getPhone());
        Email updatedEmail = editPatientDescriptor.getEmail().orElse(patientToEdit.getEmail());
        Set<Tag> updatedTags = editPatientDescriptor.getTags().orElse(patientToEdit.getTags());
        Age updatedAge = editPatientDescriptor.getAge().orElse(patientToEdit.getAge());
        Set<MedicalHistory> updatedMedicalHistory = editPatientDescriptor.getMedicalHistory()
                .orElse(patientToEdit.getMedicalHistory());

        return new Patient(updatedName, updatedPhone, updatedEmail, updatedTags, updatedAge,
                updatedMedicalHistory);
    }

    /**
     * Creates and returns a {@code Specialist} with the details of {@code specialistToEdit}
     * edited with {@code editSpecialistDescriptor}.
     */
    private static Specialist createEditedSpecialist(Specialist specialistToEdit,
                                                     EditSpecialistDescriptor editSpecialistDescriptor) {
        assert specialistToEdit != null;

        Name updatedName = editSpecialistDescriptor.getName().orElse(specialistToEdit.getName());
        Phone updatedPhone = editSpecialistDescriptor.getPhone().orElse(specialistToEdit.getPhone());
        Email updatedEmail = editSpecialistDescriptor.getEmail().orElse(specialistToEdit.getEmail());
        Location updatedLocation = editSpecialistDescriptor.getLocation().orElse(specialistToEdit.getLocation());
        Set<Tag> updatedTags = editSpecialistDescriptor.getTags().orElse(specialistToEdit.getTags());
        Specialty updatedSpecialty = editSpecialistDescriptor.getSpecialty().orElse(specialistToEdit.getSpecialty());


        return new Specialist(updatedName, updatedPhone, updatedEmail, updatedLocation, updatedTags, updatedSpecialty);
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
        return this.editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public abstract static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, tags);
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

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
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
                    && Objects.equals(tags, otherEditPersonDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("tags", tags)
                    .toString();
        }
    }

    /**
     * Stores the details to edit the patient with. Each non-empty field value will replace the
     * corresponding field value of the patient.
     */
    public static class EditPatientDescriptor extends EditPersonDescriptor {
        private Age age;
        private Set<MedicalHistory> medicalHistory;

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPatientDescriptor(EditPatientDescriptor toCopy) {
            super(toCopy);
            setMedicalHistory(toCopy.medicalHistory);
            setAge(toCopy.age);
        }

        public EditPatientDescriptor() {}
        public void setMedicalHistory(Set<MedicalHistory> medicalHistory) {
            this.medicalHistory = (medicalHistory != null) ? new HashSet<>(medicalHistory) : null;
        }

        public void setAge(Age age) {
            this.age = age;
        }

        public Optional<Set<MedicalHistory>> getMedicalHistory() {
            return (medicalHistory != null)
                    ? Optional.of(Collections.unmodifiableSet(medicalHistory))
                    : Optional.empty();

        }

        public Optional<Age> getAge() {
            return Optional.ofNullable(age);
        }

        @Override
        public boolean equals(Object other) {
            if (super.equals(other) && other instanceof EditPatientDescriptor) {
                EditPatientDescriptor otherEditPatientDescriptor = (EditPatientDescriptor) other;
                return Objects.equals(age, otherEditPatientDescriptor.age)
                        && Objects.equals(medicalHistory, otherEditPatientDescriptor.medicalHistory);
            }
            return false;
        }
        @Override
        public String toString() {
            String stringToAdd = ", age=" + age + ", medical history=" + medicalHistory;
            return StringUtil.addFieldToPersonToString(stringToAdd, super.toString());
        }
        /**
         * Returns true if at least one field is edited.
         */
        @Override
        public boolean isAnyFieldEdited() {
            return super.isAnyFieldEdited() || CollectionUtil.isAnyNonNull(age, medicalHistory);
        }

    }

    /**
     * Stores the details to edit the specialist with. Each non-empty field value will replace the
     * corresponding field value of the specialist.
     */
    public static class EditSpecialistDescriptor extends EditPersonDescriptor {
        private Specialty specialty;
        private Location location;
        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditSpecialistDescriptor(EditSpecialistDescriptor toCopy) {
            super(toCopy);
            setLocation(toCopy.location);
            setSpecialty(toCopy.specialty);
        }
        public EditSpecialistDescriptor() {}

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }
        public void setSpecialty(Specialty specialty) {
            this.specialty = specialty;
        }

        public Optional<Specialty> getSpecialty() {
            return Optional.ofNullable(specialty);
        }


        @Override
        public boolean equals(Object other) {
            if (super.equals(other) && other instanceof EditSpecialistDescriptor) {
                EditSpecialistDescriptor otherEditSpecialistDescriptor = (EditSpecialistDescriptor) other;
                return Objects.equals(specialty, otherEditSpecialistDescriptor.specialty)
                        && Objects.equals(location, otherEditSpecialistDescriptor.location);
            }
            return false;
        }

        @Override
        public String toString() {
            String stringToAdd = ", location=" + location + ", specialty=" + specialty;
            return StringUtil.addFieldToPersonToString(stringToAdd, super.toString());
        }

        /**
         * Returns true if at least one field is edited.
         */
        @Override
        public boolean isAnyFieldEdited() {
            return super.isAnyFieldEdited() || CollectionUtil.isAnyNonNull(location, specialty);
        }
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TREATMENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.patients.Patient;
import seedu.address.model.tag.Tag;
import seedu.address.model.treatment.TreatmentName;

/**
 * Edits the details of an existing patient in the address book.
 */
public class EditPatientCommand extends Command {

    public static final String COMMAND_WORD = "edit-patient";

    public static final String MESSAGE_USAGE =
        COMMAND_WORD + ": Edits the details of the patient identified "
            + "by the index number used in the displayed patient list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: PATIENT_ID (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_BIRTHDATE + "BIRTHDATE] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_TREATMENT + "TREATMENT] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_REMARK + "Allergic to Peanuts";

    public static final String MESSAGE_EDIT_PATIENT_SUCCESS = "Edited Patient: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the address book.";

    public static final String MESSAGE_INVALID_TREATMENT = "This treatment is invalid";


    private final long patientId;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * @param patientId             of the patient in the filtered patient list to edit
     * @param editPatientDescriptor details to edit the patient with
     */
    public EditPatientCommand(long patientId, EditPatientDescriptor editPatientDescriptor) {
        requireNonNull(patientId);
        requireNonNull(editPatientDescriptor);

        this.patientId = patientId;
        this.editPatientDescriptor = new EditPatientDescriptor(editPatientDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            Patient patientToEdit = model.getPatientById(patientId);

            if (patientToEdit == null) {
                throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
            }
            Patient editedPatient = createEditedPatient(patientToEdit, editPatientDescriptor);

            if (!patientToEdit.isSamePatient(editedPatient) && model.hasPatient(editedPatient)) {
                throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
            }

            String treatmentName = editedPatient.getTreatmentName().toString();
            if (!model.hasTreatmentName(editedPatient.getTreatmentName()) && !treatmentName.equals(
                "NIL")) {
                throw new CommandException(MESSAGE_INVALID_TREATMENT);
            }
            model.setPatient(patientToEdit, editedPatient);
            model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
            return new CommandResult(
                String.format(MESSAGE_EDIT_PATIENT_SUCCESS, Messages.format(editedPatient)));

        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }

    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code patientToEdit} edited with
     * {@code editPatientDescriptor}.
     */
    private static Patient createEditedPatient(Patient patientToEdit,
        EditPatientDescriptor editPatientDescriptor) {
        assert patientToEdit != null;

        Name updatedName = editPatientDescriptor.getName().orElse(patientToEdit.getName());
        Phone updatedPhone = editPatientDescriptor.getPhone().orElse(patientToEdit.getPhone());
        Email updatedEmail = editPatientDescriptor.getEmail().orElse(patientToEdit.getEmail());
        Address updatedAddress = editPatientDescriptor.getAddress()
            .orElse(patientToEdit.getAddress());
        Gender updatedGender = editPatientDescriptor.getGender().orElse(patientToEdit.getGender());
        Birthdate updatedBirthdate = editPatientDescriptor.getBirthdate()
            .orElse(patientToEdit.getBirthdate());
        Remark updatedRemark = editPatientDescriptor.getRemark().orElse(patientToEdit.getRemark());
        TreatmentName updatedTreatmentName = editPatientDescriptor.getTreatmentName()
            .orElse(patientToEdit.getTreatmentName());
        long patientIdRemains = patientToEdit.getId(); //ID Must not change!
        Set<Tag> updatedTags = editPatientDescriptor.getTags().orElse(patientToEdit.getTags());

        return new Patient(updatedName, updatedPhone, updatedBirthdate, updatedGender,
            updatedRemark, updatedTreatmentName, updatedAddress, updatedEmail, patientIdRemains,
            updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPatientCommand)) {
            return false;
        }

        EditPatientCommand otherEditCommand = (EditPatientCommand) other;
        return patientId == (otherEditCommand.patientId)
            && editPatientDescriptor.equals(otherEditCommand.editPatientDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("patientId", patientId)
            .add("editPatientDescriptor", editPatientDescriptor)
            .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPatientDescriptor {

        private Name name;
        private Phone phone;
        private Email email;
        private Address address;

        private Gender gender;
        private Birthdate birthdate;
        private Remark remark;
        private TreatmentName treatmentName;
        private long patientId;
        private Set<Tag> tags;

        public EditPatientDescriptor() {
        }

        /**
         * Copy constructor. A defensive copy of {@code tags} is used internally.
         */
        public EditPatientDescriptor(EditPatientDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setGender(toCopy.gender);
            setBirthdate(toCopy.birthdate);
            setRemark(toCopy.remark);
            setTreatmentName(toCopy.treatmentName);
            setPatientId(toCopy.patientId);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, gender, birthdate,
                remark, treatmentName);
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

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }

        public void setBirthdate(Birthdate birthdate) {
            this.birthdate = birthdate;
        }

        public Optional<Birthdate> getBirthdate() {
            return Optional.ofNullable(birthdate);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        public void setTreatmentName(TreatmentName treatmentName) {
            this.treatmentName = treatmentName;
        }

        public Optional<TreatmentName> getTreatmentName() {
            return Optional.ofNullable(treatmentName);
        }


        public void setPatientId(long id) {
            this.patientId = id;
        }

        public Optional<Long> getPatientId() {
            return Optional.ofNullable(patientId);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}. A defensive copy of {@code tags} is used
         * internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException} if
         * modification is attempted. Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags))
                : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPatientDescriptor)) {
                return false;
            }

            EditPatientDescriptor otherEditPatientDescriptor = (EditPatientDescriptor) other;
            return Objects.equals(name, otherEditPatientDescriptor.getName().get())
                && Objects.equals(phone, otherEditPatientDescriptor.getPhone().get())
                && Objects.equals(email, otherEditPatientDescriptor.getEmail().get())
                && Objects.equals(address, otherEditPatientDescriptor.getAddress().get())
                && Objects.equals(gender, otherEditPatientDescriptor.getGender().get())
                && Objects.equals(birthdate, otherEditPatientDescriptor.getBirthdate().get())
                && Objects.equals(remark, otherEditPatientDescriptor.getRemark().get())
                && Objects.equals(treatmentName, otherEditPatientDescriptor.getTreatmentName().get())
                && Objects.equals(patientId, otherEditPatientDescriptor.getPatientId().get())
                && Objects.equals(tags, otherEditPatientDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("gender", gender)
                .add("birthdate", birthdate)
                .add("remark", remark)
                .add("treatmentname", treatmentName)
                .add("tags", tags)
                .toString();
        }
    }
}

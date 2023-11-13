package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AFFILIATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.affiliation.AffiliationModifier;
import seedu.address.model.affiliation.AuthenticateAffiliation;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.ShiftDays;
import seedu.address.model.person.Specialisation;
import seedu.address.model.person.Staff;

/**
 * Edits the details of an existing person in the contact list.
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
            + "[" + PREFIX_AFFILIATION + "AFFILIATION]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON =
            "This person already exists in the contact list. Please use a different name.";
    public static final String MESSAGE_EDIT_ROLE_NOT_ALLOW = "Editing of role is not allowed.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Role updatedRole = editPersonDescriptor.getRole().orElse(personToEdit.getRole());
        Set<Affiliation> updatedAffiliations = editPersonDescriptor
                .getAffiliations().orElse(personToEdit.getAffiliations());
        Set<Affiliation> mergedAffiliationHistory = new HashSet<>(personToEdit.getAffiliationHistory());
        mergedAffiliationHistory.addAll(updatedAffiliations);
        return updatedRole.generatePerson(updatedName, updatedPhone, updatedEmail,
                updatedAffiliations, mergedAffiliationHistory);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (this.editPersonDescriptor.isAffiliationEdited()) {
            AuthenticateAffiliation.check(editedPerson.getAffiliations(), personToEdit, editedPerson, model);
        }

        if (this.editPersonDescriptor.isNameEdited()) {
            AffiliationModifier.nameChangeAffiliations(personToEdit.getAffiliations(), personToEdit.getName(),
                    editedPerson.getName(), model);
            AffiliationModifier.nameChangeAffiliationHistory(personToEdit.getAffiliationHistory(),
                    personToEdit.getName(), editedPerson.getName(), model);
        }

        if (this.editPersonDescriptor.isAffiliationEdited()) {
            AffiliationModifier.addAffiliationHistory(editedPerson.getAffiliations(), editedPerson, model);
            AffiliationModifier.removeAffiliations(personToEdit.getAffiliations(), editedPerson, model);
            AffiliationModifier.addAffiliations(editedPerson.getAffiliations(), editedPerson, model);
        }

        if (personToEdit instanceof Staff && editedPerson instanceof Staff) {
            ShiftDays shiftDays = ((Staff) personToEdit).getShiftDays();
            Staff editedStaff = (Staff) editedPerson;
            editedStaff.setShiftDays(shiftDays);
        }

        if (personToEdit instanceof Doctor && editedPerson instanceof Doctor) {
            Set<Specialisation> specialisations = ((Doctor) personToEdit).getSpecialisations();
            Doctor editedDoctor = (Doctor) editedPerson;
            editedDoctor.setSpecialisations(specialisations);
        }

        if (personToEdit instanceof Patient && editedPerson instanceof Patient) {
            NextOfKin nextOfKin = ((Patient) personToEdit).getNextOfKin();
            Patient editedPatient = (Patient) editedPerson;
            editedPatient.setNextOfKin(nextOfKin);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
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
        return index.equals(otherEditCommand.index)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
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
        private Email email;
        private Role role;
        private Set<Affiliation> affiliations;
        private Set<Affiliation> affiliationHistory;
        private ShiftDays shiftDays;
        private Set<Specialisation> specialisations;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code affiliations} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setRole(toCopy.role);
            setAffiliations(toCopy.affiliations);
            setAffiliationHistory(toCopy.affiliationHistory, toCopy.affiliations);
            setShiftDays(toCopy.shiftDays);
            setSpecialisations(toCopy.specialisations);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, role, affiliations, affiliationHistory, shiftDays);
        }

        /**
         * Returns true if name is edited.
         */
        public boolean isNameEdited() {
            return !isNull(name);
        }

        /**
         * Returns true if role is edited.
         */
        public boolean isRoleEdited() {
            return !isNull(role);
        }

        /**
         * Returns true if affiliations is edited.
         */
        public boolean isAffiliationEdited() {
            return !isNull(affiliations);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Role> getRole() {
            return Optional.ofNullable(role);
        }

        public void setRole(Role role) {
            this.role = role;
        }
        public Optional<ShiftDays> getShiftDays() {
            return Optional.ofNullable(shiftDays);
        }

        public void setShiftDays(ShiftDays shiftDays) {
            this.shiftDays = shiftDays;
        }

        public Optional<Set<Specialisation>> getSpecialisation() {
            return Optional.ofNullable(specialisations);
        }

        public void setSpecialisations(Set<Specialisation> specialisations) {
            this.specialisations = specialisations;
        }

        /**
         * Returns an unmodifiable affiliation set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code affiliations} is null.
         */
        public Optional<Set<Affiliation>> getAffiliations() {
            return (affiliations != null) ? Optional.of(Collections.unmodifiableSet(affiliations)) : Optional.empty();
        }
        /**
         * Returns an unmodifiable affiliation set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code affiliations} is null.
         */
        public Optional<Set<Affiliation>> getAffiliationHistory() {
            return (affiliationHistory != null) ? Optional.of(Collections.unmodifiableSet(affiliationHistory))
                    : Optional.empty();
        }
        /**
         * Sets {@code affiliations} to this object's {@code affiliations}.
         * A defensive copy of {@code affiliations} is used internally.
         */
        public void setAffiliations(Set<Affiliation> affiliations) {
            this.affiliations = (affiliations != null) ? new HashSet<>(affiliations) : null;
        }

        /**
         * Sets {@code affiliationHistory} and {@code affiliations} to
         * this object's {@code affiliationHistory}.
         * A defensive copy of {@code affiliationHistory} is used internally.
         */
        public void setAffiliationHistory(Set<Affiliation> affiliationHistory, Set<Affiliation> affiliations) {
            if (affiliationHistory != null) {
                this.affiliationHistory = new HashSet<>(affiliationHistory);
            } else {
                this.affiliationHistory = null;
            }
            if (affiliations != null) {
                addAffiliationsToHistory(affiliations);
            }
        }

        /**
         * Sets {@code affiliationHistory} to this object's {@code affiliationHistory}.
         * A defensive copy of {@code affiliationHistory} is used internally.
         */
        public void setAffiliationHistory(Set<Affiliation> affiliationHistory) {
            this.affiliationHistory = (affiliationHistory != null)
                    ? new HashSet<>(affiliationHistory) : null;
        }
        /**
         * Adds {@code affiliations} to this object's {@code affiliations}.
         * @param affiliations the affiliations to add to affiliation history.
         */
        public void addAffiliationsToHistory(Set<Affiliation> affiliations) {
            if (this.affiliationHistory == null) {
                this.affiliationHistory = new HashSet<>();
            }
            this.affiliationHistory.addAll(affiliations);
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
                    && Objects.equals(role, otherEditPersonDescriptor.role)
                    && Objects.equals(affiliations, otherEditPersonDescriptor.affiliations)
                    && Objects.equals(affiliationHistory, otherEditPersonDescriptor.affiliationHistory)
                    && Objects.equals(shiftDays, otherEditPersonDescriptor.shiftDays)
                    && Objects.equals(specialisations, otherEditPersonDescriptor.specialisations);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("role", role)
                    .add("affiliations", affiliations)
                    .add("affiliationHistory", affiliationHistory)
                    .add("shiftDays", shiftDays)
                    .add("specialisations", specialisations)
                    .toString();
        }
    }
}

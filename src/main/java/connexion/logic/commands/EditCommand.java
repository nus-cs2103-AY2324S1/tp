package connexion.logic.commands;

import static connexion.logic.parser.CliSyntax.PREFIX_COMPANY;
import static connexion.logic.parser.CliSyntax.PREFIX_EMAIL;
import static connexion.logic.parser.CliSyntax.PREFIX_JOB;
import static connexion.logic.parser.CliSyntax.PREFIX_NAME;
import static connexion.logic.parser.CliSyntax.PREFIX_PHONE;
import static connexion.logic.parser.CliSyntax.PREFIX_TAG;
import static connexion.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import connexion.commons.core.index.Index;
import connexion.commons.util.CollectionUtil;
import connexion.commons.util.ToStringBuilder;
import connexion.logic.Messages;
import connexion.logic.commands.exceptions.CommandException;
import connexion.model.Model;
import connexion.model.person.Company;
import connexion.model.person.Email;
import connexion.model.person.Job;
import connexion.model.person.LastModifiedDateTime;
import connexion.model.person.Mark;
import connexion.model.person.Name;
import connexion.model.person.Note;
import connexion.model.person.Person;
import connexion.model.person.Phone;
import connexion.model.person.Schedule;
import connexion.model.person.ScheduleName;
import connexion.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer and less than 2^31 - 1) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_JOB + "JOB] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

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

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.setDetailedPerson(editedPerson);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
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
        Company updatedCompany = editPersonDescriptor.getCompany().orElse(personToEdit.getCompany());
        Job updatedJob = editPersonDescriptor.getJob().orElse(personToEdit.getJob());
        Mark updatedMark = editPersonDescriptor.getMarkStatus().orElse(personToEdit.getMarkStatus());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        LastModifiedDateTime updatedLastModifiedDateTime =
                editPersonDescriptor.getLastModifiedDateTime()
                        .orElse(personToEdit.getLastModifiedDateTime());
        Note updatedNote = editPersonDescriptor.getNote().orElse(personToEdit.getNote());
        // While semantically, it would make sense that this would always be changed,
        // We do it like this for consistency with other fields
        // And to move responsibility for updating this field to the parser,
        // Like the other fields.
        Optional<Schedule> schedule = personToEdit.getSchedule();
        Optional<ScheduleName> scheduleName = personToEdit.getScheduleName();
        return new Person(updatedName, updatedPhone,
                updatedEmail, updatedCompany, updatedJob, updatedMark,
                updatedTags, schedule, scheduleName, updatedLastModifiedDateTime, updatedNote);
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
        private Company company;
        private Job job;
        private Mark markStatus;
        private Set<Tag> tags;

        private LastModifiedDateTime lastModifiedDateTime;

        private Note note;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setCompany(toCopy.company);
            setJob(toCopy.job);
            setMarkStatus(toCopy.markStatus);
            setTags(toCopy.tags);
            setLastModifiedDateTime(toCopy.lastModifiedDateTime);
            setNote(toCopy.note);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, company, job, tags);
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

        public void setCompany(Company company) {
            this.company = company;
        }

        public Optional<Company> getCompany() {
            return Optional.ofNullable(company);
        }

        public void setJob(Job job) {
            this.job = job;
        }

        public Optional<Job> getJob() {
            return Optional.ofNullable(job);
        }

        public void setMarkStatus(Mark markStatus) {
            this.markStatus = markStatus;
        }

        public Optional<Mark> getMarkStatus() {
            return Optional.ofNullable(markStatus);
        }

        public void setNote(Note note) {
            this.note = note;
        }

        public Optional<Note> getNote() {
            return Optional.ofNullable(note);
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
         * Setter for the last modified date & time as a @code LastModifiedDateTime object
         */

        public void setLastModifiedDateTime(LastModifiedDateTime lastModifiedDateTime) {
            this.lastModifiedDateTime = lastModifiedDateTime;
        }

        /**
         * Getter for last modified date & time as a @code LastModifiedDateTime object,
         * wrapped in an instance of @code Optional.
         */
        public Optional<LastModifiedDateTime> getLastModifiedDateTime() {
            return Optional.ofNullable(lastModifiedDateTime);
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
                    && Objects.equals(company, otherEditPersonDescriptor.company)
                    && Objects.equals(job, otherEditPersonDescriptor.job)
                    && Objects.equals(markStatus, otherEditPersonDescriptor.markStatus)
                    && Objects.equals(tags, otherEditPersonDescriptor.tags)
                    && Objects.equals(lastModifiedDateTime, otherEditPersonDescriptor.lastModifiedDateTime);
        }
        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("company", company)
                    .add("job", job)
                    .add("mark", markStatus)
                    .add("tags", tags)
                    .add("last_modified", lastModifiedDateTime)
                    .toString();
        }
    }
}

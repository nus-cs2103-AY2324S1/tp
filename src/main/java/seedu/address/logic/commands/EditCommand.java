package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL_MEDIA_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
import seedu.address.model.group.Group;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Person;
import seedu.address.model.person.Year;
import seedu.address.model.socialmedialink.SocialMediaLink;
import seedu.address.model.tutorial.Tutorial;

/**
 * Edits the details of an existing student in StudentConnect.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the email used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: EMAIL (must end with u.nus.edu) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_MAJOR + "MAJOR] "
            + "[" + PREFIX_YEAR + "YEAR] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TUTORIAL + "TUTORIAL]... "
            + "[" + PREFIX_SOCIAL_MEDIA_LINK + "SOCIAL_MEDIA_LINK]... "
            + "[" + PREFIX_NATIONALITY + "NATIONALITY] "
            + "[" + PREFIX_GENDER + "GENDER] \n"
            + "Example: " + COMMAND_WORD + " johnd@u.nus.edu "
            + PREFIX_YEAR + "3 "
            + PREFIX_EMAIL + "johndoe@u.nus.edu";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Details edited successfully! Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student is already on StudentConnect as this "
            + "email has already been used.";
    public static final String MESSAGE_EMAIL_NOT_FOUND = "Student with the provided email not found.";

    private final Email email;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param email of the person in the filtered student list to edit
     * @param editPersonDescriptor details to edit the student with
     */
    public EditCommand(Email email, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(email);
        requireNonNull(editPersonDescriptor);

        this.email = email;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        // Find the person with the provided email
        Optional<Person> personToEdit = model.getPersonWithEmail(email);

        if (personToEdit.isEmpty()) {
            throw new CommandException(MESSAGE_EMAIL_NOT_FOUND);
        }

        Person editedPerson = createEditedPerson(personToEdit.get(), editPersonDescriptor);

        if (!personToEdit.get().isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit.get(), editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        if (model.personIsInAGroup(editedPerson)) {
            Group targetGroup = model.getGroupThatPersonIsIn(personToEdit.get());
            targetGroup.removeMember(personToEdit.get());
            targetGroup.addMember(editedPerson);
        }
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Major updatedMajor = editPersonDescriptor.getMajor().orElse(personToEdit.getMajor());
        Year updatedYear = editPersonDescriptor.getYear().orElse(personToEdit.getYear());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Description updatedDescription = editPersonDescriptor.getDescription().orElse(personToEdit.getDescription());
        Set<Tutorial> updatedTutorials = editPersonDescriptor.getTutorials()
            .orElse(personToEdit.getTutorials());
        Set<SocialMediaLink> updatedSocialMediaLinks = editPersonDescriptor.getSocialMediaLinks()
            .orElse(personToEdit.getSocialMediaLinks());
        Nationality updatedNationality = editPersonDescriptor.getNationality().orElse(personToEdit.getNationality());
        Gender updatedGender = editPersonDescriptor.getGender().orElse(personToEdit.getGender());

        return new Person(updatedName, updatedMajor, updatedYear, updatedEmail, updatedDescription,
                updatedTutorials, updatedSocialMediaLinks, updatedNationality, updatedGender);
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
        return email.equals(otherEditCommand.email)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("email", email)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Major major;
        private Year year;
        private Email email;
        private Description description;
        private Set<Tutorial> tutorials;
        private Set<SocialMediaLink> socialMediaLinks;
        private Nationality nationality;
        private Gender gender;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code socialMediaLinks} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setMajor(toCopy.major);
            setYear(toCopy.year);
            setEmail(toCopy.email);
            setDescription(toCopy.description);
            setTutorials(toCopy.tutorials);
            setSocialMediaLinks(toCopy.socialMediaLinks);
            setNationality(toCopy.nationality);
            setGender(toCopy.gender);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, major, year, email, description,
                    tutorials, socialMediaLinks, nationality, gender);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setMajor(Major major) {
            this.major = major;
        }

        public Optional<Major> getMajor() {
            return Optional.ofNullable(major);
        }

        public void setYear(Year year) {
            this.year = year;
        }

        public Optional<Year> getYear() {
            return Optional.ofNullable(year);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        /**
         * Sets {@code tutorials} to this object's {@code tutorials}.
         * A defensive copy of {@code tutorials} is used internally.
         */
        public void setTutorials(Set<Tutorial> tutorials) {
            this.tutorials = (tutorials != null) ? new HashSet<>(tutorials) : null;
        }

        public Optional<Nationality> getNationality() {
            return Optional.ofNullable(nationality);
        }

        public void setNationality(Nationality nationality) {
            this.nationality = nationality;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }
        /**
         * Returns an unmodifiable tutorial set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tutorials} is null.
         */
        public Optional<Set<Tutorial>> getTutorials() {
            return (tutorials != null) ? Optional.of(Collections.unmodifiableSet(tutorials)) : Optional.empty();
        }

        /**
         * Sets {@code socialMediaLinks} to this object's {@code socialMediaLinks}.
         * A defensive copy of {@code socialMediaLinks} is used internally.
         */
        public void setSocialMediaLinks(Set<SocialMediaLink> socialMediaLinks) {
            this.socialMediaLinks = (socialMediaLinks != null) ? new HashSet<>(socialMediaLinks) : null;
        }

        /**
         * Returns an unmodifiable social media link set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code socialMediaLinks} is null.
         */
        public Optional<Set<SocialMediaLink>> getSocialMediaLinks() {
            return (socialMediaLinks != null) ? Optional.of(Collections.unmodifiableSet(socialMediaLinks))
                    : Optional.empty();
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
                    && Objects.equals(major, otherEditPersonDescriptor.major)
                    && Objects.equals(year, otherEditPersonDescriptor.year)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(description, otherEditPersonDescriptor.description)
                    && Objects.equals(tutorials, otherEditPersonDescriptor.tutorials)
                    && Objects.equals(socialMediaLinks, otherEditPersonDescriptor.socialMediaLinks)
                    && Objects.equals(nationality, otherEditPersonDescriptor.nationality)
                    && Objects.equals(gender, otherEditPersonDescriptor.gender);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("major", major)
                    .add("year", year)
                    .add("email", email)
                    .add("description", description)
                    .add("tutorials", tutorials)
                    .add("social media links", socialMediaLinks)
                    .add("nationality", nationality)
                    .add("gender", gender)
                    .toString();
        }
    }
}

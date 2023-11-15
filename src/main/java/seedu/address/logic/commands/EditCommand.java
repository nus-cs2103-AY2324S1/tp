package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEHANDLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
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
import seedu.address.model.course.Course;
import seedu.address.model.course.changes.CourseAddition;
import seedu.address.model.course.changes.CourseChange;
import seedu.address.model.course.changes.CourseDeletion;
import seedu.address.model.course.changes.CourseEdit;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telehandle;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.TagUtil;

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
            + "[" + PREFIX_TELEHANDLE + "TELEHANDLE] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_COURSE + "ORIGINAL_COURSE-NEW_COURSE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_COURSE + "add-MA1521 "
            + PREFIX_COURSE + "del-MA1521 "
            + PREFIX_COURSE + "MA2001-MA1521";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in NUSCoursemates.";
    public static final String MESSAGE_COURSE_DOES_NOT_EXIST = "Person %s doesn't have Course %s!";

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

        // this throws an exception if the course to delete/change does not exist
        Set<Course> updatedCourses = getUpdatedCourses(personToEdit, editPersonDescriptor);

        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor, updatedCourses);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (!TagUtil.canAddOrEditEmergencyTag(editPersonDescriptor, model.getFilteredPersonList(), personToEdit)) {
            throw new CommandException(TagUtil.EMERGENCY_TAG_LIMIT_MESSAGE);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor,
            Set<Course> updatedCourses) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Telehandle updatedTelehandle = editPersonDescriptor.getTelehandle().orElse(personToEdit.getTelehandle());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTelehandle,
                updatedTags, updatedCourses);
    }


    /**
     * Returns a set of courses that personToEdit should have after taking account the course
     * changes provided by editPersonDescriptor.
     * @param personToEdit the person to edit.
     * @param editPersonDescriptor the provided edit descriptor, which contains the course changes.
     * @return a set of courses that personToEdit should have after the specified modifications.
     * @throws CommandException when the command specifies the deletion of a course, but the person does not have it.
     */
    private Set<Course> getUpdatedCourses(Person personToEdit, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        Set<Course> originalCourses = personToEdit.getCourses();
        List<CourseChange> courseChanges = editPersonDescriptor.getCourseChanges().orElse(null);
        if (courseChanges == null) {
            return originalCourses;
        }
        if (courseChanges.isEmpty()) {
            return new HashSet<>();
        }
        Set<Course> updatedCourses = new HashSet<>(originalCourses);
        String personName = personToEdit.getName().fullName;
        for (CourseChange courseChange: courseChanges) {
            updateCourseSet(personName, courseChange, updatedCourses);
        }
        return updatedCourses;
    }

    /**
     * Updates the set of courses belonging to the person with name personName from a single course change.
     * @param personName the name of the person to modify.
     * @param courseChange the single course change specified.
     * @param updatedCourses the set of courses belonging to the person to modify.
     * @throws CommandException when the command specifies the deletion of a course, but the person does not have it.
     */
    private void updateCourseSet(String personName, CourseChange courseChange, Set<Course> updatedCourses)
            throws CommandException {
        if (courseChange instanceof CourseAddition) {
            CourseAddition courseAddition = (CourseAddition) courseChange;
            Course courseToAdd = courseAddition.getCourseToAdd();
            assert courseToAdd != null;
            updatedCourses.add(courseToAdd);
        } else if (courseChange instanceof CourseDeletion) {
            CourseDeletion courseDeletion = (CourseDeletion) courseChange;
            Course courseToDelete = courseDeletion.getCourseToDelete();
            assert courseToDelete != null;
            if (!updatedCourses.contains(courseToDelete)) {
                throw new CommandException(String.format(MESSAGE_COURSE_DOES_NOT_EXIST, personName,
                        courseToDelete.courseName));
            }
            updatedCourses.remove(courseToDelete);
        } else {
            CourseEdit courseEdit = (CourseEdit) courseChange;
            Course originalCourse = courseEdit.getOriginalCourse();
            assert originalCourse != null;
            Course newCourse = courseEdit.getNewCourse();
            assert newCourse != null;
            if (!updatedCourses.contains(originalCourse)) {
                throw new CommandException(String.format(MESSAGE_COURSE_DOES_NOT_EXIST, personName,
                        originalCourse.courseName));
            }
            updatedCourses.remove(originalCourse);
            updatedCourses.add(newCourse);
        }
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
        private Address address;
        private Telehandle telehandle;
        private Set<Tag> tags;
        private List<CourseChange> courseChanges;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTelehandle(toCopy.telehandle);
            setTags(toCopy.tags);
            setCourseChanges(toCopy.courseChanges);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, telehandle, tags, courseChanges);
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

        public void setTelehandle(Telehandle telehandle) {
            this.telehandle = telehandle;
        }

        public Optional<Telehandle> getTelehandle() {
            return Optional.ofNullable(telehandle);
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
         * Sets {@code courseChanges} to this object's {@code courseChanges}.
         * A defensive copy of {@code courseChanges} is used internally.
         */
        public void setCourseChanges(List<CourseChange> courseChanges) {
            this.courseChanges = (courseChanges != null) ? new ArrayList<>(courseChanges) : null;
        }

        /**
         * Returns an unmodifiable course pair set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code courseChanges} is null.
         */
        public Optional<List<CourseChange>> getCourseChanges() {
            return (courseChanges != null) ? Optional.of(Collections.unmodifiableList(courseChanges))
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
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(address, otherEditPersonDescriptor.address)
                    && Objects.equals(telehandle, otherEditPersonDescriptor.telehandle)
                    && Objects.equals(tags, otherEditPersonDescriptor.tags)
                    && Objects.equals(courseChanges, otherEditPersonDescriptor.courseChanges);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("telehandle", telehandle)
                    .add("tags", tags)
                    .add("courseChanges", courseChanges)
                    .toString();
        }
    }
}

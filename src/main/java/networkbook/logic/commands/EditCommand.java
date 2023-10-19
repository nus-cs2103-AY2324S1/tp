package networkbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static networkbook.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import networkbook.commons.core.index.Index;
import networkbook.commons.util.CollectionUtil;
import networkbook.commons.util.ToStringBuilder;
import networkbook.logic.Messages;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.logic.parser.CliSyntax;
import networkbook.model.Model;
import networkbook.model.person.Course;
import networkbook.model.person.Email;
import networkbook.model.person.Graduation;
import networkbook.model.person.Link;
import networkbook.model.person.Name;
import networkbook.model.person.Person;
import networkbook.model.person.Phone;
import networkbook.model.person.Priority;
import networkbook.model.person.Specialisation;
import networkbook.model.tag.Tag;
import networkbook.model.util.UniqueList;

/**
 * Edits the details of an existing person in the network book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + CliSyntax.PREFIX_NAME + "NAME] "
            + "[" + CliSyntax.PREFIX_PHONE + "PHONE] "
            + "[" + CliSyntax.PREFIX_EMAIL + "EMAIL] "
            + "[" + CliSyntax.PREFIX_LINK + "LINK] "
            + "[" + CliSyntax.PREFIX_GRADUATION + " GRADUATION DATE] "
            + "[" + CliSyntax.PREFIX_COURSE + "COURSE OF STUDY] "
            + "[" + CliSyntax.PREFIX_SPECIALISATION + "SPECIALISATION] "
            + "[" + CliSyntax.PREFIX_TAG + "TAG] "
            + "[" + CliSyntax.PREFIX_PRIORITY + "PRIORITY]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + CliSyntax.PREFIX_PHONE + " 91234567 "
            + CliSyntax.PREFIX_EMAIL + " johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the network book.";

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

        if (!personToEdit.isSame(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setItem(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        UniqueList<Phone> updatedPhones = editPersonDescriptor.getPhones().orElse(personToEdit.getPhones());
        UniqueList<Email> updatedEmails = editPersonDescriptor.getEmails().orElse(personToEdit.getEmails());
        UniqueList<Link> updatedLink = editPersonDescriptor.getLinks().orElse(personToEdit.getLinks());
        Graduation updatedGraduation = editPersonDescriptor.getGraduation()
                .orElse(personToEdit.getGraduation().orElse(null));
        UniqueList<Course> updatedCourses = editPersonDescriptor.getCourses().orElse(personToEdit.getCourses());
        UniqueList<Specialisation> updatedSpecialisations = editPersonDescriptor
                .getSpecialisations()
                .orElse(personToEdit.getSpecialisations());
        UniqueList<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Priority updatedPriority = editPersonDescriptor.getPriority().orElse(personToEdit.getPriority()
                                                                     .orElse(null));

        return new Person(updatedName, updatedPhones, updatedEmails, updatedLink, updatedGraduation,
                updatedCourses, updatedSpecialisations, updatedTags, updatedPriority);
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
        private Name name = null;
        private Optional<UniqueList<Phone>> phones = Optional.empty();
        private Optional<UniqueList<Email>> emails = Optional.empty();
        private Optional<UniqueList<Link>> links = Optional.empty();
        private Graduation graduation;
        private Optional<UniqueList<Course>> courses = Optional.empty();
        private Optional<UniqueList<Specialisation>> specialisations = Optional.empty();
        private UniqueList<Tag> tags;
        private Priority priority;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhones(toCopy.phones.map(UniqueList::copy).orElse(null));
            setEmails(toCopy.emails.map(UniqueList::copy).orElse(null));
            setLinks(toCopy.links.map(UniqueList::copy).orElse(null));
            setGraduation(toCopy.graduation);
            setCourses(toCopy.courses.map(UniqueList::copy).orElse(null));
            setSpecialisations(toCopy.specialisations.map(UniqueList::copy).orElse(null));
            setTags(toCopy.getTags().map(UniqueList::copy).orElse(null));
            setPriority(toCopy.priority);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, graduation, tags, priority)
                    || (phones.isPresent() && !phones.get().isEmpty())
                    || (emails.isPresent() && !emails.get().isEmpty())
                    || (links.isPresent() && !links.get().isEmpty())
                    || (courses.isPresent() && !courses.get().isEmpty())
                    || (specialisations.isPresent() && !specialisations.get().isEmpty());
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhones(UniqueList<Phone> phones) {
            this.phones = Optional.ofNullable(phones);
        }

        /**
         * Adds {@code phone} to the list of {@code Phone}s.
         * @param phone
         */
        public void addPhone(Phone phone) {
            this.phones = this.phones.map(phones -> {
                phones.add(phone);
                return phones;
            }).or(() -> {
                UniqueList<Phone> uniqueList = new UniqueList<>();
                uniqueList.add(phone);
                return Optional.of(uniqueList);
            });
        }

        public Optional<UniqueList<Phone>> getPhones() {
            return phones;
        }

        public void setEmails(UniqueList<Email> emails) {
            this.emails = Optional.ofNullable(emails);
        }

        /**
         * Adds {@code email} to the list of {@code emails}
         */
        public void addEmail(Email email) {
            this.emails = this.emails.map(emails -> {
                emails.add(email);
                return emails;
            }).or(() -> {
                UniqueList<Email> uniqueList = new UniqueList<>();
                uniqueList.add(email);
                return Optional.of(uniqueList);
            });
        }

        public Optional<UniqueList<Email>> getEmails() {
            return emails;
        }

        public void setLinks(UniqueList<Link> links) {
            this.links = Optional.ofNullable(links);
        }

        /**
         * Adds {@code link} to the list of {@code links}.
         */
        public void addLink(Link link) {
            this.links = this.links.map(links -> {
                links.add(link);
                return links;
            }).or(() -> {
                UniqueList<Link> uniqueList = new UniqueList<>();
                uniqueList.add(link);
                return Optional.of(uniqueList);
            });
        }

        public Optional<UniqueList<Link>> getLinks() {
            return links;
        }

        public void setGraduation(Graduation graduation) {
            this.graduation = graduation;
        }

        public Optional<Graduation> getGraduation() {
            return Optional.ofNullable(graduation);
        }

        public void setCourses(UniqueList<Course> courses) {
            this.courses = Optional.ofNullable(courses);
        }

        /**
         * Adds {@code specialisations} to the list of {@code specialisations}.
         */
        public void addCourse(Course course) {
            this.courses = this.courses.map(courses -> {
                courses.add(course);
                return courses;
            }).or(() -> {
                UniqueList<Course> uniqueList = new UniqueList<>();
                uniqueList.add(course);
                return Optional.of(uniqueList);
            });
        }

        public Optional<UniqueList<Course>> getCourses() {
            return courses;
        }

        public void setSpecialisations(UniqueList<Specialisation> specialisations) {
            this.specialisations = Optional.ofNullable(specialisations);
        }

        /**
         * Adds {@code specialisations} to the list of {@code specialisations}.
         */
        public void addSpecialisation(Specialisation specialisation) {
            this.specialisations = this.specialisations.map(specialisations -> {
                specialisations.add(specialisation);
                return specialisations;
            }).or(() -> {
                UniqueList<Specialisation> uniqueList = new UniqueList<>();
                uniqueList.add(specialisation);
                return Optional.of(uniqueList);
            });
        }

        public Optional<UniqueList<Specialisation>> getSpecialisations() {
            return specialisations;
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(UniqueList<Tag> tags) {
            this.tags = tags;
        }

        /**
         * Adds {@code tag} to the {@code UniqueList<tag>} of the descriptor.
         * If the tags field is null, initialize it with an empty {@code UniqueList}.
         *
         * @param tag is the new tag to be added.
         */
        public void addTag(Tag tag) {
            if (this.tags == null) {
                this.tags = new UniqueList<>();
            }
            this.tags.add(tag);
        }

        public Optional<UniqueList<Tag>> getTags() {
            return Optional.ofNullable(tags);
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        public Optional<Priority> getPriority() {
            return Optional.ofNullable(this.priority);
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
                    && Objects.equals(phones, otherEditPersonDescriptor.phones)
                    && Objects.equals(emails, otherEditPersonDescriptor.emails)
                    && Objects.equals(links, otherEditPersonDescriptor.links)
                    && Objects.equals(graduation, otherEditPersonDescriptor.graduation)
                    && Objects.equals(courses, otherEditPersonDescriptor.courses)
                    && Objects.equals(specialisations, otherEditPersonDescriptor.specialisations)
                    && Objects.equals(tags, otherEditPersonDescriptor.tags)
                    && Objects.equals(priority, otherEditPersonDescriptor.priority);
        }

        @Override
        public String toString() {
            ToStringBuilder tsb = new ToStringBuilder(this)
                    .add("name", name)
                    .add("phones", phones.orElse(null))
                    .add("emails", emails.orElse(null))
                    .add("links", links.orElse(null))
                    .add("graduation", graduation)
                    .add("courses", courses.orElse(null))
                    .add("specialisations", specialisations.orElse(null))
                    .add("tags", tags);
            if (priority != null) {
                tsb.add("priority", priority);
            }
            return tsb.toString();
        }
    }
}

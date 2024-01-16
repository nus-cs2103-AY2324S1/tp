package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;
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
import seedu.address.model.event.Event;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.LinkedIn;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Score;
import seedu.address.model.person.ScoreList;
import seedu.address.model.person.Status;
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
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_SCORE + "TAG SCORE] \n"
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
        List<Event> lastShownEventList = model.getFilteredEventList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPersonWithoutFilteredScoreList = createEditedPerson(personToEdit, editPersonDescriptor);

        Person editedPerson = filterScoreList(editedPersonWithoutFilteredScoreList, personToEdit);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        updateScoreList(personToEdit, editedPerson);
        model.setPerson(personToEdit, editedPerson);
        model.setLastViewedPersonIndex(index);
        model.loadSummaryStatistics();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        for (Event event : lastShownEventList) {
            if (event.getPerson().isSamePerson(personToEdit)) {
                Event updatedEvent = new Event(editedPerson, event.getDescription(), event.getStart_time(),
                        event.getEnd_time());
                model.setEvent(event, updatedEvent);
            }
        }
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(
                String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)), true);
    }

    private void updateScoreList(Person personToEdit, Person editedPerson) {
        // If the tags are the same, then the score list can be updated through setting editedPerson
        if (personToEdit.getTags().equals(editedPerson.getTags())) {
            return;
        }
        // If there are no more tags, we should clean the score-list
        ScoreList newScoreList = editedPerson.getScoreList();
        Set<Tag> newTags = editedPerson.getTags();
        if (newTags.isEmpty()) {
            editedPerson.setScoreList(new ScoreList());
            return;
        }

        // If there is a difference in tags, delete all those that are not in current updated tags
        for (Tag tag : newScoreList.getTagsWithScore()) {
            if (!newTags.contains(tag)) {
                newScoreList.removeScore(tag);
            }

        }
        editedPerson.setScoreList(newScoreList);
    }

    /**
     * Filters the score list of the person to edit, such that only the tags that are in the person's tag list
     * @param editedPerson
     * @param originalPerson
     * @return
     */
    private Person filterScoreList(Person editedPerson, Person originalPerson) throws CommandException {
        Set<Tag> currentTags = editedPerson.getTags();
        Set<Tag> previousTags = originalPerson.getTags();
        if (currentTags.isEmpty()) {
            return editedPerson;
        }

        Set<Tag> difference = new HashSet<>(currentTags);
        difference.removeAll(previousTags);

        List<Tag> tagsWithScore = editedPerson.getScoreList().getTagsWithScore();
        for (Tag tag : tagsWithScore) {
            if (!currentTags.contains(tag) && !previousTags.contains(tag)) {
                throw new CommandException(Messages.MESSAGE_ILLEGAL_TAG_SCORE);
            }
            if (!currentTags.contains(tag) && previousTags.contains(tag)) {
                editedPerson.getScoreList().removeScore(tag);
            }
        }
        return editedPerson;
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
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Remark updatedRemark = personToEdit.getRemark();
        LinkedIn updatedLinkedIn = personToEdit.getLinkedIn();
        Github updatedGithub = personToEdit.getGithub();
        Status updatedStatus = personToEdit.getStatus();
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Person editedPerson = new Person(updatedName, updatedPhone,
                updatedEmail, updatedAddress, updatedRemark, updatedTags);


        ScoreList updatedScoreList = createEditedScoreList(personToEdit.getScoreList(),
                editPersonDescriptor.getScoreList());
        editedPerson.setScoreList(updatedScoreList);
        editedPerson.setGithub(updatedGithub);
        editedPerson.setLinkedIn(updatedLinkedIn);
        editedPerson.setStatus(updatedStatus);

        return editedPerson;
    }

    private static ScoreList createEditedScoreList(
            ScoreList oldScoreList,
            Optional<ScoreList> editPersonDescriptorScoreList) {
        Optional<ScoreList> editedScoreList = editPersonDescriptorScoreList.filter(scoreList -> !scoreList.isEmpty());
        if (!editedScoreList.isPresent()) {
            return oldScoreList;
        }
        ScoreList updatedScoreList = editPersonDescriptorScoreList.get();
        // The score list that is updated, only contains the update pair, which is 1 pair of tag score entry
        Tag newTag = updatedScoreList.getTagsWithScore().get(0);
        Score newScore = updatedScoreList.getScore(newTag);
        oldScoreList.updateScoreList(newTag, newScore);
        return oldScoreList;

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

        private ScoreList scoreList;
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
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setScoreList(toCopy.scoreList);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, scoreList, tags);
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

        public void setScoreList(ScoreList scoreList) {
            this.scoreList = (scoreList != null) ? scoreList : null;
        }

        public Optional<ScoreList> getScoreList() {
            return this.scoreList == null ? Optional.empty() : Optional.ofNullable(scoreList);
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
                    && Objects.equals(address, otherEditPersonDescriptor.address)
                    && Objects.equals(scoreList, otherEditPersonDescriptor.scoreList)
                    && Objects.equals(tags, otherEditPersonDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("score-list", scoreList)
                    .add("tags", tags)
                    .toString();
        }
    }
}

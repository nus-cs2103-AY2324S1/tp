package seedu.address.logic.commands.musician;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MUSICIANS;

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
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.musician.Email;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.Name;
import seedu.address.model.musician.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing musician in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the musician identified "
            + "by the index number used in the displayed musician list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_MUSICIAN_SUCCESS = "Edited Musician: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MUSICIAN = "This musician already exists in the address book.";

    private final Index index;
    private final EditMusicianDescriptor editMusicianDescriptor;

    /**
     * @param index of the musician in the filtered musician list to edit
     * @param editMusicianDescriptor details to edit the musician with
     */
    public EditCommand(Index index, EditMusicianDescriptor editMusicianDescriptor) {
        requireNonNull(index);
        requireNonNull(editMusicianDescriptor);

        this.index = index;
        this.editMusicianDescriptor = new EditMusicianDescriptor(editMusicianDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Musician> lastShownList = model.getFilteredMusicianList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MUSICIAN_DISPLAYED_INDEX);
        }

        Musician musicianToEdit = lastShownList.get(index.getZeroBased());
        Musician editedMusician = createEditedMusician(musicianToEdit, editMusicianDescriptor);

        if (!musicianToEdit.isSameMusician(editedMusician) && model.hasMusician(editedMusician)) {
            throw new CommandException(MESSAGE_DUPLICATE_MUSICIAN);
        }

        model.setMusician(musicianToEdit, editedMusician);
        model.updateFilteredMusicianList(PREDICATE_SHOW_ALL_MUSICIANS);
        return new CommandResult(String.format(MESSAGE_EDIT_MUSICIAN_SUCCESS, Messages.format(editedMusician)));
    }

    /**
     * Creates and returns a {@code Musician} with the details of {@code musicianToEdit}
     * edited with {@code editMusicianDescriptor}.
     */
    private static Musician createEditedMusician(
            Musician musicianToEdit, EditMusicianDescriptor editMusicianDescriptor) {
        assert musicianToEdit != null;

        Name updatedName = editMusicianDescriptor.getName().orElse(musicianToEdit.getName());
        Phone updatedPhone = editMusicianDescriptor.getPhone().orElse(musicianToEdit.getPhone());
        Email updatedEmail = editMusicianDescriptor.getEmail().orElse(musicianToEdit.getEmail());
        Set<Tag> updatedTags = editMusicianDescriptor.getTags().orElse(musicianToEdit.getTags());
        Set<Tag> updatedInstrumentTags = editMusicianDescriptor.getInstruments()
                .orElse(musicianToEdit.getInstruments());
        Set<Tag> updatedGenreTags = editMusicianDescriptor.getGenres().orElse(musicianToEdit.getGenres());

        return new Musician(updatedName, updatedPhone, updatedEmail, updatedTags,
                updatedInstrumentTags, updatedGenreTags);
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
                && editMusicianDescriptor.equals(otherEditCommand.editMusicianDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editMusicianDescriptor", editMusicianDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the musician with. Each non-empty field value will replace the
     * corresponding field value of the musician.
     */
    public static class EditMusicianDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Set<Tag> tags;
        private Set<Tag> instruments;
        private Set<Tag> genres;

        public EditMusicianDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditMusicianDescriptor(EditMusicianDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setTags(toCopy.tags);
            setInstruments(toCopy.instruments);
            setGenres(toCopy.genres);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, tags, instruments, genres);
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

        public void setInstruments(Set<Tag> instrumentTags) {
            this.instruments = (instrumentTags != null) ? new HashSet<>(instrumentTags) : null;
        }

        public Optional<Set<Tag>> getInstruments() {
            return (instruments != null)
                    ? Optional.of(Collections.unmodifiableSet(instruments))
                    : Optional.empty();
        }

        public void setGenres(Set<Tag> genreTags) {
            this.genres = (genreTags != null) ? new HashSet<>(genreTags) : null;
        }

        public Optional<Set<Tag>> getGenres() {
            return (genres != null)
                    ? Optional.of(Collections.unmodifiableSet(genres))
                    : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMusicianDescriptor)) {
                return false;
            }

            EditMusicianDescriptor otherEditMusicianDescriptor = (EditMusicianDescriptor) other;
            return Objects.equals(name, otherEditMusicianDescriptor.name)
                    && Objects.equals(phone, otherEditMusicianDescriptor.phone)
                    && Objects.equals(email, otherEditMusicianDescriptor.email)
                    && Objects.equals(tags, otherEditMusicianDescriptor.tags)
                    && Objects.equals(instruments, otherEditMusicianDescriptor.instruments)
                    && Objects.equals(genres, otherEditMusicianDescriptor.genres);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("tags", tags)
                    .add("instruments", instruments)
                    .add("genres", genres)
                    .toString();
        }
    }
}

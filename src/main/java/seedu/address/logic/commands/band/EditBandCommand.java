package seedu.address.logic.commands.band;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

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
import seedu.address.model.band.Band;
import seedu.address.model.band.BandName;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing band in the address book.
 */
public class EditBandCommand extends Command {
    public static final String COMMAND_WORD = "editb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the band identified "
            + "by the index number used in the displayed band list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_GENRE + "GENRE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Rock Stars ";

    public static final String MESSAGE_EDIT_BAND_SUCCESS = "Edited Band: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_BAND = "This band already exists in the address book.";

    private final Index index;
    private final EditBandCommand.EditBandDescriptor editBandDescriptor;

    /**
     * Creates a {@code EditBandCommand} to edit one or more fields of the specified {@code Band}.
     *
     * @param index of the band in the filtered band list to edit
     * @param editBandDescriptor details to edit the band with
     */
    public EditBandCommand(Index index, EditBandCommand.EditBandDescriptor editBandDescriptor) {
        requireNonNull(index);
        requireNonNull(editBandDescriptor);

        this.index = index;
        this.editBandDescriptor = new EditBandCommand.EditBandDescriptor(editBandDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Band> lastShownList = model.getFilteredBandList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BAND_DISPLAYED_INDEX);
        }

        Band bandToEdit = lastShownList.get(index.getZeroBased());
        Band editedBand = createEditedBand(bandToEdit, editBandDescriptor);

        if (!bandToEdit.isSameBand(editedBand) && model.hasBand(editedBand)) {
            throw new CommandException(MESSAGE_DUPLICATE_BAND);
        }

        model.setBand(bandToEdit, editedBand);

        return new CommandResult(String.format(MESSAGE_EDIT_BAND_SUCCESS, Messages.format(editedBand)));
    }

    /**
     * Creates and returns a {@code Band} with the details of {@code bandToEdit}
     * edited with {@code editBandDescriptor}.
     */
    private static Band createEditedBand(
            Band bandToEdit, EditBandCommand.EditBandDescriptor editBandDescriptor) {
        assert bandToEdit != null;

        BandName updatedBandName = editBandDescriptor.getName().orElse(bandToEdit.getName());
        Set<Tag> updatedGenreTags = editBandDescriptor.getGenres().orElse(bandToEdit.getGenres());

        return new Band(updatedBandName, updatedGenreTags, bandToEdit.getMusicians());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditBandCommand)) {
            return false;
        }

        EditBandCommand otherEditBandCommand = (EditBandCommand) other;
        return index.equals(otherEditBandCommand.index)
                && editBandDescriptor.equals(otherEditBandCommand.editBandDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editBandDescriptor", editBandDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the band with. Each non-empty field value will replace the
     * corresponding field value of the band.
     */
    public static class EditBandDescriptor {
        private BandName bandName;
        private Set<Tag> genres;

        public EditBandDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditBandDescriptor(EditBandCommand.EditBandDescriptor toCopy) {
            setBandName(toCopy.bandName);
            setGenres(toCopy.genres);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(bandName, genres);
        }

        public void setBandName(BandName name) {
            this.bandName = name;
        }

        public Optional<BandName> getName() {
            return Optional.ofNullable(bandName);
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
            if (!(other instanceof EditBandCommand.EditBandDescriptor)) {
                return false;
            }

            EditBandCommand.EditBandDescriptor otherEditBandDescriptor = (EditBandCommand.EditBandDescriptor) other;
            return Objects.equals(bandName, otherEditBandDescriptor.bandName)
                    && Objects.equals(genres, otherEditBandDescriptor.genres);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("bandName", bandName)
                    .add("genres", genres)
                    .toString();
        }
    }
}

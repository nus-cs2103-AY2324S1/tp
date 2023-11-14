package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.IsBookmarked;
import seedu.address.model.person.Person;


/**
 * Bookmarks an applicant from the list of all applicants.
 */
public class BookmarkCommand extends Command {

    public static final String COMMAND_WORD = "bookmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Bookmarks an applicant, identified by the index number "
            + "used in the last list, from all future lists of applicants.\n"
            + "Parameter: INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_BOOKMARK_APPLICANT_SUCCESS = "Applicant at index %1$s has been "
            + "successfully bookmarked.";

    public final Index targetIndex;

    /**
     * Constructor for BookmarkCommand.
     * @param targetIndex
     */
    public BookmarkCommand(Index targetIndex) {
        requireAllNonNull(targetIndex);

        this.targetIndex = targetIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (this.targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToBookmark = lastShownList.get(targetIndex.getZeroBased());
        Person bookmarkedPerson = new Person(
                personToBookmark.getStudentNumber(),
                personToBookmark.getName(),
                personToBookmark.getPhone(),
                personToBookmark.getEmail(),
                personToBookmark.getGpa(),
                personToBookmark.getPreviousGrade(),
                personToBookmark.getInterviewScore(),
                personToBookmark.getComment(),
                personToBookmark.getTags(),
                personToBookmark.getAttachments(),
                personToBookmark.getIsHidden(),
                new IsBookmarked(true));
        model.setPerson(personToBookmark, bookmarkedPerson);
        return new CommandResult(String.format(MESSAGE_BOOKMARK_APPLICANT_SUCCESS, targetIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof BookmarkCommand)) {
            return false;
        }

        BookmarkCommand e = (BookmarkCommand) other;
        return targetIndex.equals(e.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
